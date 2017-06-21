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

import android.view.View
import com.martinchamarro.muvis.R
import com.martinchamarro.muvis.domain.model.*
import com.martinchamarro.muvis.domain.model.pictures.BackdropSize
import com.martinchamarro.muvis.domain.model.pictures.PosterSize
import com.martinchamarro.muvis.presentation.extensions.*
import com.martinchamarro.muvis.presentation.views.widgets.HorizontalLayoutManager
import com.martinchamarro.muvis.presentation.views.widgets.ItemOffsetDecorator
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
        ratingView.text = movie.votesAverage.toString()
        yearView.text = movie.releaseYear
        renderFab(movie)
        view.visible()
    }

    private fun renderFab(movie: Movie) = with(view.fabView) {
        var fabIconRes = R.drawable.ic_fab_fav
        if (movie.isFavorite) fabIconRes = R.drawable.ic_fab_unfav
        setImageResource(fabIconRes)
    }

    fun render(detail: Detail) = with(view) {
        runtimeView.text = String.format(ctx.string(R.string.runtime_value), detail.runtime)
        countryView.text = detail.countryName
        genresView.text = detail.formattedGenres
        descriptionView.text = detail.overview
        descriptionView.setOnClickListener { onDescriptionClick() }
    }

    fun render(credits: List<Cast>) = with(view) {
        creditsView.adapter = CreditsAdapter(ctx, credits)
    }

    private fun onDescriptionClick() = with(view.descriptionView) {
        val descMaxLines = integer(R.integer.desc_max_lines)
        maxLines = if (maxLines == descMaxLines) Int.MAX_VALUE else descMaxLines
    }

}