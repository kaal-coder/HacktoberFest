package io.itsydv.quizapp.questionView

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import io.itsydv.quizapp.R
import io.itsydv.quizapp.Utils
import io.itsydv.quizapp.databinding.FragmentQuestionBinding
import io.itsydv.quizapp.databinding.ItemOptionBinding
import io.itsydv.quizapp.feed.FeedViewModel
import io.itsydv.quizapp.feed.FeedViewModelFactory
import io.itsydv.quizapp.loadData
import io.itsydv.quizapp.models.Option
import io.itsydv.quizapp.models.QuestionModel
import kotlin.properties.Delegates


class QuestionFragment : Fragment() {

    private var _binding: FragmentQuestionBinding? = null
    private val binding get() = _binding!!
    private lateinit var question: QuestionModel
    private lateinit var selectedOption: Option
    private lateinit var selectedView: ItemOptionBinding
    private var answered = false

    private val model by activityViewModels<FeedViewModel> {
        FeedViewModelFactory(requireContext())
    }

    private val args by navArgs<QuestionFragmentArgs>()
    private var streak by Delegates.notNull<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuestionBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ibBack.setOnClickListener {
            findNavController().navigate(QuestionFragmentDirections.actionQuestionFragmentToFeedFragment())
        }

        binding.wvQuestion.clearCache(true)
        binding.wvQuestion.settings.apply {
            allowFileAccess = true
            javaScriptEnabled = true
            builtInZoomControls = false
        }

        val questionNumber = args.questionNumber
        streak = args.streak

