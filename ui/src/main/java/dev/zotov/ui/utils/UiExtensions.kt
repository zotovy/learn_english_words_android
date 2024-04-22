package dev.zotov.ui.utils

import android.content.Context
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import java.util.Locale

fun View.setMargin(top: Int = 0, start: Int = 0, end: Int = 0, bottom: Int = 0) {
    this.layoutParams = (this.layoutParams as ViewGroup.MarginLayoutParams).apply {
        setMargins(
            start,
            top,
            end,
            bottom
        )
    }
}

fun Context.toPx(dp: Int): Int = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    dp.toFloat(),
    resources.displayMetrics
).toInt()

fun String.capitalizeWord(): String {
    return replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
}