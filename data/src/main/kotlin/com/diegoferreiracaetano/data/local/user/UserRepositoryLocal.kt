package com.diegoferreiracaetano.data.local.user

import com.diegoferreiracaetano.data.util.unaccent
import com.diegoferreiracaetano.domain.user.User
import com.diegoferreiracaetano.domain.user.UserRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

internal class UserRepositoryLocal(private val dao: UserDao) : UserRepository {

    override fun user(id: Long) = dao.user(id).map { it.transform() }

    override fun save(user: List<User>) = flow {
        emit(dao.insert(user.transformEntity()))
    }

    override fun users(string: String) =
        dao.users(string.unaccent())
        .map { it.transform() }
}
