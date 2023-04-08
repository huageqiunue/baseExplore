package com.example.storage

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

/**
 * SAF打开Fragment
 */
class SafFragment : Fragment() {
    companion object {
        private const val FRAGMENT_TAG = "SafFragment"

        fun getSafFragment(activity: AppCompatActivity): SafFragment {
            val supportFragmentManager = activity.supportFragmentManager
            val findFragmentByTag =
                supportFragmentManager.findFragmentByTag(FRAGMENT_TAG) as? SafFragment
            if (findFragmentByTag == null) {
                val safFragment = SafFragment()
                supportFragmentManager.beginTransaction().add(safFragment, FRAGMENT_TAG)
                    .commit()
                return safFragment
            }
            return findFragmentByTag
        }
    }

    fun openSafForCallback(safParams: SafParams,) {

    }
}