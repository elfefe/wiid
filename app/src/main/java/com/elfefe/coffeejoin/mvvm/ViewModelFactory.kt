package com.elfefe.coffeejoin.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.elfefe.coffeejoin.mvvm.repository.RestApiRepository
import com.elfefe.coffeejoin.mvvm.repository.ChannelRepository
import com.elfefe.coffeejoin.mvvm.repository.OltpRepository
import com.elfefe.coffeejoin.mvvm.viewmodel.ApiViewModel
import com.elfefe.coffeejoin.mvvm.viewmodel.OltpViewModel

class ViewModelFactory: ViewModelProvider.Factory {

    private val oltpRepository = OltpRepository()
    private val channelRepository = ChannelRepository()
    private val apiRepository = RestApiRepository()

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = with(modelClass){
        if (isAssignableFrom(OltpViewModel::class.java))
            return OltpViewModel(
                channelRepository
            ) as T
            return ApiViewModel(
                apiRepository
            ) as T

        throw IllegalArgumentException("Unknown ViewModel class")
    }

    companion object {
        val INSTANCE = ViewModelFactory()
    }
}