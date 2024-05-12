package com.example.mytaskapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mytaskapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db: TaskDataBaseHelper
    private lateinit var tasksAdapter: TasksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnNext = findViewById<ImageView>(R.id.back2homeBtn)
        btnNext.setOnClickListener {
            val nextPage = Intent(this, HomeScreen::class.java)
            startActivity(nextPage)
            finish()
        }

        db = TaskDataBaseHelper(this)

        tasksAdapter = TasksAdapter(db.getAllTasks(), this)

        binding.taskRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.taskRecyclerView.adapter = tasksAdapter

        binding.addButton.setOnClickListener{
            val intent = Intent(this, AddNewTask::class.java)
            startActivity(intent)
        }

        //val btnNext = findViewById<ImageView>(R.id.back2homeBtn)
        //btnNext.setOnClickListener {
        //    val nextPage = Intent(this, HomeScreen::class.java)
        //    startActivity(nextPage)
        //    finish()
        //}

        ArrayAdapter.createFromResource(
            this,
            R.array.priority_levels,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.prioritySpinner.adapter = adapter
        }
        binding.prioritySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedPriority = parent.getItemAtPosition(position) as String
                filterTasksByPriority(selectedPriority)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Optional: Handle the case where no selection is made
            }
        }

        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // beginning at start are about to be replaced by new text with length after.
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // beginning at start have just replaced old text that had length before.
            }

            override fun afterTextChanged(s: Editable) {
                // This method is called to notify you that, somewhere within s, the text has been changed.
                // It is a good place to filter tasks based on the search text.
                filterTasks(s.toString())
            }
        })
    }

    override fun onResume() {
        super.onResume()
        tasksAdapter.refreshData(db.getAllTasks())
    }

    private fun filterTasks(text: String) {
        val filteredTasks = db.getAllTasks().filter {
            it.name.contains(text, ignoreCase = true) ||
                    it.description.contains(text, ignoreCase = true) ||
                    it.deadline.contains(text, ignoreCase = true) ||
                    it.priority.contains(text, ignoreCase = true)
        }
        tasksAdapter.refreshData(filteredTasks)
    }

    private fun filterTasksByPriority(priority: String) {
        val filteredTasks = if (priority == "All") {
            db.getAllTasks()
        } else {
            db.getAllTasks().filter { it.priority == priority }
        }
        tasksAdapter.refreshData(filteredTasks)

    }
}
