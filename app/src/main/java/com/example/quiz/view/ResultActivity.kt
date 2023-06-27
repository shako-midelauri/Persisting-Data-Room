package com.example.quiz.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.quiz.R
import com.example.quiz.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    private val score by lazy { intent.extras?.getInt(QuestionActivity.SCORE) }
    private val numberOfQuestions by lazy { intent.extras?.getInt(QuestionActivity.NUMBER_OF_QUESTIONS) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.playButton.setOnClickListener { playAgain() }
        binding.scoreTextView.text =
            String.format(getString(R.string.score_message), score, numberOfQuestions)
    }

    private fun playAgain() {
        val intent = Intent(this, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }

        startActivity(intent)
    }
}

