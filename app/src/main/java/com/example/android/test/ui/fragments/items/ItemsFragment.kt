package com.example.android.test.ui.fragments.items

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.android.test.MyApplication
import com.example.android.test.databinding.ItemsFragmetBinding
import com.example.android.test.ui.base.BindingFragment
import javax.inject.Inject

class ItemsFragment: BindingFragment<ItemsFragmetBinding>(ItemsFragmetBinding::inflate) {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    lateinit var viewModel: ItemsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as MyApplication).appComponent.inject(this)
        viewModel = ViewModelProvider(this,factory)[ItemsViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    companion object {
        fun newInstance() = ItemsFragment()
    }
}