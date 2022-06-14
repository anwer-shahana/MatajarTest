package com.example.testmatajar.network



import com.example.testmatajar.network.RetroApi.retrofitService
import com.example.testmatajar.provider.AddProductRequest
import com.example.testmatajar.provider.ArrayOtherImages
import com.example.testmatajar.provider.MainResponse
import com.example.testmatajar.provider.OrderItemProvider
import com.example.testmatajar.utils.CommonUtils
import com.example.testmatajar.utils.Constants
import com.example.testmatajar.utils.SessionManager
import kotlinx.coroutines.withTimeout
import org.json.JSONObject
import retrofit2.Response
import timber.log.Timber


class ConnectionManager {

    companion object {
        private var connectionManager: ConnectionManager = ConnectionManager()
        fun getDataManager(): ConnectionManager {
            return connectionManager
        }
    }


    suspend fun addProduct(
       addProductRequest: AddProductRequest,
        callback: ResultCallback<MainResponse<List<OrderItemProvider>>>

    ) {


        val job = withTimeout(Constants.MILLISEC) {
            if (CommonUtils.isInternetAvailable()) {
                val getPropertiesDeferred = retrofitService.getProductAsync(addProductRequest)
                try {
                    val response = getPropertiesDeferred.await()
                    onResponseStatusReceived(response, callback)
                } catch (e: Exception) {
                    e.message?.let {
                        Timber.d("exception--> ${e.message}")
                        callback.onError(1, "Something went wrong. Please try again later.")
                    }
                }
            } else {
                callback.onError(2, "Check Your Connection and Try Again")
            }
        }
        if (job == null) {
            callback.onError(2, "")
        }

    }









//    suspend fun getCartProducts(
//        shopId: Int,
//        callback: ResultCallback<UserCartResponse<List<UserShopCartResponse>>>
//    ) {
//        val job = withTimeout(Constants.MILLISEC) {
//            if (CommonUtils.isInternetAvailable()) {
//                val getPropertiesDeferred = retrofitService.getUserCartAsync(shopId)
//                try {
//                    val response = getPropertiesDeferred.await()
//                    onResponseStatusReceived(response, callback)
//                } catch (e: Exception) {
//                    e.message?.let {
//                        Timber.d("exception--> ${e.message}")
//                        callback.onError(1, "Something went wrong. Please try again later.")
//                    }
//                }
//            } else {
//                callback.onError(2, "Check Your Connection and Try Again")
//            }
//        }
//        if (job == null) {
//            callback.onError(2, "")
//        }
//    }
//
//






//    suspend fun getProductDetails(
//        product_uuid: String,
//        callback: ResultCallback<MainResponse<ShopProductsProvider>>
//    ) {
//        val job = withTimeout(Constants.MILLISEC) {
//            if (CommonUtils.isInternetAvailable()) {
//                val getPropertiesDeferred = retrofitService.getProductDetailsAsync(product_uuid)
//                try {
//                    val response = getPropertiesDeferred.await()
//                    onResponseStatusReceived(response, callback)
//                } catch (e: Exception) {
//                    e.message?.let {
//                        Timber.d("exception--> ${e.message}")
//                        callback.onError(1, "Something went wrong. Please try again later.")
//                    }
//                }
//            } else {
//                callback.onError(2, "Check Your Connection and Try Again")
//            }
//        }
//        if (job == null) {
//            callback.onError(2, "")
//        }
//    }
//
//
//





    private fun <T> onResponseStatusReceived(response: Response<T>, callback: ResultCallback<T>) {
        Timber.d("onResponseStatusReceived--> $response")
        if (response.isSuccessful) {
            callback.onSuccess(response.body())
        } else {
            try {
                if (response.code() == 422) {

                    val reader = response.errorBody()?.string() ?: ""
                    val jsonObject = JSONObject(reader)
                    var error = ""
                    if (jsonObject.has("errors")) {
                        val mainErrorObject = jsonObject.getJSONObject("errors")
                        val iter = mainErrorObject.keys()
                        while (iter.hasNext()) {
                            val key = iter.next()
                            val errors = mainErrorObject.getJSONArray(key)
                            error = errors[0].toString()
                        }
                    }

                    callback.onError(response.code(), error)

                } else if (response.code() == 401 || response.code() == 403) {
                    SessionManager.clear()


                } else {

                    val reader = response.errorBody()?.string() ?: ""
                    val jsonObject = JSONObject(reader)
                    var error = ""
                    if (jsonObject.has("errors")) {
                        if (jsonObject.getJSONObject("errors").has("email")) {
                            val er = jsonObject.getJSONObject("errors").getJSONArray("email")
                            for (it in 0..er.length()) {
                                error = er[it] as String
                                break
                            }
                        } else if (jsonObject.getJSONObject("errors").has("password")) {
                            val er = jsonObject.getJSONObject("errors").getJSONArray("password")
                            for (it in 0..er.length()) {
                                error = er[it] as String
                                break
                            }
                        }
                    }

                    if (error == "") {
                        error = when {
                            jsonObject.has("message") -> {
                                jsonObject.get("message").toString()
                            }
                            jsonObject.has("error") -> {
                                jsonObject.get("error").toString()
                            }
                            else -> {
                                ""
                            }
                        }
                    }


                    callback.onError(response.code(), error)

                }

            } catch (e: Exception) {
                e.printStackTrace()
                callback.onError(1, "")
            }
        }
    }



}




