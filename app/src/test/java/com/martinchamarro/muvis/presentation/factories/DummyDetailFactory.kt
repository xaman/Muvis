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

import com.martinchamarro.muvis.domain.model.Country
import com.martinchamarro.muvis.domain.model.Detail
import com.martinchamarro.muvis.domain.model.Genre

object DummyDetailFactory {

    fun givenADetail(): Detail = Detail(budget=0, homepage="http://www.starwars.com", imdbId="tt2527336", originalLanguage="en", originalTitle="Star Wars: The Last Jedi", overview="Rey develops her newly discovered abilities with the guidance of Luke Skywalker, who is unsettled by the strength of her powers. Meanwhile, the Resistance prepares to do battle with the First Order.", runtime=152, genres= listOf(Genre(id=28, name="Action"), Genre(id=12, name="Adventure")), countries= listOf(Country(iso="US", name="United States of America")))

}