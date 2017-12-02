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

package com.martinchamarro.muvis

object Config {
    var DEBUG = BuildConfig.DEBUG_MODE
    val APP_NAME = "Muvis"
    val API_DOMAIN = "https://api.themoviedb.org/"
    val TMD_API_KEY = BuildConfig.TMD_API_KEY
    val YOUTUBE_API_KEY = BuildConfig.YOUTUBE_API_KEY
    val PICTURE_URL = "https://image.tmdb.org/t/p/"
    val MOVIE_URL = "https://www.themoviedb.org/movie/"
}
