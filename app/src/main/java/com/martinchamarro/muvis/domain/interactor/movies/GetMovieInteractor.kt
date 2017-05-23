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

package com.martinchamarro.muvis.domain.interactor.movies

import com.martinchamarro.muvis.domain.exception.MovieNotFoundException
import com.martinchamarro.muvis.domain.exception.RepositoryException
import com.martinchamarro.muvis.domain.executor.Executor
import com.martinchamarro.muvis.domain.executor.MainThread
import com.martinchamarro.muvis.domain.interactor.Interactor
import com.martinchamarro.muvis.domain.model.Movie
import com.martinchamarro.muvis.domain.repository.MoviesRepository
import javax.inject.Inject

class GetMovieInteractor @Inject constructor(private var executor: Executor, private var mainThread: MainThread, private var repository: MoviesRepository) : Interactor, GetMovie {

    private var id: Int = -1
    private lateinit var successCallback: (Movie) -> Unit
    private lateinit var errorCallback: (Throwable) -> Unit

    override fun execute(id: Int, successCallback: (Movie) -> Unit, errorCallback: (Throwable) -> Unit) {
        this.id = id
        this.successCallback = successCallback
        this.errorCallback = errorCallback
        executor.execute(this)
    }

    override fun run() {
        try {
            val movie = repository.getMovieById(id)
            if (movie != null) notifySuccess(movie) else notifyError(MovieNotFoundException())
        } catch (e: RepositoryException) {
            notifyError(e)
        }
    }

    fun notifySuccess(movie: Movie) = mainThread.post { successCallback(movie) }

    fun notifyError(cause: Throwable) = mainThread.post { errorCallback(cause) }
}