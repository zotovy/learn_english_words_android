package dev.zotov.features.word_game.ui

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import dev.zotov.features.word_game.R
import dev.zotov.features.word_game.databinding.LayoutWordVariantBinding
import dev.zotov.ui.utils.capitalizeWord
import dev.zotov.ui.utils.setMargin
import dev.zotov.ui.utils.toPx
import dev.zotov.words_data.models.Word

internal class WordVariantView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding = LayoutWordVariantBinding.inflate(
        LayoutInflater.from(context),
        this,
        true,
    )

    private var strokeColor = dev.zotov.ui.R.color.gray_300
    private var primaryColor = dev.zotov.ui.R.color.indigo_25
    private var numberTextColor = dev.zotov.ui.R.color.indigo_900
    private var textColor = dev.zotov.ui.R.color.gray_700

    private var strokeAnimator: ValueAnimator? = null
    private var primaryColorAnimator: ValueAnimator? = null
    private var numberTextAnimator: ValueAnimator? = null
    private var textColorAnimator: ValueAnimator? = null

    @SuppressLint("SetTextI18n")
    fun bind(index: Int, word: Word) {
        val variantNumberTextView = binding.wordVariantNumber
        val variantTextView = binding.wordVariant
        variantNumberTextView.text = (index + 1).toString()
        variantTextView.text = word.russian.capitalizeWord()

        binding.root.background =
            ContextCompat.getDrawable(context, R.drawable.shape_word_variant_container)
        variantNumberTextView.background =
            ContextCompat.getDrawable(context, R.drawable.shape_word_number)
        variantNumberTextView.setTextColor(
            ContextCompat.getColor(
                context,
                dev.zotov.ui.R.color.indigo_900
            )
        )
        variantTextView.setTextColor(ContextCompat.getColor(context, dev.zotov.ui.R.color.gray_700))
        binding.root.setMargin(bottom = 16.toPx())
    }

    fun setOnClick(onClick: () -> Unit) {
        binding.root.setOnClickListener {
            onClick()
        }
    }

    fun changeState(state: State) {
        when (state) {
            State.IDLE -> {
                animateStrokeColorChange(dev.zotov.ui.R.color.gray_300)
                animatePrimaryColorChange(dev.zotov.ui.R.color.indigo_25)
                animateNumberTextColorChange(dev.zotov.ui.R.color.indigo_900)
                animateTextColorChange(dev.zotov.ui.R.color.gray_700)
            }

            State.CORRECT -> {
                animateStrokeColorChange(dev.zotov.ui.R.color.success_300)
                animatePrimaryColorChange(dev.zotov.ui.R.color.success_500)
                animateNumberTextColorChange(dev.zotov.ui.R.color.white)
                animateTextColorChange(dev.zotov.ui.R.color.success_500)
            }

            State.INCORRECT -> {
                animateStrokeColorChange(dev.zotov.ui.R.color.error_300)
                animatePrimaryColorChange(dev.zotov.ui.R.color.error_600)
                animateNumberTextColorChange(dev.zotov.ui.R.color.white)
                animateTextColorChange(dev.zotov.ui.R.color.error_600)
            }
        }
    }

    private fun animateStrokeColorChange(color: Int) {
        val containerBg = (binding.root.background as GradientDrawable)
        containerBg.mutate()

        strokeAnimator?.cancel()
        strokeAnimator = animateColorChange(strokeColor, color) {
            containerBg.setStroke(1.toPx(), it)
        }
    }

    private fun animatePrimaryColorChange(color: Int) {
        val backgroundDrawable = binding.wordVariantNumber.background as GradientDrawable
        backgroundDrawable.mutate()

        primaryColorAnimator?.cancel()
        primaryColorAnimator = animateColorChange(primaryColor, color) {
            backgroundDrawable.setColor(it)
        }
    }

    private fun animateNumberTextColorChange(color: Int) {
        numberTextAnimator?.cancel()
        numberTextAnimator = animateColorChange(numberTextColor, color) {
            binding.wordVariantNumber.setTextColor(it)
        }
    }

    private fun animateTextColorChange(color: Int) {
        textColorAnimator?.cancel()
        textColorAnimator = animateColorChange(textColor, color) {
            binding.wordVariant.setTextColor(it)
        }
    }

    private fun animateColorChange(
        from: Int,
        to: Int,
        onUpdate: (color: Int) -> Unit
    ): ValueAnimator {

        return ValueAnimator.ofArgb(
            ContextCompat.getColor(context, from),
            ContextCompat.getColor(context, to)
        ).apply {
            setDuration(200)
            addUpdateListener { valueAnimator ->
                onUpdate(valueAnimator.getAnimatedValue() as Int)
            }
            start()
        }
    }

    enum class State {
        IDLE,
        CORRECT,
        INCORRECT
    }
}