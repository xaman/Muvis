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

import com.martinchamarro.muvis.domain.model.Movie
import com.martinchamarro.muvis.domain.repository.MoviesRepository
import com.martinchamarro.muvis.domain.usecase.SearchMovies
import com.martinchamarro.muvis.globalutils.logger.Logger
import com.martinchamarro.muvis.presentation.threading.DummyExecutor
import com.martinchamarro.muvis.presentation.threading.DummyMainThread
import com.martinchamarro.muvis.presentation.factories.DummyMovieFactory.givenAListOfMovies
import com.martinchamarro.muvis.presentation.ui.search.SearchContract
import com.martinchamarro.muvis.presentation.ui.search.SearchPresenter
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.funktionale.either.Either
import org.funktionale.either.Either.Companion.right
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.*

class SearchPresenterTest {

    private val mockView: SearchContract.View = mock()
    private val mockRepository: MoviesRepository = mock()
    private val mockedLogger: Logger = mock()
    private lateinit var presenter: SearchPresenter

    @Before
    fun setUp() {
        presenter = createMockedPresenter()
    }

    @Test
    fun `should show the empty view if the list of results is empty`() {
        givenTheRepositoryReturns(right(emptyList()))
        presenter.search("Text without search results")
        verify(mockView).showEmpty()
    }

    @Test
    fun `should render the list of results if its not empty`() {
        val anyMoviesList = givenAListOfMovies()
        givenTheRepositoryReturns(right(anyMoviesList))
        presenter.search("Star Wars")
        verify(mockView).render(anyMoviesList)
    }

    private fun createMockedPresenter(): SearchPresenter {
        val searchMovies = SearchMovies(DummyExecutor(), DummyMainThread(), mockRepository)
        presenter = SearchPresenter(searchMovies, mockedLogger)
        presenter.view = mockView
        return presenter
    }

    private fun givenTheRepositoryReturns(result: Either<Throwable, List<Movie>>) {
        whenever(mockRepository.searchMovies(anyString())).thenReturn(result)
    }

}