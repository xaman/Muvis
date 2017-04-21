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

package com.martinchamarro.muvis.data.mapper;

import com.martinchamarro.muvis.data.entity.MovieEntity;
import com.martinchamarro.muvis.domain.model.Movie;

import javax.inject.Inject;

public class MovieEntityMapper {

    @Inject public MovieEntityMapper() {
        // Empty
    }

    public Movie map(MovieEntity entity) {
        if (entity == null) return null;
        return Movie.builder()
            .setId(entity.getId())
            .setTitle(entity.getTitle())
            .setOriginalTitle(entity.getOriginalTitle())
            .setOriginalLanguage(entity.getOriginalLanguage())
            .setOverview(entity.getOverview())
            .setReleaseDate(entity.getReleaseDate())
            .setForAdults(entity.isForAdults())
            .setPopularity(entity.getPopularity())
            .setVotesAverage(entity.getVotesAverage())
            .setVotesCount(entity.getVotesCount())
            .setPosterPath(entity.getPosterPath())
            .setBackdropPath(entity.getBackdropPath())
            .build();
    }

}
