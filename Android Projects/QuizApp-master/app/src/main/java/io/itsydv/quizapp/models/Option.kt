package io.itsydv.quizapp.models

import com.google.gson.annotations.SerializedName

data class Option(
    @field:SerializedName("id") val optionId: String,
    @field:SerializedName("image") val OptionImage: String? = null,
    val isCorrect: Boolean,
    @field:SerializedName("text") val optionText: String? = null
)