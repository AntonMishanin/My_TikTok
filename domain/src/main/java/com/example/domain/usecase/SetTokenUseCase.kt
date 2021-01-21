package com.example.domain.usecase

import com.example.domain.repository.Repository
import javax.inject.Inject

class SetTokenUseCase @Inject constructor(private val repository: Repository) {

    operator fun invoke(token: Long){
        repository.setToken(token)
    }
}