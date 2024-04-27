package dev.zotov.features.word_info.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import dev.zotov.features.word_info.databinding.LayoutWordPhoneticBinding
import dev.zotov.words_data.models.WordPhonetic

typealias onPlayCallback = () -> Unit

internal class WordPhoneticView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
): ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = LayoutWordPhoneticBinding.inflate(
        LayoutInflater.from(context),
        this,
        true,
    )

    fun bind(phonetic: WordPhonetic, onPlayCallback: onPlayCallback) {
        binding.wordPhonetic.text = phonetic.text

        if (phonetic.audio != null) {
            binding.playPhoneticIcon.isVisible = true
            binding.root.setOnClickListener {
                onPlayCallback()
            }
        }
    }
}