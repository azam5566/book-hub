package com.andysoft.test.views

import com.andysoft.test.base.BaseViewModel
import com.andysoft.test.models.local.AuthorData
import com.andysoft.test.utils.Constants
import com.andysoft.test.utils.shared_prefrence.SharedPref
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class SplashViewModel : BaseViewModel() {
    var authorNameList: List<AuthorData> =
        listOf(AuthorData("William Shakespeare"), AuthorData("Agatha Christie"))

    fun addAuthorName() {
        SharedPref.getInstance().setStringPreference(Constants.Data.IS_AUTHOR_ADDED, "AUTHOR_ADDED")
        CoroutineScope(IO).launch {
            userDao.insertAuthorData(authorNameList.toList())
        }
    }
}