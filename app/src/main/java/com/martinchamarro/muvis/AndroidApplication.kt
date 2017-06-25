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

package com.martinchamarro.muvis

import android.content.Context
import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication
import com.martinchamarro.muvis.globalutils.di.ApplicationComponent
import com.martinchamarro.muvis.globalutils.di.ApplicationModule
import com.martinchamarro.muvis.globalutils.di.DaggerApplicationComponent

class AndroidApplication : MultiDexApplication() {

    lateinit var applicationComponent: ApplicationComponent
        private set

    override fun onCreate() {
        super.onCreate()
        initializeInjection()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    private fun initializeInjection() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

}
