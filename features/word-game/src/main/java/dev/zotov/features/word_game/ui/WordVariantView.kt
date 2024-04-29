package dev.zotov.features.word_game.ui

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import dev.zotov.features.word_game.R
import dev.zotov.features.word_game.databinding.LayoutWordVariantBinding
import dev.zotov.features.word_game.word_game.WordVariantState
import dev.zotov.ui.utils.capitalizeWord
import dev.zotov.ui.utils.setMargin
import dev.zotov.ui.utils.toPx
import dev.zotov.words_data.models.Word

internal class WordVariantView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): LinearLayout(context, attrs, defStyleAttr) {

    private val binding = LayoutWordVariantBinding.inflate(
        LayoutInflater.from(context),
        this,
        true,
    )

    @SuppressLint("SetTextI18n")
    fun bind(index: Int, word: Word, wordVariantState: WordVariantState, onClick: () -> Unit) {
        val variantNumberTextView = binding.wordVariantNumber
        val variantTextView = binding.wordVariant

        binding.root.setOnClickListener {
            onClick()
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

        binding.root.background =
            ContextCompat.getDrawable(context, wordVariantDrawable)
        variantNumberTextView.background =
            ContextCompat.getDrawable(context, wordVariantNumberDrawable)
        variantNumberTextView.setTextColor(
            ContextCompat.getColor(
                context,
                wordVariantNumberColor
            )
        )
        variantTextView.setTextColor(ContextCompat.getColor(context, wordTextColor))
        binding.root.setMargin(bottom = context.toPx(16))
    }
}