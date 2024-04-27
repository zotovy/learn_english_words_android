package dev.zotov.features.word_game.word_game

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.zotov.features.word_game.R
import dev.zotov.features.word_game.databinding.FragmentWordGameBinding
import dev.zotov.features.word_game.ui.GameProgressBarFragment
import dev.zotov.ui.utils.capitalizeWord
import dev.zotov.ui.utils.setMargin
import dev.zotov.ui.utils.toPx
import dev.zotov.words_data.models.Word

@AndroidEntryPoint
class WordGameFragment : Fragment() {
    private var _binding: FragmentWordGameBinding? = null
    private val binding get() = _binding!!

    private val viewModel: WordGameViewModel by viewModels()

    private lateinit var sheetAnimation: Animation

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
                createWordVariantView(index + 1, word)
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
        val variantView = LayoutInflater.from(requireContext()).inflate(
            R.layout.layout_word_variant,
            binding.root,
            false
        )

        variantView.setOnClickListener {
            viewModel.selectVariant(word)
        }

        variantView.findViewById<TextView>(R.id.word_variant_number).text = number.toString()
        variantView.findViewById<TextView>(R.id.word_variant).text = word.russian.capitalizeWord()
        variantView.setMargin(bottom = requireContext().toPx(16))

        binding.wordsVariants.addView(variantView)
    }

    @SuppressLint("SetTextI18n")
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
        variantTextView.text = word.russian.capitalizeWord()

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

        wordVariantView.setOnClickListener {
            showWordInfoDialog(word.english)
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
        val request = NavDeepLinkRequest.Builder
            .fromUri("android-app://dev.zotov.learn_english_words/features/word-info/word-info-dialog?word=${word}".toUri())
            .build()
        findNavController().navigate(request)
    }
}