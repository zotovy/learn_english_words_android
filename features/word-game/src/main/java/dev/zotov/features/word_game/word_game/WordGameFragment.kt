package dev.zotov.features.word_game.word_game

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.zotov.features.word_game.R
import dev.zotov.features.word_game.databinding.FragmentWordGameBinding
import dev.zotov.features.word_game.ui.GameProgressBarFragment
import dev.zotov.ui.utils.setMargin
import dev.zotov.ui.utils.toPx
import dev.zotov.words_data.models.Word

@AndroidEntryPoint
class WordGameFragment : Fragment() {
    private var _binding: FragmentWordGameBinding? = null
    private val binding get() = _binding!!

    private val viewModel: WordGameViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWordGameBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.initialize()

        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is WordGameState.Loading -> handleLoadingState()
                is WordGameState.Idle -> handleIdleState(it)
                is WordGameState.Error -> handleErrorState(it)
            }
        }

        binding.skipButton.setOnClickListener {
            viewModel.skipQuestion()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun handleIdleState(state: WordGameState.Idle) {
        // Game progress bar
        binding.gameProgressBar.getFragment<GameProgressBarFragment>()
            .setActiveIndex(state.currentQuestionIndex)

        // Target word
        state.currentQuestion.targetWord.let {
            binding.targetWord.text = it.english
            binding.targetWordConjunction.text = it.conjunction
        }

        // Variants
        state.currentQuestion.variants.forEachIndexed { index, word ->
            if (binding.wordsVariants.childCount < index + 1) {
                createWordVariantView(index + 1, word)
            } else {
                updateWordVariant(index, word, state.currentQuestionState)
            }
        }

        if (state.isLast) {
            binding.skipButton.setOnClickListener {
                viewModel.skipQuestion()

                viewModel.gameResult?.let { gameResult ->
                    findNavController().navigate(
                        WordGameFragmentDirections.actionWordGameFragmentToWordGameResultFragment(
                            result = gameResult
                        )
                    )
                }
            }
        }

        binding.progressBar.isVisible = false
        binding.gameProgressBar.isVisible = true
        binding.targetWord.isVisible = true
        binding.targetWordConjunction.isVisible = true
        binding.wordsVariants.isVisible = true
        binding.skipButton.isVisible = true
    }

    private fun handleLoadingState() {
        binding.progressBar.isVisible = true
        binding.gameProgressBar.isVisible = false
        binding.targetWord.isVisible = false
        binding.targetWordConjunction.isVisible = false
    }

    private fun handleErrorState(state: WordGameState.Error) {

    }

    private fun createWordVariantView(number: Int, word: Word) {
        val variantView = LayoutInflater.from(requireContext()).inflate(
            R.layout.layout_word_variant,
            binding.root,
            false
        )

        variantView.setOnClickListener {
            viewModel.selectVariant(word)
        }

        variantView.findViewById<TextView>(R.id.word_variant_number).text = number.toString()
        variantView.findViewById<TextView>(R.id.word_variant).text = word.russian
        variantView.setMargin(bottom = requireContext().toPx(16))

        binding.wordsVariants.addView(variantView)
    }

    private fun updateWordVariant(index: Int, word: Word, wordVariantState: WordVariantState) {
        val wordVariantView = binding.wordsVariants.getChildAt(index)
        val variantNumberTextView =
            wordVariantView.findViewById<TextView>(R.id.word_variant_number)
        val variantTextView = wordVariantView.findViewById<TextView>(R.id.word_variant)

        wordVariantView.setOnClickListener {
            Log.d("setOnClickListener", word.toString())
            viewModel.selectVariant(word)
        }

        variantNumberTextView.text = (index + 1).toString()
        variantTextView.text = word.russian

        val wordVariantDrawable = wordVariantState.let {
            when {
                it is WordVariantState.Correct && it.word == word -> R.drawable.shape_word_variant_container_correct
                it is WordVariantState.InCorrect && it.word == word -> R.drawable.shape_word_variant_container_incorrect
                else -> R.drawable.shape_word_variant_container
            }
        }

        val wordVariantNumberDrawable = wordVariantState.let {
            when {
                it is WordVariantState.Correct && it.word == word -> R.drawable.shape_word_number_correct
                it is WordVariantState.InCorrect && it.word == word -> R.drawable.shape_word_number_incorrect
                else -> R.drawable.shape_word_number
            }
        }

        val wordVariantNumberColor = wordVariantState.let {
            when {
                it is WordVariantState.Correct && it.word == word -> R.color.white
                it is WordVariantState.InCorrect && it.word == word -> R.color.white
                else -> dev.zotov.ui.R.color.indigo_900
            }
        }

        val wordTextColor = wordVariantState.let {
            when {
                it is WordVariantState.Correct && it.word == word -> dev.zotov.ui.R.color.success_500
                it is WordVariantState.InCorrect && it.word == word -> dev.zotov.ui.R.color.error_600
                else -> dev.zotov.ui.R.color.gray_700
            }
        }

        wordVariantView.background =
            ContextCompat.getDrawable(requireContext(), wordVariantDrawable)
        variantNumberTextView.background =
            ContextCompat.getDrawable(requireContext(), wordVariantNumberDrawable)
        variantNumberTextView.setTextColor(
            ContextCompat.getColor(
                requireContext(),
                wordVariantNumberColor
            )
        )
        variantTextView.setTextColor(ContextCompat.getColor(requireContext(), wordTextColor))
    }
}