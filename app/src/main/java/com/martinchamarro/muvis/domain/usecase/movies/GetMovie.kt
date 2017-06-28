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

package com.martinchamarro.muvis.domain.usecase.movies

import com.martinchamarro.muvis.threading.Executor
import com.martinchamarro.muvis.threading.MainThread
import com.martinchamarro.muvis.domain.usecase.UseCase
import com.martinchamarro.muvis.domain.model.Movie
import com.martinchamarro.muvis.domain.repository.MoviesRepository
import org.funktionale.either.Either
import javax.inject.Inject
import kotlin.properties.Delegates

class GetMovie @Inject constructor(
        executor: Executor,
        mainThread: MainThread,
        private var repository: MoviesRepository) : UseCase<Movie>(executor, mainThread) {

    private var id by Delegates.notNull<Int>()

    fun execute(id: Int, onSuccess: (Movie) -> Unit, onError: (Throwable) -> Unit) {
        this.id = id
        super.execute(onSuccess, onError)
    }

    override fun onExecute(): Either<Throwable, Movie> = repository.getMovieById(id)

}