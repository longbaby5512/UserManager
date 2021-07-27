package com.duclong5512.retrofitapplication.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.duclong5512.retrofitapplication.R
import com.duclong5512.retrofitapplication.adapter.UserAdapter
import com.duclong5512.retrofitapplication.model.UserGetData
import com.duclong5512.retrofitapplication.repository.Repository
import com.duclong5512.retrofitapplication.viewmodel.UserViewModel
import com.duclong5512.retrofitapplication.viewmodel.UserViewModelFactory
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment : Fragment(), UserAdapter.ItemClickListener {
    private lateinit var userViewModel: UserViewModel
    private lateinit var users: List<UserGetData>
    private var pages : Int = 99
    private var page = 1
    private var adapter : UserAdapter? = null
    private var rvListUser : RecyclerView? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        Log.d("ListFragment", "onCreateView")

        adapter = UserAdapter(this)

        rvListUser = view.rvListUser
        rvListUser?.layoutManager = LinearLayoutManager(requireContext())
        rvListUser?.adapter = adapter

        val repository = Repository()
        val viewModelFactory = UserViewModelFactory(repository)
        userViewModel = ViewModelProvider(this, viewModelFactory).get(UserViewModel::class.java)

        updatePage(view, page)
        checkButtonEnabled(view)

        view.btnNextPage.setOnClickListener{
            if (page < pages) {
                page++
            }
            updatePage(view, page)
            checkButtonEnabled(view)
        }

        view.btnPreviousPage.setOnClickListener {
            if (page > 1) {
                page--
            }
            updatePage(view, page)
            checkButtonEnabled(view)

        }

        view.btnAddUser.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        return view
    }

    override fun onDetach() {
        super.onDetach()
        adapter = null
        rvListUser?.adapter = null
        rvListUser = null
    }

    @SuppressLint("SetTextI18n")
    private fun updateList(v: View, users: List<UserGetData>, page: Int) {
        this.users = users
        adapter?.setData(users)
        v.tvPage.text = "$page in $pages"
    }

    private fun updatePage(v: View, page: Int) {
        userViewModel.getUsers(page)
        userViewModel.repositoryUsers.observe(viewLifecycleOwner) {
            if (it.isSuccessful) {
                pages = it.body()?.meta?.pagination?.pages!!
                updateList(v, it.body()?.data!!, page)
            } else {
                Log.e("Response", it.errorBody().toString())
            }
        }
    }

    private fun checkButtonEnabled(v: View) {
        v.btnPreviousPage.isEnabled = page != 1
        v.btnNextPage.isEnabled = page != pages
    }

    override fun onClick(v: View, position: Int) {
        val action = ListFragmentDirections.actionListFragmentToUpdateFragment(users[position])
        findNavController().navigate(action)
    }
}