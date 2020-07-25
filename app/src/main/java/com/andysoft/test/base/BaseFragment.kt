package com.andysoft.test.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.andysoft.test.BaseApp
import com.andysoft.test.di.components.AppComponent
import com.andysoft.test.room.AppDB
import com.andysoft.test.room.UserDao
import com.andysoft.test.utils.shared_prefrence.SharedPref

abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel> : Fragment(),
    UICallbacks<V> {

    protected lateinit var mBinding: T
    protected lateinit var mViewModel: V

    internal lateinit var ctx: Context
    internal lateinit var mSP: SharedPref

    internal lateinit var appDB: AppDB
    internal lateinit var userDao: UserDao

    private lateinit var component: AppComponent


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(getBaseActivity()).get(getViewModel())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        activity?.baseContext.let {
            if (it != null) {
                ctx = it
            }
        }
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        component = BaseApp.instance.getAppComponent()

        mSP = SharedPref.getInstance()

        appDB = BaseApp.instance.appDB
        userDao = BaseApp.instance.userDao

        onBinding()
        viewModelListener()
    }

    protected fun getBaseActivity() = activity as BaseActivity<*, *>


}