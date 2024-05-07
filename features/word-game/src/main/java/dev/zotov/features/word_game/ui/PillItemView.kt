package dev.zotov.features.word_game.ui

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import dev.zotov.ui.utils.toPx

class PillItem @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var lineWidth = 0F
    private var lineHeight = 6.toPx().toFloat()
    private val radius = 6.toPx().toFloat()
    private var activeWidthFactor = 0F

    private val valueAnimator = ValueAnimator.ofFloat(0F, 1F).apply {
        duration = 300
        interpolator = AccelerateDecelerateInterpolator()
        addUpdateListener {
            activeWidthFactor = it.getAnimatedValue() as Float
            invalidate()
        }
    }

    private val paintInActive = Paint().apply {
        color = 0xFFEEF4FF.toInt()
    }

    private val paintActive = Paint().apply {
        color = 0xFF444CE7.toInt()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        lineWidth = w.toFloat()
        Log.d("lineWidth", lineWidth.toString())
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // In active pill
        canvas.drawRoundRect(0F, 0F, x + lineWidth, lineHeight, radius, radius, paintInActive)

        // Active pill
        canvas.drawRoundRect(
            0F,
            0F,
            lineWidth * activeWidthFactor,
            lineHeight,
            radius,
            radius,
            paintActive
        )
    }

    fun animateForward() {
        valueAnimator.start()
    }

    fun animateBackward() {
        valueAnimator.reverse()
    }
}