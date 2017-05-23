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
import com.martinchamarro.muvis.data.api.responses.ServerResponse

data class DetailEntity(
        @SerializedName("budget") var budget: Long,
        @SerializedName("homepage") var homepage: String,
        @SerializedName("imdb_id") var imdbId: String,
        @SerializedName("original_language") var originalLanguage: String,
        @SerializedName("original_title") var originalTitle: String,
        @SerializedName("overview") var overview: String,
        @SerializedName("genres") var genres: List<GenreEntity>,
        @SerializedName("production_countries") var countries: List<CountryEntity>) : ServerResponse()