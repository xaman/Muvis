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

package com.martinchamarro.muvis.presentation.ui.trailer

import android.content.Context
import android.os.Bundle
import android.view.View
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.martinchamarro.muvis.Config
import com.martinchamarro.muvis.R
import kotlinx.android.synthetic.main.activity_trailer.*
import org.jetbrains.anko.startActivity

class TrailerActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {

    companion object {
        private const val EXTRA_VIDEO_KEY = "key"
        fun start(context: Context, videoKey: String) {
            context.startActivity<TrailerActivity>(EXTRA_VIDEO_KEY to videoKey)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trailer)
        hideSystemUi()
        configureVideoPlayer()
    }

    /*
     * https://developer.android.com/training/system-ui/immersive.html
     * This snippet hides the system bars.
     */
    private fun hideSystemUi() {
        // Set the IMMERSIVE flag.
        // Set the content to appear under the system bars so that the content
        // doesn't resize when the system bars hide and show.
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                or View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
    }

    private fun configureVideoPlayer() { player.initialize(Config.YOUTUBE_API_KEY, this) }

    override fun onInitializationSuccess(provider: YouTubePlayer.Provider?, player: YouTubePlayer?, p2: Boolean) {
        player?.cueVideo(intent.getStringExtra(EXTRA_VIDEO_KEY))
    }

    override fun onInitializationFailure(provider: YouTubePlayer.Provider?, result: YouTubeInitializationResult?) {
        finish()
    }

}
