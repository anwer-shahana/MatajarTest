package com.example.testmatajar.network


interface ResultCallback<T> {
    fun onError(code: Int, errorMessage: String)
    fun <T> onSuccess(response:T)
}

