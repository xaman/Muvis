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

import com.martinchamarro.muvis.domain.interactor.Interactor
import com.martinchamarro.muvis.domain.model.Movie
import com.martinchamarro.muvis.domain.repository.MoviesRepository
import com.martinchamarro.muvis.threading.Executor
import com.martinchamarro.muvis.threading.MainThread
import javax.inject.Inject

class SetFavoriteInteractor @Inject constructor(
        val executor: Executor,
        val mainThread: MainThread,
        val repository: MoviesRepository) : Interactor, SetFavorite {

    private var id = -1
    private var isFavorite = false
    private lateinit var successCallback: (Movie) -> Unit
    private lateinit var errorCallback: (Throwable) -> Unit

    override fun execute(id: Int, isFavorite: Boolean, successCallback: (Movie) -> Unit, errorCallback: (Throwable) -> Unit) {
        this.id = id
        this.isFavorite = isFavorite
        this.successCallback = successCallback
        this.errorCallback = errorCallback
        executor.execute(this)
    }

    override fun run() {
        try {
            val movie = repository.setFavorite(id, isFavorite)
            successCallback(movie)
        } catch(e: Exception) {
            mainThread.post { errorCallback(e) }
        }
    }
}