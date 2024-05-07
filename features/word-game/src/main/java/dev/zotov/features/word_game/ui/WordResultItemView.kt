package dev.zotov.features.word_game.ui

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import dev.zotov.features.word_game.databinding.LayoutWordResultItemBinding
import dev.zotov.features.word_game.models.WordGameResult
import dev.zotov.ui.utils.capitalizeWord
import dev.zotov.ui.utils.setMargin
import dev.zotov.ui.utils.toPx

internal class WordResultItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding = LayoutWordResultItemBinding.inflate(
        LayoutInflater.from(context),
        this,
        true,
    )

    fun bind(index: Int, answer: WordGameResult.Answer) {
        @SuppressLint("SetTextI18n")
        binding.wordVariantNumber.text = (index + 1).toString()

        binding.wordEnglish.text = answer.english.let {
            if (answer.russian != null) "$it – "
            else it
        }.capitalizeWord()


        answer.russian?.let { russian ->
            binding.wordRussian.apply {
                text = russian.capitalizeWord()

                val textColor = if (answer.correct) {
                    ContextCompat.getColor(context, dev.zotov.ui.R.color.success_500)
                } else {
                    ContextCompat.getColor(context, dev.zotov.ui.R.color.error_600)
                }

                setTextColor(textColor)
            }
        }

        binding.root.setMargin(bottom = 24.toPx())
    }
}