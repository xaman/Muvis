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

package com.martinchamarro.muvis.presentation.ui.splash

import android.os.Bundle
import android.view.WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
import com.martinchamarro.muvis.R
import com.martinchamarro.muvis.presentation.base.BaseActivity
import com.martinchamarro.muvis.presentation.extensions.*

class SplashActivity : BaseActivity<SplashContract.Presenter>(), SplashContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        configureFullScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        activityComponent.inject(this)
        initializePresenter()
    }

    private fun configureFullScreen() {
        fullScreen()
        supportsKitkat { window.setFlags(FLAG_LAYOUT_NO_LIMITS, FLAG_LAYOUT_NO_LIMITS) }
    }

    private fun initializePresenter() {
        presenter.view = this
        presenter.initialize()
    }

}