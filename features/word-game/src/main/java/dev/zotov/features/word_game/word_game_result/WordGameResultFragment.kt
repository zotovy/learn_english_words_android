package dev.zotov.features.word_game.word_game_result

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import dev.zotov.features.word_game.databinding.FragmentWordGameResultBinding

class WordGameResultFragment : Fragment() {

    private val navArgs: WordGameResultFragmentArgs by navArgs()

    var _binding: FragmentWordGameResultBinding? = null
    val binding: FragmentWordGameResultBinding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWordGameResultBinding.inflate(inflater)

        binding.result.text = navArgs.result.toString()

        return binding.root
    }
}