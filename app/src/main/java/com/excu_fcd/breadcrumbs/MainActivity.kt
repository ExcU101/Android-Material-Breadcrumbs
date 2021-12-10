package com.excu_fcd.breadcrumbs

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val breadcrumbs = mutableListOf<BreadcrumbItem>()

        repeat(10) {
            breadcrumbs.add(
                BreadcrumbItem(
                    "Directory $it",
                    isArrowEnabled = true,
                    isSelected = false
                )
            )
        }

        val bc = findViewById<BreadcrumbLayout>(R.id.bc).apply {
            applyToAdapter {
                set(breadcrumbs)
            }
        }
        val fab = findViewById<ExtendedFloatingActionButton>(R.id.fab)
    }

    override fun onStart() {
        super.onStart()
        val bc = findViewById<BreadcrumbLayout>(R.id.bc)
        
        bc.find(0)?.let {
            it.breadcrumb.setOnClickListener {
                Toast.makeText(applicationContext, "Test", Toast.LENGTH_SHORT).show()
            }
        }
    }
}