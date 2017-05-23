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
import com.martinchamarro.muvis.domain.model.Country
import com.martinchamarro.muvis.domain.model.Detail
import com.martinchamarro.muvis.domain.model.Genre
import com.martinchamarro.muvis.domain.model.Movie
import com.martinchamarro.muvis.presentation.extensions.ctx
import com.martinchamarro.muvis.presentation.extensions.load
import com.martinchamarro.muvis.presentation.extensions.visible
import kotlinx.android.synthetic.main.activity_detail.view.*
import kotlinx.android.synthetic.main.layout_detail_info.view.*
import java.util.*

class DetailRenderer(val view: View) {

    fun render(movie: Movie) = with(view) {
        posterView.load(movie.posterFullPath)
        backdropView.load(movie.backdropFullPath)
        titleView.text = movie.title
        ratingView.text = movie.votesAverage.toString()
        yearView.text = movie.releaseYear
        view.visible()
    }

    fun render(detail: Detail) = with(view) {
        runtimeView.text = String.format(ctx.getString(R.string.runtime_value), detail.runtime)
        countryView.text = translateCountryName(detail.countries[0])
        genresView.text = formatGenres(detail.genres)
        descriptionView.text = detail.overview
    }

    private fun translateCountryName(country: Country): String {
        return Locale("", country.iso).displayCountry
    }

    private fun formatGenres(genres: List<Genre>): String {
        return genres.foldIndexed("") { index, text, genre ->
            val comma = if (index > 0) "," else ""
            "$text$comma ${genre.name}"
        }
    }
}