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

package com.martinchamarro.muvis.data.cache

import com.martinchamarro.muvis.data.utils.DummyMovieEntityFactory.givenAListOfMovies
import com.martinchamarro.muvis.data.utils.DummyMovieEntityFactory.givenAMovie
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MoviesCacheImplTest {

    private lateinit var cache: MoviesCache

    @Before
    fun setUp() {
        cache = MoviesCacheImpl()
    }

    @Test
    fun `should be empty after being created`() {
        assertTrue(cache.isEmpty)
        assertTrue(cache.getAll().isEmpty())
    }

    @Test
    fun `should be empty if it is cleared after to add items`() {
        cache.putAll(givenAListOfMovies())
        assertFalse(cache.isEmpty)
        cache.clear()
        assertTrue(cache.isEmpty)
        assertTrue(cache.getAll().isEmpty())
    }

    @Test
    fun `should contain the inserted item`() {
        val anyMovie = givenAMovie()
        cache.put(anyMovie)
        assertFalse(cache.isEmpty)
        assertNotNull(cache.get(anyMovie.id))
        assertEquals(anyMovie, cache.get(anyMovie.id))
    }

    @Test
    fun `should contain all inserted items`() {
        val anyMovies = givenAListOfMovies()
        cache.putAll(anyMovies)
        anyMovies.forEach { assertNotNull(cache.get(it.id)) }
        assertTrue(anyMovies.all { cache.get(it.id) != null })
        assertEquals(anyMovies.size, cache.getAll().size)
    }

}