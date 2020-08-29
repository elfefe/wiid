package com.elfefe.coffeejoin.oltp

import androidx.room.*
import com.elfefe.coffeejoin.controllers.BaseApplication
import com.elfefe.coffeejoin.oltp.dao.ChannelCommunityDao
import com.elfefe.coffeejoin.oltp.dao.MessageDao
import com.elfefe.coffeejoin.oltp.model.ChannelCommunity
import com.elfefe.coffeejoin.oltp.model.Communication
import com.elfefe.coffeejoin.utility.Converter

@Database(
    entities = [
        Communication::class,
        ChannelCommunity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converter::class)
abstract class CoffeejoinOLTP : RoomDatabase() {

    abstract fun messageDao(): MessageDao
    abstract fun channelCommunityDao(): ChannelCommunityDao

    companion object {
        val INSTANCE: CoffeejoinOLTP by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            Room.databaseBuilder(
                BaseApplication.INSTANCE,
                CoffeejoinOLTP::class.java,
                "CoffeejoinOLTP"
            ).build()
        }
    }
}