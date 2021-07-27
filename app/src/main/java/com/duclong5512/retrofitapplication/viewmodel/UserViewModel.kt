package com.duclong5512.retrofitapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duclong5512.retrofitapplication.model.UserGetData
import com.duclong5512.retrofitapplication.model.User
import com.duclong5512.retrofitapplication.model.UserUpdateData
import com.duclong5512.retrofitapplication.model.Users
import com.duclong5512.retrofitapplication.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class UserViewModel(private val repository: Repository) : ViewModel() {
    val repositoryUser: MutableLiveData<Response<User>> = MutableLiveData()
    val repositoryUsers: MutableLiveData<Response<Users>> = MutableLiveData()
    val repositoryUserUpdateData: MutableLiveData<Response<UserUpdateData>> = MutableLiveData()

    fun getUser(id: Int) {
        viewModelScope.launch {
            val response = repository.getUser(id)
            repositoryUser.value = response

        }
    }

    fun getUsers(page: Int) {
        viewModelScope.launch {
            val response = repository.getUsers(page)
            repositoryUsers.value = response
        }
    }

    fun createUser(user: UserUpdateData) {
        viewModelScope.launch {
            val response = repository.createUser(user)
            repositoryUserUpdateData.value = response
        }
    }

    fun updateUser(id: Int, user: UserUpdateData) {
        viewModelScope.launch {
            val response = repository.updateUser(id, user)
            repositoryUserUpdateData.value = response
        }
    }

    fun deleteUser(id: Int) {
        viewModelScope.launch {
            val response = repository.deleteUser(id)
            repositoryUserUpdateData.value = response
        }
    }
}