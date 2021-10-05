package com.yasser.data.remote

import com.yasser.data.model.general.GeneralApiResponse
import com.yasser.data.model.login.LoginRequest
import com.yasser.data.model.login.LoginResponseData
import com.yasser.data.remote.service.MainServices
import com.yasser.shared.AppNetworkManager
import com.yasser.shared.resource.Error
import com.yasser.shared.resource.Resource
import com.yasser.shared.resource.ResponseCode
import com.google.gson.Gson
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

/**
 *Created by Yasser.Elnagar on 28/09/2021
 */
class RemoteData @Inject constructor(
    private val mainServices: MainServices,
    private val appNetworkManager: AppNetworkManager
) : RemoteDataSource {


    override suspend fun doLogin(loginRequest: LoginRequest): Resource<LoginResponseData> {
        return processCall { mainServices.login(loginRequest) }
    }


    private suspend fun <T> processCall(requestCall: suspend () -> Response<GeneralApiResponse<T>>): Resource<T> {

        if (!appNetworkManager.isConnected()) {

            return Resource.Failure(
                Error(
                    code = ResponseCode.NETWORK_NOT_AVAILABLE.value,
                    msg = null
                )
            )
        }


        return try {
            val response = requestCall.invoke()

            return when (response.code()) {
                ResponseCode.OK.value -> {
                    Resource.Success(response.body()!!.data)
                }
                else -> {

                    val error = try {
                        val errorResponse = Gson().fromJson(
                            response.errorBody()?.charStream(),
                            GeneralApiResponse::class.java
                        )

                        Error(code = response.code(), msg = errorResponse.message)

                    } catch (e: Exception) {
                        Timber.e(e)
                        Error(code = response.code())
                    }

                    return Resource.Failure(error)

                }
            }
        } catch (t: Throwable) {
            Resource.Failure(
                Error(
                    code = ResponseCode.NETWORK_ERROR.value,
                    msg = t.message.toString()
                )
            )
        }


    }


}