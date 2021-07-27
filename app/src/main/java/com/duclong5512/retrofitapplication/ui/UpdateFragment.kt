package com.duclong5512.retrofitapplication.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.duclong5512.retrofitapplication.R
import com.duclong5512.retrofitapplication.model.UserUpdateData
import com.duclong5512.retrofitapplication.repository.Repository
import com.duclong5512.retrofitapplication.utils.checkInput
import com.duclong5512.retrofitapplication.viewmodel.UserViewModel
import com.duclong5512.retrofitapplication.viewmodel.UserViewModelFactory
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {
    private lateinit var userViewModel: UserViewModel
    private val args by navArgs<UpdateFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        initView(view)

        setHasOptionsMenu(true)

        val repository = Repository()
        val viewModelFactory = UserViewModelFactory(repository)
        userViewModel = ViewModelProvider(this, viewModelFactory).get(UserViewModel::class.java)

        view.btnCommitUpdate.setOnClickListener {
            updateData(view)
        }
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_toolbar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menuDelete) {
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireActivity())
        builder.setPositiveButton("Yes") { _, _ ->
            userViewModel.deleteUser(args.currentUser.id)
            Toast.makeText(activity?.applicationContext, "Successful removed user has id ${args.currentUser.id}", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
            .setNegativeButton("No", null)
            .setTitle("Delete user id ${args.currentUser.id}?")
            .setMessage("Are you sure you want to delete  user id ${args.currentUser.id}?")
            .create()
            .show()
    }

    private fun initView(v: View) {
        v.edtNameUpdate.setText(args.currentUser.name)
        v.edtEmailUpdate.setText(args.currentUser.email)
        v.cbStatusUpdate.isChecked = args.currentUser.status == "active"
        if (args.currentUser.gender == "male") {
            v.rbtnMaleUpdate.isChecked = true
        } else {
            v.rbtnFemaleUpdate.isChecked = true
        }
    }

    private fun updateData(v: View) {
        val id = args.currentUser.id
        val name = v.edtNameUpdate.text.toString()
        val email = v.edtEmailUpdate.text.toString()
        val status = if (v.cbStatusUpdate.isChecked) "active" else "inactive"
        val gender = if (v.rbtnMaleUpdate.isChecked) "male" else "female"

        if (checkInput(name, email)) {
            val user = UserUpdateData(name, email, gender, status)
            userViewModel.updateUser(id, user)
            userViewModel.repositoryUserUpdateData.observe(viewLifecycleOwner) {
                if (it.isSuccessful) {
                    Toast.makeText(activity?.applicationContext, "Successfully updated id $id", Toast.LENGTH_LONG).show()
                    findNavController().navigate(R.id.action_updateFragment_to_listFragment)
                } else {
                    Toast.makeText(activity?.applicationContext, "Fail updated id $id", Toast.LENGTH_LONG).show()
                }
            }
        } else {
            Toast.makeText(activity?.applicationContext, "Please fill out all fields", Toast.LENGTH_LONG).show()
        }
    }
}