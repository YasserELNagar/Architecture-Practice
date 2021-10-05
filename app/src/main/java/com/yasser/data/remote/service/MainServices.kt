package com.yasser.data.remote.service

import com.yasser.data.model.general.GeneralApiResponse
import com.yasser.data.model.login.LoginRequest
import com.yasser.data.model.login.LoginResponseData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

/**
 *Created by Yasser.Elnagar on 28/09/2021
 */
interface MainServices {


    @POST("auth/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ):Response<GeneralApiResponse<LoginResponseData>>


}