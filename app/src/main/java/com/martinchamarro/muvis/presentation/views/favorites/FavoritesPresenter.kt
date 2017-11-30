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

package com.martinchamarro.muvis.presentation.views.favorites

import com.martinchamarro.muvis.domain.model.Movie
import com.martinchamarro.muvis.domain.usecase.movies.GetFavorites
import com.martinchamarro.muvis.globalutils.logger.Logger
import com.martinchamarro.muvis.presentation.base.BasePresenter
import javax.inject.Inject

class FavoritesPresenter @Inject constructor(
        private val getFavorites: GetFavorites) : BasePresenter {

    companion object {
        val TAG: String = this::class.java.simpleName
    }

    var view: View? = null

    override fun onResume() {
        view?.showProgress()
        getFavorites.execute(this::onFavoritesSuccess, this::onFavoritesError)
    }

    private fun onFavoritesSuccess(favorites: List<Movie>) {
        view?.hideProgress()
        view?.render(favorites)
        if (favorites.isEmpty()) view?.showEmpty() else view?.hideEmpty()
    }

    private fun onFavoritesError(cause: Throwable) {
        view?.hideProgress()
        Logger.e(TAG, "Error loading favorites: ${cause.message}")
    }

    override fun onDestroy() {
        view = null
    }

    interface View {
        fun render(favorites: List<Movie>)
        fun showProgress()
        fun hideProgress()
        fun showEmpty()
        fun hideEmpty()
    }

}