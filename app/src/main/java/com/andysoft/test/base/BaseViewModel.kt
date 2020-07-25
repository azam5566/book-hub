package com.andysoft.test.base

import androidx.lifecycle.ViewModel
import com.andysoft.test.BaseApp


open class BaseViewModel : ViewModel(){

    val appDB = BaseApp.instance.appDB
    val userDao = BaseApp.instance.userDao
}