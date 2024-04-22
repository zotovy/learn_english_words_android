package dev.zotov.features.word_game.word_game_result

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dev.zotov.features.word_game.R
import dev.zotov.features.word_game.databinding.FragmentWordGameResultBinding
import dev.zotov.ui.utils.capitalizeWord
import dev.zotov.ui.utils.setMargin
import dev.zotov.ui.utils.toPx

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
            in 90.0..100.0 -> "Идеально!"
            else -> "Отличная работа!"
        }

        binding.message.text = message
    }

    @SuppressLint("SetTextI18n")
    private fun setupAnswers() {
        val inflater = LayoutInflater.from(requireContext())

        navArgs.result.answers.forEachIndexed { index, answer ->
            val view = inflater.inflate(R.layout.layout_word_result_item, binding.results, false)
            view.findViewById<TextView>(R.id.word_variant_number).text = (index + 1).toString()

            view.findViewById<TextView>(R.id.word_english).text = answer.english.let {
                if (answer.russian != null) "$it – "
                else it
            }.capitalizeWord()

            answer.russian?.let {russian ->
                view.findViewById<TextView>(R.id.word_russian).apply {
                    text = russian.capitalizeWord()

                    val textColor = if (answer.correct) {
                        ContextCompat.getColor(requireContext(), dev.zotov.ui.R.color.success_500)
                    } else {
                        ContextCompat.getColor(requireContext(), dev.zotov.ui.R.color.error_600)
                    }

                    setTextColor(textColor)
                }
            }

            view.setMargin(bottom = requireContext().toPx(24))

            binding.results.addView(view)
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