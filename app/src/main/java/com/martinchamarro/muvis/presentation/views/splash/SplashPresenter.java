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

import com.martinchamarro.muvis.globalutils.threads.Delay;
import com.martinchamarro.muvis.presentation.base.Presenter;
import com.martinchamarro.muvis.presentation.navigation.Navigator;

import javax.inject.Inject;


public class SplashPresenter implements Presenter {

    private static final int SPLASH_DELAY = 1500;

    private Navigator navigator;
    private View view;

    @Inject public SplashPresenter(Navigator navigator) {
        this.navigator = navigator;
    }

    public void setView(View view) {
        this.view = view;
    }

    @Override public void initialize() {
        navigateToHomeWithDelay();
    }

    private void navigateToHomeWithDelay() {
        new Delay(SPLASH_DELAY, this::navigateToHome).start();
    }

    private void navigateToHome() {
        navigator.navigateToHome();
        view.finish();
    }

    @Override public void onResume() {
        // Empty
    }

    @Override public void onPause() {
        // Empty
    }

    @Override public void onDestroy() {
        this.view = null;
    }

    public interface View {
        void finish();
    }
}
