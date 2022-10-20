package io.itsydv.quizapp.feed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import io.itsydv.quizapp.databinding.ItemQuestionPreviewBinding
import io.itsydv.quizapp.models.QuestionModel

// Adapter for the recycler view in the feed fragment
class AllQuestionsAdapter(val listener: (QuestionModel) -> Unit): RecyclerView.Adapter<AllQuestionsAdapter.QuestionsViewHolder>() {

    inner class QuestionsViewHolder(val binding: ItemQuestionPreviewBinding): RecyclerView.ViewHolder(binding.root) {
        init {

            // When the user clicks on a question, the listener is called
            itemView.setOnClickListener {
                listener.invoke(differ.currentList[adapterPosition])
            }
        }
    }

    // DiffUtil is used to calculate the difference between two lists
    // This improves the performance of the recycler view
    private val diffCallBack = object: DiffUtil.ItemCallback<QuestionModel>() {
        override fun areItemsTheSame(oldItem: QuestionModel, newItem: QuestionModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: QuestionModel, newItem: QuestionModel): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionsViewHolder {
        return QuestionsViewHolder(ItemQuestionPreviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: QuestionsViewHolder, position: Int) {
        val question = differ.currentList[position]
        holder.binding.apply {
            val questionNum = (position + 1).toString()
            tvQuestionNumber.text = if (questionNum.length == 1) {
                "0$questionNum"
            } else {
                questionNum
            }
            tvQuestion.text = question.question.questionText
            val source = question.exams?.get(0) + " " + question.previousYearPapers?.get(0)
            tvQuestionSource.text = source
        }
    }

    override fun getItemCount() = differ.currentList.size
}