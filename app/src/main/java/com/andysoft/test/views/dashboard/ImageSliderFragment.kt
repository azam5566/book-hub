package com.andysoft.test.views.dashboard

import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.andysoft.test.R
import com.andysoft.test.base.BaseFragment
import com.andysoft.test.databinding.ImageSliderFragmentBinding
import com.bumptech.glide.Glide


class ImageSliderFragment(val arrImage: Array<String>) :
    BaseFragment<ImageSliderFragmentBinding, ImageSliderViewModel>() {
    private val IMAGE_DIRECTORY = "/BookHub/images/"

    override fun getLayoutId() = R.layout.image_slider_fragment
    override fun getViewModel() = ImageSliderViewModel::class.java

    override fun onBinding() {
//        val arrView = arrayOf(mBinding.image1, mBinding.image2, mBinding.image3)
        for (i in arrImage.indices) {
            val strPath = "file://" + context?.getExternalFilesDir(null)
                .toString() + IMAGE_DIRECTORY + (arrImage[i])


            //ImageView Setup
            //ImageView Setup
            val imageView = ImageView(context)

            imageView.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            context?.let {
                Glide.with(it).asBitmap()
                    .load(Uri.parse(strPath))
                    .into(imageView)
            }

            mBinding.viewFlipper.addView(imageView)
        }

        mBinding.right.setOnClickListener {
            mBinding.viewFlipper.showNext()
        }
        mBinding.left.setOnClickListener {
            mBinding.viewFlipper.showPrevious()
        }

        mBinding.root.setOnClickListener{
            activity?.supportFragmentManager?.beginTransaction()
                ?.remove(this)?.commit()
        }
    }

    override fun viewModelListener() {
    }

}