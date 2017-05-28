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

package com.martinchamarro.muvis.presentation.views.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.martinchamarro.muvis.presentation.views.favorites.FavoritesFragment;
import com.martinchamarro.muvis.presentation.views.movies.MoviesFragment;


public class HomePagerAdapter extends FragmentPagerAdapter {

    private static final int NUM_PAGES = 2;
    private static final int MOVIES_POS = 0;
    private static final int FAVORITES_POS = 1;

    public HomePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == MOVIES_POS) {
            fragment = MoviesFragment.getInstance();
        } else if (position == FAVORITES_POS) {
            fragment = FavoritesFragment.Companion.getInstance();
        }
        return fragment;
    }

    @Override public int getCount() {
        return NUM_PAGES;
    }
}
