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

package com.martinchamarro.muvis.presentation.views.detail

import com.martinchamarro.muvis.domain.interactor.movies.GetMovie
import com.martinchamarro.muvis.domain.model.Movie
import com.martinchamarro.muvis.presentation.base.Presenter
import javax.inject.Inject

class DetailPresenter @Inject constructor(private val getMovie: GetMovie) : Presenter {

    var view: View? = null

    override fun initialize() {
        view?.showProgress()
        val movieId = view?.getMovieId() ?: -1
        getMovie.execute(movieId, this::onMovieLoadSuccess, this::onMovieLoadError)
    }

    private fun onMovieLoadSuccess(movie: Movie) {
        view?.hideProgress()
        view?.render(movie)
    }

    private fun onMovieLoadError(cause: Throwable) {
        view?.hideProgress()
    }

    override fun onDestroy() {
        this.view = null
    }

    interface View {
        fun getMovieId(): Int?
        fun render(movie: Movie)
        fun showProgress()
        fun hideProgress()
    }
}