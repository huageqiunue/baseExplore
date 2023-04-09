package com.example.storage

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.storage.SafOpenType.ACTION_OPEN_DOCUMENT
import com.example.storage.SafOpenType.ACTION_OPEN_DOCUMENT_TREE

/**
 * SAF打开Fragment
 */
class SafFragment : Fragment() {
    companion object {
        private const val FRAGMENT_TAG = "SafFragment"

        /** 创建文件 */
        private const val REQUEST_CODE_NEW_FILE = 0x1001

        /** 单选文件 */
        private const val REQUEST_CODE_OPEN_FILE = 0x1002

        /** 多选文件 */
        private const val REQUEST_CODE_OPEN_MULTIPLE_FILE = 0x1003

        /** 选择目录 */
        private const val REQUEST_CODE_OPEN_FILE_TREE = 0x1004

        fun getSafFragment(activity: FragmentActivity): SafFragment {
            val supportFragmentManager = activity.supportFragmentManager
            val findFragmentByTag =
                supportFragmentManager.findFragmentByTag(FRAGMENT_TAG) as? SafFragment
            if (findFragmentByTag == null) {
                val safFragment = SafFragment()
                supportFragmentManager.beginTransaction().add(safFragment, FRAGMENT_TAG).commit()
                return safFragment
            }
            return findFragmentByTag
        }

        fun getSafFragment(fragment: Fragment): SafFragment {
            return getSafFragment(fragment.requireActivity())
        }
    }

    private var mCallback: SafCallback? = null
    fun openSaf(safParams: SafParams, safCallback: SafCallback) {
        when (safParams.safOpenType) {
            ACTION_OPEN_DOCUMENT -> {

            }
            ACTION_OPEN_DOCUMENT_TREE -> {
            }
        }
        Log.e("jiahua", "test o11ut");
        safCallback.succeed(arrayListOf())
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK) {
            mCallback?.failed("")
            return
        }
        when (requestCode) {
            REQUEST_CODE_NEW_FILE -> {
                mCallback?.succeed(listOf(data?.data))
            }
            REQUEST_CODE_OPEN_FILE, REQUEST_CODE_OPEN_FILE_TREE -> {
                mCallback?.succeed(listOf(data?.data))
            }
            REQUEST_CODE_OPEN_MULTIPLE_FILE -> {
                //多选文件，如果只选择一个文件，有可能不通过clipData传递，所以获取不到clipData时通过data获取
                data?.clipData?.let { clipData ->
                    val count = clipData.itemCount
                    val list = mutableListOf<Uri?>()
                    for (i in 0 until count) {
                        list.add(clipData.getItemAt(i).uri)
                    }
                    mCallback?.succeed(list)
                } ?: kotlin.run {
                    mCallback?.succeed(listOf(data?.data))
                }
            }
        }
    }
}