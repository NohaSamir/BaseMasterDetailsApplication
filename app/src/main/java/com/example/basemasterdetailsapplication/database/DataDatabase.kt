package com.example.basemasterdetailsapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.basemasterdetailsapplication.models.DummyData

@Database(entities = [DummyData::class], version = 1, exportSchema = false)
abstract class DataDatabase : RoomDatabase() {

    abstract val dataDao: DataDao

    companion object {

        @Volatile
        private var INSTANCE: DataDatabase? = null

        fun getInstance(context: Context): DataDatabase {
            synchronized(this)
            {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        DataDatabase::class.java,
                        "data_database")

                        .fallbackToDestructiveMigration()
                        .build()
                }
                INSTANCE = instance
                return instance
            }
        }
    }
}