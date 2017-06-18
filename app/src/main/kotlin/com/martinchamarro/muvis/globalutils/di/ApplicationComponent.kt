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

package com.martinchamarro.muvis.globalutils.di

import android.content.Context

import com.martinchamarro.muvis.data.di.DataModule
import com.martinchamarro.muvis.threading.di.ThreadingModule
import com.martinchamarro.muvis.threading.Executor
import com.martinchamarro.muvis.threading.MainThread
import com.martinchamarro.muvis.domain.repository.MoviesRepository

import javax.inject.Singleton

import dagger.Component

@Singleton
@Component(modules = arrayOf(ApplicationModule::class, DataModule::class, ThreadingModule::class))
interface ApplicationComponent {

    fun context(): Context
    fun mainThread(): MainThread
    fun executor(): Executor
    fun moviesRepository(): MoviesRepository
}
