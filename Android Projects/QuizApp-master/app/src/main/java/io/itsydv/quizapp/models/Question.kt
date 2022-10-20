package io.itsydv.quizapp.models

import com.google.gson.annotations.SerializedName

data class Question(
    @field:SerializedName("image") val questionImage: String? = null,
    @field:SerializedName("text") val questionText: String?
)