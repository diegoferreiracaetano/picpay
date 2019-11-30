package com.diegoferreiracaetano.data.local.user

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "user", indices = arrayOf(Index(value = ["nameQuery"])))
internal class UserEntity(
   @PrimaryKey val id: Int,
   val name: String,
   val nameQuery: String,
   val username: String,
   val img: String?
)
