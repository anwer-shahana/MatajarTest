package com.example.testmatajar.provider

import java.io.Serializable

data class MainResponse<T>(
    var success:Boolean,
    var message: String?,
    var count: Int,
    var data: T?
): Serializable




data class OrderItemProvider(
    var pkProductId: String?,
    var strProductName: String?,
    var strDescription: String?,
    var arrayThumbnail: List<ArrayThumbnail>?,
    var arrayOtherImages:List<ArrayOtherImages>?,
    var objSubCategory: ObjSubCategory?,
    var objShop: ObjShop,
    var arrayProductDetail: Any?,
    var intSellingPrice: Float?,
    var intDiscount: Int?,
    var intMRP: Float?,
    var intMaxQuantity: Int?,
    var intMinQuantity: Int?,
    var blnExpress: Boolean?,
    var blnNormal: Boolean?,
    var strBrandName: String?,
    var fkBrandId: String?,
    var blnStockAvailability: Boolean?,
    var intMinimumAmount: Int?,
    var strDisclaimer: String?,
    var sortNum: Int?
) : Serializable
data class ArrayThumbnail(

    var imageUrl: String?,
    var imageName: String?,
)

data class ArrayOtherImages(

    var imageUrl: String?,
    var imageName: String?,
)

data class ObjSubCategory(

    var fkSubCategoryId: String?,
    var strSubCategoryName: String

)

data class ObjShop(

    var fkShopId: String?,
    var strShopName: String?,
    var strPlace: String?,
    var strImageUrl: String?
)


data class AddProductRequest(


    var intPageLimit: Int,
    var strStoreId: String,
    var intSkipCount: Int,
    var blnExpress: Boolean,
    var strSubCategoryId: String
)
