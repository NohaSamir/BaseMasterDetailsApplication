package com.example.basemasterdetailsapplication

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.basemasterdetailsapplication.database.DataDao
import com.example.basemasterdetailsapplication.database.DataDatabase
import com.example.basemasterdetailsapplication.models.DummyData
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class DatabaseTest {

    private lateinit var dataDao: DataDao
    private lateinit var db: DataDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, DataDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()

        dataDao = db.dataDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {

        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetData() {
        val item = DummyData("1", 10.0, "test", "")
        dataDao.insert(listOf(item))
        val allData = dataDao.getAllData()
        assertEquals(allData.value?.size, 1)
    }
}