package dev.zotov.features.word_game.word_game

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.zotov.features.word_game.R
import dev.zotov.features.word_game.databinding.FragmentWordGameBinding
import dev.zotov.features.word_game.ui.GameProgressBarFragment
import dev.zotov.features.word_game.ui.WordVariantView
import dev.zotov.shared.navigation.DeepLinks
import dev.zotov.ui.utils.capitalizeWord
import dev.zotov.ui.utils.toPx
import dev.zotov.words_data.models.Word

@AndroidEntryPoint
class WordGameFragment : Fragment() {
    private var _binding: FragmentWordGameBinding? = null
    private val binding get() = _binding!!

    private val viewModel: WordGameViewModel by viewModels()

    private lateinit var sheetAnimation: Animation

    private var variantsViews = mutableListOf<WordVariantView>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWordGameBinding.inflate(inflater)
        sheetAnimation =
            AnimationUtils.loadAnimation(requireContext(), R.anim.answer_sheet_animation)
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

        binding.correctBottomSheet.continueButton.setOnClickListener {
            viewModel.nextQuestion()
        }

        binding.incorrectBottomSheet.continueButton.setOnClickListener {
            viewModel.nextQuestion()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun handleIdleState(state: WordGameState.Idle) {
        // Game progress bar
        binding.gameProgressBar.getFragment<GameProgressBarFragment>()
            .setActiveIndex(state.currentQuestionIndex)

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

            binding.correctBottomSheet.continueButton.setOnClickListener {
                navigateToResultPage()
            }

            binding.incorrectBottomSheet.continueButton.setOnClickListener {
                navigateToResultPage()
            }
        }

        // Question state
        when (state.currentQuestionState) {
            is WordVariantState.Correct -> showCorrectSheet()
            is WordVariantState.InCorrect -> showIncorrectSheet()
            is WordVariantState.Idle -> hideSheets()
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
        wordVariantView.bind(number, word, WordVariantState.Idle) {
            viewModel.selectVariant(word)
        }
        variantsViews.add(wordVariantView)
        binding.wordsVariants.addView(wordVariantView)
    }

    @SuppressLint("SetTextI18n")
    private fun updateWordVariant(index: Int, word: Word, wordVariantState: WordVariantState) {
        val wordVariantView = variantsViews[index]

        wordVariantView.bind(index, word, wordVariantState) {
            when (wordVariantState) {
                is WordVariantState.Correct,
                is WordVariantState.InCorrect -> showWordInfoDialog(word.english)

                is WordVariantState.Idle -> viewModel.selectVariant(word)
            }
        }
    }

    private fun showCorrectSheet() {
        val dy = requireContext().toPx(20).toFloat()
        binding.correctBottomSheet.root.let {
            it.isVisible = true
            it.animate().setDuration(0).translationY(dy).alpha(0F).start()
            it.animate().setDuration(150).translationY(0F).alpha(1F).start()
        }
    }

    private fun showIncorrectSheet() {
        val dy = requireContext().toPx(20).toFloat()
        binding.incorrectBottomSheet.root.let {
            it.isVisible = true
            it.animate().setDuration(0).translationY(dy).alpha(0F).start()
            it.animate().setDuration(150).translationY(0F).alpha(1F).start()
        }
    }

    private fun hideSheets() {
        binding.correctBottomSheet.root.isVisible = false
        binding.incorrectBottomSheet.root.isVisible = false
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