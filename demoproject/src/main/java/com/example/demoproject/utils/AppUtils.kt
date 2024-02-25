package com.example.fivechessgame.utils

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.util.zip.ZipEntry
import java.util.zip.ZipFile
import java.util.zip.ZipInputStream


object AppUtils {
    private const val TAG = "AppUtils"

    /**
     * 检测整机支持的架构
     */
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun checkDeviceArchitecture() {
        val supported32BitAbis = Build.SUPPORTED_32_BIT_ABIS
        val supported64BitAbis = Build.SUPPORTED_64_BIT_ABIS
        Log.d(TAG, "Supported 32-bit ABIs: ${supported32BitAbis.contentToString()}")
        Log.d(TAG, "Supported 64-bit ABIs: ${supported64BitAbis.contentToString()}")
        if (supported32BitAbis.isNotEmpty() && supported64BitAbis.isNotEmpty()) {
            Log.d(TAG, "Device supports both 32-bit and 64-bit architectures")
        } else if (supported32BitAbis.isNotEmpty()) {
            Log.d(TAG, "Device supports only 32-bit architecture")
        } else if (supported64BitAbis.isNotEmpty()) {
            Log.d(TAG, "Device supports only 64-bit architecture")
        } else {
            Log.d(TAG, "Device does not support any architecture")
        }
    }

    fun getApkArchitecture(context: Context, apkFilePath: String): String? {
        val pm = context.packageManager
        val packageInfo = pm.getPackageArchiveInfo(apkFilePath, PackageManager.GET_ACTIVITIES)
        val architectures = mutableListOf<String>()
        val supportedAbis = getSupportedAbis(packageInfo?.applicationInfo!!)
        Log.d(TAG, "-----${supportedAbis.joinToString(",")}")
        packageInfo?.applicationInfo?.let { appInfo ->
            val nativeLibraryDirs = appInfo.nativeLibraryDir.split(",")
            for (nativeLibraryDir in nativeLibraryDirs) {
                if (nativeLibraryDir.contains("x86")) {
                    architectures.add("x86")
                }
                if (nativeLibraryDir.contains("x86_64")) {
                    architectures.add("x86_64")
                }
                if (nativeLibraryDir.contains("arm64")) {
                    architectures.add("arm64-v8a")
                }
                if (nativeLibraryDir.contains("armeabi-v7a")) {
                    architectures.add("armeabi-v7a")
                }
            }
        }
        Log.e("支持架构:", architectures.joinToString(","))
        return null
    }

    private fun getSupportedAbis(applicationInfo: ApplicationInfo): Array<String?> {
        if (applicationInfo.metaData != null) {
            var supportedAbis: Array<String>? = null
            if (applicationInfo.metaData.containsKey("nativeLibraryPath")) {
                val nativeLibraryPath = applicationInfo.metaData.getString("nativeLibraryPath")
                supportedAbis = nativeLibraryPath!!.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            } else if (applicationInfo.metaData.containsKey("android.app.lib_name")) {
                val libName = applicationInfo.metaData.getString("android.app.lib_name")
                supportedAbis = libName!!.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            }
            if (supportedAbis != null) {
                val abiList: MutableList<String?> = ArrayList()
                for (abi in supportedAbis) {
                    abiList.add(abi.trim { it <= ' ' })
                }
                return abiList.toTypedArray()
            }
        }
        return arrayOfNulls(0)
    }

    fun isSupportedArchitecture(packageManager: PackageManager, apkFilePath: String): Boolean {
        val packageInfo = packageManager.getPackageArchiveInfo(apkFilePath, PackageManager.GET_META_DATA)
        val appInfo: ApplicationInfo? = packageInfo?.applicationInfo
        val supportedArchitectures = appInfo?.nativeLibraryDir?.let { getSupportedArchitectures(it) }

        val deviceSupportedArchitectures = getDeviceSupportedArchitectures()
        Log.d("TAG", "Supported Architectures: $supportedArchitectures")
        Log.d("TAG", "Device Architectures: $deviceSupportedArchitectures")

        // Check if any of the device supported architectures match with the APK supported architectures
        return supportedArchitectures?.any { deviceSupportedArchitectures.contains(it) } == true
    }

    fun getSupportedArchitectures1(nativeLibraryDir: String): List<String> {
        val supportedArchitectures = mutableListOf<String>()
        val dir = File(nativeLibraryDir)
        if (dir.isDirectory) {
            val files = dir.listFiles()
            files?.forEach { file ->
                val fileName = file.name
                if (fileName.startsWith("lib") && fileName.endsWith(".so")) {
                    val architecture = fileName.substring(3, fileName.indexOf('.'))
                    supportedArchitectures.add(architecture)
                }
            }
        }
        return supportedArchitectures
    }

    fun getDeviceSupportedArchitectures(): List<String> {
        val supportedArchitectures = mutableListOf<String>()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            supportedArchitectures.addAll(Build.SUPPORTED_ABIS)
        } else {
            supportedArchitectures.add(Build.CPU_ABI)
            if (Build.CPU_ABI2.isNotEmpty()) {
                supportedArchitectures.add(Build.CPU_ABI2)
            }
        }

        return supportedArchitectures
    }

    fun getApkSupportArchitecture1(apkFilePath: String): List<String> {
        val apkSupportArchitecture = mutableListOf<String>()
        try {
            // 打开APK文件
            val apkFile = ZipFile(apkFilePath)

            // 遍历APK文件中的所有条目
            val entries = apkFile.entries()
            while (entries.hasMoreElements()) {
                val entry = entries.nextElement()
                val entryName = entry.name
                // 检查是否是lib目录下的文件
                if (entryName.startsWith("lib/") && entry.isDirectory) {
                    Log.e("jiahua", "$entryName \n")
                    val fileName = entryName.substringAfterLast('/')
                    apkSupportArchitecture.add(entryName)
                }
            }
            // 关闭APK文件
            apkFile.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return apkSupportArchitecture
    }

    fun getSupportedArchitectures(apkFilePath: String): List<String> {
        val supportedArchitectures: MutableList<String> = ArrayList()
        try {
            ZipInputStream(FileInputStream(apkFilePath)).use { zipInputStream ->
                var entry: ZipEntry
                while (zipInputStream.nextEntry.also { entry = it } != null) {
                    if (entry.name.startsWith("lib/") && !entry.isDirectory) {
                        val architecture = entry.name.substring(4)
                        supportedArchitectures.add(architecture)
                    }
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return supportedArchitectures
    }
}