package com.example.quiz

import android.app.Application
import androidx.room.Room
import com.example.quiz.data.db.QuizDatabase

class QuizApplication : Application() {

    private val DB_NAME = "quiz_database"

    companion object {
        lateinit var database: QuizDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this, QuizDatabase::class.java, DB_NAME)
            .build()
    }
}