package com.example.quiz.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quiz.data.QuizRepository
import com.example.quiz.data.model.QuestionAndAllAnswers
import com.example.quiz.data.model.QuizState

class QuizViewModel(repository: QuizRepository) : ViewModel() {

    private val questionAndAnswers = MediatorLiveData<QuestionAndAllAnswers>()
    private val currentQuestion = MutableLiveData<Int>()
    private val currentState = MediatorLiveData<QuizState>()
    private val allQuestionAndAllAnswers = repository.getQuestionAndAllAnswers()
    private var score: Int = 0

    init {
        currentState.postValue(QuizState.LoadingState)
        addStateSources()
        addQuestionSources()
        currentQuestion.postValue(0)
    }

    fun getCurrentState(): LiveData<QuizState> = currentState

    private fun addQuestionSources() {
        questionAndAnswers.addSource(currentQuestion) { currentQuestionNumber ->
            val questions = allQuestionAndAllAnswers.value

            if (questions != null && currentQuestionNumber < questions.size) {
                questionAndAnswers.postValue(questions[currentQuestionNumber])
            }
        }

        questionAndAnswers.addSource(allQuestionAndAllAnswers) { questionsAndAnswers ->
            val currentQuestionNumber = currentQuestion.value

            if (currentQuestionNumber != null && questionsAndAnswers.isNotEmpty()) {
                questionAndAnswers.postValue(questionsAndAnswers[currentQuestionNumber])
            }
        }
    }

    fun nextQuestion(choice: Int) { // 1
        verifyAnswer(choice)
        changeCurrentQuestion()
    }

    private fun verifyAnswer(choice: Int) { // 2
        val currentQuestion = questionAndAnswers.value

        if (currentQuestion != null && currentQuestion.answers[choice].isCorrect) {
            score++
        }
    }

    private fun changeCurrentQuestion() {
        currentQuestion.postValue(currentQuestion.value?.plus(1))
    }

    private fun addStateSources() {
        currentState.addSource(currentQuestion) { currentQuestionNumber ->
            if (currentQuestionNumber == allQuestionAndAllAnswers.value?.size) {
                currentState.postValue(QuizState.FinishState(currentQuestionNumber, score))
            }
        }
        currentState.addSource(allQuestionAndAllAnswers) { allQuestionsAndAnswers ->
            if (allQuestionsAndAnswers.isEmpty()) {
                currentState.postValue(QuizState.EmptyState)
            }
        }
        currentState.addSource(questionAndAnswers) { questionAndAnswers ->
            currentState.postValue(QuizState.DataState(questionAndAnswers))
        }
    }
}