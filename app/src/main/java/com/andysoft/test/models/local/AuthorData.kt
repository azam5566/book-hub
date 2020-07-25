package com.andysoft.test.models.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "author_data")
data class AuthorData(@PrimaryKey var authorName: String){
}