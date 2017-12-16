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

package com.martinchamarro.muvis.presentation

import com.martinchamarro.muvis.domain.exception.RepositoryException
import com.martinchamarro.muvis.domain.model.Movie
import com.martinchamarro.muvis.domain.repository.MoviesRepository
import com.martinchamarro.muvis.domain.usecase.GetFeatured
import com.martinchamarro.muvis.presentation.utils.DummyExecutor
import com.martinchamarro.muvis.presentation.utils.DummyMainThread
import com.martinchamarro.muvis.presentation.utils.DummyMovieFactory.givenAListOfMovies
import com.martinchamarro.muvis.presentation.views.movies.MoviesContract
import com.martinchamarro.muvis.presentation.views.movies.MoviesPresenter
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.funktionale.either.Either
import org.funktionale.either.Either.Companion.left
import org.funktionale.either.Either.Companion.right
import org.junit.Before
import org.junit.Test

class MoviesPresenterTest {

    val repository: MoviesRepository = mock()
    val view: MoviesContract.View = mock()
    lateinit var presenter: MoviesPresenter

    @Before
    fun setUp() {
        presenter = createAMockedPresenter()
    }

    @Test
    fun `should show an error if the repository returns an exception`() {
        givenTheRepositoryReturns(left(RepositoryException()))
        presenter.initialize()
        verify(view).showFeaturedError()
    }

    @Test
    fun `should show an empty view if the list of featured movies is empty`() {
        givenTheRepositoryReturns(right(emptyList()))
        presenter.initialize()
        verify(view).showEmptyView()
    }

    @Test
    fun `should render de list of movies if it is not empty`(){
        val anyMoviesList = givenAListOfMovies()
        givenTheRepositoryReturns(right(anyMoviesList))
        presenter.initialize()
        verify(view).render(anyMoviesList)
    }

    private fun createAMockedPresenter(): MoviesPresenter {
        val getFeatured = GetFeatured(DummyExecutor(), DummyMainThread(), repository)
        presenter = MoviesPresenter(getFeatured)
        presenter.view = view
        return presenter
    }

    private fun givenTheRepositoryReturns(result: Either<Throwable, List<Movie>>) {
        whenever(repository.getFeaturedMovies(1)).thenReturn(result)
    }

}
