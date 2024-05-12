package com.example.mytaskapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ProgressScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress_screen)

        val btnNext = findViewById<Button>(R.id.b2homeBtn)
        btnNext.setOnClickListener {
            val nextPage = Intent(this, HomeScreen::class.java)
            startActivity(nextPage)
            finish()
        }
    }
}