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

package com.martinchamarro.muvis.presentation.views.splash;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;

import com.martinchamarro.muvis.R;
import com.martinchamarro.muvis.presentation.base.BaseActivity;

import javax.inject.Inject;

import static android.view.WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;

public class SplashActivity extends BaseActivity implements SplashPresenter.View {

    @Inject protected SplashPresenter presenter;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        configureFullScreen();
        super.onCreate(savedInstanceState);
        injectDependencies();
        initializePresenter();
    }

    private void configureFullScreen() {
        Window window = getWindow();
        // Show layout below status bar
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        // Show layout below navigation bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.setFlags(FLAG_LAYOUT_NO_LIMITS, FLAG_LAYOUT_NO_LIMITS);
        }
    }

    @Override protected int getLayoutResource() {
        return R.layout.activity_splash;
    }

    private void injectDependencies() {
        getActivityComponent().inject(this);
    }

    private void initializePresenter() {
        presenter.setView(this);
        presenter.initialize();
    }

    @Override protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override protected void onPause() {
        presenter.onPause();
        super.onPause();
    }

    @Override protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override public void finish() {
        super.finish();
    }
}
