package com.andysoft.test.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.andysoft.test.models.local.BookData
import com.andysoft.test.models.local.AuthorData

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAuthorData(authorName : List<AuthorData>)

    @Query("SELECT authorName FROM author_data")
    fun getAuthorData() : List<String>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBookData(bookData : BookData)

    @Query("SELECT * FROM books_data")
    fun getAllBookData() : LiveData<List<BookData>>
}