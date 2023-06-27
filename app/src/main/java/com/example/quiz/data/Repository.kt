package com.example.quiz.data

import com.example.quiz.QuizApplication
import com.example.quiz.data.db.QuizDao
import com.example.quiz.data.model.Answer
import com.example.quiz.data.model.Question

class Repository : QuizRepository {

    private val quizDao: QuizDao by lazy {
        QuizApplication.database.quizDao()
    }
    private val allQuestions by lazy {
        quizDao.getAllQuestions()
    }
    private val allQuestionsAndAllAnswers by lazy {
        quizDao.getQuestionAndAllAnswers()
    }

    override fun getSavedQuestions() = allQuestions

    override fun getQuestionAndAllAnswers() = allQuestionsAndAllAnswers

    override suspend fun saveQuestion(question: Question) {
        quizDao.insert(question)
    }

    override suspend fun saveAnswer(answer: Answer) {
        quizDao.insert(answer)
    }

    override suspend fun deleteQuestions() {
        quizDao.clearQuestions()
    }
}