package com.example.basemasterdetailsapplication.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.basemasterdetailsapplication.models.DummyData

@Dao
interface DataDao {

    @Query("Select * from DummyData")
    fun getAllData(): LiveData<List<DummyData>>

    @Query("Select * from DummyData where id = :key ")
    fun get(key: String): DummyData

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(list: List<DummyData>)
}