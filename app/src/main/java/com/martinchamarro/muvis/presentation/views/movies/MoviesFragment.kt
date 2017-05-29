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

import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager.VERTICAL
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.martinchamarro.muvis.R
import com.martinchamarro.muvis.domain.model.Movie
import com.martinchamarro.muvis.presentation.extensions.activityComponent
import com.martinchamarro.muvis.presentation.extensions.dimen
import com.martinchamarro.muvis.presentation.extensions.gone
import com.martinchamarro.muvis.presentation.extensions.visible
import com.martinchamarro.muvis.presentation.views.detail.DetailActivity
import com.martinchamarro.muvis.presentation.views.home.HomeActivity
import com.martinchamarro.muvis.presentation.views.widgets.ItemOffsetDecorator
import kotlinx.android.synthetic.main.fragment_movies.*
import kotlinx.android.synthetic.main.item_movie.view.*
import javax.inject.Inject

class MoviesFragment : Fragment(), MoviesPresenter.View {

    companion object {
        private val NUM_COLUMNS = 3
        fun getInstance() = MoviesFragment()
    }

    @Inject lateinit var presenter: MoviesPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return View.inflate(context, R.layout.fragment_movies, null)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureRecyclerView()
        configurePresenter()
    }

    private fun configureRecyclerView() {
        recyclerView.layoutManager = StaggeredGridLayoutManager(NUM_COLUMNS, VERTICAL)
        val itemsSpacing = dimen(R.dimen.movies_grid_spacing)
        recyclerView.addItemDecoration(ItemOffsetDecorator(itemsSpacing))
    }

    private fun configurePresenter() {
        presenter.view = this
        presenter.initialize()
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun render(movies: List<Movie>) {
        recyclerView.adapter = MoviesAdapter(activity, movies, this::onMovieClick)
    }

    private fun onMovieClick(movie: Movie, view: View) {
        val transitionName = getString(R.string.transition_picture)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, view.posterView, transitionName)
        startActivity(DetailActivity.createIntent(context, movie.id), options.toBundle())
    }

    override fun showEmptyView() = emptyView.visible()

    override fun hideEmptyView() = emptyView.gone()

    override fun showProgress() = (activity as HomeActivity).showToolbarProgress()

    override fun hideProgress() = (activity as HomeActivity).hideToolbarProgress()

    override fun showFeaturedError() {}

    override fun onPause() {
        presenter.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

}