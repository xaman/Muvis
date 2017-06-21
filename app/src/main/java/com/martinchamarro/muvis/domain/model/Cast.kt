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
import com.martinchamarro.muvis.domain.model.pictures.ProfileSize

data class Cast(val character: String,
                val name: String,
                private val profilePath: String?) {

    fun getProfileUrl(size: ProfileSize) = PICTURE_URL + size + profilePath

}