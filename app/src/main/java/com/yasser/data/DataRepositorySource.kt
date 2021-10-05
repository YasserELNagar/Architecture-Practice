package com.yasser.data

import com.yasser.data.model.login.LoginRequest
import com.yasser.data.model.login.LoginResponseData
import com.yasser.shared.resource.Resource

/**
 *Created by Yasser.Elnagar on 04/10/2021
 */
interface DataRepositorySource {
    suspend fun login(loginRequest: LoginRequest): Resource<LoginResponseData>
}