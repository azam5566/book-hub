package com.andysoft.test.models.local

import android.graphics.Bitmap
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class CoverPhoto() : Serializable {

    @SerializedName("image1")
    @Expose
    var image1: ByteArray? = null

    @SerializedName("image2")
    @Expose
    var image2: ByteArray? = null

    @SerializedName("image3")
    @Expose
    var image3: ByteArray? = null

    constructor(
        image1 : ByteArray?,
        image2 : ByteArray?,
        image3 : ByteArray?

    ): this(){
        this.image1 = image1
        this.image2 = image2
        this.image3 = image3
    }

}