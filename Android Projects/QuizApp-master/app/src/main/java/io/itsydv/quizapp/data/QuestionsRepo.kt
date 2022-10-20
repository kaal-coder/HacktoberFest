package io.itsydv.quizapp.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.itsydv.quizapp.models.QuestionModel

class QuestionsRepo(private val questionsDao: QuestionsDao) {

    suspend fun unAttemptedQuestions() = questionsDao.getUnAttemptedQuestion()
    suspend fun attemptedQuestions() = questionsDao.getAttemptedQuestion()

    suspend fun updateQuestion(questionModel: QuestionModel) {
        questionsDao.updateQuestion(questionModel)
    }

    // This function is used to get the questions from the json file and save it to the database
    suspend fun loadQuestions(context: Context) {
        var questions = getQuestions(context)

        // reversing the list to get the questions of recent year first
        // also indexing the questions for passing the questionNumber between fragments
        questions = questions.reversed().mapIndexed { index, question ->
            question.questionIndex = index
            question
        }
        questionsDao.insertQuestions(questions)
    }

    // loading questions from json file
    private fun getQuestions(context: Context): List<QuestionModel> {
        val gson = Gson()
        val jsonString = context.assets.open("questions.json").bufferedReader().use { it.readText() }
        val listQuestionType = object : TypeToken<List<QuestionModel>>() {}.type
        return gson.fromJson(jsonString, listQuestionType)
    }
}