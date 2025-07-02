package com.example.loopie.features.dashboard.presentation

import android.content.Intent
import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loopie.AdapterClass
import com.example.loopie.DataClass
import com.example.loopie.R
import com.example.loopie.core.utils.ShowSnackbar
import com.example.loopie.features.post.presentation.PostPageActivity

class HomeFragment : Fragment() {
    private lateinit var dataList: ArrayList<DataClass>
    private lateinit var recyclerView: RecyclerView
    lateinit var imageList: Array<Int>
    lateinit var titleList: Array<String>
    private lateinit var adapter: AdapterClass
    private lateinit var button: Button



    companion object {
        fun newInstance() = HomeFragment()
    }

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    private fun getData(){
        dataList = ArrayList() // ✅ Bắt buộc phải có

        imageList = arrayOf(
            R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_foreground,
            R.drawable.outline_add_home_24
        )

        titleList = arrayOf(
            "ListView",
            "Check box",
            "Image View"
        )

        for(i in imageList.indices) {
            val dataClass = DataClass(imageList[i], titleList[i])
            dataList.add(dataClass)
        }

        adapter = AdapterClass(dataList, { postId ->
            viewModel.toggle()
        })
        recyclerView.adapter = adapter
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_home, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        button = view.findViewById(R.id.recyclerView2)


        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        dataList = ArrayList()
//        getData()
//        adapter = AdapterClass(dataList,  { postId ->
//            viewModel.toggle()
//        })

        adapter = AdapterClass(mutableListOf()) { postId ->
            viewModel.toggle() // Xử lý click Like
        }
        // Tạo adapter một lần
        recyclerView.adapter = adapter
//        viewModel.postData.observe(viewLifecycleOwner) { data ->
//            dataList.clear()
//            dataList.addAll(data) // Cập nhật dataList
//            adapter.notifyDataSetChanged() // Cập nhật giao diện
//            Log.d("HomeFragment", "Data updated, size: ${dataList.size}")
//
//        }
        viewModel.postData.observe(viewLifecycleOwner) { data ->
            adapter.updateData(data) // Cập nhật Adapter
            Log.d("HomeFragment", "Data updated, size: ${data.size}")
        }



        // Gọi hàm addPostData
        button.setOnClickListener {
//            viewModel.addPostData()
//            Log.d("TEST","TEST")
//            val intent = Intent(requireContext(), PostPageActivity::class.java)
//            startActivity(intent)
            ShowSnackbar.showCustomSnackbar(
                view = requireView() ,// Sử dụng FrameLayout (root View) làm anchor view
                message = "Custom Snackbar Message!",
                type = 200
            )

        }
        return view
    }
}