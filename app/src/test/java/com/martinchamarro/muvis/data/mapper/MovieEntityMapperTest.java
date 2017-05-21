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
        MovieEntity entity = givenAnEntity();
        Movie movie = mapper.map(entity);
        assertEquals(entity.getId(), movie.getId());
        assertEquals(entity.getTitle(), movie.getTitle());
        assertEquals(entity.getOriginalTitle(), movie.getOriginalTitle());
        assertEquals(entity.getOriginalLanguage(), movie.getOriginalLanguage());
        assertEquals(entity.getOverview(), movie.getOverview());
        assertEquals(entity.isForAdults(), movie.getForAdults());
        assertEquals(entity.getVotesCount(), movie.getVotesCount());
        assertEquals(entity.getVotesAverage(), movie.getVotesAverage(), 0.1f);
        assertEquals(entity.getPopularity(), movie.getPopularity(), 0.1f);
        assertEquals(entity.getReleaseDate(), movie.getReleaseDate());
        assertEquals(entity.getPosterPath(), movie.getPosterPath());
        assertEquals(entity.getBackdropPath(), movie.getBackdropPath());
    }

    private MovieEntity givenAnEntity() {
        MovieEntity entity = new MovieEntity();
        entity.setId(1);
        entity.setTitle("Logan");
        entity.setOriginalTitle("Logan");
        entity.setOriginalLanguage("en");
        entity.setOverview("Sin sus poderes, por primera vez, Wolverine es verdaderamente vulnerable. Después de una vida de dolor y angustia, sin rumbo y perdido en el mundo donde los X-Men son leyenda, su mentor Charles Xavier lo convence de asumir una última misión: proteger a una joven que será la única esperanza para la raza mutante. Tercera y última película protagonizada por Hugh Jackman en el papel de Lobezno.");
        entity.setForAdults(false);
        entity.setVotesCount(1234);
        entity.setVotesAverage(7.5f);
        entity.setPopularity(10.123324f);
        entity.setReleaseDate("2017-01-01");
        entity.setPosterPath("/4b6bc23af9.png");
        entity.setBackdropPath("/a34b4bc45.png");
        return entity;
    }

}
