package com.bodakesatish.skeleton.data.source.local.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import androidx.test.runner.AndroidJUnit4
import com.bodakesatish.skeleton.data.source.local.database.MutualFundDatabase
import com.bodakesatish.skeleton.data.source.local.entity.SchemeMetaData
import junit.framework.TestCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class SchemeMetaDataDaoTest {

    // using an in-memory database because the information stored here disappears when the
    // process is killed
    private lateinit var database: MutualFundDatabase

    // Ensure that we use a new database for each test.
    @Before
    fun initDb() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            MutualFundDatabase::class.java
        ).allowMainThreadQueries().build()
    }

    @Test
    fun insertSchemeMetaAndGetById() = runBlocking {
        // GIVEN - insert a task
        val task = SchemeMetaData(
            1,"a","a","a","a","a"
        )
        database.schemeMetaDataDao().insert(task)

        // WHEN - Get the task by id from the database
        val loaded = database.schemeMetaDataDao().getSchemeMetaDataById(task.schemeCode)

        // THEN - The loaded data contains the expected values
        TestCase.assertNotNull(loaded as SchemeMetaData)
        assertEquals(task.schemeCode, loaded.schemeCode)
//        assertEquals(task.title, loaded.title)
//        assertEquals(task.description, loaded.description)
//        assertEquals(task.isCompleted, loaded.isCompleted)
    }

}