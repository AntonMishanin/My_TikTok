package com.example.shared_domain.usecase

import com.example.shared_domain.entity.UserEntity
import com.example.shared_domain.repository.Repository
import io.reactivex.Single
import javax.inject.Inject

class InsertUserUseCase @Inject constructor(private val repository: Repository) {

    operator fun invoke(user: UserEntity): Single<Long> = repository.insertUser(user)
}