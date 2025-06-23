package com.example.loopie

import android.os.Bundle
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

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment, R.id.userFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)


    }
}