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

package com.martinchamarro.muvis.presentation.ui.home

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

import com.martinchamarro.muvis.presentation.ui.favorites.FavoritesFragment
import com.martinchamarro.muvis.presentation.ui.movies.MoviesFragment


class HomePagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    companion object {
        val NUM_PAGES = 2
        val MOVIES_POS = 0
        val FAVORITES_POS = 1
    }

    override fun getItem(pos: Int) = when (pos) {
        MOVIES_POS -> MoviesFragment.getInstance()
        else -> FavoritesFragment.getInstance()
    }

    override fun getCount() = NUM_PAGES

}
