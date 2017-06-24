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

import com.martinchamarro.muvis.data.entity.DetailEntity
import com.martinchamarro.muvis.domain.model.Detail
import javax.inject.Inject

class DetailEntityMapper @Inject constructor(
        private val genreMapper: GenreEntityMapper,
        private val countryMapper: CountryEntityMapper) {

    operator fun invoke(entity: DetailEntity?): Detail? {
        return entity?.let {
            Detail(
                    budget = entity.budget,
                    homepage = entity.homepage,
                    imdbId = entity.imdbId,
                    originalLanguage = entity.originalLanguage,
                    originalTitle = entity.originalTitle,
                    overview = entity.overview,
                    runtime = entity.runtime,
                    genres = entity.genres.map { genreMapper(it) }.toList(),
                    countries = entity.countries.map { countryMapper(it) })
        }
    }
}