package com.example.android.test.di

import com.example.android.test.ui.fragments.items.ItemsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [FactoryModule::class])
interface AppComponent {

    fun inject(fragment: ItemsFragment)
}