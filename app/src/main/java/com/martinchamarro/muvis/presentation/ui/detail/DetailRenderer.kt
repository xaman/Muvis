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

import android.view.View
import com.martinchamarro.muvis.R
import com.martinchamarro.muvis.domain.model.*
import com.martinchamarro.muvis.domain.model.pictures.*
import com.martinchamarro.muvis.presentation.extensions.*
import com.martinchamarro.muvis.presentation.widgets.HorizontalLayoutManager
import com.martinchamarro.muvis.presentation.widgets.ItemOffsetDecorator
import kotlinx.android.synthetic.main.activity_detail.view.*
import kotlinx.android.synthetic.main.layout_detail_info.view.*

class DetailRenderer(private val view: View) {

    init {
        with(view) {
            creditsView.layoutManager = HorizontalLayoutManager(ctx)
            val itemOffset = ctx.dimen(R.dimen.small_padding)
            creditsView.addItemDecoration(ItemOffsetDecorator(itemOffset))
        }
    }

    fun render(movie: Movie) = with(view) {
        collapsingToolbar.title = movie.title
        titleView.text = movie.title
        posterView.load(movie.getPosterUrl(PosterSize.SMALL))
        backdropView.load(movie.getBackdropUrl(BackdropSize.SMALL))
        yearView.text = movie.releaseYear
        renderRating(movie)
        renderFab(movie)
        view.visible()
    }

    private fun renderRating(movie: Movie) = with(view.ratingView) {
        if (movie.votesAverage == 0.0f) gone() else visible()
        ratingView.text = movie.votesAverage.toString()
    }

    private fun renderFab(movie: Movie) = with(view.fabView) {
        var fabIconRes = R.drawable.ic_fab_fav
        if (movie.isFavorite) fabIconRes = R.drawable.ic_fab_unfav
        setImageResource(fabIconRes)
    }

    fun render(detail: Detail) = with(view) {
        countryView.text = detail.countryName
        genresView.text = detail.formattedGenres
        renderRuntime(detail)
        renderDescription(detail)
    }

    private fun renderRuntime(detail: Detail) = with(view.runtimeView) {
        if (detail.runtime == 0) gone() else visible()
        text = String.format(ctx.string(R.string.runtime_value), detail.runtime)
    }

    private fun renderDescription(detail: Detail) = with(view.descriptionView) {
        text = detail.overview
        setOnClickListener { onDescriptionClick() }
    }

    private fun onDescriptionClick() = with(view.descriptionView) {
        val descMaxLines = integer(R.integer.desc_max_lines)
        maxLines = if (maxLines == descMaxLines) Int.MAX_VALUE else descMaxLines
    }

    fun render(credits: List<Cast>) = with(view) {
        creditsView.adapter = CreditsAdapter(ctx, credits)
    }

    fun render(trailer: Video) = with(view) {
        videoThumbnail.renderVideo(trailer.key)
        videoThumbnail.visible()
    }

}