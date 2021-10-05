package com.yasser.data.model.general

data class  GeneralApiResponse<T>(
    val message: String,
    val status: String,
    val data:T
)