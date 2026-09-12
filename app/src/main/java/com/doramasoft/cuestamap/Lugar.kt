package com.gajodoramas.hometown-guide

import com.google.android.gms.maps.model.LatLng
import java.io.File
import java.io.IOException
import java.io.RandomAccessFile

data class Lugar(val nombre : String, val descripcion : String, val lati : Double, val longi : Double, val icono : Int) {


    companion object {

        var listaLugares: ArrayList<Lugar> = ArrayList()
        private var rutaArchivo = File(Welcome.applicationContext().filesDir, "Lugares.dat")

        /**
         * Lee el archivo de lugares y las carga en el arrayList.
         */
        fun leerArchivoLugares() {
            try {
                val archivo = RandomAccessFile(rutaArchivo, "r")

                var lugar = CharArray(25) //longitud maxima de nombres de lugar
                var explicacion = CharArray(50) //longitud maxima de descripciones de lugar
                var lat : Double
                var long : Double
                var icono : Int

                archivo.seek(0)
                //lectura
                while (archivo.filePointer < archivo.length()) {

                    for (i in 0..24) {
                        lugar[i] = archivo.readChar()
                    }
                    for (i in 0..49) {
                        explicacion[i] = archivo.readChar()
                    }
                    lat = archivo.readDouble()
                    long = archivo.readDouble()
                    icono = archivo.readInt()
                    listaLugares.add(Lugar(String(lugar), String(explicacion), lat, long, icono))
                }
            } catch (e: Exception) {
                println(e.message)
            }
        }

        /**
         * Guarda las lugares del arrayList en el archivo.
         */
        fun escribirArchivoLugares() {

            try {

                val archivo = RandomAccessFile(rutaArchivo, "rw")
                var buffer: StringBuffer?

                archivo.seek(0)
                archivo.setLength(0)
                for (i in 10 until listaLugares.size) {
                    buffer = StringBuffer(listaLugares[i].nombre)
                    buffer.setLength(25) // Esta lognitud se debe a que el EditText está capado a 25 caracteres
                    archivo.writeChars(buffer.toString())

                    buffer = StringBuffer(listaLugares[i].descripcion)
                    buffer.setLength(50) // El EditText está capado a un máximo de 50 caracteres
                    archivo.writeChars(buffer.toString())

                    archivo.writeDouble(listaLugares[i].lati)
                    archivo.writeDouble(listaLugares[i].longi)
                    archivo.writeInt(listaLugares[i].icono)
                }

                archivo.close()

            } catch (ex: IOException) {
                println(ex.message)
            }
        }

        fun comprobarArchivoLugares() {
            if (!rutaArchivo.exists()) {

                rutaArchivo.createNewFile()

            } else {
                leerArchivoLugares()
            }
        }

        //guarda un unico lugar al final de un fichero existente
        fun guardarNuevoLugar(nuevaPregunta : Lugar) {
            try {

                val file = RandomAccessFile(rutaArchivo, "rw")

                var buffer: StringBuffer?

                file.seek(file.length())

                buffer = StringBuffer(nuevaPregunta.nombre)
                buffer.setLength(25) // Esta lognitud se debe a que el EditText está capado a 75 caracteres, a los que luego añado las dos interrogaciones
                file.writeChars(buffer.toString())

                buffer = StringBuffer(nuevaPregunta.descripcion)
                buffer.setLength(50) // El EditText está capado a un máximo de 50 caracteres
                file.writeChars(buffer.toString())

                file.writeDouble(nuevaPregunta.lati)
                file.writeDouble(nuevaPregunta.longi)
                file.writeInt(nuevaPregunta.icono)

                file.close()

            } catch (ex: IOException) {
                println(ex.message)
            }
        }

        //llena el array list con lugares establecidos y con los del fichero
        fun llenarLista() {

            listaLugares = ArrayList()

            listaLugares.add(Lugar("La Cuesta","Barrio de la cuesta", 28.466348706679632, -16.292562959973598, R.drawable.ic_mapa))
            listaLugares.add(Lugar("Barranco de Santos","Barranco enorme", 28.47412507936864, -16.283147695133344, R.drawable.ic_cascada))
            listaLugares.add(Lugar("Parada del tranvía","Para coger el tranvia", 28.467098922617474, -16.28599334112421, R.drawable.ic_tranviaa))
            listaLugares.add(Lugar("Parque José Clavell","Tiene buenas pokeparadas", 28.46202891965239, -16.290595330497386, R.drawable.ic_parque))
            listaLugares.add(Lugar("Hospital Universitario","pa si te pones malito", 28.45625788595729, -16.291872746014462, R.drawable.ic_hospita))
            listaLugares.add(Lugar("Facultad de Bellas Artes","pa pintar dibujitos yo k se", 28.465548468501492, -16.300626192869515, R.drawable.ic_libro))
            listaLugares.add(Lugar("Facultad de Filología","filololol", 28.46854653194081, -16.303831184802565, R.drawable.ic_libro))
            listaLugares.add(Lugar("Colegio Hispano Británico","pa los pijos", 28.468161718468004, -16.29862173026803, R.drawable.ic_libro))
            listaLugares.add(Lugar("Parque Tecnológico y Científico","en el mapa sale un descampado pa pobres pero esta reformadito en verdad", 28.466141932771393, -16.29648526404087, R.drawable.ic_parque))
            listaLugares.add(Lugar("Complejo deportivo","pa que te quites los complejos", 28.47011973135758, -16.291188240567894, R.drawable.ic_deportes))

            leerArchivoLugares()
        }

        //da las coordenadas de un lugar
        fun getLatLong(nombre: String): LatLng {
            for (lugar in listaLugares) {
                if (lugar.nombre == nombre) {
                    return LatLng(lugar.lati, lugar.longi)
                }

            }
            return LatLng( 28.468533063898676, -16.2883420461797)
        }

        //devuelve un lugar
        fun getLugar(nombre: String): Lugar {
            for (lugar in listaLugares) {
                if (lugar.nombre == nombre) {
                    return lugar
                }

            }
            return listaLugares[0]
        }

        //elimina un lugar
        fun delLugar(nombre: String?) {
            for (lugar in listaLugares) {
                if (lugar.nombre == nombre) {
                    listaLugares.remove(lugar)
                    break
                }

            }
            escribirArchivoLugares()
        }
    }
}