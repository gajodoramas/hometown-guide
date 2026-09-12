package com.gajodoramas.hometown-guide

data class Imagen(val imagen : Int, val titulo : String, val descripcion : String) {



    companion object {

        var listaImagenes : ArrayList<Imagen> = ArrayList()

        //variable que se utilizara en otra clase para navegador por el array de imagenes
        var posicion : Int = 0

        //llena la lista con imagenes y datos
        fun llenarLista() {
            listaImagenes.add(Imagen(R.drawable.avenida, "Avenida Los Menceyes",
                "La avenida Los Menceyes conecta Santa Cruz de Tenerife con San Cristóbal de La Laguna " +
                        "y pasa por La Cuesta diviendo el barrio en dos zonas. Al unir esta avenida las dos " +
                        "principales ciudades de la isla, resulta ser muy transitada y se encuentran en ella " +
                        "numerosos comercios de todo tipo."))

            listaImagenes.add(Imagen(R.drawable.barranco, "Barranco de Santos",
                "El barranco atraviesa el barrio lagunero de La Cuesta.\n\nDurante gran parte de su " +
                        "recorrido de más de dieciséis kilómetros, el barranco se convierte en un verdadero " +
                        "cañón con paredes acantiladas en sus márgenes de hasta cien metros de desnivel."))

            listaImagenes.add(Imagen(R.drawable.plaza, "Plaza del Tranvía",
                "Plaza ubicada en el antiguo terreno de la estación de tranvía.\n\nLa desaparecida " +
                        "estación del tranvía en la Cuesta  quedó en desuso en 1924, sin embargo, la chimenea, " +
                        "que se utilizaba para la expulsión del humo proveniente de las calderas que generaban " +
                        "la electricidad necesaria para el correcto funcionamiento del tranvía, se conserva en " +
                        "pie sin ningún uso tangible y forma parte de la plaza pública actual."))

            listaImagenes.add(Imagen(R.drawable.hospital, "Hospital Universitario de Canarias",
                "El Hospital Universitario de Canarias (HUC) es el centro hospitalario público de " +
                        "alcance general principal de la isla de Tenerife.\n\nFundado en el año 1971 bajo la " +
                        "denominación de Hospital General y Clínico de Tenerife, ocupa una superficie construida " +
                        "de 71.000 m², próxima a la Autopista del Norte de Tenerife. Con un total de 2.534 " +
                        "profesionales, está orientado a la asistencia médica de la zona norte de Tenerife, " +
                        "y es hospital de referencia para la isla de La Palma."))

            listaImagenes.add(Imagen(R.drawable.facultad, "Facultad de Bellas Artes",
                "La Facultad de Bellas Artes cuenta con el edificio de nueva planta más moderno entre los de las Facultades " +
                        "de Bellas Artes de España (fue inaugurado en 2014), concebido específicamente para la docencia " +
                        "de las disciplinas artísticas. Acoge todas las aulas, talleres y laboratorios de los grados " +
                        "en Bellas Artes, Diseño y Conservación y restauración de bienes culturales, así como las " +
                        "necesarias para la docencia de los másteres. Posee diversos espacios para exposiciones y " +
                        "eventos que permiten organizar actividades culturales tanto propias como vinculadas a " +
                        "instituciones y empresas, lo cual lo sitúa como referente académico y cultural de nuestra Comunidad."))

            listaImagenes.add(Imagen(R.drawable.mirador, "Mirador Félix Hernández",
                "El Mirador Félix Hernández permite una amplia visión de la zona más ancha del barranco de Santos. " +
                        "En los días de lluvia (especialmente cuando llueve de manera extrema), desde este mirador se puede " +
                        "ver el agua fluir por todo el barranco, y justo delante del mirador se forma una gran cascada."))

            listaImagenes.add(Imagen(R.drawable.tranvia, "Parada de tranvía",
                "La Cuesta es el nombre de una de las paradas cabeceras de la línea 2 del Tranvía de Tenerife. " +
                        "Se encuentra en la Carretera La Cuesta-Taco, del cual toma su nombre. Se inauguró el 30 de " +
                        "mayo de 2009, cuando entró en servicio la línea."))

        }
    }
}