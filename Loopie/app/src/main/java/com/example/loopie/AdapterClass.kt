package com.example.loopie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.loopie.databinding.ItemLayoutBinding

class AdapterClass(private val dataList: MutableList<DataClass>, private val onLikeClick: (Int) -> Unit) : RecyclerView.Adapter<AdapterClass.ViewHolderClass>() {
    class ViewHolderClass(var binding: ItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DataClass, onLikeClick: (Int) -> Unit) {
            binding.image.setImageResource(item.dataImage)
            binding.title.text = item.dataTitle

            binding.image.setOnClickListener {
                onLikeClick(1)
            }
        }
    }
//    class ViewHolderClasserClass(itemView: View) :  RecyclerView.ViewHolder(itemView) {
//        val rvImage: ImageView = itemView.findViewById(R.id.image)
//        val rvTitle: TextView = itemView.findViewById(R.id.title)
//    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
//        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
//        return ViewHolderClass(itemView)
        return ViewHolderClass(ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
//        val currentItem = dataList[position]
//        holder.binding.image.setImageResource(currentItem.dataImage)
//        holder.binding.title.setText(currentItem.dataTitle)
        holder.bind(dataList[position], onLikeClick)
    }
    fun updateData(newData: List<DataClass>) {
        dataList.clear()
        dataList.addAll(newData)
        notifyDataSetChanged()
    }
}