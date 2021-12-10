package com.excu_fcd.breadcrumbs

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.View.MeasureSpec.EXACTLY
import android.view.View.MeasureSpec.makeMeasureSpec
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BreadcrumbLayout : FrameLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context, attr: AttributeSet) : super(context, attr)

    private var needToMeasure: Boolean = false

    private val adapter = BreadcrumbAdapter()
    private val recycler = RecyclerView(context)

    init {
        setBackgroundColor(Color.parseColor("#212121"))
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recycler.setHasFixedSize(true)

        addView(recycler, LayoutParams(MATCH_PARENT, MATCH_PARENT))
    }

    // Here, we're measuring size of view!
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        val desireWidth = getSize(widthMeasureSpec) + paddingEnd + paddingStart
//        val desireHeight =
//            (resources.displayMetrics.density.toInt() * 48) + paddingTop + paddingBottom
//
//        val width = makeMeasureSpec(desireWidth, getMode(widthMeasureSpec))
//        val height = makeMeasureSpec(desireHeight, getMode(heightMeasureSpec))
//        needToMeasure = false
//        setMeasuredDimension(width, height)

        super.onMeasure(widthMeasureSpec, makeMeasureSpec(dp(48), EXACTLY))
    }

    fun getLayoutManager(): LinearLayoutManager? {
        return recycler.layoutManager as LinearLayoutManager?
    }

    fun getAdapter(): BreadcrumbAdapter {
        return adapter
    }

    fun setBreadcrumbs(list: List<BreadcrumbItem>) {
        adapter.set(list)
    }

    fun get() {

    }

    fun find(index: Int): BreadcrumbViewHolder? {
        return recycler.findViewHolderForAdapterPosition(index) as BreadcrumbViewHolder?
    }

    private fun dp(value: Int) = resources.displayMetrics.density.toInt() * value

    fun getBreadcrumb(index: Int): BreadcrumbView? =
        (recycler.findViewHolderForAdapterPosition(index) as BreadcrumbViewHolder?)?.breadcrumb?.logIt()

}

inline fun BreadcrumbLayout.getBreadcrumb(index: Int, block: BreadcrumbView.() -> Unit) {
    getBreadcrumb(index)?.let {
        block.invoke(it)
    }
}

inline fun BreadcrumbLayout.applyToAdapter(block: BreadcrumbAdapter.() -> Unit) {
    block(getAdapter())
}

fun <T : Any> T.logIt(): T {
    Log.v("Loggable", "$this")
    return this
}