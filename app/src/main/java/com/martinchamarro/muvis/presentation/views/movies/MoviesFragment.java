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

package com.martinchamarro.muvis.presentation.views.movies;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.martinchamarro.muvis.R;
import com.martinchamarro.muvis.domain.model.Movie;
import com.martinchamarro.muvis.presentation.base.BaseFragment;
import com.martinchamarro.muvis.presentation.views.widgets.ItemOffsetDecorator;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import kotlin.Unit;


public class MoviesFragment extends BaseFragment implements MoviesPresenter.View {

    private static final int NUM_COLUMNS = 3;

    @BindView(R.id.emptyView) protected View emptyView;
    @BindView(R.id.recyclerView) protected RecyclerView recyclerView;
    @BindDimen(R.dimen.small_margin) protected int itemOffset;

    @Inject MoviesPresenter presenter;

    public static MoviesFragment getInstance() {
        return new MoviesFragment();
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
    }

    private void injectDependencies() {
        getActivityComponent().inject(this);
    }

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = View.inflate(activity, R.layout.fragment_movies, null);
        ButterKnife.bind(this, layout);
        configureRecyclerView();
        initializePresenter();
        return layout;
    }

    private void configureRecyclerView() {
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(NUM_COLUMNS, GridLayoutManager.VERTICAL));
        recyclerView.addItemDecoration(new ItemOffsetDecorator(itemOffset));
    }

    private void initializePresenter() {
        presenter.setView(this);
        presenter.initialize();
    }

    @Override public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override public void onPause() {
        presenter.onPause();
        super.onPause();
    }

    @Override public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override public void showProgress() {

    }

    @Override public void hideProgress() {

    }

    @Override public void render(@NotNull List<Movie> movies) {
        recyclerView.setAdapter(new MoviesAdapter(activity, movies, this::onMovieClick));
    }

    private Unit onMovieClick(Movie movie) {
        presenter.showMovieDetail(movie.getId());
        return null;
    }

    @Override public void showFeaturedError() {
        
    }

    @Override public void showEmptyView() {
        emptyView.setVisibility(View.VISIBLE);
    }

    @Override public void hideEmptyView() {
        emptyView.setVisibility(View.GONE);
    }
}
