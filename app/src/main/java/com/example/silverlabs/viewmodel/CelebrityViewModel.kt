package com.example.silverlabs.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.silverlabs.datamodel.Celebrity
import com.example.silverlabs.rest.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CelebrityViewModel() : ViewModel() {
    private lateinit var clelebrities: MutableLiveData<List<Pair<String,Celebrity>>>
    fun getClelebrities(): MutableLiveData<List<Pair<String,Celebrity>>> {
        if (!::clelebrities.isInitialized) {
            clelebrities = MutableLiveData()
            loadClelebrities()
        }
        loadClelebrities()
        return clelebrities
    }
    private fun loadClelebrities() {
            val apiInterface = ApiInterface.create().getClelebrities()
            apiInterface.enqueue( object : Callback<List<Pair<String,Celebrity>>> {
                override fun onResponse(call: Call<List<Pair<String,Celebrity>>>?, response: Response<List<Pair<String,Celebrity>>>?) {
                    clelebrities.value = response?.body()
                }
                override fun onFailure(call: Call<List<Pair<String,Celebrity>>>?, t: Throwable?) {

                }
            })
        }
}