package com.example.devsprint.data.repository

import com.example.devsprint.data.api.RetrofitInstance
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import com.example.devsprint.util.Constants

data class MashupResult(
    val insult: String,
    val rickMortyName: String,
    val rickMortyImage: String,
    val catImage: String
)

class MashupRepository {

    suspend fun getData(): MashupResult = coroutineScope {

        val insult = async { RetrofitInstance.api.getInsult() }
        val rickMorty = async { RetrofitInstance.api.getRickMorty((1..826).random()) }
        val cat = async { RetrofitInstance.api.getCatImage() }

        MashupResult(
            insult = insult.await().insult,
            rickMortyName = rickMorty.await().name,
            rickMortyImage = rickMorty.await().image,
            catImage = cat.await().first().url
        )
    }
}