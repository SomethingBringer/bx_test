package com.example.android.test.ui.fragments.items

import android.content.res.Configuration
import android.media.VolumeShaper
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.test.MyApplication
import com.example.android.test.databinding.ItemsFragmetBinding
import com.example.android.test.models.Wrapper
import com.example.android.test.ui.base.BindingFragment
import java.lang.ref.WeakReference
import javax.inject.Inject

class ItemsFragment : BindingFragment<ItemsFragmetBinding>(ItemsFragmetBinding::inflate) {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    lateinit var viewModel: ItemsViewModel
    val itemsAdapter by lazy { ItemsAdapter(viewModel::onRemoveItem) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as MyApplication).appComponent.inject(this)
        viewModel = ViewModelProvider(this, factory)[ItemsViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            val orientation = it.resources.configuration.orientation
            if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                initRecycler(GridLayoutManager(requireContext(), PORTRAIT_COLUMNS))
            } else {
                initRecycler(GridLayoutManager(requireContext(), LANDSCAPE_COLUMNS))
            }
        }
        observeLiveData()
    }

    private fun initRecycler(manager: RecyclerView.LayoutManager) {
        binding.recycler.apply {
            adapter = itemsAdapter
            layoutManager = manager
        }
    }

    private fun observeLiveData() {
        viewModel.itemsLiveData.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                val wrappers = it.map { i -> Wrapper(i)  }
                itemsAdapter.submitList(wrappers)
            }
        }
    }

    companion object {
        private var instanceRef: WeakReference<ItemsFragment>? = null
        fun newInstance(): ItemsFragment{
            return instanceRef?.get() ?: ItemsFragment().apply { instanceRef= WeakReference(this) }
        }
        const val PORTRAIT_COLUMNS = 2
        const val LANDSCAPE_COLUMNS = 4
    }
}