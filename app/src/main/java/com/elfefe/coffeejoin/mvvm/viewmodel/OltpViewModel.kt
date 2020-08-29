package com.elfefe.coffeejoin.mvvm.viewmodel

import androidx.lifecycle.*
import com.elfefe.coffeejoin.mvvm.repository.ChannelRepository
import com.elfefe.coffeejoin.oltp.model.ChannelCommunity
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.collect

class OltpViewModel(
    private val channelRepository: ChannelRepository
) : ViewModel() {
    private val _allChannelsLivedata: MutableLiveData<List<ChannelCommunity>> = MutableLiveData()
    private val _channelByNameLivedata: MutableLiveData<ChannelCommunity> = MutableLiveData()

    fun getAllChannels(): LiveData<List<ChannelCommunity>> {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                channelRepository.getAllChannels().collect() {
                    _allChannelsLivedata.postValue(it)
                }
            } catch (e: Throwable) {
                println("Exception from the flow: $e")
            }
        }
        return _allChannelsLivedata
    }

    fun getChannelByName(name: String): LiveData<ChannelCommunity?> {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                channelRepository.getChannelByName(name).collect() {
                    _channelByNameLivedata.postValue(it)
                }
            } catch (e: Throwable) {
                println("Exception from the flow: $e")
            }
        }
        return _channelByNameLivedata
    }

    fun sendChannels(vararg channelCommunity: ChannelCommunity) {
        channelRepository.sendChannels(channelCommunity)
    }

    fun sendChannelCommunity(channelCommunity: ChannelCommunity) {
        channelRepository.sendChannelCommunity(channelCommunity)
    }
}