package com.example.android.test.ui.fragments.items

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.random.Random
import kotlin.random.Random.Default.nextInt

class ItemsViewModel @Inject constructor() : ViewModel() {

    private val initList = List(15) { i -> i + 1 }
    private val deletePool: Queue<Int> = LinkedList()

    var counter = 16

    val itemsLiveData = MutableLiveData<List<Int>>()
    val updateEventLiveData = MutableLiveData<UpdateEvent>()
    fun onRemoveItem(item: Int) {
        deletePool.add(item)
        val list = itemsLiveData.value as MutableList
        list.remove(item)
        itemsLiveData.value = list
        updateEventLiveData.value = UpdateEvent.DELETE
    }

    init {
        itemsLiveData.value = initList
        updateEventLiveData.value = UpdateEvent.ADD
        updateEventLiveData.observeForever { if (it==UpdateEvent.ADD) runSomethingConcurrent() }
    }

    private fun runSomethingConcurrent() = viewModelScope.launch {
        delay(5000)
        val update = itemsLiveData.value as MutableList
        val randomPos = nextInt(0, update.size)
        if (deletePool.isNotEmpty()) {
            val i = deletePool.poll()
            i?.let { update.add(randomPos, it) }
            itemsLiveData.value = update
            updateEventLiveData.value = UpdateEvent.ADD
        } else {
            update.add(randomPos, counter)
            itemsLiveData.value = update
            updateEventLiveData.value = UpdateEvent.ADD
            counter++
        }
    }

    enum class UpdateEvent { ADD, DELETE }
}