package com.example.genericapp.presentation

import app.cash.turbine.test
import com.example.genericapp.feature_photo_list.data.repositores.PhotoListRepositoryImpl
import com.example.genericapp.feature_photo_list.presentation.PhotoListViewModel
import com.example.genericapp.feature_photo_list.utils.Resource
import com.google.common.truth.Truth
import com.nhaarman.mockitokotlin2.doNothing
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.mockito.Mockito.spy

@OptIn(ExperimentalCoroutinesApi::class)
class PhotoListViewModelTest {

    private lateinit var viewModel: PhotoListViewModel
    private lateinit var repo: PhotoListRepositoryImpl
    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        repo = mockk()
        coEvery { repo.getPhotoList() } returns flowOf(Resource.Success(emptyList()))
        viewModel = PhotoListViewModel(repo)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `on event with upload post with non null file test`() {
       /* runTest {
            val spyF = spyk(viewModel)
            coEvery { repo.getPhotoList() } returns flowOf(Resource.Error("hayhay"))
            spyF.getPhotoList()
            spyF.uiStateFlow.test {
                val ans = awaitItem().photoList
                val error =  awaitItem().error
                Truth.assertThat(ans.size).isEqualTo(1)
                Truth.assertThat(error).contains("hayahay")
            }
        }*/
    }
}