package com.example.storage

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

}