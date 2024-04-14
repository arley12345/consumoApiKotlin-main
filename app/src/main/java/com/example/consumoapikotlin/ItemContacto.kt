package com.example.consumoapikotlin

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ItemContacto : AppCompatActivity() {
    private var itemNombre: TextView? = null
    private var itemTelefono: TextView? = null
    private var itemEmail: TextView? = null
    private var itemUserName: TextView? = null
    private var itemAdress: TextView? = null
    private var itemWebsite: TextView? = null
    private var itemCompany: TextView? = null
    private lateinit var itemContactos: Contactos

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_contacto)

        title = javaClass.simpleName
        itemNombre = findViewById(R.id.itemNombre)
        itemTelefono = findViewById(R.id.itemTelefono)
        itemEmail = findViewById(R.id.itemEmail)
        itemUserName = findViewById(R.id.itemUserName)
        itemAdress = findViewById(R.id.itemAdres)
        itemWebsite = findViewById(R.id.itemWebsite)
        itemCompany = findViewById(R.id.itemCompany)
        initValues()
    }

    private fun initValues() {
        itemContactos = (intent.extras!!.getSerializable("itemContacto") as Contactos)
        itemNombre!!.setText(itemContactos.name)
        itemTelefono!!.setText(itemContactos.phone)
        itemEmail!!.setText(itemContactos.email)
        itemUserName!!.setText(itemContactos.username)
        itemAdress!!.setText(itemContactos.address.city + itemContactos.address.street)
        itemWebsite!!.setText(itemContactos.website)
        itemCompany!!.setText(itemContactos.company.bs)
    }


}