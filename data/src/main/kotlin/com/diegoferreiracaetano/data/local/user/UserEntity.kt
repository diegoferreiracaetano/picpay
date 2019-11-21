package com.diegoferreiracaetano.data.local.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
internal class UserEntity(
   @PrimaryKey val id: Int,
   val name: String,
   val username: String,
   val img: String?
)
