package com.mobile.azrinurvani.databindingdemo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//TODO 9 - Create MainActivityViewModel
class MainActivityViewModel : ViewModel() {

    lateinit var recyclerViewListData : MutableLiveData<RecyclerList>

    lateinit var recyclerViewAdapter: RecyclerViewAdapter

    init {
        recyclerViewListData = MutableLiveData()
        recyclerViewAdapter = RecyclerViewAdapter()
    }

    fun getRecyclerListDataObserver() : MutableLiveData<RecyclerList> {
        return recyclerViewListData
    }

    fun makeApiCall(input : String){
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)

        val call = retroInstance.getDataFromApi(input)
        call.enqueue(object : Callback<RecyclerList>{
            override fun onFailure(call: Call<RecyclerList>, t: Throwable) {
                //Hal seperti ini tidak bisa dilakukan jikalau return nya berupa LiveData() bukan MutableLiveData
                //Karena LiveData tidak bisa dimodifikasi
                recyclerViewListData.postValue(null)
            }

            override fun onResponse(call: Call<RecyclerList>, response: Response<RecyclerList>) {
                if (response.isSuccessful){
                    recyclerViewListData.postValue(response.body())
                }else{
                    recyclerViewListData.postValue(null)
                }
            }

        })

    }

    /*Adapter*/
    fun getAdapter(): RecyclerViewAdapter{
        return recyclerViewAdapter
    }
    fun setAdapterData(data : ArrayList<RecyclerData>){
        recyclerViewAdapter.setDataLists(data)
        recyclerViewAdapter.notifyDataSetChanged()
    }

}