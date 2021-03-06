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

package com.martinchamarro.muvis.domain.repository

import com.martinchamarro.muvis.domain.model.Cast
import com.martinchamarro.muvis.domain.model.Detail
import com.martinchamarro.muvis.domain.model.Movie
import com.martinchamarro.muvis.domain.model.Video
import org.funktionale.either.Either

interface MoviesRepository {

    fun getFeaturedMovies(page: Int): Either<Throwable, List<Movie>>

    fun getMovieById(id: Int): Either<Throwable, Movie>

    fun getMovieDetail(id: Int): Either<Throwable, Detail>

    fun getCredits(id: Int): Either<Throwable, List<Cast>>

    fun setFavorite(id: Int): Either<Throwable, Movie>

    fun getFavorites(): Either<Throwable, List<Movie>>

    fun searchMovies(text: String): Either<Throwable, List<Movie>>

    fun getMovieTrailer(id: Int): Either<Throwable, Video>

}
