package com.gajodoramas.hometown-guide

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.gajodoramas.hometown-guide.databinding.ActivityMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var spinner : Spinner
    var viendoRuta = false
    lateinit var botonRuta : Button
    lateinit var lineasRuta : Polyline
    lateinit var opcionesRuta : PolylineOptions
    lateinit var lineasLimite : Polyline
    lateinit var opcionesLimite : PolylineOptions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)

        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment


        mapFragment.getMapAsync(this)
        Lugar.llenarLista()
        initVariables()

    }

    //mete los lugares en el spinner y ya de paso pone los marcadores
    private fun construirSpinner() {

        var lista : ArrayList<String> = ArrayList()
        for (i in 0 until Lugar.listaLugares.size) {
            lista.add(Lugar.listaLugares[i].nombre)

            if (i != 0) {
                if (i < 10) { //para evitar los marcadores del turista en la ruta
                    opcionesRuta.add(LatLng(Lugar.listaLugares[i].lati, Lugar.listaLugares[i].longi))
                }

                mMap.addMarker(
                    MarkerOptions().position(
                        LatLng(
                            Lugar.listaLugares[i].lati,
                            Lugar.listaLugares[i].longi
                        )
                    ).title(Lugar.listaLugares[i].nombre).snippet(Lugar.listaLugares[i].descripcion).icon(
                        BitmapDescriptorFactory.fromResource(Lugar.listaLugares[i].icono)
                    )
                )

            }
        }

        var adapter = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, lista)

        spinner.adapter = adapter

        }

    private fun initVariables() {

        spinner = findViewById(R.id.spinner)
        botonRuta = findViewById(R.id.botonRuta)
        opcionesRuta = PolylineOptions()
        opcionesLimite = PolylineOptions()

    }

    //calcula la distancia para la ruta
    private fun  distance(lat1 : Double, lat2 : Double, lon1 : Double, lon2 : Double) : Double {

        val radioTierra = 6371; // Radius of the earth

        var latDistance: Double = Math.toRadians(lat2 - lat1);
        var lonDistance: Double = Math.toRadians(lon2 - lon1);
        var a: Double = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2)
        var c: Double = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        var distance: Double = radioTierra * c * 1000; // convert to meters

        var height: Double = 0.0; //no voy a contemplar elevaciones

        distance = Math.pow(distance, 2.0) + Math.pow(height, 2.0);

        return Math.sqrt(distance);

    }

    private fun declararListeners() {

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, indice: Int, p3: Long) {
                if (indice == 0) {
                    irLugar(Lugar.listaLugares[indice], 14)
                } else {
                    irLugar(Lugar.listaLugares[indice])
                }

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }


    mMap.setOnMarkerClickListener { marcador ->
        var titulo = marcador.title.toString()
        Toast.makeText(this, "${titulo}: ${String.format("%.5f",Lugar.getLatLong(titulo).latitude)}, ${String.format("%.5f",Lugar.getLatLong(titulo).longitude)}", Toast.LENGTH_SHORT).show()
        irLugar(Lugar.getLugar(titulo), 18)
        return@setOnMarkerClickListener false
    }

        findViewById<Button>(R.id.botonRuta).setOnClickListener {


            if (!viendoRuta) {
                irLugar(Lugar.listaLugares[0], 14)
                botonRuta.text = "Ocultar ruta"
                opcionesRuta.color(getColor(R.color.malva_fuerte))
                lineasRuta = mMap.addPolyline(opcionesRuta)

                lineasRuta.isClickable = true
            } else {
                lineasRuta.remove()
                botonRuta.text = "Ver ruta"
            }
            viendoRuta = !viendoRuta

        }
        mMap.setOnPolylineClickListener {
            var distancia = 0.0
            val tramos : MutableList<LatLng> = it.points

            for (tramo in tramos) {
                var index = tramos.indexOf(tramo)
                if (index < tramos.size-1) {
                    distancia += distance(tramo.latitude, tramos.get(index + 1).latitude, tramo.longitude, tramos[index + 1].longitude)
                }

        }

            Toast.makeText(this, "Recorrido total: ${String.format("%.2f", distancia / 1000)} km", Toast.LENGTH_SHORT).show()


    }
    }

    private fun crearLimitePueblo() {
        opcionesLimite.add(LatLng(28.46865797934797, -16.306077335089277))
        opcionesLimite.add(LatLng(28.45370607435003, -16.291022099895766))
        opcionesLimite.add(LatLng(28.460145675665256, -16.28992887711265))
        opcionesLimite.add(LatLng(28.465939608519683, -16.281620386207152))
        opcionesLimite.add(LatLng(28.474602455738644, -16.277997134634692))
        opcionesLimite.add(LatLng(28.47447890191071, -16.28888250703543))
        opcionesLimite.add(LatLng(28.472213722550205, -16.29189667776091))
        opcionesLimite.add(LatLng(28.474135696033223, -16.296332037363115))
        opcionesLimite.add(LatLng(28.47140373752774, -16.298940154150944))
        opcionesLimite.add(LatLng(28.472515749259966, -16.30036134331926))
        opcionesLimite.add(LatLng(28.46865797934797, -16.306077335089277))


        opcionesLimite.color(getColor(R.color.malva_mas_flojo))
        lineasLimite = mMap.addPolyline(opcionesLimite)
    }

    //para mover la camara hacia un lugar
    private fun irLugar(lugar : Lugar) {
        irLugar(lugar, 18)
    }

    private fun irLugar(lugar : Lugar, zoom : Int) {
        val lugar = LatLng(lugar.lati, lugar.longi)

        var transicion = CameraPosition.builder().target(lugar).zoom(zoom.toFloat()).build()

        var actualizarCamara = CameraUpdateFactory.newCameraPosition(transicion)
        mMap.animateCamera(actualizarCamara)
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


        declararListeners()
        construirSpinner()
        crearLimitePueblo()
        // Add a marker in Sydney and move the camera

        //

    }
}