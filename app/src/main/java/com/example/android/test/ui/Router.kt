package com.example.android.test.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.android.test.R
import com.example.android.test.ui.fragments.items.ItemsFragment

object Router {

    private fun showFragment(
        fragment: Fragment,
        fm: FragmentManager,
        container: Int = R.id.fragmentContainer,
        addToBackStack: Boolean = true
    ) {
        fm.beginTransaction().apply {
            setCustomAnimations(
                android.R.anim.fade_in,
                android.R.anim.fade_out,
                android.R.anim.fade_in,
                android.R.anim.fade_out
            )
            replace(container, fragment, fragment::class.java.simpleName)
            if (addToBackStack)
                addToBackStack(null)
            commitAllowingStateLoss()
        }
    }

    fun showItems(fm: FragmentManager) {
        showFragment(ItemsFragment.newInstance(), fm, addToBackStack = false)
    }
}