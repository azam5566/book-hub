package com.andysoft.test.di.modules

import android.content.Context
import androidx.room.Room
import com.andysoft.test.room.AppDB
import com.andysoft.test.room.UserDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDb(context: Context): AppDB {
        return Room.databaseBuilder(context, AppDB::class.java, "app_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideUserDao(appDB: AppDB): UserDao = appDB.userDao()
}
