package com.example.quiz.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.quiz.R
import com.example.quiz.data.Repository
import com.example.quiz.databinding.ActivityMainBinding
import com.example.quiz.getViewModel
import com.example.quiz.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by lazy { getViewModel { MainViewModel(Repository()) } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startButton.setOnClickListener { goToQuestionActivity() }
    }

    private fun goToQuestionActivity() {
        val intent = Intent(this, QuestionActivity::class.java)
        startActivity(intent)
    }

    private fun prepopulateQuestions() = viewModel.prepopulateQuestions()

    private fun clearQuestions() = viewModel.clearQuestions()

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.prepopulate -> prepopulateQuestions()
            R.id.clear -> clearQuestions()
            else -> Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }
}
