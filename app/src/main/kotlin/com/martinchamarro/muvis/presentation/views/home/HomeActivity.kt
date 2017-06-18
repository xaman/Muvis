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

package com.martinchamarro.muvis.presentation.views.home

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import butterknife.ButterKnife
import butterknife.OnPageChange
import com.martinchamarro.muvis.R
import com.martinchamarro.muvis.presentation.extensions.activityComponent
import com.martinchamarro.muvis.presentation.extensions.gone
import com.martinchamarro.muvis.presentation.extensions.setVisible
import com.martinchamarro.muvis.presentation.extensions.visible
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.layout_toolbar_progress.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent.inject(this)
        setContentView(R.layout.activity_home)
        ButterKnife.bind(this)
        configureToolbar()
        configurePager()
        configureNavigation()
    }

    private fun configureToolbar() = toolbar.inflateMenu(R.menu.home_menu)

    private fun configurePager() {
        pager.adapter = HomePagerAdapter(supportFragmentManager)
    }

    private fun configureNavigation() {
        bottomNav.setOnNavigationItemSelectedListener({
            val isFirstPage = it.itemId == R.id.action_movies
            pager.currentItem = if (isFirstPage) 0 else 1
            bottomNav.menu.setVisible(isFirstPage)
            true
        })
    }

    fun showToolbarProgress() = toolbarProgress.visible()

    fun hideToolbarProgress() = toolbarProgress.gone()

    @OnPageChange(R.id.pager) fun onPageChange(position: Int) {
        bottomNav.menu.getItem(position).isChecked = true
        toolbar.menu.setVisible(position == 0)
    }

}