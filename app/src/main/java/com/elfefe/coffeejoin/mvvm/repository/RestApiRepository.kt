package com.elfefe.coffeejoin.mvvm.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.elfefe.coffeejoin.models.Signature
import com.elfefe.coffeejoin.models.XOvh
import com.elfefe.coffeejoin.models.consumervalidation.ConsumerValidation
import com.elfefe.coffeejoin.models.credential.Credentials
import com.elfefe.coffeejoin.utility.*
import com.elfefe.coffeejoin.utility.rest.OvhService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ApiRepository {
    private val _consumerValidationLivedata = MutableLiveData<ConsumerValidation>()
    val consumerValidationLivedata: LiveData<ConsumerValidation> = _consumerValidationLivedata

    private val _containerContentLivedata = MutableLiveData<String>()
    val containerContentLivedata: LiveData<String> = _containerContentLivedata

    private var ck: String = ""

    fun receiveConsumerValidation(credentials: Credentials): LiveData<ConsumerValidation> {
        GlobalScope.launch(Dispatchers.IO) {
            val consumerValidation = OvhService.Request().getConsumerValidation(credentials)
            _consumerValidationLivedata.postValue(consumerValidation)
            consumerValidation?.run {
                log("Consumer validation: $this")
                ck = consumerKey
                ping(validationUrl)
            }
        }
        return _consumerValidationLivedata
    }

    private fun getHash(method: String, url: String): Signature {
        val timestamp = ping(OVH_TIMESTAMP_URL)
        val secret = "$APP_SECRET+$ck+$method+$url+$timestamp"
        val sha1 = "$1$${SHA1.sha1Hash(secret).toLowerCase()}"
        log("SECRET = $secret")
        log("SHA1 = $sha1")
        return Signature(timestamp, sha1)
    }

    fun receiveContainerContent(): LiveData<String> {
        GlobalScope.launch(Dispatchers.IO) {
            val signature = getHash("GET","${OVH_URL}cloud/+project/$SERVICE_NAME/storage/$CONTAINER_MESSAGERIE")
            val headers = mapOf(
                Pair(XOvh.APPLICATION.type, APP_KEY),
                Pair(XOvh.TIMESTAMP.type, signature.timestamp),
                Pair(XOvh.SIGNATURE.type, signature.sha1),
                Pair(XOvh.CONSUMER.type, ck))

            val containerContent = OvhService.Request().getContainerContent(headers)
            _containerContentLivedata.postValue(containerContent)
        }
        return _containerContentLivedata
    }
}