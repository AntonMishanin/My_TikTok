package com.example.domain.usecase

import com.example.domain.entity.UserEntity
import com.example.domain.repository.Repository
import io.reactivex.Flowable
import javax.inject.Inject

class GetUserByNameUseCase @Inject constructor(private val repository: Repository) {

    operator fun invoke(userName: String): Flowable<UserEntity> = repository.getUserByName(userName)
}