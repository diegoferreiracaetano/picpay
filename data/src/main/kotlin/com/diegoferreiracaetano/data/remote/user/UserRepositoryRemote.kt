package com.diegoferreiracaetano.data.remote.user

import com.diegoferreiracaetano.data.remote.PicpayApi
import com.diegoferreiracaetano.domain.user.User
import com.diegoferreiracaetano.domain.user.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class UserRepositoryRemote(private val api: PicpayApi) : UserRepository {

    override fun users(string: String): Flow<List<User>> = api.users().map { it.transform() }

    override fun user(id: Long): Flow<User> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun save(user: List<User>): Flow<List<Long>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
