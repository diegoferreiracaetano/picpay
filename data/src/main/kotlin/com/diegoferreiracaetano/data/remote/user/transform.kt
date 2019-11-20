package com.diegoferreiracaetano.data.remote.user

import com.diegoferreiracaetano.domain.user.User

internal fun UserEntity.transform() = User(id, name, username, img)

internal fun List<UserEntity>.transform() = map { it.transform() }
