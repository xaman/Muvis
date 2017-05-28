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
import com.martinchamarro.muvis.data.entity.MovieEntityBuilder;
import com.martinchamarro.muvis.domain.model.Movie;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class MovieEntityMapperTest {

    private MovieEntityMapper mapper;

    @Before public void setUp() {
        mapper = new MovieEntityMapper();
    }

    @Test public void testEntityMapping() {
        MovieEntity entity = MovieEntityBuilder.Companion.build();
        Movie movie = mapper.invoke(entity);
        assertEquals(entity.getId(), movie.getId());
        assertEquals(entity.getTitle(), movie.getTitle());
        assertEquals(entity.getOriginalTitle(), movie.getOriginalTitle());
        assertEquals(entity.getOriginalLanguage(), movie.getOriginalLanguage());
        assertEquals(entity.getOverview(), movie.getOverview());
        assertEquals(entity.isForAdults(), movie.getForAdults());
        assertEquals(entity.getVotesCount(), movie.getVotesCount());
        assertEquals(entity.getVotesAverage(), movie.getVotesAverage(), 0.1f);
        assertEquals(entity.getPopularity(), movie.getPopularity(), 0.1f);
        assertEquals(entity.getPosterPath(), movie.getPosterPath());
        assertEquals(entity.getBackdropPath(), movie.getBackdropPath());
    }
}
