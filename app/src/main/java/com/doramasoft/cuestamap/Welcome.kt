package com.gajodoramas.hometown-guide

import android.content.Context
import android.content.Intent
import android.graphics.text.LineBreaker
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.RequiresApi

class Welcome : AppCompatActivity() {

    lateinit var description : TextView
    lateinit var botonGaleria : Button
    lateinit var botonLugares : Button
    lateinit var botonMusica : ImageButton

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        initVariables()
        construirListeners()
        Lugar.comprobarArchivoLugares()

    }

    init {
        instancia = this
    }

    /**
     * Utilizaremos el companion object para utilizar un archivo txt donde guardar lugares
     */
    companion object {
        private var instancia: Welcome? = null

        fun applicationContext() : Context {
            return instancia!!.applicationContext
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initVariables() {
        botonGaleria = findViewById(R.id.botonGaleria)
        botonLugares = findViewById(R.id.botonLugares)
        botonMusica = findViewById(R.id.botonMusica)
        description = findViewById<TextView>(R.id.description)
        description.textSize = 20f
        description.justificationMode = LineBreaker.JUSTIFICATION_MODE_INTER_WORD
        description.text =
            "La Cuesta es la mayor entidad poblacional del municipio de San Cristóbal de La Laguna, en la isla de Tenerife. " +
                    "La Cuesta está situada al sureste de la La Laguna, a una altitud media de 350 sobre el nivel del mar y a 3,6 kilómetros del casco histórico del municipio.\n\n" +
                    "Está formado por los núcleos de Barrio de La Candelaria/El Becerril, Barrio del Obispado, El Charcón, La Higuerita, La Piterita, Las Mantecas, Princesa Ibaya, Urbanización La Florida y Zona Industrial La Cuesta." +
                    "También forman parte de La Cuesta parte de los barrios de Vistabella, Cuesta de Piedra y Salud Alto.\n\n" +
                    "El efervescente barrio de La Cuesta, que comenzó siendo en 1869 un pequeño caserío en un cruce de caminos, supone hoy un núcleo poblacional en el que conviven cerca de 30.000 ciudadanos. " +
                    "Cuenta con múltiples servicios, transportes, colegios, institutos, y facultades universitarias, además de un importantísimo tejido comercial.\n\n" +
                    "El 1911 el incipiente núcleo urbano de La Cuesta tomó el nombre de la zona en la que se situaba, ya que se trata de una pendiente natural por la que asciende la conexión entre Santa Cruz de Tenerife y San Cristóbal de La Laguna.\n\n" +
                    "No obstante, lo largo de su historia ha sido conocida por diferentes nombres: El Mesón de La Cuesta, el barrio de Arguijón, El Castillo de La Cuesta o La Cuesta vieja."
    }

    //construye el comportamiento de los diferentes botones
    private fun construirListeners() {
        botonGaleria.setOnClickListener {
            startActivity(Intent(this, Galeria::class.java))
        }
        botonLugares.setOnClickListener {
            startActivity(Intent(this, MapsActivity::class.java))
        }

        findViewById<Button>(R.id.botonMapaTurista).setOnClickListener {
            startActivity(Intent(this, MapaTurista::class.java))
        }
        botonMusica.setOnClickListener {
            if (Login.mediaPlayer.isPlaying) {
                Login.mediaPlayer.pause()
                botonMusica.setImageResource(R.drawable.ic_round_music_note_24)
            } else {
                Login.mediaPlayer.start()
                botonMusica.setImageResource(R.drawable.ic_round_music_off_24)
            }
        }

    }

}