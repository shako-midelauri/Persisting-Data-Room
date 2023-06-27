package com.example.quiz.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.quiz.data.model.Answer
import com.example.quiz.data.model.Question

@Database(entities = [(Question::class), (Answer::class)], version = 1)
abstract class QuizDatabase : RoomDatabase() {
    abstract fun quizDao(): QuizDao
}