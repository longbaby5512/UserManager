package com.duclong5512.retrofitapplication.api

import com.duclong5512.retrofitapplication.model.User
import com.duclong5512.retrofitapplication.model.UserUpdateData
import com.duclong5512.retrofitapplication.model.Users
import retrofit2.Response
import retrofit2.http.*

interface UserApi {
    @GET("users/{id}")
    suspend fun getUser(@Path("id") id: Int) : Response<User>

    @GET("users")
    suspend fun getUsers(@Query("page") page: Int) : Response<Users>

    @POST("users")
    suspend fun createUser(@Body user: UserUpdateData) : Response<UserUpdateData>

    @PUT("users/{id}")
    suspend fun updateUser(@Path("id") id: Int, @Body user: UserUpdateData) : Response<UserUpdateData>

    @DELETE("users/{id}")
    suspend fun deleteUser(@Path("id") id: Int) : Response<UserUpdateData>
}