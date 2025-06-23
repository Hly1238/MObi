package com.example.loopie.features.dashboard.presentation

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loopie.AdapterClass
import com.example.loopie.DataClass
import com.example.loopie.R

class HomeFragment : Fragment() {
    private lateinit var dataList: ArrayList<DataClass>
    private lateinit var recyclerView: RecyclerView
    lateinit var imageList: Array<Int>
    lateinit var titleList: Array<String>
    private lateinit var adapter: AdapterClass



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

        adapter = AdapterClass(dataList)
        recyclerView.adapter = adapter
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_home, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)

        // Gọi getData để điền dữ liệu
        getData()

        return view
    }
}