package com.yasser.data.model.login

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 *Created by Yasser.Elnagar on 27/09/2021
 */
data class LoginRequest(val email:String,val password:String)