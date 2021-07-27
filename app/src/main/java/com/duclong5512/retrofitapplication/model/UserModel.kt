package com.duclong5512.retrofitapplication.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class Users(
    val meta: Meta,
    val data: List<UserGetData>
)

data class Meta(
    val pagination: Pagination
)
data class Pagination(
    val total: Int,
    val pages: Int,
    val page: Int,
    val limit: Int,
    val links: Links
)

data class Links(
    val previous: String,
    val current: String,
    val next: String
)

data class User(
    val meta: String,
    val userGetData: UserGetData
)

@Parcelize
data class UserGetData(
    val id: Int,
    val name: String,
    val email: String,
    val gender: String,
    val status: String
) : Parcelable

data class UserUpdateData(
    val name: String,
    val email: String,
    val gender: String,
    val status: String
)

