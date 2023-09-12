package com.example.padraomvvm.repositories

import com.example.padraomvvm.rest.RetrofitService

class MainRepository constructor(private val retrofitService: RetrofitService){

    fun getAllLives() = retrofitService.getAllLives()

}