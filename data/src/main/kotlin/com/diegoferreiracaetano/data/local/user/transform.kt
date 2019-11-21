package com.diegoferreiracaetano.data.local.user

import com.diegoferreiracaetano.domain.user.User

internal fun UserEntity.transform() = User(id, name, username, img)

internal fun User.transform() = UserEntity(id, name, username, img)

