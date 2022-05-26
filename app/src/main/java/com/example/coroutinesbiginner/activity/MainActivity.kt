package com.example.coroutinesbiginner.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.coroutinesbiginner.databinding.ActivityMainBinding
import com.example.coroutinesbiginner.utils.Status
import com.example.coroutinesbiginner.viewmodel.UserViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        initViews()
    }

    private fun initViews() {
        userViewModel.getUsers().observe(this, Observer {

            when(it.status){
                Status.LOADING ->{

                }
                Status.ERROR ->{

                }
                Status.SUCCESS ->{
                    binding.tv1.text = it.data
                }
            }
        })

    }
}