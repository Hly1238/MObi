package com.example.loopie.core.utils

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.setPadding
import androidx.core.view.updateLayoutParams
import com.example.loopie.databinding.CustomSnackbarBinding
import com.google.android.material.snackbar.Snackbar

object ShowSnackbar {

    fun showCustomSnackbar(view: View, message: String, type: Int) {
        try {
            // Create the Snackbar with no default text
            val snackbar = Snackbar.make(view, "", Snackbar.LENGTH_LONG)
            snackbar.view.updateLayoutParams {
                when (this) {
                    is CoordinatorLayout.LayoutParams -> {
                        gravity = Gravity.TOP
                    }
                    is FrameLayout.LayoutParams -> {
                        gravity = Gravity.TOP
                    }
                    else -> {
                        android.util.Log.w("ShowSnackbar", "Unsupported LayoutParams, defaulting to top")
                    }
                }
            }

            // Remove default padding
            snackbar.view.setPadding(0, 0, 0, 0)
            val binding = CustomSnackbarBinding.inflate(LayoutInflater.from(view.context))
            binding.textView2.text = message

            (snackbar.view as ViewGroup).apply {
//                findViewById<View>(com.google.android.material.R.id.snackbar_text)?.visibility = View.GONE
//                addView(binding.root)
            }
            snackbar.show()

        } catch (e: Exception) {
            android.util.Log.e("ShowSnackbar", "Error showing Snackbar: ${e.message}", e)
        }
    }


}