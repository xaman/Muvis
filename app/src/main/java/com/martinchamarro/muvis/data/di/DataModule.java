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

package com.martinchamarro.muvis.data.di;

import com.martinchamarro.muvis.data.api.Api;
import com.martinchamarro.muvis.data.api.RetrofitApi;
import com.martinchamarro.muvis.data.cache.MoviesCache;
import com.martinchamarro.muvis.data.cache.MoviesCacheImpl;
import com.martinchamarro.muvis.data.repository.MoviesRepositoryImpl;
import com.martinchamarro.muvis.domain.repository.MoviesRepository;

import dagger.Module;
import dagger.Provides;

@Module public class DataModule {

    @Provides static MoviesRepository provideMoviesRepository(MoviesRepositoryImpl repository) {
        return repository;
    }

    @Provides static Api provideApi(RetrofitApi api) {
        return api;
    }

    @Provides static MoviesCache provideMoviesCache(MoviesCacheImpl cache) {
        return cache;
    }
}
