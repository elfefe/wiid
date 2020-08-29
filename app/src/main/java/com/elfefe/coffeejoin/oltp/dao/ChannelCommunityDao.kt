package com.elfefe.coffeejoin.oltp.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.elfefe.coffeejoin.oltp.model.ChannelCommunity
import com.elfefe.coffeejoin.oltp.model.Communication
import kotlinx.coroutines.flow.Flow

@Dao
interface ChannelCommunityDao {
    @Query("SELECT * from channel_community")
    fun getAllChannels(): Flow<List<ChannelCommunity>>

    @Query("SELECT * from channel_community WHERE name = :name")
    fun getChannelByName(name: String): Flow<ChannelCommunity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(channels: List<ChannelCommunity>)

    @Query("DELETE FROM channel_community")
    suspend fun deleteAll()

    @Delete()
    suspend fun delete(vararg channel: ChannelCommunity)
}