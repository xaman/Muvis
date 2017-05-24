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

package com.martinchamarro.muvis.presentation.di;

import com.martinchamarro.muvis.domain.interactor.movies.GetCredits;
import com.martinchamarro.muvis.domain.interactor.movies.GetDetail;
import com.martinchamarro.muvis.domain.interactor.movies.GetFeatured;
import com.martinchamarro.muvis.domain.interactor.movies.GetMovie;
import com.martinchamarro.muvis.presentation.navigation.Navigator;
import com.martinchamarro.muvis.presentation.views.detail.DetailPresenter;
import com.martinchamarro.muvis.presentation.views.movies.MoviesPresenter;
import com.martinchamarro.muvis.presentation.views.splash.SplashPresenter;

import dagger.Module;
import dagger.Provides;

@Module public class PresenterModule {

    @Provides SplashPresenter provideSplashPresenter(Navigator navigator) {
        return new SplashPresenter(navigator);
    }

    @Provides MoviesPresenter provideMoviesPresenter(GetFeatured getFeatured, Navigator navigator) {
        return new MoviesPresenter(getFeatured, navigator);
    }

    @Provides DetailPresenter provideDetailPresenter(GetMovie getMovie, GetDetail getDetail, GetCredits getCredits) {
        return new DetailPresenter(getMovie, getDetail, getCredits);
    }

}
