package com.example.quiz.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "answers",
    foreignKeys = [
        ForeignKey(
            entity = Question::class,
            parentColumns = ["question_id"],
            childColumns = ["question_id"],
            onDelete = CASCADE
        )
    ],
    indices = [Index("question_id")]
)
data class Answer(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "answer_id")
    val answerId: Int,
    @ColumnInfo(name = "question_id")
    val questionId: Int,
    @ColumnInfo(name = "is_correct")
    val isCorrect: Boolean,
    @ColumnInfo(name = "text")
    val text: String
)
