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
import com.martinchamarro.muvis.data.database.Database
import com.martinchamarro.muvis.data.mapper.*
import com.martinchamarro.muvis.domain.exception.MovieNotFoundException
import com.martinchamarro.muvis.domain.model.*
import com.martinchamarro.muvis.domain.repository.MoviesRepository
import org.funktionale.either.Either
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepositoryImpl @Inject constructor(
        private val api: Api,
        private val cache: MoviesCache,
        private val db: Database,
        private val moviesMapper: MovieEntityMapper,
        private val detailMapper: DetailEntityMapper,
        private val castMapper: CastEntityMapper) : MoviesRepository {

    override fun getFeaturedMovies(): Either<Throwable, List<Movie>> {
        return api.getFeaturedMovies().fold(
                { throwable -> Either.left(throwable) },
                { movies ->
                    cache.putAll(movies)
                    Either.right(movies.map { moviesMapper(it) })
                })
    }

    override fun getMovieById(id: Int): Either<Throwable, Movie> {
        val entity = cache.get(id) ?: return Either.left(MovieNotFoundException())
        entity.isFavorite = db.contains(id)
        return Either.right(moviesMapper(entity))
    }

    override fun getMovieDetail(id: Int): Either<Throwable, Detail> {
        val entity = cache.get(id)?.detail ?: return getMovieDetailFromApi(id)
        return Either.Right(detailMapper(entity))
    }

    private fun getMovieDetailFromApi(id: Int): Either<Throwable, Detail> {
        return api.getMovieDetail(id).fold(
                { throwable -> Either.left(throwable) },
                { entity ->
                    cache.get(id)?.detail = entity
                    Either.right(detailMapper(entity))
                })
    }

    override fun getCredits(id: Int): Either<Throwable, List<Cast>> {
        return api.getCredits(id).fold(
                { throwable -> Either.left(throwable) },
                { credits -> Either.right(credits.map { castMapper(it) }) })
    }

    override fun setFavorite(id: Int): Either<Throwable, Movie> {
        val entity = cache.get(id) ?: return Either.left(MovieNotFoundException())
        entity.isFavorite = !db.contains(id)
        if (entity.isFavorite) db.save(entity) else db.delete(id)
        return Either.right(moviesMapper(entity))
    }

    override fun getFavorites(): Either<Throwable, List<Movie>> {
        val movies = db.loadAll().map { moviesMapper(it) }
        return Either.right(movies)
    }
}
