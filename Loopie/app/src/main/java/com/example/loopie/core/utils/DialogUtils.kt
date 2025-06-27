package com.example.loopie.core.utils

import android.content.Context
import androidx.appcompat.app.AlertDialog


object DialogUtils {
    fun showMessage(
        context: Context,
        message: String,
        textPositive: String,
        positiveListener: () -> Unit,
        textNegative: String? = null,
        negativeListener: (() -> Unit)? = null
    ) {
        val builder = AlertDialog.Builder(context)
        builder.setMessage(message)
            .setPositiveButton(textPositive) { _, _ ->
                positiveListener.invoke()
            }

        // Thêm nút Negative nếu có
        if (textNegative != null) {
            builder.setNegativeButton(textNegative) { _, _ ->
                negativeListener?.invoke()
            }
        }

        // Hiển thị dialog
        val dialog = builder.create()
        dialog.show()
    }
}