package io.itsydv.quizapp.feed

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import io.itsydv.quizapp.data.QuestionsRepo
import io.itsydv.quizapp.Resource
import io.itsydv.quizapp.data.QuestionsDatabase
import io.itsydv.quizapp.models.QuestionModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


// Using ViewModel to store and manage UI-related data in a lifecycle conscious way.
// as ViewModel survives configuration changes.
class FeedViewModel(context: Context): ViewModel() {

    private val questionRepo: QuestionsRepo
    private val _questions = MutableLiveData<Resource<List<QuestionModel>>>()
    val questions get() = _questions

    init {
        val questionDao = QuestionsDatabase.getDatabase(context).questionsDao()
        questionRepo = QuestionsRepo(questionDao)
        viewModelScope.launch {
            questionRepo.loadQuestions(context)
        }
        getUnAttemptedQuestions()
    }

    fun getUnAttemptedQuestions() {
        _questions.postValue(Resource.Loading())
        viewModelScope.launch {
            delay(800)
            val data =  questionRepo.unAttemptedQuestions()
            _questions.postValue(Resource.Success(data))
        }
    }

    fun getAttemptedQuestions() {
        _questions.postValue(Resource.Loading())
        viewModelScope.launch {
            delay(800)
            val data =  questionRepo.attemptedQuestions()
            _questions.postValue(Resource.Success(data))
        }
    }

    fun updateQuestion(question: QuestionModel) {
        viewModelScope.launch {
            questionRepo.updateQuestion(question)
        }
    }

}