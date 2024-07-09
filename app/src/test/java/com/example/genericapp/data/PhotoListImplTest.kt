package com.example.genericapp.data

import app.cash.turbine.test
import com.example.genericapp.feature_photo.data.Api
import com.example.genericapp.feature_photo.data.repositores.PhotoListRepositoryImpl
import com.example.genericapp.feature_photo.domain.models.PhotoObject
import com.example.genericapp.feature_photo.utils.Resource
import com.example.genericapp.utils.readJsonFromFile
import com.google.common.truth.Truth
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response

@RunWith(JUnit4::class)

class PhotoListImplTest {


    private lateinit var photoListRepoImpl: PhotoListRepositoryImpl
    private lateinit var api: Api

    @Before
    fun setup() {
        api = mockk()
        photoListRepoImpl = PhotoListRepositoryImpl(api)
    }


    @Test
    fun `photo list repo  success response returns success flow`() {
        val jsonString = readJsonFromFile("PhotoListSuccessResponse.json")
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val adapter = moshi.adapter<List<PhotoObject>>(
            Types.newParameterizedType(
                List::class.java,
                PhotoObject::class.java
            )
        )
        val photoListResponse = adapter.fromJson(jsonString.toString())
        coEvery { api.getPhotoList() } returns Response.success(photoListResponse)
        runTest {
            photoListRepoImpl.getPhotoList().test {
                //loading item first
                val item = awaitItem()
                Truth.assertThat(item).isInstanceOf(Resource.Loading::class.java)

                //success item second
                val secondItem = awaitItem()
                Truth.assertThat(secondItem.message).isEmpty()
                Truth.assertThat(secondItem.data).isEqualTo(photoListResponse)
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun `photo list api returns 404 error and emits error 404 flow`() {
        coEvery { api.getPhotoList() } returns Response.error(
            404, byteArrayOf().toResponseBody("".toMediaTypeOrNull())
        )
        runTest {
            photoListRepoImpl.getPhotoList().test {
                //loading item first
                val item = awaitItem()
                Truth.assertThat(item).isInstanceOf(Resource.Loading::class.java)

                //error item second
                val secondItem = awaitItem()
                Truth.assertThat(secondItem.message).isEqualTo("Image Not Found")
                Truth.assertThat(secondItem.data).isNull()
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun `photo list api returns 400 error and emits error 400 flow`() {
        coEvery { api.getPhotoList() } returns Response.error(
            400, byteArrayOf().toResponseBody("".toMediaTypeOrNull())
        )
        runTest {
            photoListRepoImpl.getPhotoList().test {
                //loading item first
                val item = awaitItem()
                Truth.assertThat(item).isInstanceOf(Resource.Loading::class.java)

                //error item second
                val secondItem = awaitItem()
                Truth.assertThat(secondItem.message).isEqualTo("Bad Request")
                Truth.assertThat(secondItem.data).isNull()
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun `photo list api returns 500 error and emits error 500 flow`() {
        coEvery { api.getPhotoList() } returns Response.error(
            500, byteArrayOf().toResponseBody("".toMediaTypeOrNull())
        )
        runTest {
            photoListRepoImpl.getPhotoList().test {
                //loading item first
                val item = awaitItem()
                Truth.assertThat(item).isInstanceOf(Resource.Loading::class.java)
                //error item second
                val secondItem = awaitItem()
                Truth.assertThat(secondItem.message).isEqualTo("Internal Server Error")
                Truth.assertThat(secondItem.data).isNull()
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun `photo list api throws exception and emits error flow`() {
        coEvery { api.getPhotoList() } throws  Exception("something went wrong")
        runTest {
            photoListRepoImpl.getPhotoList().test {
                //loading item first
                val item = awaitItem()
                Truth.assertThat(item).isInstanceOf(Resource.Loading::class.java)
                //error item second
                val secondItem = awaitItem()
                Truth.assertThat(secondItem.message).isEqualTo("something went wrong")
                Truth.assertThat(secondItem.data).isNull()
                cancelAndIgnoreRemainingEvents()
            }
        }
    }
}