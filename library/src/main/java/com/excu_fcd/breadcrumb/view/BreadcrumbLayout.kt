package com.excu_fcd.breadcrumb.view

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

    private val adapter = com.excu_fcd.breadcrumb.adapter.BreadcrumbAdapter()
    private val recycler = RecyclerView(context)

    init {
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recycler.setHasFixedSize(true)

        addView(recycler, LayoutParams(MATCH_PARENT, MATCH_PARENT))
    }

    // Here, we're measuring size of view!
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, makeMeasureSpec(dp(48), EXACTLY))
    }

    fun getLayoutManager(): LinearLayoutManager? {
        return recycler.layoutManager as LinearLayoutManager?
    }

    fun getAdapter(): com.excu_fcd.breadcrumb.adapter.BreadcrumbAdapter {
        return adapter
    }

    fun setBreadcrumbs(list: List<com.excu_fcd.breadcrumb.model.BreadcrumbItem>) {
        adapter.set(list)
    }

    fun find(index: Int): com.excu_fcd.breadcrumb.adapter.BreadcrumbViewHolder? {
        return recycler.findViewHolderForAdapterPosition(index) as com.excu_fcd.breadcrumb.adapter.BreadcrumbViewHolder?
    }

    private fun dp(value: Int) = resources.displayMetrics.density.toInt() * value

    fun getBreadcrumb(index: Int): BreadcrumbView? =
        (recycler.findViewHolderForAdapterPosition(index) as com.excu_fcd.breadcrumb.adapter.BreadcrumbViewHolder?)?.breadcrumb

}

inline fun BreadcrumbLayout.getBreadcrumb(index: Int, block: BreadcrumbView.() -> Unit) {
    getBreadcrumb(index)?.let {
        block.invoke(it)
    }
}

inline fun BreadcrumbLayout.applyToAdapter(block: com.excu_fcd.breadcrumb.adapter.BreadcrumbAdapter.() -> Unit) {
    block(getAdapter())
}