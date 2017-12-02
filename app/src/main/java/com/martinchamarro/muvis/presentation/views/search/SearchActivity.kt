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

package com.martinchamarro.muvis.presentation.views.search

import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import butterknife.ButterKnife
import butterknife.OnTextChanged
import com.martinchamarro.muvis.R
import com.martinchamarro.muvis.domain.model.Movie
import com.martinchamarro.muvis.presentation.extensions.*
import com.martinchamarro.muvis.presentation.navigation.Navigator
import com.martinchamarro.muvis.presentation.views.detail.DetailActivity
import com.martinchamarro.muvis.presentation.views.favorites.FavoritesAdapter
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.item_movie.view.*
import kotlinx.android.synthetic.main.layout_toolbar_progress.*
import javax.inject.Inject

class SearchActivity : AppCompatActivity(), SearchPresenter.View {

    @Inject protected lateinit var presenter: SearchPresenter
    @Inject protected lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        ButterKnife.bind(this)
        activityComponent.inject(this)
        initializePresenter()
        configureRecyclerView()
    }

    private fun initializePresenter() {
        presenter.view = this
        presenter.initialize()
    }

    private fun configureRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onPause() {
        presenter.onPause()
        super.onPause()
    }

    @OnTextChanged(R.id.searchInput)
    fun onSearch(text: CharSequence) = presenter.search(text.toString())

    override fun render(movies: List<Movie>) {
        recyclerView.adapter = FavoritesAdapter(context, movies, this::onMovieClick)
    }

    private fun onMovieClick(movie: Movie, view: View) {
        val transitionName = string(R.string.transition_picture)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, view.posterView, transitionName)
        startActivity(DetailActivity.createIntent(context, movie.id), options.toBundle())
    }

    override fun showProgress() = toolbarProgress.visible()

    override fun hideProgress() = toolbarProgress.gone()

    override fun showEmpty() = emptyView.visible()

    override fun hideEmpty() = emptyView.gone()

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(0, 0)
    }

}