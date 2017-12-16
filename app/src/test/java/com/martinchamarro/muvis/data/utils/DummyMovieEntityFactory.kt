/*
 * Copyright 2017 Martin Chamarro (@martinchamarro)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.martinchamarro.muvis.data.utils

import com.martinchamarro.muvis.data.entity.MovieEntity

object DummyMovieEntityFactory {

    fun givenAMovie(): MovieEntity {
        return MovieEntity(id = 157336, title = "Interstellar", originalTitle = "Interstellar", originalLanguage = "en", overview = "Narra las aventuras de un grupo de exploradores que hacen uso de un agujero de gusano recientemente descubierto para superar las limitaciones de los viajes espaciales tripulados y vencer las inmensas distancias que tiene un viaje interestelar.", releaseDate = "2014-11-05", isForAdults = false, popularity = 46.78944f, votesCount = 11968, votesAverage = 8.1f, posterPath = "/x0Y4lhF2eTOVfhStOlpBEKU49RS.jpg", backdropPath = "/xu9zaAevzQ5nnrsXN6JcahLnG4i.jpg", detail = null)
    }

    fun givenAnotherMovie(): MovieEntity {
        return MovieEntity(id = 27205, title = "Origen", originalTitle = "Inception", originalLanguage = "en", overview = "Dom Cobb es un ladrón hábil, el mejor de todos, especializado en el peligroso arte de extracción: el robo de secretos valiosos desde las profundidades del subconsciente durante el estado de sueño cuando la mente está más vulnerable. Esta habilidad excepcional de Cobb le ha hecho un jugador codiciado en el traicionero nuevo mundo de espionaje corporativo, pero al mismo tiempo, le ha convertido en un fugitivo internacional y ha tenido que sacrificar todo que le importaba. Ahora a Cobb se le ofrece una oportunidad para redimirse. Con un último trabajo podría recuperar su vida anterior, pero solamente si logra lo imposible.", releaseDate = "2010-07-14", isForAdults = false, popularity = 41.11573f, votesCount = 14951, votesAverage = 8.1f, posterPath = "/tsxJFwA5rKJCS70Cv62TRivwqxJ.jpg", backdropPath = "/s2bT29y0ngXxxu2IA8AOzzXTRhd.jpg", detail = null)
    }

    fun givenAListOfMovies(): List<MovieEntity> {
        return arrayListOf(
                MovieEntity(id = 11324, title = "Shutter Island", originalTitle = "Shutter Island", originalLanguage = "en", overview = "Verano de 1954. Los agentes judiciales Teddy Daniels y Chuck Aule son destinados a una remota isla del puerto de Boston para investigar la desaparición de una peligrosa asesina recluida en el hospital psiquiátrico Ashecliffe, un centro penitenciario para criminales perturbados dirigido por el siniestro doctor John Cawley. Pronto descubrirán que el centro guarda muchos secretos, y que la isla esconde algo más peligroso que los pacientes.", releaseDate = "2010-02-18", isForAdults = false, popularity = 30.925695f, votesCount = 6995, votesAverage = 7.9f, posterPath = "/sOlQC5vyMG9JAurKCs6RfqDK8KS.jpg", backdropPath = "/fmLWuAfDPaUa3Vi5nO1YUUyZaX6.jpg", detail = null),
                MovieEntity(id = 11, title = "La guerra de las galaxias. Episodio IV: Una nueva esperanza", originalTitle = "Star Wars", originalLanguage = "en", overview = "La princesa Leia, líder del movimiento rebelde que desea reinstaurar la República en la galaxia en los tiempos ominosos del Imperio, es capturada por las malévolas Fuerzas Imperiales, capitaneadas por el implacable Darth Vader, el sirviente más fiel del emperador. El intrépido Luke Skywalker, ayudado por Han Solo, capitán de la nave espacial \"El Halcón Milenario\", y los androides, R2D2 y C3PO, serán los encargados de luchar contra el enemigo y rescatar a la princesa para volver a instaurar la justicia en el seno de la Galaxia.", releaseDate = "1977-05-25", isForAdults = false, popularity = 44.017136f, votesCount = 7306, votesAverage = 8.1f, posterPath = "/8ae71OAm6XwnvakAx6rYa1Lo5qD.jpg", backdropPath = "/c4zJK1mowcps3wvdrm31knxhur2.jpg", detail = null),
                MovieEntity(id = 17654, title = "District 9", originalTitle = "District 9", originalLanguage = "en", overview = "Tras la llegada de una enorme nave espacial extraterrestre a Johannesburgo (Sudáfrica), a los alienígenas recién llegados a la Tierra se les obligó a vivir en condiciones penosas como \"refugiados\", en una especie de campo de concentración construido en las afueras la ciudad. Todo empezó unos veinte años atrás, cuando los extraterrestres tomaron el primer contacto con nuestro planeta. Los humanos esperaban un ataque hostil, o un gran avance tecnológico de la raza alienígena. Pero nada de ello sucedió. Los propios extraterrestres eran refugiados de su propio mundo. Sin saber bien que hacer, se confinó a los alienígenas en un campo de refugiados, el \"Distrito 9\", hasta que las naciones del mundo decidieran qué hacer ante la nueva y extraña situación. Pero el tiempo pasó.", releaseDate = "2009-08-05", isForAdults = false, popularity = 18.419073f, votesCount = 3574, votesAverage = 7.3f, posterPath = "/rlJ9oq9bkbphBCrxH4OO8KwlehK.jpg", backdropPath = "/yosGyMJ2CkavMSGx6PhcZO6kCAV.jpg", detail = null),
                MovieEntity(id = 329865, title = "La llegada", originalTitle = "Arrival", originalLanguage = "en", overview = "Cuando misteriosas naves espaciales aterrizan en todo el mundo, un equipo de élite (Jeremy Renner y Forest Whitaker) liderado por la lingüista Louise Banks (Amy Adams) intentan descifrar el motivo de su visita. A medida que la humanidad se tambalea al borde de una guerra, Louise y su equipo luchan contra el tiempo llegando a poner en peligro su vida y, muy posiblemente, la del resto de la humanidad.", releaseDate = "2016-11-10", isForAdults = false, popularity = 27.454678f, votesCount = 6355, votesAverage = 7.2f, posterPath = "/AcilyyFwtFuZAkSD9oi2CBw4J9Q.jpg", backdropPath = "/yIZ1xendyqKvY3FGeeUYUd5X9Mm.jpg", detail = null)
        )
    }

}