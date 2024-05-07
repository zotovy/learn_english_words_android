package dev.zotov.features.word_game.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import dev.zotov.features.word_game.databinding.ProgressBarViewBinding

class ProgressBarView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding = ProgressBarViewBinding.inflate(
        LayoutInflater.from(context),
        this,
        true,
    )

    private var activeIndex = -1

    private fun increment() {
        if (activeIndex != 4) {
            activeIndex++
            val pillItem = binding.root.getChildAt(activeIndex) as PillItem
            pillItem.animateForward()
        }
    }

    private fun decrement() {
        if (activeIndex != -1) {
            val pillItem = binding.root.getChildAt(activeIndex) as PillItem
            activeIndex--
            pillItem.animateBackward()
        }
    }

    fun changeActiveIndex(index: Int) {
        for (i in activeIndex..<index) {
            increment()
        }
        for (i in activeIndex downTo index + 1) {
            decrement()
        }
    }
}