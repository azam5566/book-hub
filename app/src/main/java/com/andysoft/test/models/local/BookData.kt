package com.andysoft.test.models.local

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books_data")
data class BookData(@PrimaryKey var doi : Long ){
    constructor(
        bookName: String?,
        authorName: String?,
        price: Float?,
        doi: Long,
        coverPhoto: Array<String>?
    ): this(doi){
        this.authorName = authorName
        this.price = price
        this.bookName = bookName
        this.coverPhoto = coverPhoto
    }

    var authorName : String? = null
    var price : Float? = null
    var bookName : String? = null
    var coverPhoto : Array<String>? = null
}