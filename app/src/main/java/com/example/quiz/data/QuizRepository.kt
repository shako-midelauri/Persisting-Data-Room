package com.example.quiz.data

import androidx.lifecycle.LiveData
import com.example.quiz.data.model.Answer
import com.example.quiz.data.model.Question
import com.example.quiz.data.model.QuestionAndAllAnswers

interface QuizRepository {

    fun getSavedQuestions(): LiveData<List<Question>>

    suspend fun saveQuestion(question: Question)

    suspend fun saveAnswer(answer: Answer)

    fun getQuestionAndAllAnswers(): LiveData<List<QuestionAndAllAnswers>>

    suspend fun deleteQuestions()
}