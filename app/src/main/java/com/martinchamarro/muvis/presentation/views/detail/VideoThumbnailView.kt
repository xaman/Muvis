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
import android.support.v7.widget.CardView
import android.util.AttributeSet
import com.comprea.client.presentation.extensions.isNotNullOrEmpty
import com.google.android.youtube.player.*
import com.martinchamarro.muvis.Config
import com.martinchamarro.muvis.R
import com.martinchamarro.muvis.presentation.extensions.*
import kotlinx.android.synthetic.main.layout_video_thumbnail.view.*
import org.jetbrains.anko.dimen

class VideoThumbnailView : CardView, YouTubeThumbnailView.OnInitializedListener {

    private var key: String? = ""
    private var loader: YouTubeThumbnailLoader? = null

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        inflate(R.layout.layout_video_thumbnail, attach = true)
        cardElevation = 0.0f
        radius = dimen(R.dimen.video_thumbnail_radius).toFloat()
        setCardBackgroundColor(color(R.color.silver))
        youtubeThumbnail.initialize(Config.YOUTUBE_API_KEY, this)
    }

    override fun onInitializationSuccess(thumbnail: YouTubeThumbnailView?, loader: YouTubeThumbnailLoader?) {
        this.loader = loader
        showThumbnail()
    }

    override fun onInitializationFailure(thumbnail: YouTubeThumbnailView?, result: YouTubeInitializationResult?) {
        youtubeThumbnail.gone()
    }

    fun renderVideo(key: String) {
        this.key = key
        showThumbnail()
    }

    private fun showThumbnail() {
        if (key.isNotNullOrEmpty()) loader?.setVideo(key)
    }
}