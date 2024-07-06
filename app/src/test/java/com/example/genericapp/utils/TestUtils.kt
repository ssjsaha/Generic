package com.example.genericapp.utils

import com.example.genericapp.feature_photo.domain.models.PhotoObject
import okio.buffer
import okio.source
import java.io.File
import java.io.IOException

val dummyObject = PhotoObject(
    "",
    1280L,
    720L,
    "",
    0L,
    "",
    "",
    ""
)

const val dummyError = "Something went wrong"

enum class FakeVerdict {
    SUCCESS,
    ERROR
}

fun readJsonFromFile(fileName: String): String? {
    return try {
        val file = File("src/test/resources/$fileName")
        val source = file.source().buffer()
        source.use { it.readUtf8() }
    } catch (e: IOException) {
        e.printStackTrace()
        null
    }
}