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

import android.support.v7.widget.RecyclerView
import android.view.View
import com.martinchamarro.muvis.R
import com.martinchamarro.muvis.domain.model.Cast
import com.martinchamarro.muvis.domain.model.pictures.ProfileSize
import com.martinchamarro.muvis.presentation.extensions.load
import kotlinx.android.synthetic.main.item_cast.view.*

class CastViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    fun bind(cast: Cast) = with(itemView) {
        nameView.text = cast.name
        characterView.text = cast.character
        pictureView.load(cast.getProfileUrl(ProfileSize.SMALL), R.drawable.default_avatar)
    }

}