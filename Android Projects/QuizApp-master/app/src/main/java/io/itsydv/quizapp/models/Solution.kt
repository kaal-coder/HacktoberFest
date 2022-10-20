package io.itsydv.quizapp.models

import com.google.gson.annotations.SerializedName

data class Solution(
    @field:SerializedName("image") val solutionImage: String? = null,
    @field:SerializedName("text") val solutionText: String?
)