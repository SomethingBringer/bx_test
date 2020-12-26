package com.example.android.test.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

open class BindingFragment<B : ViewBinding>(inflater: (LayoutInflater, ViewGroup?, Boolean) -> ViewBinding) :
    Fragment() {

    private val mInflater = inflater
    private var mBinding: B? = null
    val binding get() = mBinding!!

    @Suppress("UNCHECKED_CAST")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = mInflater.invoke(inflater, container, false) as B
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }
}