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

package com.martinchamarro.muvis.data.api

import com.martinchamarro.muvis.data.entity.*
import com.martinchamarro.muvis.domain.exception.ApiException
import org.funktionale.either.Either
import org.funktionale.either.eitherTry
import retrofit2.Call
import javax.inject.Inject

class RetrofitApi @Inject constructor(servicesFactory: RetrofitServicesFactory) : Api {

    private val services = servicesFactory.create()

    override fun getFeaturedMovies(page: Int): Either<Throwable, List<MovieEntity>> = eitherTry {
        val response = execute(services.getFeaturedMovies("2017", page))
        response.results
    }

    override fun getMovieDetail(id: Int): Either<Throwable, DetailEntity> = eitherTry {
        execute(services.getMovieDetail(id))
    }

    override fun getCredits(id: Int): Either<Throwable, List<CastEntity>> = eitherTry {
        val response = execute(services.getCredits(id))
        response.cast
    }

    @Throws(ApiException::class)
    private fun <T> execute(call: Call<T>) = call.execute().body()!!
}
