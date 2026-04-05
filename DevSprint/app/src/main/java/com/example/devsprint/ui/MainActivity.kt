package com.example.devsprint.ui

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.devsprint.R
import com.example.devsprint.data.repository.MashupRepository
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private val repo = MashupRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.generateBtn)
        val insultText = findViewById<TextView>(R.id.insultText)
        val rickMortyText = findViewById<TextView>(R.id.rickMortyText)
        val rickMortyImage = findViewById<ImageView>(R.id.rickMortyImage)
        val catImage = findViewById<ImageView>(R.id.catImage)

        button.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                try {
                    val result = repo.getData()

                    insultText.text = result.insult
                    rickMortyText.text = result.rickMortyName

                    Glide.with(this@MainActivity)
                        .load(result.rickMortyImage)
                        .into(rickMortyImage)

                    Glide.with(this@MainActivity)
                        .load(result.catImage)
                        .into(catImage)

                } catch (e: Exception) {
                    insultText.text = "Error 😅"
                }
            }
        }
    }
}