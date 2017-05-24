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

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.martinchamarro.muvis.R
import com.martinchamarro.muvis.domain.model.Cast
import com.martinchamarro.muvis.presentation.extensions.dimen

class CreditsAdapter(val ctx: Context, val credits: List<Cast>) : RecyclerView.Adapter<CastViewHolder>() {

    val itemWidth = ctx.dimen(R.dimen.item_cast_width)
    val itemHeight = ctx.dimen(R.dimen.item_cast_height)

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CastViewHolder {
        val view = View.inflate(ctx, R.layout.item_cast, null)
        view.layoutParams = RecyclerView.LayoutParams(itemWidth, itemHeight)
        return CastViewHolder(view)
    }

    override fun onBindViewHolder(holder: CastViewHolder?, position: Int) {
        holder?.bind(credits[position])
    }

    override fun getItemCount(): Int = credits.size
}
