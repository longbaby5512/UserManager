package com.duclong5512.retrofitapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupActionBarWithNavController(findNavController(R.id.fragment))


        /* viewModel.getUser(2)
         viewModel.repositoryUser.observe(this) {
             if (it.isSuccessful) {
                 Log.d("Response", it.body()?.data?.name!!)
             } else {
                 Log.e("Response", it.errorBody().toString())
             }
         }*/
    }

    override fun onSupportNavigateUp() =
        findNavController(R.id.fragment).navigateUp() || super.onSupportNavigateUp()
}