package com.gajodoramas.hometown-guide

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Button
import android.widget.Toast

class Login : AppCompatActivity() {

    lateinit var campoUser : EditText
    lateinit var campoPass : EditText
    lateinit var boton : Button
    //hashMap que guarda clave:valor usuario:contraseña
    lateinit var mapaLogin : HashMap<String,String>

    //reproductor, lo guardo en el companion object porque se pide que suene durante toda la app
    // (es decir, empezando en la actividad de login) y qe se pueda parar desde la app de bienvenida
    companion object {
        lateinit var mediaPlayer : MediaPlayer
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initVariables()
        buildListeners()
    }

    private fun initVariables() {
        mediaPlayer = MediaPlayer.create(this, R.raw.tenerife)
        mediaPlayer.start()
        mediaPlayer.isLooping = true
        campoUser = findViewById(R.id.userField)
        campoPass = findViewById(R.id.passField)
        boton = findViewById(R.id.button)
        mapaLogin = HashMap()

        //usuario:contraseña para el acceso
        mapaLogin["doramas"] = "123456"
        mapaLogin["alejandro"] = "qwerty"
        mapaLogin["paco"] = "password"

    }

    //construye los diferentes comportamientos de botones
    private fun buildListeners() {
        boton.setOnClickListener {
            //si algun campo esta vacio
            if (campoUser.text.isBlank() || campoPass.text.isBlank())
                Toast.makeText(this,
                    "Por favor, rellene correctamente todos los campos.",
                    Toast.LENGTH_SHORT).show()
            else {
                //si el usuario existe
                if (mapaLogin.containsKey(campoUser.text.toString())) {
                    //si el usuario y la password son correctos
                    if (mapaLogin.getValue(campoUser.text.toString()).equals(campoPass.text.toString())) {
                        Toast.makeText(this,
                            "Correcto.",
                            Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, Welcome::class.java))

                        //si la contraseña esta mal
                    } else Toast.makeText(this,
                        "La contraseña introducida no es correcta.",
                        Toast.LENGTH_SHORT).show()

                    //si el usuario no existe
                } else Toast.makeText(this,
                    "El usuario introducido no existe.",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }
}