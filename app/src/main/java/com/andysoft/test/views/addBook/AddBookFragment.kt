package com.andysoft.test.views.addBook

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.media.MediaScannerConnection
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.widget.ArrayAdapter
import androidx.activity.addCallback
import androidx.lifecycle.MutableLiveData
import com.andysoft.test.R
import com.andysoft.test.base.BaseFragment
import com.andysoft.test.databinding.AddBookFragmentBinding
import com.andysoft.test.models.local.BookData
import com.andysoft.test.utils.dialogs.DialogBuilder
import com.andysoft.test.utils.dialogs.DialogListener
import com.andysoft.test.utils.extensions.READABLE_FORMAT
import com.andysoft.test.utils.extensions.toFormat
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

class AddBookFragment : BaseFragment<AddBookFragmentBinding, AddBookViewModel>() {
    private var date: Calendar = Calendar.getInstance()
    var image1 = MutableLiveData<Bitmap?>(null)
    var image2 = MutableLiveData<Bitmap?>(null)
    var image3 = MutableLiveData<Bitmap?>(null)

    private val IMAGE_DIRECTORY = "/BookHub/images"

    var listAuthor = mutableListOf<String>()

    override fun getLayoutId() = R.layout.add_book_fragment

    override fun getViewModel() = AddBookViewModel::class.java

    override fun onBinding() {

        mBinding.doi.setOnClickListener {
            fragmentManager?.let {
                context?.let { it1 ->
                    DialogBuilder.showDateDialog(
                        it1,
                        null,
                        it,
                        object : DialogListener {
                            override fun onDateSelection(cal: Calendar) {
                                super.onDateSelection(cal)
                                date = cal
                                mBinding.doi.setText(cal.toFormat(READABLE_FORMAT))
                            }
                        })
                }
            }
        }
        val pickPhoto = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        mBinding.cover1.setOnClickListener {
            startActivityForResult(pickPhoto, 1)
        }
        mBinding.cover2.setOnClickListener {
            startActivityForResult(pickPhoto, 2)
        }
        mBinding.cover3.setOnClickListener {
            startActivityForResult(pickPhoto, 3)
        }

        mBinding.createBook.setOnClickListener {
            if (mBinding.authorName.text.isNullOrEmpty() || mBinding.bookName.text.isNullOrEmpty() || mBinding.price.text.isNullOrEmpty() || image1.value == null) {
                Snackbar.make(mBinding.createBook, "Please fill all details", Snackbar.LENGTH_SHORT)
                    .show()
            } else {
                image1.value?.let {
                    saveImage(it, date.timeInMillis, "image1")
                }
                image2.value?.let {
                    saveImage(it, date.timeInMillis, "image2")
                }
                image3.value?.let {
                    saveImage(it, date.timeInMillis, "image3")
                }
                CoroutineScope(IO).launch {
                    userDao.insertBookData(
                        BookData(
                            mBinding.authorName.text.toString(),
                            mBinding.bookName.text.toString(),
                            mBinding.price.text.toString().trim().toFloat(),
                            date.timeInMillis,
                            arrayOf(
                                "image1${date.timeInMillis}.jpg",
                                "image2${date.timeInMillis}.jpg",
                                "image3${date.timeInMillis}.jpg"
                            )
                        )
                    )

                    withContext(Main) {
                        activity?.supportFragmentManager?.beginTransaction()
                            ?.remove(this@AddBookFragment)?.commit()
                    }
                }
            }
        }

        CoroutineScope(IO).launch {
            listAuthor = userDao.getAuthorData().toMutableList()
            withContext(Main) {
                mBinding.authorName.setAdapter(
                    context?.let {
                        ArrayAdapter(
                            it,
                            R.layout.dropdown_menu_popup_item,
                            listAuthor
                        )
                    }
                )
            }
        }

        image1.observe(this, androidx.lifecycle.Observer {
            it?.let {
                mBinding.ivCover1.setImageBitmap(it)
            }
        })
        image2.observe(this, androidx.lifecycle.Observer {
            it?.let {
                mBinding.ivCover2.setImageBitmap(it)
            }
        })
        image3.observe(this, androidx.lifecycle.Observer {
            it?.let {
                mBinding.ivCover3.setImageBitmap(it)
            }
        })

        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            activity?.supportFragmentManager?.beginTransaction()
                ?.remove(this@AddBookFragment)?.commit()
        }

        callback.isEnabled

    }

    override fun viewModelListener() {
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && data?.data != null) {
            val bitmap = try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    ImageDecoder.decodeBitmap(
                        ImageDecoder.createSource(
                            requireContext().contentResolver,
                            data.data!!
                        )
                    )
                } else {
                    MediaStore.Images.Media.getBitmap(
                        requireContext().contentResolver,
                        data.data
                    )
                }
            } catch (e: Exception) {
                null
            }
            when (requestCode) {
                1 -> {
                    image1.value = bitmap
                }
                2 -> {
                    image2.value = bitmap
                }
                3 -> {
                    image3.value = bitmap
                }
            }
        }
    }

    //this method return your folder image path
    fun saveImage(myBitmap: Bitmap, date: Long, name: String): String? {
        val bytes = ByteArrayOutputStream()
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val wallpaperDirectory = File(
            context?.getExternalFilesDir(null), IMAGE_DIRECTORY
        )
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs()
        }
        try {
            val f = File(
                wallpaperDirectory,
                "$name$date.jpg"
            )
            f.createNewFile()
            val fo = FileOutputStream(f)
            fo.write(bytes.toByteArray())
            MediaScannerConnection.scanFile(
                context,
                arrayOf(f.path),
                arrayOf("image/jpeg"),
                null
            )
            fo.close()
            return f.absolutePath
        } catch (e1: IOException) {
            e1.printStackTrace()
        }
        return ""
    }
}