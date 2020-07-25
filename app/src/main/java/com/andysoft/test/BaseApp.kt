package com.andysoft.test

import android.app.Application
import android.content.Context
import com.andysoft.test.di.components.AppComponent
import com.andysoft.test.di.components.DaggerAppComponent
import com.andysoft.test.di.modules.AppModule
import com.andysoft.test.room.AppDB
import com.andysoft.test.room.UserDao
import javax.inject.Inject

class BaseApp : Application() {
    @Inject
    internal lateinit var ctx: Context

    // Room Database Dao
    @Inject
    internal lateinit var appDB: AppDB
    @Inject
    internal lateinit var userDao: UserDao

    lateinit var component: AppComponent


    companion object {
        lateinit var instance: BaseApp
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        component = createComponent()
        component.getActivityComponentBuilder().build().inject(this)

    }

    fun createComponent() = DaggerAppComponent.builder().appModule(AppModule(this)).build()

    fun getAppComponent() = component
}