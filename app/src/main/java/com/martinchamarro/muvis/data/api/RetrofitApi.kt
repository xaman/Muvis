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
import retrofit2.Call
import javax.inject.Inject

class RetrofitApi @Inject constructor(servicesFactory: RetrofitServicesFactory) : Api {

    private val services = servicesFactory.create()

    @Throws(ApiException::class)
    override fun getFeaturedMovies(): List<MovieEntity> {
        val response = execute(services.getFeaturedMovies("2017"))
        return response?.results ?: throw ApiException()
    }

    @Throws(ApiException::class)
    override fun getMovieDetail(id: Int): DetailEntity {
        val response = execute(services.getMovieDetail(id))
        return response ?: throw ApiException()
    }

    @Throws(ApiException::class)
    override fun getCredits(id: Int): List<CastEntity> {
        val response = execute(services.getCredits(id))
        return response?.cast ?: throw ApiException()
    }

    @Throws(ApiException::class)
    private fun <T> execute(call: Call<T>): T? {
        try {
            return call.execute().body()
        } catch (t: Throwable) {
            throw ApiException("Error executing Api call", t)
        }
    }
}
