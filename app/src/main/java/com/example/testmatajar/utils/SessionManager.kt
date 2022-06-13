package com.example.testmatajar.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

class SessionManager {

    companion object {

        lateinit var sharedPref: SharedPreferences
        lateinit var editor: SharedPreferences.Editor
        lateinit var context: Context

        private var PRIVATE_MODE = 0
        private val PREF_NAME = "eMart"

        private const val IS_LOGIN = "isLogin"
        private val IS_REGISTERED = "isRegistered"
        private const val USER_ID = "userId"
        private val USER_TOKEN = "user_token"
        private val EMAIL = "email"
        private val USERTYPE = "userType"
        private val NAME = "name"
        private val PHONE = "phone"
        private val BUSINESS_NAME = "businessName"
        private val REFERRAL_CODE = "referralCode"

        private val UPLOAD_IMAGE = "uploadImage"
        private val BUSINESS_DETAILS = "businessDetails"
        private val NEW_BUSINESS_DETAILS = "newBusinessDetails"
        private val USER_DATA = "userData"
        private val FIRST_LETTER = "firstLetter"
        private val LAT = "lat"
        private val LNG = "lng"

        private val COLOR_NAME="colorName"
        private val THEME_DETAILS = "themeDetails"
        private val THEME_NAME = "themeName"
        private val THEME_IMAGE = "themeImage"
        private val THEME_COLOR_NAME = "themeColorName"

        private val IS_USER_VALID = "isUserValid"

        @SuppressLint("CommitPrefEdits")
        fun initializeContext(context: Context) {
            this.context = context
            sharedPref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
            editor = sharedPref.edit()
        }

        fun clear() {
            editor.clear()
            editor.apply()
        }

        fun setIsUserValid(data: Boolean) {
            editor.putBoolean(IS_USER_VALID, data)
            editor.commit()
        }

        fun getIsUserValid() : Boolean {
            return sharedPref.getBoolean(IS_USER_VALID,false)
        }

        fun saveStringDataWithKey(key: String, data: String) {
            editor.putString(key, data)
            editor.commit()
        }

        fun getStringDataWithKey(key: String, defValue: String): String? {
            return sharedPref.getString(key, defValue)
        }

        fun saveBooleanDataWithKey(key: String, data: Boolean) {
            editor.putBoolean(key, data)
            editor.commit()
        }

        fun getBooleanDataWithKey(key: String, defValue: Boolean): Boolean {
            return sharedPref.getBoolean(key, defValue)
        }

        fun setIsLogin(isLogin: Boolean) {
            editor.putBoolean(IS_LOGIN, isLogin)
            editor.apply()

        }

        fun getIsLogin(): Boolean {
            return sharedPref.getBoolean(
                IS_LOGIN,
                false
            )
        }


        fun getIsRegistered(): Boolean {
            return sharedPref.getBoolean(IS_REGISTERED, false)
        }

        fun setIsRegistered(s: Boolean) {
            editor.putBoolean(IS_REGISTERED, s)
        }


        fun setUserId(token: Int) {
            editor.putInt(USER_ID, token)
            editor.apply()
        }

        fun getUserId(): Int {
            return sharedPref.getInt(
                USER_ID, 0
            ) ?: 0
        }



//        fun saveUserData(userModel: UserProvider) {
//            val json: String = Gson().toJson(userModel)
//            editor.putString("userdata", json)
//            editor.commit()
//        }
//
//        fun getUserData(): UserProvider? {
//            val json: String? = sharedPref.getString("userdata", "")
//            if (json != null && json != "") {
//                val gson = Gson()
//                return gson.fromJson(json, UserProvider::class.java)
//            }
//            return null
//        }










        var isLogin: Boolean
            get() = sharedPref.getBoolean(IS_LOGIN, false)
            set(value) = editor.putBoolean(IS_LOGIN, value).apply()




























    }










}