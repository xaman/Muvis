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

import com.martinchamarro.muvis.Config
import com.martinchamarro.muvis.domain.model.Cast
import com.martinchamarro.muvis.domain.model.Detail
import com.martinchamarro.muvis.domain.model.Movie
import com.martinchamarro.muvis.domain.usecase.GetMovie
import com.martinchamarro.muvis.domain.usecase.GetDetail
import com.martinchamarro.muvis.domain.usecase.GetCredits
import com.martinchamarro.muvis.domain.usecase.SetFavorite
import com.martinchamarro.muvis.globalutils.logger.Logger
import com.martinchamarro.muvis.presentation.base.BasePresenter
import javax.inject.Inject

class DetailPresenter @Inject constructor(
        private val getMovie: GetMovie,
        private val getDetail: GetDetail,
        private val getCredits: GetCredits,
        private val setFavorite: SetFavorite) : BasePresenter {

    companion object {
        val TAG: String = DetailPresenter::class.java.simpleName
    }

    var view: View? = null
    lateinit var movie: Movie
    lateinit var detail: Detail

    override fun initialize() {
        view?.showProgress()
        val movieId = view?.getMovieId() ?: -1
        getMovie.execute(movieId, this::onMovieLoadSuccess, this::onMovieLoadError)
        getDetail.execute(movieId, this::onDetailLoadSuccess, this::onDetailLoadError)
        getCredits.execute(movieId, this::onCreditsLoadSuccess, this::onCreditsLoadError)
    }

    private fun onMovieLoadSuccess(movie: Movie) {
        this.movie = movie
        view?.hideProgress()
        view?.render(movie)
    }

    private fun onMovieLoadError(cause: Throwable) {
        Logger.e(TAG, "Error loading movie: ${cause.message}")
        view?.finish()
    }

    private fun onDetailLoadSuccess(detail: Detail) {
        view?.render(detail)
    }

    private fun onDetailLoadError(cause: Throwable) {
        Logger.e(TAG, "Error loading detail: ${cause.message}")
        view?.finish()
    }

    private fun onCreditsLoadSuccess(credits: List<Cast>) {
        view?.render(credits)
    }

    private fun onCreditsLoadError(cause: Throwable) {
        Logger.e(TAG, "Error loading credits: ${cause.message}")
    }

    fun setFavorite() {
        setFavorite.execute(movie.id, this::onSetFavoriteSuccess, this::onSetFavoriteError)
    }

    fun onSetFavoriteSuccess(movie: Movie) {
        this.movie = movie
        view?.render(movie)
    }

    fun onSetFavoriteError(cause: Throwable) {
        Logger.e(TAG, "Error setting movie as favorite: ${cause.message}")
    }

    fun shareMovie() = view?.shareMovie(Config.MOVIE_URL + movie.id)

    override fun onDestroy() { this.view = null }

    interface View {
        fun getMovieId(): Int?
        fun render(movie: Movie)
        fun render(detail: Detail)
        fun render(credits: List<Cast>)
        fun showProgress()
        fun hideProgress()
        fun shareMovie(url: String)
        fun finish()
    }
}