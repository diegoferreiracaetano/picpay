package com.diegoferreiracaetano.data.local.user

import com.diegoferreiracaetano.data.util.unaccent
import com.diegoferreiracaetano.domain.user.User

internal fun UserEntity.transform() = User(id, name, username, img)

internal fun List<UserEntity>.transform() = map { it.transform() }

internal fun User.transform() = UserEntity(id, name, name.unaccent(), username, img)

internal fun List<User>.transformEntity() = map { it.transform() }

