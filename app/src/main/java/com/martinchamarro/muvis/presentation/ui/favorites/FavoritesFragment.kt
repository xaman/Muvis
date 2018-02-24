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

package com.martinchamarro.muvis.presentation.ui.favorites

import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.martinchamarro.muvis.R
import com.martinchamarro.muvis.domain.model.Movie
import com.martinchamarro.muvis.presentation.extensions.*
import com.martinchamarro.muvis.presentation.ui.detail.DetailActivity
import com.martinchamarro.muvis.presentation.ui.home.HomeActivity
import com.martinchamarro.muvis.presentation.widgets.ItemOffsetDecorator
import kotlinx.android.synthetic.main.fragment_favorites.*
import kotlinx.android.synthetic.main.item_movie.view.*
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.ctx
import javax.inject.Inject

class FavoritesFragment : Fragment(), FavoritesContract.View {

    companion object {
        fun getInstance() = FavoritesFragment()
    }

    @Inject lateinit var presenter: FavoritesContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return View.inflate(context, R.layout.fragment_favorites, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureRecyclerView()
        initializePresenter()
    }

    private fun configureRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(ctx)
        val margin = dimen(R.dimen.favorites_grid_spacing)
        recyclerView.addItemDecoration(ItemOffsetDecorator(margin))
    }

    private fun initializePresenter() {
        presenter.view = this
        presenter.initialize()
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onPause() {
        presenter.onPause()
        super.onPause()
    }

    override fun render(favorites: List<Movie>) {
        val adapter = FavoritesAdapter(ctx, favorites, this::onFavoriteClick)
        recyclerView.adapter = adapter
    }

    private fun onFavoriteClick(movie: Movie, view: View) {
        val transitionName = getString(R.string.transition_picture)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(act, view.posterView, transitionName)
        startActivity(DetailActivity.createIntent(ctx, movie.id), options.toBundle())
    }

    override fun showProgress() = (activity as HomeActivity).showToolbarProgress()

    override fun hideProgress() = (activity as HomeActivity).hideToolbarProgress()

    override fun showEmpty() = emptyView.visible()

    override fun hideEmpty() = emptyView.gone()

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

}