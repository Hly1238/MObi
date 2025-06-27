package com.example.loopie.core.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.core.content.ContextCompat


interface PermissionResultListener {
    /**
     * Callback on permission denied
     */
    fun onPermissionRationaleShouldBeShown()

    /**
     * Callback on permission "Never show again" checked and denied
     */
    fun onPermissionPermanentlyDenied()

    /**
     * Callback on permission granted
     */
    fun onPermissionGranted()
}
interface RequestPermissionListener {
    /**
     * Callback on permission previously denied
     * should show permission rationale and continue permission request
     */
    fun onPermissionRationaleShouldBeShown(requestPermission: () -> Unit)

    /**
     * Callback on permission "Never show again" checked and denied
     * should show message and open app setting
     */
    fun onPermissionPermanentlyDenied(openAppSetting: () -> Unit)

    /**
     * Callback on permission granted
     */
    fun onPermissionGranted()
}

object PermissionUtils {
    fun openAppDetailSettings(context: Context) {
        val intent = Intent()
        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        val uri = Uri.fromParts("package", context.packageName, null)
        intent.data = uri
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        }
    }

    fun shouldAskPermissions(permissions: Array<String>, context: Context): Boolean {
        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(context, permission)
                != PackageManager.PERMISSION_GRANTED
            ) {
                return true
            }
        }
        return false
    }

    fun shouldShowRequestPermissionsRationale(permissions: Array<out String>,activity: Activity): Boolean {
        for (permission in permissions) {
            if (activity.shouldShowRequestPermissionRationale(permission)) {
                return true
            }
        }
        return false
    }

    fun isFirstTimeAskingPermissions(permissions: Array<String>, context: Context): Boolean {
        val sharedPreference: SharedPreferences? = context.getSharedPreferences(context.packageName, MODE_PRIVATE)
        for (permission in permissions) {
            if (sharedPreference?.getBoolean(permission, true) == true) {
                return true
            }
        }
        return false
    }

    fun firstTimeAskingPermissions(permissions: Array<String>, isFirstTime: Boolean, context: Context) {
        val sharedPreference: SharedPreferences? = context.getSharedPreferences(context.packageName, MODE_PRIVATE)
        for (permission in permissions) {
            sharedPreference?.edit()?.putBoolean(permission, isFirstTime)?.apply()
        }
    }

    fun isGrantedGrantResults(grantResults: IntArray): Boolean {
        if (grantResults.isEmpty()) return false
        for (grantResult in grantResults) {
            if (grantResult != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }

    fun requestPermissions(
        permissions: Array<String>,
        permissionRequestCode: Int,
        requestPermissionListener: RequestPermissionListener, context: Context, activity: Activity
    ) {
        // permissions is not granted
        if (shouldAskPermissions(permissions,activity)) {
            // permissions denied previously
            if (shouldShowRequestPermissionsRationale(permissions, activity)) {
                requestPermissionListener.onPermissionRationaleShouldBeShown {
                    requestPermissions(permissions, permissionRequestCode, requestPermissionListener, context, activity)                }
            } else {
                // Permission denied or first time requested
                if (isFirstTimeAskingPermissions(permissions, activity)) {
                    firstTimeAskingPermissions(permissions, false, context)
                    // request permissions
                    requestPermissions(permissions, permissionRequestCode, requestPermissionListener, context, activity)
                } else {
                    // permission disabled
                    // Handle the feature without permission or ask user to manually allow permission
                    requestPermissionListener.onPermissionPermanentlyDenied {
                        PermissionUtils.openAppDetailSettings(context = context)
                    }
                }
            }
        } else {
            // permission granted
            requestPermissionListener.onPermissionGranted()
        }
    }

    fun handleOnRequestPermissionResult(
        requestPermissionCode: Int,
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
        permissionResultListener: PermissionResultListener, activity: Activity
    ) {
        if (requestPermissionCode == requestCode) {
            if (isGrantedGrantResults(grantResults)) {
                // permission was granted, yay! Do the
                // contacts-related task you need to do.
                permissionResultListener.onPermissionGranted()
            } else {
                // permission denied, boo! Disable the
                // functionality that depends on this permission.
                if (shouldShowRequestPermissionsRationale(permissions,activity)) {
                    // permission denied
                    permissionResultListener.onPermissionRationaleShouldBeShown()
                } else {
                    // permission disabled or never ask again
                    permissionResultListener.onPermissionPermanentlyDenied()
                }
            }
        }
    }
    fun requestMultiplePermissionWithListener(multiplePermissions: Array<String>,multiplePermissionsCode: Int, context: Context, activity: Activity) {
        requestPermissions(
            multiplePermissions,
            multiplePermissionsCode,

            object : RequestPermissionListener {
                override fun onPermissionRationaleShouldBeShown(requestPermission: () -> Unit) {
                    DialogUtils.showMessage(
                        context = context,
                        message = "Please allow permissions to use this feature",
                        textPositive = "OK",
                        positiveListener = {
                            requestPermission.invoke()
                        },
                        textNegative = "Cancel"
                    )
                }

                override fun onPermissionPermanentlyDenied(openAppSetting: () -> Unit) {
                    DialogUtils.showMessage(
                        context = context,
                        message = "Permission Disabled, Please allow permissions to use this feature",
                        textPositive = "OK",
                        positiveListener = {
                            openAppSetting.invoke()
                        },
                        textNegative = "Cancel"
                    )
                }

                override fun onPermissionGranted() {
                    Toast.makeText(context, "Permission Granted", Toast.LENGTH_LONG).show()
                }
            }, context, activity)
    }
}