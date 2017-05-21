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

package com.martinchamarro.muvis.domain.model

import com.martinchamarro.muvis.Config
import java.util.*

data class Movie(
    val id: Int,
    val title: String,
    val originalTitle: String,
    val originalLanguage: String,
    val overview: String,
    val releaseDate: Calendar,
    val forAdults: Boolean,
    val popularity: Float,
    val votesCount: Int,
    val votesAverage: Float,
    val posterPath: String,
    val backdropPath: String) {

    val releaseYear: Int
        get() = releaseDate[Calendar.YEAR]

    val posterFullPath: String
        get() = Config.PICTURE_DOMAIN + posterPath

    override fun hashCode(): Int {
        return id
    }

    override fun equals(other: Any?): Boolean {
        return other is Movie && other.id == id
    }

}