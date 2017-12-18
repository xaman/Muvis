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
import com.martinchamarro.muvis.domain.model.*
import com.martinchamarro.muvis.domain.repository.MoviesRepository
import com.martinchamarro.muvis.domain.usecase.*
import com.martinchamarro.muvis.globalutils.logger.Logger
import com.martinchamarro.muvis.presentation.navigation.Navigator
import com.martinchamarro.muvis.presentation.factories.DummyCreditsFactory.givenAListOfCredits
import com.martinchamarro.muvis.presentation.factories.DummyDetailFactory.givenADetail
import com.martinchamarro.muvis.presentation.threading.*
import com.martinchamarro.muvis.presentation.threading.DummyMainThread
import com.martinchamarro.muvis.presentation.factories.DummyMovieFactory.givenAMovie
import com.martinchamarro.muvis.presentation.factories.DummyTrailerFactory.givenATrailer
import com.martinchamarro.muvis.presentation.views.detail.*
import com.nhaarman.mockito_kotlin.*
import org.funktionale.either.Either
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.*

class DetailPresenterTest {

    private val mockNavigator: Navigator = mock()
    private val mockRepository: MoviesRepository = mock()
    private val mockView: DetailContract.View = mock()
    private val mockedLogger: Logger = mock()
    private lateinit var presenter: DetailPresenter

    @Before
    fun setUp() {
        presenter = createMockedPresenter()
        addDefaultRepositoryResponses()
    }

    @Test
    fun `should finish the view if there is an exception instead of the movie`() {
        givenTheRepositoryWithAMovie(Either.left(RepositoryException()))
        presenter.initialize()
        verify(mockView, atLeastOnce()).finish()
    }

    @Test
    fun `should get the id and load the movie info`() {
        val anyMovie = givenAMovie()
        givenTheRepositoryWithAMovie(Either.right(anyMovie))
        presenter.initialize()
        verify(mockView).getMovieId()
        verify(mockView).render(anyMovie)
    }

    @Test
    fun `should finish the view if there is an exception instead of the detail`() {
        whenever(mockRepository.getMovieDetail(anyInt())).thenReturn(Either.left(RepositoryException()))
        presenter.initialize()
        verify(mockView, atLeastOnce()).finish()
    }

    @Test
    fun `should get the id and load the movie detail`() {
        val anyDetail = givenADetail()
        whenever(mockRepository.getMovieDetail(anyInt())).thenReturn(Either.right(anyDetail))
        presenter.initialize()
        verify(mockView).getMovieId()
        verify(mockView).render(anyDetail)
    }

    @Test
    fun `should get the id and load the credits`() {
        val anyListOfCredits = givenAListOfCredits()
        whenever(mockRepository.getCredits(anyInt())).thenReturn(Either.right(anyListOfCredits))
        presenter.initialize()
        verify(mockView).getMovieId()
        verify(mockView).render(anyListOfCredits)
    }

    @Test
    fun `should get the id and load the trailer`() {
        val anyTrailer = givenATrailer()
        givenTheRepositoryWithATrailer(Either.right(anyTrailer))
        presenter.initialize()
        verify(mockView).getMovieId()
        verify(mockView).render(anyTrailer)
    }

    @Test
    fun `should go to the full screen player if the trailer thumbnail is clicked`() {
        val anyTrailer = givenATrailer()
        givenTheRepositoryWithATrailer(Either.right(anyTrailer))
        presenter.initialize()
        presenter.showTrailer()
        verify(mockNavigator).navigateToTrailer(anyString())
    }

    @Test
    fun `should call the view to share the movie if the item is clicked`() {
        val anyMovie = givenAMovie()
        givenTheRepositoryWithAMovie(Either.right(anyMovie))
        presenter.initialize()
        presenter.shareMovie()
        verify(mockView).shareMovie(anyString())
    }

    private fun createMockedPresenter(): DetailPresenter {
        val executor = DummyExecutor()
        val mainThread = DummyMainThread()
        val getMovie = GetMovie(executor, mainThread, mockRepository)
        val getDetail = GetDetail(executor, mainThread, mockRepository)
        val getCredits = GetCredits(executor, mainThread, mockRepository)
        val getTrailer = GetTrailer(executor, mainThread, mockRepository)
        val setFavorite = SetFavorite(executor, mainThread, mockRepository)
        presenter = DetailPresenter(getMovie, getDetail, getCredits, getTrailer, setFavorite, mockNavigator, mockedLogger)
        presenter.view = mockView
        return presenter
    }

    private fun givenTheRepositoryWithAMovie(result: Either<Throwable, Movie>) {
        whenever(mockRepository.getMovieById(anyInt())).thenReturn(result)
    }

    private fun givenTheRepositoryWithATrailer(result: Either<Throwable, Video>) {
        whenever(mockRepository.getMovieTrailer(anyInt())).thenReturn(result)
    }

    private fun addDefaultRepositoryResponses() {
        whenever(mockRepository.getMovieById(anyInt())).thenReturn(Either.left(RepositoryException()))
        whenever(mockRepository.getMovieDetail(anyInt())).thenReturn(Either.left(RepositoryException()))
        whenever(mockRepository.getCredits(anyInt())).thenReturn(Either.left(RepositoryException()))
        whenever(mockRepository.getMovieTrailer(anyInt())).thenReturn(Either.left(RepositoryException()))
    }

}