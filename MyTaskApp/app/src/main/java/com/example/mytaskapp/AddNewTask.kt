package com.example.mytaskapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.example.mytaskapp.databinding.ActivityAddNewTaskBinding

class AddNewTask : AppCompatActivity() {

    private lateinit var binding: ActivityAddNewTaskBinding
    private lateinit var db: TaskDataBaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNewTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnNext = findViewById<Button>(R.id.cancelButton)
        btnNext.setOnClickListener {
            val nextPage = Intent(this, MainActivity::class.java)
            startActivity(nextPage)
            finish()
        }

        db = TaskDataBaseHelper(this)

        binding.saveButton.setOnClickListener {
            val name = binding.nameEditText.text.toString().trim()
            val description = binding.descriptionEditText.text.toString().trim()
            val deadline = binding.deadlineEditText.text.toString().trim()
            val priority = binding.priorityEditText.text.toString().trim()

            if (name.isEmpty() || description.isEmpty() || deadline.isEmpty() || priority.isEmpty()) {
                // Show error message if any field is empty.
                Toast.makeText(this, "All fields need to be filled!", Toast.LENGTH_LONG).show()
            } else {
                // All fields are filled, proceed to create a new task.
                val task = Task(0, name, description, deadline, priority)
                db.insertTask(task)
                finish()
                Toast.makeText(this, "Task Added", Toast.LENGTH_SHORT).show()
            }
        }
    }
}