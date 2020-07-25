package com.andysoft.test.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.andysoft.test.models.local.BookData
import com.andysoft.test.models.local.AuthorData

@Database(
    entities = [AuthorData::class, BookData::class],
    version = 1
)
@TypeConverters(CommonConverters::class,ADConverters::class)
abstract class AppDB : RoomDatabase() {
    abstract fun userDao(): UserDao
}
