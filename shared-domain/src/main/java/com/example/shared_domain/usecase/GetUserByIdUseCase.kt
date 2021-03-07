package com.example.shared_domain.usecase

import com.example.shared_domain.entity.UserEntity
import com.example.shared_domain.repository.Repository
import io.reactivex.Flowable
import javax.inject.Inject

class GetUserByIdUseCase @Inject constructor(private val repository: Repository)  {

    operator fun invoke(id: Int): Flowable<UserEntity> = repository.getUserById(id)
}