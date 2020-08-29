package com.elfefe.coffeejoin.oltp.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.elfefe.coffeejoin.oltp.model.Communication

@Dao
interface MessageDao {

    @Query("SELECT * from communication")
    fun getAlphabetizedWords(): LiveData<List<Communication>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(communication: List<Communication>)

    @Query("DELETE FROM communication")
    suspend fun deleteAll()

    @Delete()
    suspend fun delete(vararg communication: Communication)
}