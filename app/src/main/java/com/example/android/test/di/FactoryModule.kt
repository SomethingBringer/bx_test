package com.example.android.test.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.test.ui.fragments.items.ItemsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FactoryModule {

    @Binds
    internal abstract fun bind(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ItemsViewModel::class)
    abstract fun bindItemsViewModel(viewModel: ItemsViewModel): ViewModel
}