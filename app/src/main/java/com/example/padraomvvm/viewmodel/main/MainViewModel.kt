package com.example.padraomvvm.viewmodel.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.padraomvvm.models.Live
import com.example.padraomvvm.repositories.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel constructor(private val repository: MainRepository) : ViewModel() {

    var liveList = MutableLiveData<List<Live>>()
    var erroMessage = MutableLiveData<String>()

    fun  getAllLives(){

        val request = repository.getAllLives()

        request.enqueue( object : Callback<List<Live>> {
            override fun onResponse(call: Call<List<Live>>, response: Response<List<Live>>) {
                 // quando ouver uma resposta
                liveList.postValue(response.body())

            }

            override fun onFailure(call: Call<List<Live>>, t: Throwable) {
               // quando ouver uma falha na resposta
                erroMessage.postValue(t.message)
            }


        })

    }

}