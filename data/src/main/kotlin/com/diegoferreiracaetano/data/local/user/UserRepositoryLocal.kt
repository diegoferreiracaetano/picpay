package com.diegoferreiracaetano.data.local.user

import com.diegoferreiracaetano.domain.user.User
import com.diegoferreiracaetano.domain.user.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

internal class UserRepositoryLocal(private val dao: UserDao) : UserRepository {

    override fun user(id: Long) = dao.user(id).map { it.transform() }

    override fun save(user: User) = flow {
        emit(dao.insert(user.transform()))
    }

    override fun users(): Flow<List<User>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
