package com.example.mytaskapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.example.mytaskapp.databinding.ActivityUpdateTaskBinding

class UpdateTask : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateTaskBinding
    private lateinit var db: TaskDataBaseHelper
    private var taskId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUpdateTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnNext = findViewById<Button>(R.id.cancelUpdateButton)
        btnNext.setOnClickListener {
            val nextPage = Intent(this, MainActivity::class.java)
            startActivity(nextPage)
            finish()
        }

        db = TaskDataBaseHelper(this)

        taskId = intent.getIntExtra("task_id", -1)
        if(taskId == -1){
            // If no task ID was passed, close the activity and return to the previous screen.
            finish()
            return
        }

        val task = db.getTaskByID(taskId)
        binding.updateNameEditText.setText(task.name)
        binding.updateDescriptionEditText.setText(task.description)
        binding.updateDeadlineEditText.setText(task.deadline)
        binding.updatePriorityEditText.setText(task.priority)

        binding.updateSaveButton.setOnClickListener {
            val newName = binding.updateNameEditText.text.toString()
            val newDescription = binding.updateDescriptionEditText.text.toString()
            val newDeadline = binding.updateDeadlineEditText.text.toString()
            val newPriority = binding.updatePriorityEditText.text.toString()

            val updateTask = Task(taskId, newName, newDescription, newDeadline, newPriority)
            db.updateTask(updateTask)
            finish()
            Toast.makeText(this, "Task Updated", Toast.LENGTH_SHORT).show()
        }

    }
}