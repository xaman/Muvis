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

package com.martinchamarro.muvis.presentation.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.martinchamarro.muvis.AndroidApplication;
import com.martinchamarro.muvis.globalutils.di.ApplicationComponent;
import com.martinchamarro.muvis.presentation.di.ActivityComponent;
import com.martinchamarro.muvis.presentation.di.ActivityModule;
import com.martinchamarro.muvis.presentation.di.DaggerActivityComponent;
import com.martinchamarro.muvis.presentation.di.PresenterModule;

import butterknife.ButterKnife;

public class BaseActivity extends AppCompatActivity {

    private ActivityComponent activityComponent;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectViews();
    }

    public ActivityComponent getActivityComponent() {
        if (activityComponent == null) {
            activityComponent = DaggerActivityComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(new ActivityModule(this))
                .presenterModule(new PresenterModule())
                .build();
        }
        return activityComponent;
    }

    private ApplicationComponent getApplicationComponent() {
        return ((AndroidApplication) getApplication()).getApplicationComponent();
    }

    private void injectViews() {
        int layoutRes = getLayoutResource();
        if (layoutRes == 0) return;
        setContentView(layoutRes);
        ButterKnife.bind(this);
    }

    protected int getLayoutResource() {
        return 0;
    }

}
