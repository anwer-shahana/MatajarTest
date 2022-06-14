package com.example.testmatajar.views.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testmatajar.network.ConnectionManager
import com.example.testmatajar.network.ResultCallback
import com.example.testmatajar.provider.AddProductRequest
import com.example.testmatajar.provider.ArrayOtherImages
import com.example.testmatajar.provider.MainResponse
import com.example.testmatajar.provider.OrderItemProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private var itemList = MutableLiveData<ArrayList<OrderItemProvider>>()
    private val clickOptions = MutableLiveData<Int>()
    private val errorMsg = MutableLiveData<String>()
    val showProgress = MutableLiveData<Boolean>()
    init {
        getItemsList()
    }
     fun getItemsList() {
         Timber.d("getItemsList --> getItemsList")
        coroutineScope.launch {
            showProgress.value = true
            ConnectionManager.getDataManager().addProduct(
                AddProductRequest(20,"",60,
                false,"6232def93142bf316c2ed43a"),

                object : ResultCallback<MainResponse<List<OrderItemProvider>>> {
                    override fun onError(code: Int, errorMessage: String) {
                        Timber.d("error --> $errorMessage")
                        showProgress.value = false
                        errorMsg.value = errorMessage
                    }

                    override fun <T> onSuccess(response: T) {
                        showProgress.value = false

                        if (response is MainResponse<*>) {
                            Timber.d("response --> $response")
                            val orderList = response.data as ArrayList<OrderItemProvider>
                            itemList.value = orderList


                        }
                    }
                })
        }
    }



    override fun onCleared() {
        viewModelJob.cancel()
        super.onCleared()
    }

    fun getProducts(): LiveData<ArrayList<OrderItemProvider>> {
        return itemList
    }


    fun getError(): LiveData<String> {
        return errorMsg
    }

    fun getClickOption(): LiveData<Int> {
        return clickOptions
    }

    fun onClicked(click: Int) {
        clickOptions.value = click
    }

//    fun searchApi(text: String) {
//        page = 1
//        endOfPage = false
//        searchKey.value = text
//        getProductList()
//    }


//    fun pageSwiped() {
//        page = 1
//        endOfPage = false
//        getProductList()
//    }
}