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

package com.martinchamarro.muvis.data.repository;

import com.martinchamarro.muvis.domain.exception.RepositoryException;
import com.martinchamarro.muvis.domain.model.Movie;
import com.martinchamarro.muvis.domain.repository.MoviesRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MoviesRepositoryImpl implements MoviesRepository {

    @Inject public MoviesRepositoryImpl() {
        // Empty
    }

    @Override public List<Movie> getFeaturedMovies() throws RepositoryException {
        return null;
    }

    @Override public Movie getMovieById(int id) throws RepositoryException {
        return null;
    }
}
