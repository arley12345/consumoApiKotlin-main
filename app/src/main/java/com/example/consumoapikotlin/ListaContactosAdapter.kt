package com.example.consumoapikotlin

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import java.lang.Exception
import java.util.ArrayList
import java.util.stream.Collectors

class ListaContactosAdapter : RecyclerView.Adapter<ContactosHolder>(){

    private var dataset: ArrayList<Contactos> = ArrayList()
    private var listaFiltrada: ArrayList<Contactos> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactosHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ContactosHolder(layoutInflater.inflate(R.layout.item_contacto,parent,false))
    }

    override fun onBindViewHolder(holder: ContactosHolder, position: Int) {
        val c = dataset.get(position)
        holder.txtNombre.setText(c.name)
        holder.txtTelefono.setText(c.phone)
        holder.txtEmail.setText(c.website)
        holder.txtVerPublicaciones.setText("VER DETALLES")

        holder.txtVerPublicaciones.setOnClickListener(View.OnClickListener {
            val intent = Intent(holder.itemView.context, ItemContacto::class.java)
            intent.putExtra("itemContacto", c)
            holder.itemView.getContext().startActivity(intent)
        })
    }
    override fun getItemCount(): Int = dataset.size

    fun adicionalListaContacto(listaContactos: ArrayList<Contactos>) {
        dataset.addAll(listaContactos)
        listaFiltrada.addAll(dataset)
        notifyDataSetChanged()
    }

    fun filtrado(buscador: String, context: Context?) {
        try {
            val longitud = buscador.length
            if (longitud == 0) {
                dataset.clear()
                listaFiltrada.clear()
                dataset.addAll(listaFiltrada)
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    dataset.clear()
                    var coleccion: ArrayList<Contactos>  = listaFiltrada.stream()
                        .filter { i: Contactos ->
                            i.name.toLowerCase().contains(buscador)
                        }
                        .collect(Collectors.toList<Any>()) as ArrayList<Contactos>
                    dataset.addAll(coleccion)
                    if (dataset.isEmpty()) {
                        SingleToast.show(context,"lista vacia", Toast.LENGTH_SHORT)
                    }
                } else {
                    dataset.clear()
                    for (c in listaFiltrada) {
                        if (c.name.toLowerCase().contains(buscador)) {
                            dataset.add(c)
                        }
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        notifyDataSetChanged()
    }


    object SingleToast {
        private var mToast: Toast? = null
        @SuppressLint("SuspiciousIndentation")
        fun show(context: Context?, text: String?, duration: Int) {
            if (mToast != null)
                mToast!!.cancel()
                mToast = Toast.makeText(context, text, duration)
                mToast!!.show()

        }
    }

}