package com.example.quiz.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEachIndexed
import com.example.quiz.R
import com.example.quiz.data.Repository
import com.example.quiz.data.model.QuizState
import com.example.quiz.databinding.ActivityQuestionBinding
import com.example.quiz.getViewModel
import com.example.quiz.viewmodel.QuizViewModel

class QuestionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuestionBinding

    companion object {
        const val SCORE = "SCORE"
        const val NUMBER_OF_QUESTIONS = "NUMBER_OF_QUESTIONS"
    }

    private val viewModel by lazy { getViewModel { QuizViewModel(Repository()) } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener { nextQuestion() }
        getQuestionsAndAnswers()
    }

    private fun getQuestionsAndAnswers() {
        viewModel.getCurrentState().observe(this) {
            render(it)
        }
    }

    private fun render(state: QuizState) {
        when (state) {
            is QuizState.EmptyState -> renderEmptyState()
            is QuizState.DataState -> renderDataState(state)
            is QuizState.FinishState -> goToResultActivity(state.numberOfQuestions, state.score)
            is QuizState.LoadingState -> renderLoadingState()
        }
    }

    private fun renderDataState(quizState: QuizState.DataState) {
        binding.progressBar.visibility = View.GONE
        displayQuestionsView()
        binding.questionsRadioGroup.clearCheck()
        binding.questionsRadioGroup.jumpDrawablesToCurrentState()
        binding.questionTextView.text = quizState.data.question?.text
        binding.questionsRadioGroup.forEachIndexed { index, view ->
            if (index < quizState.data.answers.size)
                (view as RadioButton).text = quizState.data.answers[index].text
        }
    }

    private fun renderLoadingState() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun renderEmptyState() {
        binding.progressBar.visibility = View.GONE
        binding.emptyTextView.visibility = View.VISIBLE
    }

    private fun nextQuestion() {
        val radioButton =
            findViewById<RadioButton>(binding.questionsRadioGroup.checkedRadioButtonId)
        val selectedOption = binding.questionsRadioGroup.indexOfChild(radioButton)
        if (selectedOption != -1) {
            viewModel.nextQuestion(selectedOption)
        } else {
            Toast.makeText(this, getString(R.string.please_select_an_option), Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun displayQuestionsView() {
        binding.questionsRadioGroup.visibility = View.VISIBLE
        binding.questionTextView.visibility = View.VISIBLE
        binding.button.visibility = View.VISIBLE
    }

    private fun goToResultActivity(numberOfQuestions: Int, score: Int) {
        val intent = Intent(this, ResultActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            putExtra(SCORE, score)
            putExtra(NUMBER_OF_QUESTIONS, numberOfQuestions)
        }

        startActivity(intent)
    }
}
