package com.gajodoramas.hometown-guide

import android.graphics.text.LineBreaker
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import androidx.annotation.RequiresApi

class Galeria : AppCompatActivity() {

    lateinit var imagen : ImageView
    lateinit var titulo : TextView
    lateinit var descripcion : TextView
    lateinit var botonAnterior : Button
    lateinit var botonSiguiente : Button
    lateinit var botonVolver : Button

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_galeria)
        inicializarVariables()
        Imagen.llenarLista()
        construirListeners()
        cambiarDatos()
    }


    //La siguiente linea me la requeria para usar el alineamiento justificado
    @RequiresApi(Build.VERSION_CODES.O)
    private fun inicializarVariables() {
        imagen = findViewById(R.id.imagen)
        titulo = findViewById(R.id.titulo)
        descripcion = findViewById(R.id.descripcion)
        botonAnterior = findViewById(R.id.botonAnterior)
        botonSiguiente = findViewById(R.id.botonSiguiente)
        botonVolver = findViewById(R.id.botonVolver)
        descripcion.justificationMode = LineBreaker.JUSTIFICATION_MODE_INTER_WORD
    }

    //construye los listeners de la interfaz,  botones
    private fun construirListeners() {
        botonSiguiente.setOnClickListener {
            if (Imagen.posicion == Imagen.listaImagenes.size-1) {
                Imagen.posicion = 0
            } else {
                Imagen.posicion++
            }
            cambiarDatos()
        }

        botonAnterior.setOnClickListener {
            if (Imagen.posicion == 0) {
                Imagen.posicion = Imagen.listaImagenes.size-1
            } else {
                Imagen.posicion--
            }
            cambiarDatos()
        }

        botonVolver.setOnClickListener {
            finish()
        }
    }

    //cambia los datos en pantalla
    private fun cambiarDatos() {
        //sube el scroll del scrollpanel al principio del texto
        findViewById<ScrollView>(R.id.scrollView3).scrollTo(0, 0)
        imagen.setImageResource(Imagen.listaImagenes.get(Imagen.posicion).imagen)
        titulo.text = Imagen.listaImagenes.get(Imagen.posicion).titulo
        descripcion.text = Imagen.listaImagenes.get(Imagen.posicion).descripcion
    }
}