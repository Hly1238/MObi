package com.example.loopie.features.auth.presentation

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.loopie.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Tìm nút và container
        val addImageButton: Button = findViewById(R.id.toolbarButton1)
        val removeImageButton: Button = findViewById(R.id.toolbarButton2)
        val imageContainer: LinearLayout = findViewById(R.id.imageContainer)

        // Xử lý sự kiện nhấn nút thêm ImageView
        addImageButton.setOnClickListener {
            // Tạo ImageView mới
            val imageView = ImageView(this).apply {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    setMargins(0, 16, 0, 16) // Khoảng cách giữa các ImageView
                }
                // Đặt hình ảnh (thay R.drawable.sample_image bằng tài nguyên hình ảnh thực tế)
                setImageResource(android.R.drawable.ic_menu_gallery)
            }
            // Thêm ImageView vào container
            imageContainer.addView(imageView)
        }

        // Xử lý sự kiện nhấn nút xóa ImageView
        removeImageButton.setOnClickListener {
            // Xóa tất cả ImageView trong container
            imageContainer.removeAllViews()
        }
    }
}