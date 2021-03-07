package com.example.shared_domain.usecase

import com.example.shared_domain.entity.UserEntity
import com.example.shared_domain.repository.Repository
import javax.inject.Inject

class DeleteUserUseCase @Inject constructor(private val repository: Repository) {

    operator fun invoke(userEntity: UserEntity) = repository.deleteUser(userEntity)
}