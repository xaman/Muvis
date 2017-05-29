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

import com.martinchamarro.muvis.data.entity.MovieEntity
import javax.inject.Inject

class MoviesCacheImpl @Inject constructor() : MoviesCache {

    val values = HashMap<Int, MovieEntity>()

    override fun getAll(): List<MovieEntity> = values.map { it.value }.toList()

    override fun get(id: Int): MovieEntity? = values[id]

    override fun put(movie: MovieEntity) { values.put(movie.id, movie) }

    override fun putAll(movies: List<MovieEntity>) = movies.forEach { put(it) }

    override fun clear() = values.clear()
}