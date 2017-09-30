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

import com.martinchamarro.muvis.data.api.responses.CreditsResponse
import com.martinchamarro.muvis.data.api.responses.FeaturedMoviesResponse
import com.martinchamarro.muvis.data.entity.DetailEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Services {

    companion object {
        const val HEADER_CACHE = "Cache-Control: public, max-age=3600"
    }

    @Headers(HEADER_CACHE)
    @GET("/3/discover/movie")
    fun getFeaturedMovies(
            @Query("primary_release_year") releaseYear: String,
            @Query("page") page: Int): Call<FeaturedMoviesResponse>

    @Headers(HEADER_CACHE)
    @GET("/3/movie/{movie_id}")
    fun getMovieDetail(@Path("movie_id") id: Int): Call<DetailEntity>

    @Headers(HEADER_CACHE)
    @GET("/3/movie/{movie_id}/credits")
    fun getCredits(@Path("movie_id") id: Int): Call<CreditsResponse>

}
