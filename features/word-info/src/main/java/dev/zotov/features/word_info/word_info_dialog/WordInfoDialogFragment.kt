package dev.zotov.features.word_info.word_info_dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import dev.zotov.features.word_info.databinding.FragmentWordInfoDialogBinding
import dev.zotov.ui.utils.capitalizeWord

@AndroidEntryPoint
internal class WordInfoDialogFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentWordInfoDialogBinding? = null
    private val binding: FragmentWordInfoDialogBinding get() = _binding!!

    private val arguments: WordInfoDialogFragmentArgs by navArgs()

    private val viewModel: WordInfoDialogViewModel by viewModels()

    private lateinit var adapter: WordMeaningsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWordInfoDialogBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.initialize(arguments.word)

        adapter = WordMeaningsAdapter()

        binding.recyclerWordDefinitions.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerWordDefinitions.adapter = adapter

        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is WordInfoDialogState.Loading -> handleLoadingState()
                is WordInfoDialogState.Idle -> handleIdleState(it)
                is WordInfoDialogState.Error -> handleErrorState(it)
            }
        }
    }

    private fun handleLoadingState() {
        binding.apply {
            progressBar.isVisible = true

            word.isVisible = false
            phonetic.isVisible = false
            recyclerWordDefinitions.isVisible = false
        }
    }

    private fun handleIdleState(state: WordInfoDialogState.Idle) {
        adapter.meanings = state.wordInfo.meanings

        binding.apply {
            word.apply {
                text = state.wordInfo.word.capitalizeWord()
                isVisible = true
            }

            state.wordInfo.phonetics?.let { phonetic ->
                binding.phonetic.bind(
                    phonetic = phonetic,
                    onPlayCallback = { viewModel.playWordPronunciation() }
                )

                binding.phonetic.isVisible = true
            }

            progressBar.isVisible = false
            recyclerWordDefinitions.isVisible = true
        }
    }

    private fun handleErrorState(state: WordInfoDialogState.Error) {
        binding.apply {
            word.text = state.word.capitalizeWord()
            errorText.text = state.message
            errorText.isVisible = true
            word.isVisible = true

            phonetic.isVisible = false
            recyclerWordDefinitions.isVisible = false
            progressBar.isVisible = false
        }
    }
}