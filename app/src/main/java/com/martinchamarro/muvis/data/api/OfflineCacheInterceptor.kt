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
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class OfflineCacheInterceptor(val context: Context) : Interceptor {

    companion object {
        const val CACHE_HEADER = "Cache-Control"
        const val CACHE_HEADER_IF_NOT_CONNECTED = "public, only-if-cached, max-stale=2419200"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        if (!hasConnection() && containsCacheHeader(request)) {
            request = request.newBuilder()
                    .header(CACHE_HEADER, CACHE_HEADER_IF_NOT_CONNECTED)
                    .build()
        }
        return chain.proceed(request)
    }

    private fun containsCacheHeader(request: Request) = request.header(CACHE_HEADER) != null

    private fun hasConnection() = NetworkChecker.isAvailable(context)
}