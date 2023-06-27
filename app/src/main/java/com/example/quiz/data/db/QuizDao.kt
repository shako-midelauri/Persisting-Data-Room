package com.example.quiz.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.quiz.data.model.Answer
import com.example.quiz.data.model.Question
import com.example.quiz.data.model.QuestionAndAllAnswers

@Dao
interface QuizDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(question: Question)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(answer: Answer)

    @Query("DELETE FROM questions")
    fun clearQuestions()

    @Delete
    fun deleteQuestion(question: Question)

    @Query("SELECT * FROM questions ORDER BY question_id")
    fun getAllQuestions(): LiveData<List<Question>>

    @Transaction
    @Query("SELECT * FROM questions")
    fun getQuestionAndAllAnswers(): LiveData<List<QuestionAndAllAnswers>>
}