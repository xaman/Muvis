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

package com.martinchamarro.muvis.data.repository

import com.martinchamarro.muvis.data.api.Api
import com.martinchamarro.muvis.data.cache.MoviesCache
import com.martinchamarro.muvis.data.mapper.*
import com.martinchamarro.muvis.domain.exception.MovieNotFoundException
import com.martinchamarro.muvis.domain.exception.RepositoryException
import com.martinchamarro.muvis.domain.model.*
import com.martinchamarro.muvis.domain.repository.MoviesRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepositoryImpl @Inject constructor(
        private val api: Api,
        private val cache: MoviesCache,
        private val moviesMapper: MovieEntityMapper,
        private val detailMapper: DetailEntityMapper,
        private val castMapper: CastEntityMapper) : MoviesRepository {

    @Throws(RepositoryException::class)
    override fun getFeaturedMovies(): List<Movie> {
        val movies = api.getFeaturedMovies()
        cache.putAll(movies)
        return movies.map { moviesMapper(it) }.toList()
    }

    @Throws(RepositoryException::class)
    override fun getMovieById(id: Int): Movie {
        val entity = cache.get(id) ?: throw MovieNotFoundException()
        return moviesMapper(entity)
    }

    @Throws(RepositoryException::class)
    override fun getMovieDetail(id: Int): Detail {
        val entity = api.getMovieDetail(id)
        return detailMapper(entity)
    }

    @Throws(RepositoryException::class)
    override fun getCredits(id: Int): List<Cast> {
        val credits = api.getCredits(id)
        return credits.map { castMapper(it) }.toList()
    }
}
