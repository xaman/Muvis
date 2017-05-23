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

package com.martinchamarro.muvis.data.api;

import com.martinchamarro.muvis.data.api.responses.CreditsResponse;
import com.martinchamarro.muvis.data.api.responses.FeaturedMoviesResponse;
import com.martinchamarro.muvis.data.api.responses.ServerResponse;
import com.martinchamarro.muvis.data.entity.CastEntity;
import com.martinchamarro.muvis.data.entity.DetailEntity;
import com.martinchamarro.muvis.data.entity.MovieEntity;
import com.martinchamarro.muvis.domain.exception.ApiException;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Response;

public class RetrofitApi implements Api {

    private Services services;

    @Inject public RetrofitApi(RetrofitServicesFactory servicesFactory) {
        services = servicesFactory.create();
    }

    @Override public List<MovieEntity> getFeaturedMovies() throws ApiException {
        FeaturedMoviesResponse response = execute(services.getFeaturedMovies("2017")).body();
        return response.getResults();
    }

    @Override public DetailEntity getMovieDetail(int id) throws ApiException {
        DetailEntity entity = execute(services.getMovieDetail(id)).body();
        return entity;
    }

    @Override public List<CastEntity> getCredits(int id) throws ApiException {
        CreditsResponse response = execute(services.getCredits(id)).body();
        return response.getCast();
    }

    private <T> Response<T> execute(Call<T> call) throws ApiException {
        try {
            return call.execute();
        } catch (IOException e) {
            throw new ApiException("IOException executing Api call", e);
        } catch (RuntimeException e) {
            throw new ApiException("RuntimeException executing Api call", e);
        }
    }

    private void checkApiError(ServerResponse response) throws ApiException {
        if (response.hasError()) throw new ApiException(response.getStatusMessage());
    }
}
