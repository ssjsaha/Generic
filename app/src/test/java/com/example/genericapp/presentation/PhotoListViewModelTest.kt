package com.example.genericapp.presentation

import app.cash.turbine.test
import com.example.genericapp.data.FakePhotoListRepoImpl
import com.example.genericapp.feature_photo.data.repositores.PhotoListRepositoryImpl
import com.example.genericapp.feature_photo.domain.models.PhotoObject
import com.example.genericapp.feature_photo.presentation.ui_events.PhotoListPageUiEvent
import com.example.genericapp.feature_photo.presentation.viewmodels.PhotoListViewModel
import com.example.genericapp.feature_photo.utils.Resource
import com.example.genericapp.utils.FakeVerdict
import com.example.genericapp.utils.dummyError
import com.example.genericapp.utils.dummyObject
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(JUnit4::class)
class PhotoListViewModelTest {
    private lateinit var viewModel: PhotoListViewModel
    private lateinit var repo: PhotoListRepositoryImpl
    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        repo = mockk()
        coEvery { repo.getPhotoList() } returns flowOf(Resource.Success(emptyList()))
        viewModel = PhotoListViewModel(repo, mockk())
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

    @Test
    fun `is landscape returns true when width is greater than height`() {
        val width = 1280L
        val height = 720L
        val isLandscape = viewModel.isLandscape(width, height)
        Truth.assertThat(isLandscape).isTrue()
    }

    @Test
    fun `is landscape returns true when width is lower  than height`() {
        //width less than height
        val width = 720L
        val height = 1280L
        val isLandscape = viewModel.isLandscape(width, height)
        Truth.assertThat(isLandscape).isFalse()
    }

    @Test
    fun `is landscape returns true when width is equal to height`() {
        //width equal to height
        val width = 720L
        val height = 720L
        val isLandscape = viewModel.isLandscape(width, height)
        Truth.assertThat(isLandscape).isFalse()
    }

    @Test
    fun `on event calls get photos from api with refresh item event`() {
        val spyK = spyk(viewModel)
        coJustRun { spyK.getPhotoList() }
        spyK.onEvent(PhotoListPageUiEvent.RefreshItems)
        verify(exactly = 1) { spyK.getPhotoList() }
    }

    @Test
    fun `on event call with navigate to details page calls required methods and updates accordingly`() {

        val spyK = spyk(viewModel)
        every { spyK.constructPhotoUrl(dummyObject) } returns "url"
        every { spyK.isLandscape(any(), any()) } returns true
        spyK.onEvent(
            PhotoListPageUiEvent.NavigateToPhotoDetails(
                PhotoObject(
                    "",
                    1280L,
                    720L,
                    "",
                    0L,
                    "",
                    "",
                    ""
                )
            )
        )
        verify(exactly = 1) { spyK.constructPhotoUrl(dummyObject) }
        verify(exactly = 1) { spyK.isLandscape(any(), any()) }
        runTest {
            spyK.photoDetailsUiState.test {
                val item = awaitItem()
                Truth.assertThat(item.photo).isEqualTo(dummyObject)
                Truth.assertThat(item.isLandscape).isTrue()
                Truth.assertThat(item.photoUrl).isEqualTo("url")
            }
        }
    }


    @Test
    fun `get photo list returns success`() {
        val fakeRepo = FakePhotoListRepoImpl(FakeVerdict.SUCCESS)
        viewModel = PhotoListViewModel(fakeRepo, mockk())
        runTest {
            viewModel.getPhotoList()
            viewModel.uiStateFlow.test {

                //first check the loading value
                var item = awaitItem()
                Truth.assertThat(item.loading).isTrue()
                Truth.assertThat(item.photoList).isEmpty()
                Truth.assertThat(item.error).isEmpty()

                //then check the success value
                item = awaitItem()
                Truth.assertThat(item.loading).isFalse()
                Truth.assertThat(item.photoList).isEqualTo(listOf(dummyObject))
                Truth.assertThat(item.error).isEmpty()
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun `get photo list returns error`() {
        val fakeRepo = FakePhotoListRepoImpl(FakeVerdict.ERROR)
        viewModel = PhotoListViewModel(fakeRepo, mockk())
        runTest {
            viewModel.getPhotoList()
            viewModel.uiStateFlow.test {

                //first check the loading value
                var item = awaitItem()
                Truth.assertThat(item.loading).isTrue()
                Truth.assertThat(item.photoList).isEmpty()
                Truth.assertThat(item.error).isEmpty()

                //then check the error value
                item = awaitItem()
                Truth.assertThat(item.loading).isFalse()
                Truth.assertThat(item.photoList).isEmpty()
                Truth.assertThat(item.error).isEqualTo(dummyError)
                cancelAndIgnoreRemainingEvents()
            }
        }
    }
}