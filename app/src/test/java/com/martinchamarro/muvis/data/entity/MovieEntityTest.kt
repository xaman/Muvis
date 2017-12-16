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

import com.martinchamarro.muvis.data.utils.DummyMovieEntityFactory.givenAMovie
import com.martinchamarro.muvis.data.utils.DummyMovieEntityFactory.givenAnotherMovie
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MovieEntityTest {

    companion object {
        private val ANY_ID = 123456
    }

    @Test
    fun `should use the id to compare if two movies are equal`() {
        val anyMovie = givenAMovie()
        anyMovie.id = ANY_ID
        val anyOtherMovie = givenAnotherMovie()
        anyOtherMovie.id = ANY_ID
        assertTrue(anyMovie == anyOtherMovie)
    }

    @Test
    fun `should generate the hashcode using the id`() {
        val anyMovie = givenAMovie()
        assertTrue(anyMovie.id == anyMovie.hashCode())
    }

}
