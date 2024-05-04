package dev.zotov.features.word_info.word_info_dialog

import android.app.ActionBar.LayoutParams
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import dev.zotov.features.word_info.components.WordMeaningDefinitionView
import dev.zotov.features.word_info.databinding.LayoutWordMeaningItemBinding
import dev.zotov.features.word_info.models.WordMeaningUiModel
import dev.zotov.ui.utils.toPx

internal class WordMeaningsAdapter :
    RecyclerView.Adapter<WordMeaningsAdapter.WordMeaningViewHolder>() {
    var meanings: List<WordMeaningUiModel> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = meanings.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordMeaningViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutWordMeaningItemBinding.inflate(inflater)
        return WordMeaningViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WordMeaningViewHolder, position: Int) {
        val meaning = meanings[position]
        val context = holder.itemView.context

        with(holder.binding) {
            wordRepeat.text = meaning.word
            speechOfText.text = meaning.partOfSpeech

            meaning.definitions.forEachIndexed { index, definition ->
                val definitionView = WordMeaningDefinitionView(context)
                definitionView.bind(index + 1, definition)

                val marginBottom = if (index == meaning.definitions.size - 1) 24 else 16

                definitionView.layoutParams = LinearLayout.LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT,
                ).apply {
                    setMargins(0, 0, 0, context.toPx(marginBottom))
                }

                meaningsContainer.addView(definitionView)
            }
        }
    }

    class WordMeaningViewHolder(val binding: LayoutWordMeaningItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}