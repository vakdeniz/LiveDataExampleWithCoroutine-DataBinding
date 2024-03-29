package com.vakdeniz.livedataexample.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vakdeniz.livedataexample.networking.RestApiService

class MainViewModel : ViewModel() {

    private val movieRepository = BlogRepository(RestApiService.init().getPopularBlogAsync())
    val allMutableBlogs: MutableLiveData<PopularBlogsResult>? =
        movieRepository.popularBlogsMutableLiveData.also { movieRepository.getPopularBlogsCoroutineCall() }

    override fun onCleared() {
        super.onCleared()
        movieRepository.completableJob.cancel()
    }
}