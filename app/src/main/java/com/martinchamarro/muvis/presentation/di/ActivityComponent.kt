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

package com.martinchamarro.muvis.presentation.di


import android.app.Activity

import com.martinchamarro.muvis.domain.di.UseCaseModule
import com.martinchamarro.muvis.globalutils.di.ApplicationComponent
import com.martinchamarro.muvis.presentation.views.detail.DetailActivity
import com.martinchamarro.muvis.presentation.views.favorites.FavoritesFragment
import com.martinchamarro.muvis.presentation.views.movies.MoviesFragment
import com.martinchamarro.muvis.presentation.views.home.HomeActivity
import com.martinchamarro.muvis.presentation.views.splash.SplashActivity

import dagger.Component

@PerActivity
@Component(
        dependencies = arrayOf(ApplicationComponent::class),
        modules = arrayOf(ActivityModule::class, PresenterModule::class, UseCaseModule::class))
interface ActivityComponent {

    fun inject(activity: SplashActivity)
    fun inject(activity: HomeActivity)
    fun inject(activity: DetailActivity)

    fun inject(fragment: MoviesFragment)
    fun inject(fragment: FavoritesFragment)

    fun activity(): Activity

}
