package com.example.loopie.features.auth.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.loopie.R
import com.example.loopie.core.utils.ShowSnackbar
import com.example.loopie.databinding.CustomTextBinding
import com.example.loopie.features.workermanager.MyWorker
import java.util.concurrent.TimeUnit
import androidx.core.view.isEmpty

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
        val btnShowSnackbar: Button = findViewById(R.id.btnShowSnackbar)
        val btnWorkManager: Button = findViewById(R.id.btnWorkManager)
        val rootView: View = findViewById(R.id.main)

        // Xử lý sự kiện nhấn nút thêm ImageView
//        addImageButton.setOnClickListener {
//            // Tạo ImageView mới
//            val imageView = ImageView(this).apply {
//                layoutParams = LinearLayout.LayoutParams(
//                    LinearLayout.LayoutParams.WRAP_CONTENT,
//                    LinearLayout.LayoutParams.WRAP_CONTENT
//                ).apply {
//                    setMargins(0, 16, 0, 16) // Khoảng cách giữa các ImageView
//                }
//                // Đặt hình ảnh (thay R.drawable.sample_image bằng tài nguyên hình ảnh thực tế)
//                setImageResource(android.R.drawable.ic_menu_gallery)
//            }
//            // Thêm ImageView vào container
//            imageContainer.addView(imageView)
//        }
        addImageButton.setOnClickListener {
            // Giả lập preferenceHelper.isLoggedIn

            // Inflate custom_text layout
            val binding = CustomTextBinding.inflate(LayoutInflater.from(this))
            // Kiểm tra imageContainer có view hay không
            if (imageContainer.isEmpty()) {
                // Nếu container rỗng, inflate và thêm custom_text
                binding.customTextView.text = "aaaaaaaaaaaaaa"
                binding.customTextView.setOnClickListener {
                    imageContainer.removeAllViews()
                }
                imageContainer.addView(binding.root)
                ShowSnackbar.showCustomSnackbar(
                    view = rootView,
                    message = "Custom text added: aaaaaaaaaaaaaa",
                    type = 200
                )
            } else {
                // Nếu container đã có view, cập nhật text của view đầu tiên
                val existingView = imageContainer.getChildAt(0)
                val binding = CustomTextBinding.bind(existingView)
                binding.customTextView.text = "bbbbbbbbbbb"
                ShowSnackbar.showCustomSnackbar(
                    view = rootView,
                    message = "Custom text updated: bbbbbbbbbbb",
                    type = 200
                )
            }


            // Hiển thị Snackbar xác nhận
            ShowSnackbar.showCustomSnackbar(
                view = rootView,
                message = "Custom text added: ",
                type = 200
            )
        }

        // Xử lý sự kiện nhấn nút xóa ImageView
        removeImageButton.setOnClickListener {
            // Xóa tất cả ImageView trong container
            imageContainer.removeAllViews()
        }

        btnShowSnackbar.setOnClickListener {
            ShowSnackbar.showCustomSnackbar(
                view = rootView, // Sử dụng root RelativeLayout làm anchor view
                message = "Custom Snackbar Message!",
                type = 200
            )       }

        btnWorkManager.setOnClickListener {
            // Tạo OneTimeWorkRequest với độ trễ 10 giây
            val logWorkRequest = OneTimeWorkRequestBuilder<MyWorker>()
                .setInitialDelay(10, TimeUnit.SECONDS)
                .build()

            // Lên lịch công việc
            WorkManager.getInstance(this).enqueueUniqueWork(
                "getAPI",
                ExistingWorkPolicy.KEEP,
                logWorkRequest
            )
        }
    }
}