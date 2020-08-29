package com.elfefe.coffeejoin.mvvm.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.elfefe.coffeejoin.oltp.CoffeejoinOLTP
import com.elfefe.coffeejoin.oltp.model.ChannelCommunity
import com.elfefe.coffeejoin.utility.log
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ChannelRepository {
    private val dao by lazy { CoffeejoinOLTP.INSTANCE.channelCommunityDao() }
    private val firestore by lazy { FirebaseFirestore.getInstance() }

    init {
        loadAllChannels()
    }

    fun getAllChannels(): Flow<List<ChannelCommunity>> =  dao.getAllChannels()

    fun sendChannels(channelCommunity: Array<out ChannelCommunity>) {
        GlobalScope.launch(Dispatchers.IO) {
            log("Inserting $channelCommunity")
            dao.insert(channelCommunity.asList())
        }
    }

    private fun loadAllChannels() {
        GlobalScope.launch(Dispatchers.IO) {
            firestore.collection(COLLECTION_CHANNEL_COMMUNITY).addSnapshotListener { querySnapshot, _ ->
                querySnapshot?.apply {
                    val documents = documents.mapNotNull {
                        it.toObject(ChannelCommunity::class.java)
                    }
                    sendChannels(documents.toTypedArray())
                }
            }
        }
    }

    fun sendChannelCommunity(channel: ChannelCommunity) {
        GlobalScope.launch(Dispatchers.IO) {
            firestore
                .collection(COLLECTION_CHANNEL_COMMUNITY)
                .document(channel.name)
                .set(channel)
        }
    }

    fun getChannelByName(name: String) = dao.getChannelByName(name)

    companion object {
        val DB_ID = "coffeejoin-407fa"
        val COLLECTION_CHANNEL_COMMUNITY = "Community channel"
    }
}