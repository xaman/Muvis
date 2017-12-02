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

import android.content.Context
import com.google.gson.GsonBuilder
import com.martinchamarro.muvis.Config
import com.martinchamarro.muvis.globalutils.logger.Logger
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import javax.inject.Inject

class RetrofitServicesFactory @Inject constructor(private val context: Context) {

    private companion object {
        val PARAM_API_KEY = "api_key"
        val PARAM_LANGUAGE = "language"
    }

    private val COMMON_PARAMS_INTERCEPTOR = { chain: Interceptor.Chain ->
        var request = chain.request()
        val urlBuilder = request.url().newBuilder()
        addCommonParams(urlBuilder)
        request = request.newBuilder().url(urlBuilder.build()).build()
        chain.proceed(request)
    }

    fun create(): Services {
        val retrofit = getHttpAdapter()
        return retrofit.create(Services::class.java)
    }

    private fun getHttpAdapter(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(Config.API_DOMAIN)
                .addConverterFactory(getGsonConverter())
                .client(createClient())
                .build()
    }

    /*
     * This is the place to register type adapters
     */
    private fun getGsonConverter(): GsonConverterFactory {
        val gsonBuilder = GsonBuilder()
        val gson = gsonBuilder.create()
        return GsonConverterFactory.create(gson)
    }

    private fun createClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .cache(CacheFactory.create(context))
                .addInterceptor(OfflineCacheInterceptor(context))
                .addInterceptor(createLoggingInterceptor())
                .addInterceptor(COMMON_PARAMS_INTERCEPTOR)
                .build()
    }

    private fun createLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { Logger.l(it) })
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    private fun addCommonParams(builder: HttpUrl.Builder) {
        builder.addQueryParameter(PARAM_API_KEY, Config.TMD_API_KEY)
        builder.addQueryParameter(PARAM_LANGUAGE, Locale.getDefault().language)
    }
}
