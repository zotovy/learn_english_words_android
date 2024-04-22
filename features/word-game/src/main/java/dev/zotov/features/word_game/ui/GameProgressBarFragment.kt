package dev.zotov.features.word_game.ui

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import dev.zotov.features.word_game.R
import dev.zotov.features.word_game.databinding.FragmentGameProgressBarBinding
import dev.zotov.ui.utils.setMargin
import dev.zotov.ui.utils.toPx
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class GameProgressBarFragment : Fragment() {
    private var itemCount: Int = 0

    private var _binding: FragmentGameProgressBarBinding? = null
    private val binding: FragmentGameProgressBarBinding get() = _binding!!

    private val items = mutableListOf<View>()

    private var activeIndex = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameProgressBarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemCount = arguments?.getInt("itemCount") ?: 3

        attachItemsToView()
    }

    override fun onInflate(context: Context, attrs: AttributeSet, savedInstanceState: Bundle?) {
        super.onInflate(context, attrs, savedInstanceState)
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.GameProgressBarFragment)
        attributes.getString(R.styleable.GameProgressBarFragment_itemCount)?.let { argumentValue ->
            arguments = bundleOf("itemCount" to argumentValue.toInt())
        }
        attributes.recycle()
    }

    private fun attachItemsToView() {
        for (i in 1..itemCount) {
            val item = LayoutInflater.from(requireContext()).inflate(
                R.layout.layout_game_progress_bar_item,
                binding.root,
                false
            )

            // add spacing
            if (i != itemCount) {
                item.setMargin(end = requireContext().toPx(4))
            }

            binding.root.addView(item)
            items.add(item.findViewById(R.id.active_bar))

            val activeBar = item.findViewById<View>(R.id.active_bar)

            // first item initially active
            if (i != 1) {
                activeBar.animate().scaleX(0F).setDuration(0)
            }
        }
    }

    private fun fillItem(index: Int) {
        val item = items[index]
        MainScope().launch {
            item.animate().scaleX(1F).setDuration(500)
        }
    }

    private fun refuseItem(index: Int) {
        val item = items[index]
        MainScope().launch {
            item.animate().scaleX(0F).setDuration(500)
        }
    }

    fun setActiveIndex(index: Int) {
        lifecycleScope.launch {
            for (i in (activeIndex + 1)..index) {
                fillItem(i)
                delay(750)
            }
            for (i in activeIndex downTo (index + 1)) {
                refuseItem(i)
                delay(750)
            }
            activeIndex = index
        }
    }
}