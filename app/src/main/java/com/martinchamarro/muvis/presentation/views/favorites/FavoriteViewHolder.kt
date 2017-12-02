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

import android.support.v7.widget.RecyclerView
import android.view.View
import com.martinchamarro.muvis.R
import com.martinchamarro.muvis.domain.model.Movie
import com.martinchamarro.muvis.domain.model.pictures.PosterSize
import com.martinchamarro.muvis.presentation.extensions.*
import kotlinx.android.synthetic.main.item_favorite.view.*

class FavoriteViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    companion object {
        private const val MAX_GENRES = 2
    }

    private val runtimeText = view.string(R.string.runtime_value)

    fun render(movie: Movie) = with(itemView) {
        titleView.text = movie.title
        yearView.text = movie.releaseYear
        posterView.load(movie.getPosterUrl(PosterSize.SMALL))
        renderRating(movie)
        renderDetail(movie)
    }

    private fun renderRating(movie: Movie) = with(itemView.ratingView) {
        val average = movie.votesAverage
        if (average == 0.0f) gone() else visible()
        text = average.toString()
    }

    private fun renderDetail(movie: Movie) = with(itemView) {
        movie.detail?.run {
            genresView.text = genres.take(MAX_GENRES).joinToString { it.name }
            countryView.text = countryName
            runtimeView.text = String.format(runtimeText, runtime)
        }
    }

}