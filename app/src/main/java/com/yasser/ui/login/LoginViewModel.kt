package com.yasser.ui.login

import androidx.lifecycle.*
import com.yasser.data.DataRepository
import com.yasser.data.model.login.LoginRequest
import com.yasser.data.model.login.LoginResponseData
import com.yasser.shared.resource.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 *Created by Yasser.Elnagar on 28/09/2021
 */
@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: DataRepository, private val savedStateHandle: SavedStateHandle):ViewModel() {

    private val _loginApiWrapper = MutableLiveData<Resource<LoginResponseData>>()
    val loginApiWrapper :LiveData<Resource<LoginResponseData>> get() = _loginApiWrapper


    private val _loginRequestLiveData = MutableLiveData<LoginRequest>()
    val loginRequestLiveData :LiveData<LoginRequest> get() = _loginRequestLiveData



    init {
        Timber.v("${this.javaClass} created")
    }

    fun onLoginClick(){
        if (loginRequestLiveData.value?.email.isNullOrEmpty()){

        }
        else if (loginRequestLiveData.value?.password.isNullOrEmpty()){

        }
        else{
            login(loginRequestLiveData.value!!)
        }
    }

    fun login(loginRequest: LoginRequest){
        viewModelScope.launch {
            Timber.v("request login with :$loginRequest")
            _loginApiWrapper.value= Resource.Loading()
            val loginResource = repository.login(loginRequest)
            _loginApiWrapper.value=loginResource
            Timber.v("login response arrived with :$loginResource")
        }
    }



    override fun onCleared() {
        super.onCleared()
        Timber.v("${this.javaClass} cleared")

    }
}