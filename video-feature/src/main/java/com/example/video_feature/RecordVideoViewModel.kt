package com.example.video_feature

import androidx.lifecycle.ViewModel
import com.example.domain.usecase.GetUserByNameUseCase
import javax.inject.Inject

class RecordVideoViewModel @Inject constructor(private val getUserByNameUseCase: GetUserByNameUseCase): ViewModel() {
}