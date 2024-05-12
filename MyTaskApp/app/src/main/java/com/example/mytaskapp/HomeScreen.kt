package com.example.mytaskapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class HomeScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        val btnNext = findViewById<ImageView>(R.id.logoutBtn)
        btnNext.setOnClickListener {
            val nextPage = Intent(this, StartupScreen::class.java)
            startActivity(nextPage)
            finish()
        }

        val btnNext1 = findViewById<ImageView>(R.id.addtaskbtn1)
        btnNext1.setOnClickListener {
            val nextPage = Intent(this, AddNewTask::class.java)
            startActivity(nextPage)
            finish()
        }

        val btnNext2 = findViewById<ImageView>(R.id.addTaskBtn)
        btnNext2.setOnClickListener {
            val nextPage = Intent(this, AddNewTask::class.java)
            startActivity(nextPage)
            finish()
        }

        val btnNext3 = findViewById<ImageView>(R.id.statBtn)
        btnNext3.setOnClickListener {
            val nextPage = Intent(this, ProgressScreen::class.java)
            startActivity(nextPage)
            finish()
        }

        val btnNext4 = findViewById<ImageView>(R.id.ProgressBtn)
        btnNext4.setOnClickListener {
            val nextPage = Intent(this, ProgressScreen::class.java)
            startActivity(nextPage)
            finish()
        }

        val btnNext5 = findViewById<ImageView>(R.id.manageBtn)
        btnNext5.setOnClickListener {
            val nextPage = Intent(this, MainActivity::class.java)
            startActivity(nextPage)
            finish()
        }

        val btnNext6 = findViewById<ImageView>(R.id.mngbtn)
        btnNext6.setOnClickListener {
            val nextPage = Intent(this, MainActivity::class.java)
            startActivity(nextPage)
            finish()
        }


    }
}