package com.example.domain.usecase

import com.example.domain.entity.UserEntity
import com.example.domain.repository.Repository
import javax.inject.Inject

class DeleteUserUseCase @Inject constructor(private val repository: Repository) {

    operator fun invoke(userEntity: UserEntity) = repository.deleteUser(userEntity)
}