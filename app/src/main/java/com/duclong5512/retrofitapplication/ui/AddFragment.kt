package com.duclong5512.retrofitapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.duclong5512.retrofitapplication.R
import com.duclong5512.retrofitapplication.model.UserUpdateData
import com.duclong5512.retrofitapplication.repository.Repository
import com.duclong5512.retrofitapplication.utils.checkInput
import com.duclong5512.retrofitapplication.viewmodel.UserViewModel
import com.duclong5512.retrofitapplication.viewmodel.UserViewModelFactory
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {
    private lateinit var userViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add, container, false)
        val repository = Repository()
        val viewModelFactory = UserViewModelFactory(repository)
        userViewModel = ViewModelProvider(this, viewModelFactory).get(UserViewModel::class.java)
        view.btnCommitAdd.setOnClickListener {
            insertData(view)
        }
        return view
    }

    private fun insertData(v: View) {
        val name = v.edtNameAdd.text.toString()
        val email = v.edtEmailAdd.text.toString()
        val status = if (v.cbStatusAdd.isChecked) "active" else "inactive"
        val gender = if (v.rbtnMaleAdd.isChecked) "male" else "female"

        if (checkInput(name, email)) {
            val user = UserUpdateData(name, email, gender, status)
            userViewModel.createUser(user)
            userViewModel.repositoryUserUpdateData.observe(viewLifecycleOwner) {
                if (it.isSuccessful) {
                    Toast.makeText(activity?.applicationContext, "Successfully added", Toast.LENGTH_LONG).show()
                    findNavController().navigate(R.id.action_addFragment_to_listFragment)
                } else {
                    Toast.makeText(activity?.applicationContext, "The user $name had in database", Toast.LENGTH_LONG).show()
                }
            }

        } else {
            Toast.makeText(activity?.applicationContext, "Please fill out all fields", Toast.LENGTH_LONG).show()
        }

    }

}