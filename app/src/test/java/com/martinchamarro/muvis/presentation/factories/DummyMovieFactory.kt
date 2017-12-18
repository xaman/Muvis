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

import com.martinchamarro.muvis.domain.model.Movie

object DummyMovieFactory {

    fun givenAMovie() = Movie(id=346364, title="It", originalTitle="It", originalLanguage="en", overview="In a small town in Maine, seven children known as The Losers Club come face to face with life problems, bullies and a monster that takes the shape of a clown called Pennywise.", releaseDate=null, forAdults=false, popularity=792.8831f, votesCount=4463, votesAverage=7.2f, posterPath="/9E2y5Q7WlCVNEhP5GiVTjhEhx1o.jpg", backdropPath="/tcheoA2nPATCm2vvXw2hVQoaEFD.jpg", isFavorite=false, detail=null)

    fun givenAListOfMovies() : List<Movie> {
        return listOf(
            Movie(id=141052, title="Justice League", originalTitle="Justice League", originalLanguage="en", overview="Fueled by his restored faith in humanity and inspired by Superman's selfless act, Bruce Wayne and Diana Prince assemble a team of metahumans consisting of Barry Allen, Arthur Curry, and Victor Stone to face the catastrophic threat of Steppenwolf and the Parademons who are on the hunt for three Mother Boxes on Earth.", releaseDate=null, forAdults=false, popularity=428.0867f, votesCount=1696, votesAverage=6.6f, posterPath="/9rtrRGeRnL0JKtu9IMBWsmlmmZz.jpg", backdropPath="/o5T8rZxoWSBMYwjsUFUqTt6uMQB.jpg", isFavorite=false, detail=null),
            Movie(id=181808, title="Star Wars: The Last Jedi", originalTitle="Star Wars: The Last Jedi", originalLanguage="en", overview="Rey develops her newly discovered abilities with the guidance of Luke Skywalker, who is unsettled by the strength of her powers. Meanwhile, the Resistance prepares to do battle with the First Order.", releaseDate=null, forAdults=false, popularity=261.0552f, votesCount=488, votesAverage=7.6f, posterPath="/xGWVjewoXnJhvxKW619cMzppJDQ.jpg", backdropPath="/5Iw7zQTHVRBOYpA0V6z0yypOPZh.jpg", isFavorite=false, detail=null),
            Movie(id=374720, title="Dunkirk", originalTitle="Dunkirk", originalLanguage="en", overview="The story of the miraculous evacuation of Allied soldiers from Belgium, Britain, Canada and France, who were cut off and surrounded by the German army from the beaches and harbour of Dunkirk between May 26th and June 4th 1940 during World War II.", releaseDate=null, forAdults=false, popularity=137.7439f, votesCount=3348, votesAverage=7.5f, posterPath="/bOXBV303Fgkzn2K4FeKDc0O31q4.jpg", backdropPath="/4yjJNAgXBmzxpS6sogj4ftwd270.jpg", isFavorite=false, detail=null),
            Movie(id=281338, title="War for the Planet of the Apes", originalTitle="War for the Planet of the Apes", originalLanguage="en", overview="Caesar and his apes are forced into a deadly conflict with an army of humans led by a ruthless Colonel. After the apes suffer unimaginable losses, Caesar wrestles with his darker instincts and begins his own mythic quest to avenge his kind. As the journey finally brings them face to face, Caesar and the Colonel are pitted against each other in an epic battle that will determine the fate of both their species and the future of the planet.", releaseDate=null, forAdults=false, popularity=160.035f, votesCount=2646, votesAverage=6.7f, posterPath="/3vYhLLxrTtZLysXtIWktmd57Snv.jpg", backdropPath="/ulMscezy9YX0bhknvJbZoUgQxO5.jpg", isFavorite=false, detail=null)
        )
    }

}