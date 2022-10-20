package io.itsydv.quizapp.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey


// This is the model class for the quiz
// This is also the entity class for the quiz table in the database
@Entity(tableName = "questions")
data class QuestionModel(
    var attempted: Boolean = false,
    val chapters: ArrayList<String>? = arrayListOf(),
    val exams: ArrayList<String>? = arrayListOf(),
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val options: ArrayList<Option>,
    val previousYearPapers: ArrayList<String>? = arrayListOf(),
    @Embedded
    val question: Question,
    var questionIndex: Int? = null,
    @Embedded
    val solution: Solution,
    val source: String? = null,
    val subjects: ArrayList<String>? = arrayListOf(),
    val type: String? = null,
)