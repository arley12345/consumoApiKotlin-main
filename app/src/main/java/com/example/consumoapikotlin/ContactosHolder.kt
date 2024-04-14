package com.example.consumoapikotlin

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactosHolder(view: View) : RecyclerView.ViewHolder(view) {

        var txtNombre = itemView.findViewById<TextView>(R.id.txtNombre)
        var txtTelefono = itemView.findViewById<TextView>(R.id.txtTelefono)
        var txtEmail = itemView.findViewById<TextView>(R.id.txtEmail)
        var txtVerPublicaciones = itemView.findViewById<TextView>(R.id.txtVerPublicaciones)

        fun ViewHolder(itemView: View) {
                txtNombre = itemView.findViewById(R.id.txtNombre)
                txtTelefono = itemView.findViewById(R.id.txtTelefono)
                txtEmail = itemView.findViewById(R.id.txtEmail)
                txtVerPublicaciones = itemView.findViewById(R.id.txtVerPublicaciones)
        }






}