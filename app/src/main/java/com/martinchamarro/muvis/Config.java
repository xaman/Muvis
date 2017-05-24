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

package com.martinchamarro.muvis;

public class Config {

    public static boolean DEBUG = BuildConfig.DEBUG_MODE;
    public static final String APP_NAME = "Muvis";
    public static final String DOMAIN = "https://api.themoviedb.org/";
    public static final String API_KEY = BuildConfig.API_KEY;
    public static final String PICTURE_DOMAIN = "https://image.tmdb.org/t/p/";

    private Config() {
        // Empty
    }
}
