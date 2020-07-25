package com.andysoft.test.base

interface UICallbacks<V> {

    fun getLayoutId(): Int
    fun getViewModel(): Class<V>
    fun onBinding()
    fun viewModelListener()
}