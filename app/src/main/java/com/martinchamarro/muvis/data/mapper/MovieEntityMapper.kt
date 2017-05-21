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

package com.martinchamarro.muvis.data.mapper

import com.martinchamarro.muvis.data.entity.MovieEntity
import com.martinchamarro.muvis.domain.model.Movie
import javax.inject.Inject

class MovieEntityMapper @Inject constructor() {

    fun map(entity: MovieEntity?): Movie? {
        if (entity == null) return null
        return Movie(
            id = entity.id,
            title = entity.title,
            originalTitle = entity.originalTitle,
            originalLanguage = entity.originalLanguage,
            overview = entity.overview,
            releaseDate = entity.releaseCalendar,
            forAdults = entity.isForAdults,
            popularity = entity.popularity,
            votesCount = entity.votesCount,
            votesAverage = entity.votesAverage,
            posterPath = entity.posterPath,
            backdropPath = entity.backdropPath
        )
    }

}