        model.questions.observe(viewLifecycleOwner) { questions ->
            if (questions.data != null) {
                if (questionNumber < 1) {
                    binding.ibPrevious.visibility = View.INVISIBLE
                    binding.ibNext.setOnClickListener {
                        val action = QuestionFragmentDirections.actionQuestionFragmentForward(questionNumber + 1, streak)
                        findNavController().navigate(action)
                    }
                } else if (questionNumber > questions.data.size - 2) {
                    binding.ibNext.visibility = View.INVISIBLE
                    binding.ibPrevious.setOnClickListener {
                        val action = QuestionFragmentDirections.actionQuestionFragmentBackward(questionNumber - 1, streak)
                        findNavController().navigate(action)
                    }
                } else {
                    binding.ibNext.setOnClickListener {
                        val action = QuestionFragmentDirections.actionQuestionFragmentForward(questionNumber + 1, streak)
                        findNavController().navigate(action)
                    }
                    binding.ibPrevious.setOnClickListener {
                        val action = QuestionFragmentDirections.actionQuestionFragmentBackward(questionNumber - 1, streak)
                        findNavController().navigate(action)
                    }
                }
                question = questions.data[questionNumber]
                displayQuestion()
                displayOptions()
                binding.btnCheckAnswer.setOnClickListener {
                    checkAnswer()
                }
                binding.includeStreak.btnViewSolution.setOnClickListener {
                    binding.includeStreak.rlStreak.visibility = View.GONE
                }
                binding.includeStreak.btnNextQuestion.setOnClickListener {
                    val action = QuestionFragmentDirections.actionQuestionFragmentForward(questionNumber + 1, streak)
                    findNavController().navigate(action)
                }
            } else {
                Log.e("QuestionFragment", "Error: ${questions.message}")
            }
        }

    }

    private fun checkAnswer() {
        if (selectedOption.isCorrect) {
            selectedView.llOption.backgroundTintList = ContextCompat.getColorStateList(requireContext(),
                R.color.success
            )
            selectedView.tvOptionNumber.backgroundTintList = ContextCompat.getColorStateList(requireContext(),
                R.color.success
            )
            streak++
            if (streak > 2) {
                binding.includeStreak.rlStreak.visibility = View.VISIBLE
                binding.includeStreak.tvStreak.text = getString(R.string.streak_count, streak)
            } else {
                showSuccess()
            }
        } else {
            streak = 0
            question.options.forEachIndexed {index, option ->
                if (option.isCorrect) {
                    val correctView = if (index == 0) binding.option1 else if (index == 1) binding.option2 else if (index == 2) binding.option3 else binding.option4
                    correctView.llOption.backgroundTintList = ContextCompat.getColorStateList(requireContext(),
                        R.color.success
                    )
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        correctView.tvOptionNumber.setTextColor(resources.getColor(R.color.textWhite, null))
                    }
                    correctView.tvOptionNumber.backgroundTintList = ContextCompat.getColorStateList(requireContext(),
                        R.color.success
                    )
                }
            }
            selectedView.llOption.backgroundTintList = ContextCompat.getColorStateList(requireContext(),
                R.color.danger
            )
            selectedView.tvOptionNumber.backgroundTintList = ContextCompat.getColorStateList(requireContext(),
                R.color.danger
            )
        }
        binding.apply {
            btnCheckAnswer.text = getString(R.string.next_question)
            btnCheckAnswer.setOnClickListener {
                val action = QuestionFragmentDirections.actionQuestionFragmentForward(args.questionNumber + 1, streak)
                findNavController().navigate(action)
            }
            option1.llOption.isEnabled = false
            option2.llOption.isEnabled = false
            option3.llOption.isEnabled = false
            option4.llOption.isEnabled = false
            option1.wvOption.isEnabled = false
            option2.wvOption.isEnabled = false
            option3.wvOption.isEnabled = false
            option4.wvOption.isEnabled = false
            tvSolutionText.visibility = View.VISIBLE
            wvSolution.visibility = View.VISIBLE
            loadData(wvSolution, question.solution.solutionText.toString())
        }
        question.attempted = true
        model.updateQuestion(question)
    }

    private fun showSuccess() {
        val snackbar = Snackbar.make(binding.coordinatorLayout, "", Snackbar.LENGTH_LONG)
        val customSnackView: View =
            layoutInflater.inflate(R.layout.layout_snackbar, binding.coordinatorLayout, false)
        snackbar.view.setBackgroundColor(Color.TRANSPARENT)
        val snackbarLayout = snackbar.view as Snackbar.SnackbarLayout
        snackbarLayout.setPadding(0, 0, 0, 0)
        snackbarLayout.addView(customSnackView, 0)
        snackbar.show()
    }

    private fun displayQuestion() {
        binding.tvChapter.text = question.chapters?.joinToString(", ") ?: "General Question"
        val questionNumberAndType = "Q${question.questionIndex!! + 1} (Single Choice)"
        val source = question.exams?.get(0) + " " + question.previousYearPapers?.get(0)
        binding.tvQuestionSource.text = source
        binding.tvQuestionNumberAndType.text = questionNumberAndType
        loadData(binding.wvQuestion, Utils.JS_FILES + question.question.questionText!!)
    }


    @SuppressLint("ClickableViewAccessibility")
    private fun displayOptions() {
        binding.apply {
            createOption(option1, "A", question.options[0]).also {
                option1.llOption.setOnClickListener {
                    selectOption(option1, question.options[0])
                    deselectOption(arrayListOf(option2, option3, option4))
                }
                option1.wvOption.setOnTouchListener { _, _ ->
                    selectOption(option1, question.options[0])
                    deselectOption(arrayListOf(option2, option3, option4))
                    true
                }
            }
            createOption(option2, "B", question.options[1]).also {
                option2.llOption.setOnClickListener {
                    selectOption(option2, question.options[1])
                    deselectOption(arrayListOf(option1, option3, option4))
                }
                option2.wvOption.setOnTouchListener { _, _ ->
                    selectOption(option2, question.options[1])
                    deselectOption(arrayListOf(option1, option3, option4))
                    true
                }
            }
            createOption(option3, "C", question.options[2]).also {
                option3.llOption.setOnClickListener {
                    selectOption(option3, question.options[2])
                    deselectOption(arrayListOf(option1, option2, option4))
                }
                option3.wvOption.setOnTouchListener { _, _ ->
                    selectOption(option3, question.options[2])
                    deselectOption(arrayListOf(option1, option2, option4))
                    true
                }
            }
            createOption(option4, "D", question.options[3]).also {
                option4.llOption.setOnClickListener {
                    selectOption(option4, question.options[3])
                    deselectOption(arrayListOf(option1, option2, option3))
                }
                option4.wvOption.setOnTouchListener { _, _ ->
                    selectOption(option4, question.options[3])
                    deselectOption(arrayListOf(option1, option2, option3))
                    true
                }
            }
        }
    }

    private fun selectOption(view: ItemOptionBinding, option: Option) {
        selectedOption = option
        selectedView = view
        if (!answered) {
            binding.btnCheckAnswer.isEnabled = true
            binding.btnCheckAnswer.backgroundTintList = ContextCompat.getColorStateList(requireContext(),
                R.color.primaryColor
            )
        }
        answered = true
        view.llOption.backgroundTintList = ContextCompat.getColorStateList(requireContext(),
            R.color.primaryColor
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            view.tvOptionNumber.setTextColor(resources.getColor(R.color.textWhite, null))
        }
        view.tvOptionNumber.backgroundTintList = ContextCompat.getColorStateList(requireContext(),
            R.color.primaryColor
        )
    }

    private fun deselectOption(options: ArrayList<ItemOptionBinding>) {
        options.forEach { option ->
            option.llOption.backgroundTintList = ContextCompat.getColorStateList(requireContext(),
                R.color.shimmerDark
            )
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                option.tvOptionNumber.setTextColor(resources.getColor(R.color.textColor, null))
            }
            option.tvOptionNumber.backgroundTintList = ContextCompat.getColorStateList(requireContext(),
                R.color.textWhite
            )
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun createOption(view: ItemOptionBinding, text: String, option: Option) {
        view.apply {
            tvOptionNumber.text = text
            wvOption.clearCache(true)
            wvOption.settings.apply {
                allowFileAccess = true
                javaScriptEnabled = true
                builtInZoomControls = false
                allowContentAccess = true
                cacheMode = WebSettings.LOAD_CACHE_ONLY
            }
            loadData(wvOption, Utils.JS_FILES + option.optionText!!)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}