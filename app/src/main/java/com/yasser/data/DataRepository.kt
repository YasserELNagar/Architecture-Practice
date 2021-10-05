package com.yasser.data

import com.yasser.data.model.login.LoginRequest
import com.yasser.data.model.login.LoginResponseData
import com.yasser.data.remote.RemoteData
import com.yasser.shared.resource.Resource
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 *Created by Yasser.Elnagar on 04/10/2021
 */
class DataRepository @Inject constructor(private val remoteData: RemoteData, private val coroutineContext: CoroutineContext) :
    DataRepositorySource {

    override suspend fun login(loginRequest: LoginRequest): Resource<LoginResponseData> {
        return withContext(coroutineContext){
            remoteData.doLogin(loginRequest)
        }
    }

}