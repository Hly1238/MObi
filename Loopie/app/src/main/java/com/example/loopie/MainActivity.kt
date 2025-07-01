package com.example.loopie

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.loopie.databinding.ActivityMainBinding
import com.example.loopie.features.workermanager.MyWorker
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.concurrent.TimeUnit


import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.loopie.core.di.ServiceLocator
import com.example.loopie.core.helper.PreferenceHelper
import com.example.loopie.features.auth.presentation.LoginActivity

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        ServiceLocator.init(applicationContext)
    }
}

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var btn: Button
    private val preferenceHelper: PreferenceHelper by lazy { ServiceLocator.getPreferenceHelper() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        btn = binding.testBtn
//        if (preferenceHelper.isLoggedIn) {
//            startActivity(Intent(this, LoginActivity::class.java))
//            finish()
//            return
//        }
//        preferenceHelper.isLoggedIn = false



//        btn.setOnClickListener {
//            preferenceHelper.isLoggedIn = true
//        }

//        binding.apply {
//            periodicBtn.setOnClickListener{
////                val saveRequest = OneTimeWorkRequestBuilder<MyWorker>()
////                    .setInitialDelay(10, TimeUnit.SECONDS)
////                    .build()
////                WorkManager.getInstance(this@MainActivity).enqueueUniqueWork(
////                    "getAPI",
////                    ExistingWorkPolicy.KEEP,
////                    saveRequest
////                )
////                WorkManager.getInstance(this@MainActivity).cancelUniqueWork("getAPI")
//                periodicBtn.setOnClickListener {
//                    val saveRequest = PeriodicWorkRequestBuilder<MyWorker>(1, TimeUnit.SECONDS)
//                        .build()
//                    WorkManager.getInstance(this@MainActivity).enqueueUniquePeriodicWork(
//                        "getAPI",
//                        ExistingPeriodicWorkPolicy.KEEP,
//                        saveRequest
//                    )
//                }
//            }
//
//        }
//
//

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)


    }
}