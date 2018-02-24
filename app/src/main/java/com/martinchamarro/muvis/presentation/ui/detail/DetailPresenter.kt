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

package com.martinchamarro.muvis.presentation.ui.detail

import com.martinchamarro.muvis.Config
import com.martinchamarro.muvis.domain.model.Cast
import com.martinchamarro.muvis.domain.model.Detail
import com.martinchamarro.muvis.domain.model.Movie
import com.martinchamarro.muvis.domain.model.Video
import com.martinchamarro.muvis.domain.usecase.*
import com.martinchamarro.muvis.globalutils.logger.Logger
import com.martinchamarro.muvis.presentation.navigation.Navigator
import javax.inject.Inject

class DetailPresenter @Inject constructor(
        private val getMovie: GetMovie,
        private val getDetail: GetDetail,
        private val getCredits: GetCredits,
        private val getTrailer: GetTrailer,
        private val setFavorite: SetFavorite,
        private val navigator: Navigator,
        private val logger: Logger) : DetailContract.Presenter {

    companion object {
        val TAG: String = DetailPresenter::class.java.simpleName
    }

    override var view: DetailContract.View? = null
    private lateinit var movie: Movie
    private var trailer: Video? = null

    override val isFavorite: Boolean get() = movie.isFavorite

    override fun initialize() {
        view?.showProgress()
        val movieId = view?.getMovieId() ?: -1
        getMovie.execute(movieId, this::onMovieLoadSuccess, this::onMovieLoadError)
        getDetail.execute(movieId, this::onDetailLoadSuccess, this::onDetailLoadError)
        getCredits.execute(movieId, this::onCreditsLoadSuccess, this::onCreditsLoadError)
        getTrailer.execute(movieId, this::onTrailerLoadSuccess, this::onTrailerLoadError)
    }

    private fun onMovieLoadSuccess(movie: Movie) {
        this.movie = movie
        view?.hideProgress()
        view?.render(movie)
    }

    private fun onMovieLoadError(cause: Throwable) {
        logger.e(TAG, "Error loading movie: ${cause.message}")
        view?.finish()
    }

    private fun onDetailLoadSuccess(detail: Detail) { view?.render(detail) }

    private fun onDetailLoadError(cause: Throwable) {
        logger.e(TAG, "Error loading detail: ${cause.message}")
        view?.finish()
    }

    private fun onCreditsLoadSuccess(credits: List<Cast>) { view?.render(credits) }

    private fun onCreditsLoadError(cause: Throwable) {
        logger.e(TAG, "Error loading credits: ${cause.message}")
    }

    private fun onTrailerLoadSuccess(trailer: Video) {
        this.trailer = trailer
        view?.render(trailer)
    }

    private fun onTrailerLoadError(cause: Throwable) {
        logger.e(TAG, "Error loading trailer: ${cause.message}")
    }

    override fun setFavorite() {
        setFavorite.execute(movie.id, this::onSetFavoriteSuccess, this::onSetFavoriteError)
    }

    private fun onSetFavoriteSuccess(movie: Movie) {
        this.movie = movie
        view?.render(movie)
    }

    private fun onSetFavoriteError(cause: Throwable) {
        logger.e(TAG, "Error setting movie as favorite: ${cause.message}")
    }

    override fun shareMovie() { view?.shareMovie(Config.MOVIE_URL + movie.id) }

    override fun showTrailer() { trailer?.run { navigator.navigateToTrailer(key) } }

    override fun onDestroy() { this.view = null }

}