package dev.zotov.features.word_info.components

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import dev.zotov.features.word_info.databinding.LayoutMeaningDefinitionItemBinding
import dev.zotov.words_data.models.WordMeaningDefinition

internal class WordMeaningDefinitionView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding =
        LayoutMeaningDefinitionItemBinding.inflate(LayoutInflater.from(context), this, true)

    @SuppressLint("SetTextI18n")
    fun bind(number: Int, model: WordMeaningDefinition) {
        binding.number.text = "${number}."
        binding.wordMeaning.text = model.definition
    }
}