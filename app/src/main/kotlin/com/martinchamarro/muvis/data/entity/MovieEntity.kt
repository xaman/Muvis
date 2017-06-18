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

package com.martinchamarro.muvis.data.entity

import com.google.gson.annotations.SerializedName

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.GregorianCalendar
import java.util.Locale

data class MovieEntity(
        @SerializedName("id") var id: Int,
        @SerializedName("title") var title: String,
        @SerializedName("original_title") var originalTitle: String,
        @SerializedName("original_language") var originalLanguage: String,
        @SerializedName("overview") var overview: String?,
        @SerializedName("release_date") var releaseDate: String,
        @SerializedName("adult") var isForAdults: Boolean,
        @SerializedName("popularity") var popularity: Float,
        @SerializedName("vote_count") var votesCount: Int,
        @SerializedName("vote_average") var votesAverage: Float,
        @SerializedName("poster_path") var posterPath: String?,
        @SerializedName("backdrop_path") var backdropPath: String?) {

    companion object {
        val DATE_FORMAT = "yyyy-MM-dd"
    }

    val releaseCalendar: Calendar?
        get() {
            try {
                val format = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
                val calendar = GregorianCalendar()
                calendar.time = format.parse(releaseDate)
                return calendar
            } catch (e: ParseException) {
                return null
            }
        }

    var isFavorite: Boolean = false

    override fun equals(other: Any?) = other != null && other is MovieEntity && other.id == id

    override fun hashCode() = id
}
