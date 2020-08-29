package com.elfefe.coffeejoin.mvvm.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.elfefe.coffeejoin.oltp.model.ChannelCommunity
import com.elfefe.coffeejoin.oltp.model.ChannelPrivate

class OltpViewModel(channelRepository: ChannelRepository): ViewModel() {
    val allChannelsLivedata: LiveData<List<ChannelCommunity>> = liveData {
        val data = channelRepository.getAllChannels() // loadUser is a suspend function.
        emit(data)
    }
}