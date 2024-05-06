package dev.zotov.features.word_game.word_game_result

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dev.zotov.features.word_game.databinding.FragmentWordGameResultBinding
import dev.zotov.features.word_game.ui.WordResultItemView

class WordGameResultFragment : Fragment() {

    private val navArgs: WordGameResultFragmentArgs by navArgs()

    private var _binding: FragmentWordGameResultBinding? = null
    private val binding: FragmentWordGameResultBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWordGameResultBinding.inflate(inflater)
        setupView()
        return binding.root
    }

    private fun setupView() {
        setupScore()
        setupMessage()
        setupAnswers()
        setupTryAgainButton()
    }

    @SuppressLint("SetTextI18n")
    private fun setupScore() {
        binding.score.text = "${navArgs.result.score} / ${navArgs.result.maxScore}"
    }

    private fun setupMessage() {
        val percent = navArgs.result.let { it.score.toFloat() / it.maxScore * 100 }

        val message = when (percent) {
            in 0.0..50.0 -> "Ты можешь лучше!"
            in 50.0..70.0 -> "Круто!"
            in 70.0..80.0 -> "Ого! Почти максимум!"
            in 90.0..100.0 -> "Ты просто машина..."
            else -> "Отличная работа!"
        }

        binding.message.text = message
    }

    @SuppressLint("SetTextI18n")
    private fun setupAnswers() {
        navArgs.result.answers.forEachIndexed { index, answer ->
            val wordResultItemView = WordResultItemView(requireContext())
            wordResultItemView.bind(index, answer)
            binding.results.addView(wordResultItemView)
        }
    }

    private fun setupTryAgainButton() {
        binding.btnTryAgain.setOnClickListener {
            findNavController().navigate(
                WordGameResultFragmentDirections.actionWordGameResultFragmentToWordGameFragment()
            )
        }
    }
}