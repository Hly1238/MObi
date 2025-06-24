package com.example.loopie.features.dashboard.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loopie.DataClass
import com.example.loopie.R

class HomeViewModel : ViewModel() {
    val postData = MutableLiveData<ArrayList<DataClass>>()

    init {
        // Dữ liệu mẫu, sử dụng arrayListOf thay vì arrayOf
        postData.value = arrayListOf(
            DataClass(R.drawable.ic_launcher_foreground, "ListView"),
            DataClass(R.drawable.ic_launcher_foreground, "Check box"),
            DataClass(R.drawable.outline_add_home_24, "Image View")
        )
    }

    // Hàm thêm một phần tử mới
    fun addPostData() {
        val currentList = postData.value ?: arrayListOf() // Nếu null, tạo ArrayList mới
        currentList.add(DataClass(R.drawable.ic_launcher_foreground, "New Post"))
        postData.value = currentList
    }
}