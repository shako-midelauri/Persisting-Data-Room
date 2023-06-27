package com.example.quiz.data

import com.example.quiz.data.model.Answer
import com.example.quiz.data.model.Question

object QuestionInfoProvider {

    val questionList = initQuestionList()
    val answerList = initAnswersList()

    private fun initQuestionList(): MutableList<Question> {
        val questions = mutableListOf<Question>()
        questions.add(
            Question(
                1,
                "Which of the following languages is not commonly used to develop Android Apps"
            )
        )
        questions.add(
            Question(
                2,
                "What is the meaning of life?"
            )
        )
        questions.add(
            Question(
                3,
                "Kotlin was developed by which team?"
            )
        )
        return questions
    }

    private fun initAnswersList(): MutableList<Answer> {
        val answers = mutableListOf<Answer>()
        answers.add(
            Answer(
                1,
                1,
                false,
                "Java"
            )
        )
        answers.add(
            Answer(
                2,
                1,
                false,
                "Kotlin"
            )
        )
        answers.add(
            Answer(
                3,
                1,
                true,
                "Ruby"
            )
        )
        answers.add(
            Answer(
                4,
                2,
                true,
                "42"
            )
        )
        answers.add(
            Answer(
                5,
                2,
                false,
                "35"
            )
        )
        answers.add(
            Answer(
                6,
                2,
                false,
                "7"
            )
        )
        answers.add(
            Answer(
                7,
                3,
                false,
                "Google Team"
            )
        )
        answers.add(
            Answer(
                8,
                3,
                true,
                "IntelliJ Team"
            )
        )
        answers.add(
            Answer(
                9,
                3,
                false,
                "Linux Team"
            )
        )
        return answers
    }
}