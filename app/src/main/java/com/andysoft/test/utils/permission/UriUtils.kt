package com.andysoft.test.utils.permission

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.provider.ContactsContract
import java.io.ByteArrayOutputStream


class UriUtils {

    companion object{

        fun pickContact(activity:Activity, resultCode:Int) {
            val contactPickerIntent = Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI)
            activity.startActivityForResult(contactPickerIntent, resultCode)
        }

        fun getBytes(bitmap: Bitmap) : ByteArray{
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
            return stream.toByteArray()
        }

        fun getImage(image : ByteArray) : Bitmap {
            return  BitmapFactory.decodeByteArray(image, 0, image.size)
        }

    }

}
