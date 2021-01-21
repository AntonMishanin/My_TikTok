package com.example.domain.usecase

import com.example.domain.entity.UserEntity
import com.example.domain.repository.Repository
import io.reactivex.Flowable
import javax.inject.Inject

class GetUserByIdUseCase @Inject constructor(private val repository: Repository)  {

    operator fun invoke(id: Int): Flowable<UserEntity> = repository.getUserById(id)
}