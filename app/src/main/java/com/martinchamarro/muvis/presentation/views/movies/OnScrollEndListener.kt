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

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager

class OnScrollEndListener(
        private val layoutManager: StaggeredGridLayoutManager,
        private val callback: () -> Unit) : RecyclerView.OnScrollListener() {

    private companion object {
        val OFFSET = 15
    }

    private var wasOnEnd = false

    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val visibleItems = recyclerView?.childCount ?: 0
        val totalItems = layoutManager.itemCount
        val firstVisibleItem = layoutManager.findFirstVisibleItemPositions(null).min() ?: 0
        val lastVisibleItem = firstVisibleItem + visibleItems
        val isOnEnd = totalItems - lastVisibleItem < OFFSET
        if (isOnEnd && !wasOnEnd) callback()
        wasOnEnd = isOnEnd
    }

}