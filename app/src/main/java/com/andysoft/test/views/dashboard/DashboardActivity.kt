package com.andysoft.test.views.dashboard

import android.annotation.SuppressLint
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.andysoft.test.R
import com.andysoft.test.base.BaseActivity
import com.andysoft.test.databinding.ActivityDashboardBinding
import com.andysoft.test.views.addBook.AddBookFragment
import com.andysoft.test.views.dashboard.adapters.BookListAdapter
import org.jetbrains.annotations.TestOnly

class DashboardActivity : BaseActivity<ActivityDashboardBinding, DashboardViewModel>() {
    private lateinit var bookListAdapter: BookListAdapter
    private var listSortedBy = ""
    override fun getLayoutId() = R.layout.activity_dashboard

    override fun getViewModel() = DashboardViewModel::class.java

    @SuppressLint("SetTextI18n")
    override fun onBinding() {
        bookListAdapter = BookListAdapter(this)


        mBinding.apply {
            recyclerView.layoutManager = LinearLayoutManager(this@DashboardActivity)
            recyclerView.adapter = bookListAdapter
        }

        mBinding.add.setOnClickListener {
            supportFragmentManager.beginTransaction().add(R.id.container, AddBookFragment())
                .commit()
        }

        userDao.getAllBookData().observe(this, Observer {
            if (it.isNotEmpty()) {
                mBinding.arrowWrapper.visibility = View.GONE
                bookListAdapter.populate(it.toMutableList())
            }
            else{
                mBinding.arrowWrapper.visibility = View.VISIBLE
            }
        })
    }

    override fun viewModelListener() {

    }

    @Suppress("unused", "UNUSED_VARIABLE")
    @TestOnly
    fun setTestViewModel(testViewModel: DashboardViewModel) {
        val viewModel = testViewModel
    }
}