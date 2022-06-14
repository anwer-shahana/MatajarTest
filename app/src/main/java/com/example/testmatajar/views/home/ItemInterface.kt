package com.example.testmatajar.views.home

import com.example.testmatajar.provider.OrderItemProvider

interface ItemInterface {


    fun cartOptions(type: String, position: Int, orderItemProvider: OrderItemProvider)
}