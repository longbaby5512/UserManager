package com.duclong5512.retrofitapplication.repository

import com.duclong5512.retrofitapplication.api.RetrofitInstance
import com.duclong5512.retrofitapplication.model.User
import com.duclong5512.retrofitapplication.model.UserUpdateData
import com.duclong5512.retrofitapplication.model.Users
import retrofit2.Response

class Repository {
    suspend fun getUser(id: Int): Response<User> = RetrofitInstance.API.getUser(id)
    suspend fun getUsers(page: Int): Response<Users> = RetrofitInstance.API.getUsers(page)
    suspend fun createUser(user: UserUpdateData) : Response<UserUpdateData> = RetrofitInstance.API.createUser(user)
    suspend fun updateUser(id: Int, user: UserUpdateData) : Response<UserUpdateData> = RetrofitInstance.API.updateUser(id, user)
    suspend fun deleteUser(id: Int) : Response<UserUpdateData> = RetrofitInstance.API.deleteUser(id)
}