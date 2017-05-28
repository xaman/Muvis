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

package com.martinchamarro.muvis.data.entity

import com.google.gson.Gson

import org.junit.Assert.*

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MovieEntityTest {

    companion object {
        private val ANY_ID = 123456
        private val ANY_MOVIE_JSON = "{\n" +
                "      \"id\": 263115,\n" +
                "      \"title\": \"Logan\",\n" +
                "      \"original_title\": \"Logan\",\n" +
                "      \"original_language\": \"en\",\n" +
                "      \"overview\": \"Sin sus poderes, por primera vez, Wolverine es verdaderamente vulnerable. Después de una vida de dolor y angustia, sin rumbo y perdido en el mundo donde los X-Men son leyenda, su mentor Charles Xavier lo convence de asumir una última misión: proteger a una joven que será la única esperanza para la raza mutante. Tercera y última película protagonizada por Hugh Jackman en el papel de Lobezno.\",\n" +
                "      \"release_date\": \"2017-02-28\",\n" +
                "      \"adult\": false,\n" +
                "      \"popularity\": 63.118666,\n" +
                "      \"vote_count\": 2491,\n" +
                "      \"vote_average\": 7.5,\n" +
                "      \"poster_path\": \"/3pRgLyNXt5lNeVSnwttwFW8gOdB.jpg\",\n" +
                "      \"backdrop_path\": \"/5pAGnkFYSsFJ99ZxDIYnhQbQFXs.jpg\"\n" +
                "    }"
    }

    @Test fun testTwoMoviesAreEqualIfSameId() {
        val movie = MovieEntityBuilder.build()
        movie.id = ANY_ID
        val otherMovie = MovieEntityBuilder.build()
        otherMovie.id = ANY_ID
        assertEquals(movie, otherMovie)
    }

    @Test fun testHashCodeIsEqualThanId() {
        val movie = MovieEntityBuilder.build()
        movie.id = ANY_ID
        assertEquals(movie.id.toLong(), movie.hashCode().toLong())
    }

    @Test fun testAllFieldsParsedFromJson() {
        val gson = Gson()
        val entity = gson.fromJson(ANY_MOVIE_JSON, MovieEntity::class.java)
        with(entity) {
            assertEquals(263115, id.toLong())
            assertEquals("Logan", title)
            assertEquals("Logan", originalTitle)
            assertEquals("en", originalLanguage)
            assertEquals("Sin sus poderes, por primera vez, Wolverine es verdaderamente vulnerable. Después de una vida de dolor y angustia, sin rumbo y perdido en el mundo donde los X-Men son leyenda, su mentor Charles Xavier lo convence de asumir una última misión: proteger a una joven que será la única esperanza para la raza mutante. Tercera y última película protagonizada por Hugh Jackman en el papel de Lobezno.", overview)
            assertEquals("2017-02-28", releaseDate)
            assertEquals(false, isForAdults)
            assertEquals(63.118666f, popularity, 0.1f)
            assertEquals(2491, votesCount.toLong())
            assertEquals(7.5f, votesAverage, 0.1f)
            assertEquals("/3pRgLyNXt5lNeVSnwttwFW8gOdB.jpg", posterPath)
            assertEquals("/5pAGnkFYSsFJ99ZxDIYnhQbQFXs.jpg", backdropPath)
        }
    }

}
