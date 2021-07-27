package com.duclong5512.retrofitapplication.utils

import android.text.TextUtils

const val BASE_URL = "https://gorest.co.in/public/v1/"
const val ACCESS_TOKEN = "855cb908729d3ff84ba34a8d1b102730ef5958b05822422ad8fa4926a2c0b243"

fun checkInput(name: String, email: String): Boolean {
    return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(email))
}
