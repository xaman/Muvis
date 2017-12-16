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

package com.martinchamarro.muvis.data.mapper

import com.martinchamarro.muvis.data.utils.DummyMovieEntityFactory.givenAMovie
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MovieEntityMapperTest {

    private lateinit var mapper: MovieEntityMapper

    @Before
    fun setUp() {
        mapper = MovieEntityMapper(DetailEntityMapper(GenreEntityMapper(), CountryEntityMapper()))
    }

    @Test
    fun `should map an entity object to a domain one`() {
        val entity = givenAMovie()
        val movie = mapper.invoke(entity)
        assertEquals(entity.id, movie.id)
        assertEquals(entity.title, movie.title)
        assertEquals(entity.originalTitle, movie.originalTitle)
        assertEquals(entity.originalLanguage, movie.originalLanguage)
        assertEquals(entity.overview, movie.overview)
        assertEquals(entity.isForAdults, movie.forAdults)
        assertEquals(entity.votesCount.toLong(), movie.votesCount.toLong())
        assertEquals(entity.votesAverage, movie.votesAverage, 0.1f)
        assertEquals(entity.popularity, movie.popularity, 0.1f)
    }
}
