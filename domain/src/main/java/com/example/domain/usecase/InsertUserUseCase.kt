package com.example.domain.usecase

import com.example.domain.entity.UserEntity
import com.example.domain.repository.Repository
import io.reactivex.Single
import javax.inject.Inject

class InsertUserUseCase @Inject constructor(private val repository: Repository) {

    operator fun invoke(user: UserEntity): Single<Long> = repository.insertUser(user)
}