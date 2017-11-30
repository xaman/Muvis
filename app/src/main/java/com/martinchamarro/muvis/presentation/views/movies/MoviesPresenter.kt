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

package com.martinchamarro.muvis.presentation.views.movies

import com.martinchamarro.muvis.domain.model.Movie
import com.martinchamarro.muvis.domain.usecase.GetFeatured
import com.martinchamarro.muvis.presentation.base.BasePresenter
import javax.inject.Inject

class MoviesPresenter @Inject constructor(private val getFeatured: GetFeatured) : BasePresenter {

    var view: View? = null

    private var page: Int = 1
    private var featured: MutableList<Movie> = mutableListOf()

    override fun initialize() {
        loadMovies()
    }

    private fun loadMovies() {
        view?.showProgress()
        getFeatured.execute(page, { onMoviesLoadSuccess(it) }, { onMoviesLoadError() })
    }

    private fun onMoviesLoadSuccess(movies: List<Movie>) {
        featured.addAll(movies)
        if (featured.isNotEmpty()) view?.render(featured) else view?.showEmptyView()
        view?.hideProgress()
    }

    private fun onMoviesLoadError() {
        view?.hideProgress()
        view?.showFeaturedError()
    }

    fun onScrollEnd() {
        page += 1
        loadMovies()
    }

    override fun onDestroy() {
        featured.clear()
        view = null
    }

    interface View {
        fun render(movies: List<Movie>)
        fun showEmptyView()
        fun hideEmptyView()
        fun showProgress()
        fun hideProgress()
        fun showFeaturedError()
    }
}