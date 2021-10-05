package com.yasser.data.remote

import com.yasser.data.model.login.LoginRequest
import com.yasser.data.model.login.LoginResponseData
import com.yasser.shared.resource.Resource

/**
 *Created by Yasser.Elnagar on 04/10/2021
 */
interface RemoteDataSource {
    suspend fun doLogin(loginRequest: LoginRequest): Resource<LoginResponseData>
}