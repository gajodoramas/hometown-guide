package com.gajodoramas.hometown-guide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.gajodoramas.hometown-guide.databinding.ActivityMapaTuristaBinding
import com.google.android.gms.maps.model.*

class MapaTurista : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapaTuristaBinding
    private lateinit var lugarLatLng : LatLng

    lateinit var botonGuardar : Button
    lateinit var botonBorrar : Button
    lateinit var campoNombre : EditText
    lateinit var campoDescripcion : EditText
    lateinit var marcadorSeleccionado : Marker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapaTuristaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Lugar.llenarLista()
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


    }

    private fun listenersYVariables() {

        botonGuardar = findViewById<Button>(R.id.button3)
        botonGuardar.isEnabled = false
        botonBorrar = findViewById<Button>(R.id.button2)
        botonBorrar.isEnabled = false
        campoNombre = findViewById<EditText>(R.id.editTextTextPersonName)
        campoDescripcion = findViewById<EditText>(R.id.editTextTextPersonName2)

        mMap.setOnMapClickListener { coordenadas ->

            botonBorrar.isEnabled = false
            lugarLatLng = coordenadas
            Toast.makeText(this, "Pulsaste LAT ${lugarLatLng.latitude} LONG ${lugarLatLng.longitude}",
                Toast.LENGTH_SHORT).show()
            botonGuardar.isEnabled = true

        }

        mMap.setOnMarkerClickListener { marcador ->
            marcadorSeleccionado = marcador
            botonBorrar.isEnabled = true
            botonGuardar.isEnabled = false
            return@setOnMarkerClickListener false
        }

        botonBorrar.setOnClickListener {

            marcadorSeleccionado.remove()
            Lugar.delLugar(marcadorSeleccionado.title)
            limpiarCampos()
        }

        botonGuardar.setOnClickListener {

            if (campoNombre.length() == 0 || campoDescripcion.length() == 0)

                Toast.makeText(
                    this,
                    "Rellene los campos", Toast.LENGTH_LONG
                ).show()
            else {


                var nuevoLugar = Lugar(
                    campoNombre.text.toString(),
                    campoDescripcion.text.toString(),
                    lugarLatLng.latitude,
                    lugarLatLng.longitude,
                    R.drawable.estrella
                )

                Lugar.listaLugares.add(nuevoLugar)
                Lugar.guardarNuevoLugar(nuevoLugar)
                mMap.addMarker(
                    MarkerOptions().position(
                        LatLng(
                            nuevoLugar.lati,
                            nuevoLugar.longi
                        )
                    ).title(nuevoLugar.nombre).snippet(nuevoLugar.descripcion).icon(
                        BitmapDescriptorFactory.fromResource(nuevoLugar.icono)
                    )
                )

                Toast.makeText(
                    this,
                    "Añadido ${nuevoLugar.nombre}", Toast.LENGTH_LONG
                ).show()

                limpiarCampos()

            }

        }
    }

        fun limpiarCampos() {

            campoNombre.text.clear()
            campoDescripcion.text.clear()
            botonGuardar.isEnabled = false
            botonBorrar.isEnabled = false

        }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE

        // Add a marker in Sydney and move the camera
        val laCuesta = LatLng(28.466348706679632, -16.292562959973598)
        var transicion = CameraPosition.builder().target(laCuesta).zoom(15f).build()
        var actualizarCamara = CameraUpdateFactory.newCameraPosition(transicion)
        mMap.animateCamera(actualizarCamara)
        listenersYVariables()
        cargarPOI()
    }

    //carga los marcadores
    private fun cargarPOI() {

        for (i in 10 until Lugar.listaLugares.size) {

                mMap.addMarker(
                    MarkerOptions().position(
                        LatLng(
                            Lugar.listaLugares[i].lati,
                            Lugar.listaLugares[i].longi
                        )
                    ).title(Lugar.listaLugares[i].nombre).icon(
                        BitmapDescriptorFactory.fromResource(Lugar.listaLugares[i].icono)
                    )
                )
        }
    }

}