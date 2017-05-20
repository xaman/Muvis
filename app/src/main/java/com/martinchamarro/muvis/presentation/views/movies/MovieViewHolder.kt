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

import android.support.v7.widget.RecyclerView
import android.view.View
import com.martinchamarro.muvis.domain.model.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    fun render(movie: Movie) = with(itemView) {
        titleView.text = movie.title()
        yearView.text = movie.releaseDate()
        ratingView.text = movie.votesAverage().toString()
        Picasso.with(itemView.context)
            .load(movie.posterFullPath())
            .into(itemView.posterView)
    }

}