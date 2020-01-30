package com.example.basemasterdetailsapplication.data.source.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.basemasterdetailsapplication.data.source.database.models.DatabaseDummyData

@Dao
interface DataDao {

    @Query("Select * from DatabaseDummyData")
    fun getAllData(): LiveData<List<DatabaseDummyData>>

    @Query("Select * from DatabaseDummyData where id = :key ")
    fun get(key: String): DatabaseDummyData

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg list: DatabaseDummyData)
}