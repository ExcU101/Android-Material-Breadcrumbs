package com.excu_fcd.breadcrumbs

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.excu_fcd.breadcrumb.view.applyToAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val breadcrumbs = mutableListOf<com.excu_fcd.breadcrumb.model.BreadcrumbItem>()

        repeat(10) {
            breadcrumbs.add(
                com.excu_fcd.breadcrumb.model.BreadcrumbItem(
                    "Directory $it",
                    isArrowEnabled = true,
                    isSelected = false
                )
            )
        }

        findViewById<com.excu_fcd.breadcrumb.view.BreadcrumbLayout>(R.id.bc).apply {
            applyToAdapter {
                set(breadcrumbs)
            }
        }
    }
}