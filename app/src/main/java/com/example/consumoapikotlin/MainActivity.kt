package com.example.consumoapikotlin

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.consumoapikotlin.databinding.ActivityMainBinding
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.ArrayList

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener  {

    private lateinit var binding:ActivityMainBinding


    lateinit var listaContactos: List<Contactos>
    var buscador: SearchView? = null
    lateinit var recyclerView: RecyclerView
    lateinit var listaContactosAdapter: ListaContactosAdapter

    lateinit var service: ContactosApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        buscador = findViewById(R.id.buscador)
        recyclerView = findViewById(R.id.recyclerView)

        elementsRecyclerView()
        info()
        getData()
        buscador!!.setOnQueryTextListener(this)
    }

    private fun elementsRecyclerView() {
        listaContactosAdapter = ListaContactosAdapter()
        recyclerView.adapter = listaContactosAdapter
        recyclerView.setHasFixedSize(true)
        val layoutManager = GridLayoutManager(this, 1)
        recyclerView.layoutManager = layoutManager
    }

    private fun info(){
        val retrofit=  Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        service = retrofit.create<ContactosApiService>(ContactosApiService::class.java)
    }

    private fun getData() {

        service.obtenerListaContacto().enqueue(object :  Callback<ArrayList<Contactos>> {
            @SuppressLint("SuspiciousIndentation")
            override fun onResponse(
                call: Call<ArrayList<Contactos>>,
                response: Response<ArrayList<Contactos>>
            ) {

              listaContactos = response.body()!!


                println(listaContactos.size)
                for (c in listaContactos){
                    println(c.email)
                }
                listaContactosAdapter.adicionalListaContacto(listaContactos as ArrayList<Contactos>)
                /*Log.i("TAG", Gson().toJson(contactos))
                println("----------------------------------")
                Log.i("TAG", contactos.toString())*/
            }

            override fun onFailure(call: Call<ArrayList<Contactos>>, t: Throwable) {
                Log.i("TAG", "fallo")
            }
        })
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false

    }

    override fun onQueryTextChange(s: String): Boolean {
        listaContactosAdapter.filtrado(s, this)
        if (s.length == 0) {
            getData()
        }
        return false
    }


}




