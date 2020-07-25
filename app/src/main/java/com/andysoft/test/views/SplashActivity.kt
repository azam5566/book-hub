package com.andysoft.test.views

import android.content.Intent
import android.os.Handler
import com.andysoft.test.R
import com.andysoft.test.base.BaseActivity
import com.andysoft.test.databinding.ActivitySplashBinding
import com.andysoft.test.utils.Constants
import com.andysoft.test.views.dashboard.DashboardActivity

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {
    override fun getLayoutId() = R.layout.activity_splash

    override fun getViewModel() = SplashViewModel::class.java

    override fun onBinding() {
        Handler().postDelayed({
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }, 1 * 1000)

        if (mSP.getStringPreference(Constants.Data.IS_AUTHOR_ADDED) != "AUTHOR_ADDED") {
            mViewModel.addAuthorName()
        }

    }

    override fun viewModelListener() {
    }
}