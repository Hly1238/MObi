package com.example.loopie.features.account.presentation

import android.app.Activity.RESULT_OK
import android.app.Application
import android.content.Intent
import androidx.fragment.app.viewModels
import android.os.Bundle
import android.provider.MediaStore
import android.provider.MediaStore.Images.Media
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import com.example.loopie.R
import com.example.loopie.databinding.FragmentUserBinding

class UserFragment : Fragment() {

    companion object {
        fun newInstance() = UserFragment()
    }

    private var _binding: FragmentUserBinding? = null

    private val binding get() = _binding!!
    private val viewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserBinding.inflate(inflater, container, false)

        val launchGalery = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK && result.data != null) {
                val imageUri = result.data!!.data
                binding.imageView.setImageBitmap(MediaStore.Images.Media.getBitmap(requireContext().contentResolver, imageUri))
            }
        }

        binding.imageBtn.setOnClickListener {
            val openGallery = Intent(Intent.ACTION_GET_CONTENT).setType("image/*") // Sửa "images/*" thành "image/*"
            launchGalery.launch(openGallery)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

//https://www.geeksforgeeks.org/android/view-binding-with-fragments-in-android-jetpack/