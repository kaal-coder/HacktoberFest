package io.itsydv.quizapp.data

import androidx.room.*
import io.itsydv.quizapp.models.QuestionModel

@Dao
interface QuestionsDao {

    // Inserting data into the database with conflict strategy set of ignore
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertQuestions(questions: List<QuestionModel>)

    // updating the data in the database whenever user submits the quiz
    @Update
    suspend fun updateQuestion(question: QuestionModel)

    // getting all the not attempted questions from the database
    @Query("SELECT * FROM questions where attempted = 0 ORDER BY questionIndex")
    suspend fun getUnAttemptedQuestion(): List<QuestionModel>

    // getting all the attempted questions from the database
    @Query("SELECT * FROM questions where attempted = 1 ORDER BY questionIndex")
    suspend fun getAttemptedQuestion(): List<QuestionModel>
}