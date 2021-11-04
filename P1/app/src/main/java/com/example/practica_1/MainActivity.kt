package com.example.practica_1

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SeekBar
import android.widget.Toast

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practica_1.Model.Factura
import com.example.practica_1.Model.RespuestaFactura
import com.example.practica_1.Network.FacturaApi
import com.example.practica_1.RecyclerView.FacturaAdapter
import com.example.practica_1.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.Serializable
import java.text.SimpleDateFormat
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity(), FacturaAdapter.onFacturaListener {

    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: FacturaAdapter

    private var _facturas = mutableListOf<Factura>()

    private val BASE_URL = "http://viewnextandroid.mocklab.io/"

    private var importeMaximo: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(
            layoutInflater
        )
        binding.contenido.visibility= View.GONE
        setContentView(binding.root)
        getFacturas()
        initRecyclerView()
        setSupportActionBar(binding.toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
    if(item.itemId==R.id.icono_filtro){
        val intent: Intent = Intent(this, SegundaActividad::class.java)
        intent.putExtra("importeMaximo", importeMaximo)
        startActivity(intent)
        //Como hacer para que no se me creen varias actividades de mostrar facturas y al darle al boton del movil para atrás no se stackeen
    }
        return super.onOptionsItemSelected(item)

    }


    //Iniciamos el recyclerview con su correspondiente adaptador y lista de datos a mostrar,
    //también indicamos la disposición que queremos en nuestra vista, en este caso vertical
    private fun initRecyclerView() {
        adapter = FacturaAdapter(this)
        binding.recyclerFacturas.layoutManager = LinearLayoutManager(this)
        binding.recyclerFacturas.adapter = adapter
        adapter.submitList(_facturas)

    }

    //Instanciamos la clase Retrofit pasandole como parámetro del método add..Factory el GSONConverter
    //este objeto nos permitira realizar las llamadas a la API y nos devolverá el objeto parseado
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    //Método GET para obtener las fácturas de nuestra API el cual se realiza en un hilo secundario, IO,
    //mediante una corutina
    private fun getFacturas() {

        CoroutineScope(Dispatchers.IO).launch {
            val call: Response<RespuestaFactura> =
                getRetrofit().create(FacturaApi::class.java).getFacturas()
            runOnUiThread {

                //Si la llamada es exitosa almacenamos las facturas recibidas de la API en una variable local
                if (call.isSuccessful) {
                    val callBody = call.body()

                    //si callBody es nullo facturas = lista vacía
                    var facturas = callBody?.facturas ?: emptyList()

                    //Borramos los dato que puedan haber en _facturas y añadimos todas las facturas obtenidas desde la API
                    _facturas.clear()
                    _facturas.addAll(facturas)

                    //Ahora que tenemos el conjunto total de facturas calculamos el importe máximo de estas
                    importeMaximo = getMaxImporte(_facturas)

                    //Si existen los campos correspondientes a los filtros enviados desde la SegundaActividad (donde se escogen los diferentes filtros)
                    // realizamos los correspondientes filtros
                    if (intent.hasExtra("importeFiltro")) {

                        //Variables para almacenar los datos del intent recibido
                        val fecha1 = intent.getStringExtra("fechaDesde")
                        val fecha2 = intent.getStringExtra("fechaHasta")
                        val maximoFiltro = intent.getIntExtra("importeFiltro", 0)
                        val estados: MutableList<String>  = intent.getSerializableExtra("estadosSeleccionados") as MutableList<String>
                        println(estados)

                        facturas =_facturas.filter { it.importeOrdenacion < maximoFiltro &&
                                validarFechas(it.fecha, fecha1!!, fecha2!!) &&
                                estados.contains(it.descEstado.toString())}

                        _facturas.clear()

                        _facturas.addAll(facturas)

                    }

                    adapter.notifyDataSetChanged()
                    Thread.sleep(3000)
                    binding.contenido.visibility= View.VISIBLE
                    binding.progressBar.visibility= View.GONE


                }
                //Si la llamada no es exitosa mostramos un error por consola
                else {
                    Log.i("MyTag", "ERROR COROUTINESCOPE ")
                }
            }

        }
    }


    //Lógica que se realiza al realizar click en el icono de cada factura
    override fun onIconoClick() {

        //Instanciamos el constructor del dialog y seteamos el título y el mensaje
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Título")
        builder.setMessage("Está función aún no está disponible")
        builder.setNeutralButton("Cerrar") { dialog, which ->
            {}
        }

        //Mostramos el dialog
        builder.show()
    }

    private fun getMaxImporte(facturas: List<Factura>): Double {
        var max: Double = 0.00

        for (factura in facturas) {
            if (factura.importeOrdenacion > max) max = factura.importeOrdenacion
        }
        return max

    }


    //Parseamos la fecha de la factura para compararlas en orden AÑO -> MES -> DIA
    private fun validarFechas(fechaFiltrado : String,fecha1: String, fecha2: String): Boolean {

        val parser =  SimpleDateFormat("dd/MM/yyyy")
        val formatter = SimpleDateFormat("yyyy/MM/dd")
        val fechaFiltradoFormatted = formatter.format(parser.parse(fechaFiltrado))
        return (fechaFiltradoFormatted.compareTo(fecha1)>=0 && fechaFiltrado.compareTo(fecha2)<=0)

}
}