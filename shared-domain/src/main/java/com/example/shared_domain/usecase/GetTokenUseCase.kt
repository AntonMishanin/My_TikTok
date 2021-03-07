package com.example.shared_domain.usecase

import com.example.shared_domain.repository.Repository
import javax.inject.Inject

class GetTokenUseCase @Inject constructor(private val repository: Repository) {

    operator fun invoke() = repository.getToken()
}