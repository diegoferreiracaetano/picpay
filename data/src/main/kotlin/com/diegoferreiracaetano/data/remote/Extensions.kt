package com.diegoferreiracaetano.data.remote

import com.diegoferreiracaetano.data.remote.user.UserEntity
import com.diegoferreiracaetano.domain.user.User

internal fun UserEntity.transform() = User(id, name, username, img)

internal fun List<UserEntity>.transform() = map { it.transform() }
