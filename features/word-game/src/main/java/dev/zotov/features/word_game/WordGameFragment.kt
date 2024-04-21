package dev.zotov.features.word_game

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.zotov.features.word_game.databinding.FragmentWordGameBinding
import dev.zotov.features.word_game.ui.GameProgressBarFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class WordGameFragment : Fragment() {
    private var _binding: FragmentWordGameBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWordGameBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CoroutineScope(Dispatchers.IO).launch {
            delay(3500)
            val fragment = childFragmentManager.findFragmentById(R.id.game_progress_bar) as GameProgressBarFragment?
            if (fragment != null) {
                fragment.setActiveIndex(4)
                delay(5000)
                fragment.setActiveIndex(-1)
            } else {
                Log.d("WordGameFragment", "FRAGMENT IS NULLLLLL")
            }
        }
    }
}