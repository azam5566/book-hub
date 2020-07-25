package com.andysoft.test.di.components

import android.app.Application
import android.content.SharedPreferences
import com.andysoft.test.di.modules.AppModule
import com.andysoft.test.di.modules.DatabaseModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class, DatabaseModule::class])
interface AppComponent {

    fun provideApplication(): Application
    fun provideSharedPrefs(): SharedPreferences

    fun getActivityComponentBuilder(): ActivityComponent.Builder

}