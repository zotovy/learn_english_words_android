package dev.zotov.features.word_game.word_game

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.transition.MaterialSharedAxis
import dagger.hilt.android.AndroidEntryPoint
import dev.zotov.features.word_game.databinding.FragmentWordGameBinding
import dev.zotov.features.word_game.ui.WordVariantView
import dev.zotov.shared.navigation.DeepLinks
import dev.zotov.ui.utils.capitalizeWord
import dev.zotov.words_data.models.Word

@AndroidEntryPoint
class WordGameFragment : Fragment() {
    private var _binding: FragmentWordGameBinding? = null
    private val binding get() = _binding!!

    private val viewModel: WordGameViewModel by viewModels()

    private var variantsViews = mutableListOf<WordVariantView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialSharedAxis(MaterialSharedAxis.X, false)
        exitTransition = MaterialSharedAxis(MaterialSharedAxis.X,  true)
    }

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

        binding.correctBottomSheet.setOnContinueClickListener {
            viewModel.nextQuestion()
        }

        binding.incorrectBottomSheet.setOnContinueClickListener {
            viewModel.nextQuestion()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun handleIdleState(state: WordGameState.Idle) {
        // Game progress bar
        binding.gameProgressBar.changeActiveIndex(state.currentQuestionIndex)

        // Target word
        state.currentQuestion.targetWord.let {
            binding.targetWord.text = it.english.capitalizeWord()
            binding.targetWordConjunction.text = it.conjunction
        }

        // Variants
        state.currentQuestion.variants.forEachIndexed { index, word ->
            if (binding.wordsVariants.childCount < index + 1) {
                createWordVariantView(index, word)
            } else {
                updateWordVariant(index, word, state.currentQuestionState)
            }
        }

        if (state.isLast) {
            binding.skipButton.setOnClickListener {
                viewModel.skipQuestion()
                navigateToResultPage()
            }

            binding.correctBottomSheet.setOnContinueClickListener {
                navigateToResultPage()
            }

            binding.incorrectBottomSheet.setOnContinueClickListener {
                navigateToResultPage()
            }
        }

        // Question state
        when (state.currentQuestionState) {
            is WordVariantState.Correct -> binding.correctBottomSheet.show()
            is WordVariantState.InCorrect -> binding.incorrectBottomSheet.show()
            is WordVariantState.Idle -> {
                binding.correctBottomSheet.hide()
                binding.incorrectBottomSheet.hide()
            }
        }

        binding.progressBar.isVisible = false
        binding.errorMessage.isVisible = false

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
        binding.wordsVariants.isVisible = false
        binding.skipButton.isVisible = false
        binding.errorMessage.isVisible = false
    }

    private fun handleErrorState(state: WordGameState.Error) {
        binding.progressBar.isVisible = false
        binding.gameProgressBar.isVisible = false
        binding.targetWord.isVisible = false
        binding.targetWordConjunction.isVisible = false
        binding.wordsVariants.isVisible = false
        binding.skipButton.isVisible = false

        binding.errorMessage.text = state.message
        binding.errorMessage.isVisible = true
    }

    private fun createWordVariantView(number: Int, word: Word) {
        val wordVariantView = WordVariantView(requireContext())
        wordVariantView.bind(number, word)
        wordVariantView.setOnClick { viewModel.selectVariant(word) }
        variantsViews.add(wordVariantView)
        binding.wordsVariants.addView(wordVariantView)
    }

    @SuppressLint("SetTextI18n")
    private fun updateWordVariant(index: Int, word: Word, wordVariantState: WordVariantState) {
        val wordVariantView = variantsViews[index]

        when (wordVariantState) {
            is WordVariantState.Correct -> {
                wordVariantView.setOnClick { showWordInfoDialog(word.english) }
                if (wordVariantState.word == word) {
                    wordVariantView.changeState(WordVariantView.State.CORRECT)
                }
            }

            is WordVariantState.InCorrect -> {
                wordVariantView.setOnClick { showWordInfoDialog(word.english) }
                if (wordVariantState.word == word) {
                    wordVariantView.changeState(WordVariantView.State.INCORRECT)
                }
            }

            is WordVariantState.Idle -> {
                wordVariantView.changeState(WordVariantView.State.IDLE)
                wordVariantView.bind(index, word)
                wordVariantView.setOnClick { viewModel.selectVariant(word) }
            }
        }
    }

    private fun navigateToResultPage() {
        viewModel.gameResult?.let { gameResult ->
            findNavController().navigate(
                WordGameFragmentDirections.actionWordGameFragmentToWordGameResultFragment(
                    result = gameResult
                )
            )
        }
    }

    private fun showWordInfoDialog(word: String) {
        val request = DeepLinks.wordInfoDialog(word)
        findNavController().navigate(request)
    }
}