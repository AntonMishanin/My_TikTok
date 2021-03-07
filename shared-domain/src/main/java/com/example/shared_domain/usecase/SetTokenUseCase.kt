package com.example.shared_domain.usecase

import com.example.shared_domain.repository.Repository
import javax.inject.Inject

class SetTokenUseCase @Inject constructor(private val repository: Repository) {

    operator fun invoke(token: Long) = repository.setToken(token)
}