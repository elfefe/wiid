package com.elfefe.coffeejoin.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elfefe.coffeejoin.models.consumervalidation.ConsumerValidation
import com.elfefe.coffeejoin.models.credential.Credentials
import com.elfefe.coffeejoin.mvvm.repository.RestApiRepository

class ApiViewModel(private val restApiRepository: RestApiRepository): ViewModel() {
    private val _consumerValidationLivedata = MutableLiveData<ConsumerValidation>()
    val consumerValidationLivedata: LiveData<ConsumerValidation> = _consumerValidationLivedata

    private var _containerContentLivedata = MutableLiveData<String>()
    val containerContentLivedata: LiveData<String> = _containerContentLivedata

    fun queryConsumerValidation(credentials: Credentials): LiveData<ConsumerValidation> {
        return restApiRepository.receiveConsumerValidation(credentials)
    }

    fun queryContainerContent(): LiveData<String> {
        return restApiRepository.receiveContainerContent()
    }
}