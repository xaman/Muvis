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

package com.martinchamarro.muvis.data.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.martinchamarro.muvis.Config;
import com.martinchamarro.muvis.globalutils.logger.Logger;

import java.util.Locale;

import javax.inject.Inject;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitServicesFactory {

    private interface PARAM {
        String API_KEY = "api_key";
        String LANGUAGE = "language";
    }

    private final Interceptor COMMON_PARAMS_INTERCEPTOR = chain -> {
        Request request = chain.request();
        HttpUrl.Builder urlBuilder = request.url().newBuilder();
        addCommonParams(urlBuilder);
        request = request.newBuilder().url(urlBuilder.build()).build();
        return chain.proceed(request);
    };

    @Inject public RetrofitServicesFactory() {
        // Empty
    }

    public Services create() {
        Retrofit retrofit = getHttpAdapter();
        return retrofit.create(Services.class);
    }

    private Retrofit getHttpAdapter() {
        return new Retrofit.Builder()
            .baseUrl(Config.DOMAIN)
            .addConverterFactory(getGsonConverter())
            .client(getClient())
            .build();
    }

    private GsonConverterFactory getGsonConverter() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        // This is the place to register type adapters
        Gson gson = gsonBuilder.create();
        return GsonConverterFactory.create(gson);
    }

    private OkHttpClient getClient() {
        return new OkHttpClient.Builder()
            .addInterceptor(COMMON_PARAMS_INTERCEPTOR)
            .addInterceptor(getLoggingInterceptor())
            .build();
    }

    private HttpLoggingInterceptor getLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(Logger::l);
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }

    private void addCommonParams(HttpUrl.Builder builder) {
        builder.addQueryParameter(PARAM.API_KEY, Config.API_KEY);
        builder.addQueryParameter(PARAM.LANGUAGE, Locale.getDefault().getLanguage());
    }


}
