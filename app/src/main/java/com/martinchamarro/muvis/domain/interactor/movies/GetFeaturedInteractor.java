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

package com.martinchamarro.muvis.domain.interactor.movies;

import com.martinchamarro.muvis.domain.exception.RepositoryException;
import com.martinchamarro.muvis.domain.executor.Executor;
import com.martinchamarro.muvis.domain.executor.MainThread;
import com.martinchamarro.muvis.domain.interactor.ErrorCallback;
import com.martinchamarro.muvis.domain.interactor.Interactor;
import com.martinchamarro.muvis.domain.interactor.SuccessCallback;
import com.martinchamarro.muvis.domain.model.Movie;
import com.martinchamarro.muvis.domain.repository.MoviesRepository;

import java.util.List;

import javax.inject.Inject;

public class GetFeaturedInteractor implements Interactor, GetFeatured {

    private Executor executor;
    private MainThread mainThread;
    private MoviesRepository repository;
    private SuccessCallback<List<Movie>> successCallback;
    private ErrorCallback errorCallback;

    @Inject public GetFeaturedInteractor(Executor executor, MainThread mainThread, MoviesRepository repository) {
        this.executor = executor;
        this.mainThread = mainThread;
        this.repository = repository;
    }

    @Override public void execute(SuccessCallback<List<Movie>> successCallback, ErrorCallback errorCallback) {
        this.successCallback = successCallback;
        this.errorCallback = errorCallback;
        executor.execute(this);
    }

    @Override public void run() {
        try {
            List<Movie> movies = repository.getFeaturedMovies();
            notifySuccess(movies);
        } catch (RepositoryException e) {
            notifyError(e);
        }
    }

    private void notifySuccess(List<Movie> movies) {
        mainThread.post(() -> successCallback.onSuccess(movies));
    }

    private void notifyError(Throwable cause) {
        mainThread.post(() -> errorCallback.onError(cause));
    }
}
