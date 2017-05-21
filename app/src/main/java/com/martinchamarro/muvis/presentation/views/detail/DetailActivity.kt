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

import com.martinchamarro.muvis.R
import com.martinchamarro.muvis.domain.model.Movie
import com.martinchamarro.muvis.presentation.base.BaseActivity
import javax.inject.Inject

class DetailActivity : BaseActivity(), DetailPresenter.View {

    @Inject lateinit var presenter: DetailPresenter

    companion object {
        val EXTRA_MOVIE_ID = "movie_id"
        fun start(context: Context, movieId: Int) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(EXTRA_MOVIE_ID, movieId)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        injectDependencies()
        initializePresenter()
    }

    fun injectDependencies() {
        activityComponent.inject(this)
    }

    fun initializePresenter() {
        presenter.view = this
        presenter.initialize()
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun getMovieId(): Int {
        return intent.getIntExtra(EXTRA_MOVIE_ID, -1)
    }

    override fun render(movie: Movie) {

    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun onPause() {
        presenter.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

}