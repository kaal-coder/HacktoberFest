package io.itsydv.quizapp.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import io.itsydv.quizapp.R
import io.itsydv.quizapp.Resource
import io.itsydv.quizapp.databinding.FragmentFeedBinding


class FeedFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private var _binding: FragmentFeedBinding? = null
    private val binding get() = _binding!!

    private lateinit var allQuestionsAdapter: AllQuestionsAdapter

    private val model by activityViewModels<FeedViewModel> {
        FeedViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFeedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        val items = arrayOf("Not Attempted", "Attempted")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, items)
        binding.spinnerFilter.adapter = adapter
        binding.spinnerFilter.onItemSelectedListener = this

        model.questions.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Loading -> {
                    binding.rvQuestions.visibility = View.GONE
                    binding.shimmerViewContainer.visibility = View.VISIBLE
                    binding.shimmerViewContainer.startShimmer()
                }
                is Resource.Success -> {
                    binding.shimmerViewContainer.stopShimmer()
                    binding.shimmerViewContainer.visibility = View.GONE

                    if (response.data.isNullOrEmpty()) {
                        binding.rvQuestions.visibility = View.GONE
                        binding.llNothingFound.visibility = View.VISIBLE
                        binding.btnClearFilter.setOnClickListener {
                            binding.spinnerFilter.setSelection(0)
                            binding.llNothingFound.visibility = View.GONE
                        }
                    } else {
                        binding.llNothingFound.visibility = View.GONE
                        binding.rvQuestions.visibility = View.VISIBLE
                        binding.tvNumOfQuestions.text = getString(
                            R.string.number_of_questions_and_chapter_feedback,
                            response.data.size)
                        allQuestionsAdapter.differ.submitList(response.data)
                    }
                }
                is Resource.Error -> {
                    binding.rvQuestions.visibility = View.GONE
                    binding.shimmerViewContainer.stopShimmer()
                    binding.shimmerViewContainer.visibility = View.GONE
                }
            }
        }

    }

    private fun setupRecyclerView() {
        allQuestionsAdapter = AllQuestionsAdapter{
            val action = FeedFragmentDirections.feedFragmentToQuestionFragment(it.questionIndex!!, 0)
            findNavController().navigate(action)
        }
        binding.rvQuestions.apply{
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
            adapter = allQuestionsAdapter
        }
    }

    // OnItemSelected Listener for the spinner
    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        when (pos) {
            0 -> {
                binding.llNothingFound.visibility = View.GONE
                model.getUnAttemptedQuestions()
            }
            1 -> model.getAttemptedQuestions()
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>) {}

    override fun onResume() {
        super.onResume()
        binding.shimmerViewContainer.startShimmer()
    }

    // Pausing Shimmer Effect to not waste resources
    override fun onPause() {
        binding.shimmerViewContainer.stopShimmer()
        super.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}