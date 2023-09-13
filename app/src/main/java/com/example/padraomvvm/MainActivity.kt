package com.example.padraomvvm

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.padraomvvm.adapters.MainAdapter
import com.example.padraomvvm.databinding.ActivityMainBinding
import com.example.padraomvvm.repositories.MainRepository
import com.example.padraomvvm.rest.RetrofitService
import com.example.padraomvvm.viewmodel.main.MainViewModel
import com.example.padraomvvm.viewmodel.main.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    // variavel de iniação tardia
    lateinit var viewModel: MainViewModel
    private var retrofitService = RetrofitService.getInstance()
    private var adapter = MainAdapter{
       openLink(it.link)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, MainViewModelFactory(MainRepository(retrofitService))).get(
            MainViewModel::class.java
        )

        binding.recyclerView.adapter = adapter



    }


    override fun onStart() {
        super.onStart()

        viewModel.liveList.observe(this, Observer{ lives ->
            adapter.setLiveList(lives)
        })

        viewModel.erroMessage.observe(this, Observer{ message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onResume() {
        super.onResume()

        viewModel.getAllLives()
    }

    private fun openLink(link: String) {

        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        startActivity(browserIntent)

    }
}