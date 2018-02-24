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

package com.martinchamarro.muvis.presentation.navigation

import android.app.Activity
import android.content.Intent
import com.martinchamarro.muvis.presentation.ui.detail.DetailActivity

import com.martinchamarro.muvis.presentation.ui.home.HomeActivity
import com.martinchamarro.muvis.presentation.ui.search.SearchActivity
import com.martinchamarro.muvis.presentation.ui.trailer.TrailerActivity
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.noAnimation

import javax.inject.Inject

class Navigator @Inject constructor(private val activity: Activity) {

    fun navigateToHome() = start<HomeActivity>()

    fun navigateToSearch() = start(intent<SearchActivity>().noAnimation())

    fun navigateToDetail(movieId: Int) = start(DetailActivity.createIntent(activity, movieId))

    fun navigateToTrailer(videoKey: String) = TrailerActivity.start(activity, videoKey)

    private inline fun <reified T : Any> start() = start(intent<T>())

    private fun start(intent: Intent) = activity.startActivity(intent)

    private inline fun <reified T : Any> intent() = activity.intentFor<T>()

}