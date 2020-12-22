package com.example.splash_feature

import androidx.lifecycle.ViewModel
import com.example.data.RepositoryImpl

class SplashViewModel: ViewModel() {

    fun onViewCreated(
        repository: RepositoryImpl,
        navigator: SplashNavigator
    ) {
        val token = repository.getToken()

        if(token==-1){
            navigator.navigateToAuthorization()
        }else{
            navigator.navigateToProfile()
        }
    }
}