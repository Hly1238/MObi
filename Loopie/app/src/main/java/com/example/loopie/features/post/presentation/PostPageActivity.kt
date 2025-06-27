package com.example.loopie.features.post.presentation

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.loopie.R
import com.example.loopie.core.utils.DialogUtils
import com.example.loopie.core.utils.PermissionResultListener
import com.example.loopie.core.utils.PermissionUtils
import com.example.loopie.core.utils.PermissionUtils.handleOnRequestPermissionResult
import com.example.loopie.databinding.PostPageBinding

class PostPageActivity : AppCompatActivity() {

    private lateinit var binding: PostPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = PostPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.requestBtn.setOnClickListener {
            PermissionUtils.requestMultiplePermissionWithListener(
                multiplePermissions,
                multiplePermissionsCode,
                this@PostPageActivity,
                this
            )
        }
    }

    // multiple permissions
    private val multiplePermissions = arrayOf(
        Manifest.permission.CAMERA
    )
    private val multiplePermissionsCode = 2111
    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        // multiple permission
        PermissionUtils.handleOnRequestPermissionResult(
            multiplePermissionsCode,
            requestCode,
            permissions,
            grantResults,
            object : PermissionResultListener {
                override fun onPermissionRationaleShouldBeShown() {
                    Toast.makeText(this@PostPageActivity, "permission denied", Toast.LENGTH_LONG).show()
                }

                override fun onPermissionPermanentlyDenied() {
                    Toast.makeText(this@PostPageActivity, "permission permanently disabled", Toast.LENGTH_LONG).show()
                }

                override fun onPermissionGranted() {
                    Toast.makeText(this@PostPageActivity, "permission granted", Toast.LENGTH_LONG).show()
                }
            },
            this
        )
    }
}
