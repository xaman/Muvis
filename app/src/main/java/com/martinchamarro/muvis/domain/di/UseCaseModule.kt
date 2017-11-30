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

package com.martinchamarro.muvis.domain.di

import com.martinchamarro.muvis.domain.repository.MoviesRepository
import com.martinchamarro.muvis.domain.usecase.*
import com.martinchamarro.muvis.threading.Executor
import com.martinchamarro.muvis.threading.MainThread

import dagger.Module
import dagger.Provides

@Module class UseCaseModule {

    @Provides fun provideGetFeatured(executor: Executor, mainThread: MainThread, repository: MoviesRepository): GetFeatured {
        return GetFeatured(executor, mainThread, repository)
    }

    @Provides fun provideGetMovie(executor: Executor, mainThread: MainThread, repository: MoviesRepository): GetMovie {
        return GetMovie(executor, mainThread, repository)
    }

    @Provides fun provideGetDetail(executor: Executor, mainThread: MainThread, repository: MoviesRepository): GetDetail {
        return GetDetail(executor, mainThread, repository)
    }

    @Provides fun provideGetCredits(executor: Executor, mainThread: MainThread, repository: MoviesRepository): GetCredits {
        return GetCredits(executor, mainThread, repository)
    }

    @Provides fun provideSetFavorite(executor: Executor, mainThread: MainThread, repository: MoviesRepository): SetFavorite {
        return SetFavorite(executor, mainThread, repository)
    }

    @Provides fun provideGetFavorites(executor: Executor, mainThread: MainThread, repository: MoviesRepository): GetFavorites {
        return GetFavorites(executor, mainThread, repository)
    }

    @Provides fun provideSearchMovies(executor: Executor, mainThread: MainThread, repository: MoviesRepository): SearchMovies {
        return SearchMovies(executor, mainThread, repository)
    }

}
