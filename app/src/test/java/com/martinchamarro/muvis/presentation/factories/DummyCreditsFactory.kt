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

package com.martinchamarro.muvis.presentation.factories

import com.martinchamarro.muvis.domain.model.Cast

object DummyCreditsFactory {

    fun givenAListOfCredits(): List<Cast> {
        return listOf(
            Cast(character="Luke Skywalker", name="Mark Hamill", profilePath="/ws544EgE5POxGJqq9LUfhnDrHtV.jpg"),
            Cast(character="General Leia Organa", name="Carrie Fisher", profilePath="/pbleNurCYdrLFQMEnlQB2nkOR1O.jpg"),
            Cast(character="Kylo Ren", name="Adam Driver", profilePath="/rsjwgpV2OukxOJ9HEiEyf4qu1vR.jpg"),
            Cast(character="Rey", name="Daisy Ridley", profilePath="/iiBJCkVVLHAUBW6vbUhJ3RtxlXv.jpg"),
            Cast(character="Finn", name="John Boyega", profilePath="/3153CfpgZQXTzCY0i74WpJumMQe.jpg")
        )
    }

}