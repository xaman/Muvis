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
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import com.martinchamarro.muvis.R
import com.martinchamarro.muvis.domain.model.Cast
import com.martinchamarro.muvis.domain.model.Detail
import com.martinchamarro.muvis.domain.model.Movie
import com.martinchamarro.muvis.presentation.extensions.activityComponent
import com.martinchamarro.muvis.presentation.extensions.fullScreen
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject

class DetailActivity : AppCompatActivity(), DetailPresenter.View {

    @Inject lateinit var presenter: DetailPresenter
    private lateinit var renderer: DetailRenderer

    companion object {
        val EXTRA_MOVIE_ID = "movie_id"
        fun start(context: Context, movieId: Int) {
            context.startActivity(createIntent(context, movieId))
        }
        fun createIntent(ctx: Context, movieId: Int): Intent {
            val intent = Intent(ctx, DetailActivity::class.java)
            intent.putExtra(EXTRA_MOVIE_ID, movieId)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        fullScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        injectDependencies()
        configureToolbar()
        initializeRenderer()
        initializePresenter()
    }

    fun injectDependencies() = activityComponent.inject(this)

    fun configureToolbar() {
        toolbar.inflateMenu(R.menu.detail_menu)
        toolbar.setNavigationOnClickListener { finish() }
    }

    fun initializeRenderer() {
        renderer = DetailRenderer(rootView)
    }

    fun initializePresenter() {
        presenter.view = this
        presenter.initialize()
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun getMovieId() = intent.getIntExtra(EXTRA_MOVIE_ID, -1)

    override fun render(movie: Movie) = renderer.render(movie)

    override fun render(detail: Detail) = renderer.render(detail)

    override fun render(credits: List<Cast>) = renderer.render(credits)

    override fun showProgress() {}

    override fun hideProgress() {}

    override fun onPause() {
        presenter.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

}
