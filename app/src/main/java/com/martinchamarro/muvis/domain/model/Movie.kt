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

import com.martinchamarro.muvis.Config.PICTURE_URL
import com.martinchamarro.muvis.domain.model.pictures.BackdropSize
import com.martinchamarro.muvis.domain.model.pictures.PosterSize
import java.util.*

data class Movie(
        val id: Int,
        val title: String,
        val originalTitle: String,
        val originalLanguage: String,
        val overview: String?,
        val releaseDate: Calendar?,
        val forAdults: Boolean,
        val popularity: Float,
        val votesCount: Int,
        val votesAverage: Float,
        private val posterPath: String?,
        private val backdropPath: String?,
        val isFavorite: Boolean,
        val detail: Detail?) {

    val releaseYear: String?
        get() = releaseDate?.get(Calendar.YEAR).toString()

    fun getPosterUrl(size: PosterSize) = PICTURE_URL + size + posterPath

    fun getBackdropUrl(size: BackdropSize) = PICTURE_URL + size + backdropPath

    override fun hashCode() = id

    override fun equals(other: Any?) = other is Movie && other.id == id

}