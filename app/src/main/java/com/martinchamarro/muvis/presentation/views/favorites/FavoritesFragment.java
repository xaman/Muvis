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

package com.martinchamarro.muvis.presentation.views.favorites;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import com.martinchamarro.muvis.R;
import com.martinchamarro.muvis.presentation.base.BaseFragment;
import com.martinchamarro.muvis.presentation.views.home.HomeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FavoritesFragment extends BaseFragment {

    @BindView(R.id.recycler_view) protected RecyclerView recyclerView;

    public static FavoritesFragment getInstance() {
        return new FavoritesFragment();
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
    }

    private void injectDependencies() {
        getActivityComponent().inject(this);
    }

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = View.inflate(activity, R.layout.fragment_favorites, null);
        ButterKnife.bind(this, layout);
        configureToolbar();
        return layout;
    }

    private void configureToolbar() {
        Toolbar toolbar = ((HomeActivity) activity).getToolbar();
        Menu menu = toolbar.getMenu();
        if (menu != null) menu.clear();
    }
}