package dev.zotov.features.word_game.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import dev.zotov.features.word_game.databinding.LayoutIncorrectAnswerBinding
import dev.zotov.ui.utils.toPx

class InCorrectBottomSheetView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
) : ConstraintLayout(context, attrs, defStyle) {

    private val binding = LayoutIncorrectAnswerBinding.inflate(
        LayoutInflater.from(context),
        this,
        true,
    )

    private val dy = 40.toPx().toFloat()

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        y = dy
        alpha = 0F
        isVisible = false
    }

    fun setOnContinueClickListener(onClick: () -> Unit) {
        binding.continueButton.setOnClickListener {
            onClick()
        }
    }

    fun show() {
        isVisible = true
        animate().apply {
            setInterpolator(AccelerateDecelerateInterpolator())
            setDuration(150)
            translationY(0F)
            alpha(1F)
            start()
        }
    }

    fun hide() {
        animate().apply {
            setInterpolator(AccelerateDecelerateInterpolator())
            setDuration(150)
            translationY(dy)
            alpha(0F)
            withEndAction {
                isVisible = false
            }
            start()
        }
    }
}