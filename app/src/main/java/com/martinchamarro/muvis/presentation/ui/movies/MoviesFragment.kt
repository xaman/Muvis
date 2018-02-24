/*
 * Copyright 2018 Martin Chamarro (@martinchamarro)
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

package com.martinchamarro.muvis.presentation.ui.movies

import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.widget.GridLayoutManager.VERTICAL
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.martinchamarro.muvis.R
import com.martinchamarro.muvis.domain.model.Movie
import com.martinchamarro.muvis.presentation.base.BaseFragment
import com.martinchamarro.muvis.presentation.extensions.*
import com.martinchamarro.muvis.presentation.ui.detail.DetailActivity
import com.martinchamarro.muvis.presentation.ui.home.HomeActivity
import com.martinchamarro.muvis.presentation.widgets.ItemOffsetDecorator
import kotlinx.android.synthetic.main.fragment_movies.*
import kotlinx.android.synthetic.main.item_movie.view.*
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.ctx

class MoviesFragment : BaseFragment<MoviesContract.Presenter>(), MoviesContract.View {

    companion object {
        private const val NUM_COLUMNS = 3
        fun getInstance() = MoviesFragment()
    }

    private lateinit var adapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return View.inflate(context, R.layout.fragment_movies, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureRecyclerView()
        initializePresenter()
    }

    private fun configureRecyclerView() {
        val layoutManager = StaggeredGridLayoutManager(NUM_COLUMNS, VERTICAL)
        recyclerView.layoutManager = layoutManager
        val itemsSpacing = dimen(R.dimen.movies_grid_spacing)
        recyclerView.addItemDecoration(ItemOffsetDecorator(itemsSpacing))
        recyclerView.addOnScrollListener(OnScrollEndListener(layoutManager, presenter::onScrollEnd))
        adapter = MoviesAdapter(ctx)
        adapter.onItemClick = this::onMovieClick
        recyclerView.adapter = adapter
    }

    private fun initializePresenter() {
        presenter.view = this
        presenter.initialize()
    }

    override fun render(movies: List<Movie>) = adapter.setMovies(movies)

    private fun onMovieClick(movie: Movie, view: View) {
        val transitionName = getString(R.string.transition_picture)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(act, view.posterView, transitionName)
        startActivity(DetailActivity.createIntent(ctx, movie.id), options.toBundle())
    }

    override fun showEmptyView() = emptyView.visible()

    override fun hideEmptyView() = emptyView.gone()

    override fun showProgress() = (activity as HomeActivity).showToolbarProgress()

    override fun hideProgress() = (activity as HomeActivity).hideToolbarProgress()

    override fun showFeaturedError() {}

}