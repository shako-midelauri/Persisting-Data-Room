package com.example.quiz.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class QuestionAndAllAnswers(
    @Embedded
    var question: Question? = null,
    @Relation(
        parentColumn = "question_id",
        entityColumn = "question_id"
    )
    var answers: List<Answer> = ArrayList()
)