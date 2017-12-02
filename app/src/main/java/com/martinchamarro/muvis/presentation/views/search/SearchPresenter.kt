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

package com.martinchamarro.muvis.presentation.views.search

import com.martinchamarro.muvis.domain.model.Movie
import com.martinchamarro.muvis.domain.usecase.SearchMovies
import com.martinchamarro.muvis.globalutils.logger.Logger
import javax.inject.Inject

class SearchPresenter @Inject constructor(
        private val searchMovies: SearchMovies) : SearchContract.Presenter {

    override var view: SearchContract.View? = null

    companion object {
        private val TAG: String = SearchPresenter::class.java.simpleName
    }

    override fun search(text: String) {
        if (text.isNullOrEmpty()) view?.showEmpty()
        else searchMovies.execute(text, this::onSearchSuccess, this::onSearchError)
    }

    private fun onSearchSuccess(movies: List<Movie>) {
        view?.hideProgress()
        if (movies.isNotEmpty()) {
            view?.render(movies)
            view?.hideEmpty()
        } else {
            view?.showEmpty()
        }
    }

    private fun onSearchError(cause: Throwable) {
        view?.hideProgress()
        Logger.e(TAG, "Error searching movies: ${cause.message}")
    }

    override fun onDestroy() { view = null }

}