package com.excu_fcd.breadcrumbs

import android.content.Context
import android.graphics.Color
import android.text.TextUtils
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity.CENTER_VERTICAL
import android.view.View.MeasureSpec.EXACTLY
import android.view.View.MeasureSpec.makeMeasureSpec
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.view.updateLayoutParams

class BreadcrumbView : LinearLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context, attr: AttributeSet) : super(context, attr)

    private val title: TextView = TextView(context).apply {
        text = "Sex"
        setTextColor(Color.BLACK)
        ellipsize = TextUtils.TruncateAt.END
        isClickable = true
        isFocusable = true
        textAlignment = TEXT_ALIGNMENT_CENTER
        maxLines = 1
        isAllCaps = true
        textSize = 14F
        setBackgroundResource(R.drawable.breadcrumb_text_background)
    }

    private val textMargins = MarginLayoutParams(LayoutParams(WRAP_CONTENT, WRAP_CONTENT))
    private val arrowMargins = MarginLayoutParams(LayoutParams(WRAP_CONTENT, WRAP_CONTENT))

    private val arrow = ImageView(context).apply {
        setImageResource(R.drawable.ic_arrow_24)
    }

    var isArrowVisible: Boolean = false
        set(value) {
            if (!value) {
                arrow.visibility = GONE
            }
            field = value
        }

    init {
        orientation = HORIZONTAL
        gravity = CENTER_VERTICAL
        addView(title, textMargins)
        addView(arrow, arrowMargins)

        val back = TypedValue()
        context.theme.resolveAttribute(R.attr.colorSurface, back, true)
        setBackgroundResource(back.resourceId)
        setTitleHorizontalMargins(value = 8)
        setArrowHorizontalMargins(value = 0)
    }

    fun setTitle(value: String) {
        title.text = value
    }

    fun setTitleHorizontalMargins(value: Int) {
        title.updateLayoutParams<MarginLayoutParams> {
            marginStart = (resources.displayMetrics.density * value).toInt()
            marginEnd = (resources.displayMetrics.density * value).toInt()
        }
    }

    fun setArrowHorizontalMargins(value: Int) {
        arrow.updateLayoutParams<MarginLayoutParams> {
            marginStart = (resources.displayMetrics.density * value).toInt()
            marginEnd = (resources.displayMetrics.density * value).toInt()
        }
    }

    fun setTitleVerticalMargins(value: Int) {
        title.updateLayoutParams<MarginLayoutParams> {
            topMargin = (resources.displayMetrics.density * value).toInt()
            bottomMargin = (resources.displayMetrics.density * value).toInt()
        }
    }

    fun setArrowVerticalMargins(value: Int) {
        arrow.updateLayoutParams<MarginLayoutParams> {
            topMargin = (resources.displayMetrics.density * value).toInt()
            bottomMargin = (resources.displayMetrics.density * value).toInt()
        }
    }

    fun setTitleColor(@ColorInt color: Int) {
        title.setTextColor(color)
    }

    fun setTitleColorRes(@ColorRes id: Int) {
        title.setTextColor(ContextCompat.getColor(context, id))
    }

    fun getTitle() = title

    fun getArrow() = arrow

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, makeMeasureSpec(dp(48), EXACTLY))
    }

    private fun dp(value: Int) = resources.displayMetrics.density.toInt() * value

}