package com.lucasginard.pelispedia.login.signUp.presenter

import com.lucasginard.pelispedia.PelisPediaApp
import com.lucasginard.pelispedia.login.signUp.UserSave

class SignUpPresenter():SignUpContract.Presenter {

    override fun validateInputs(user: String,password:String): Boolean {
        val validate = !user.contains(" ") && !password.contains(" ") && user.isNotEmpty() && password.isNotEmpty()
        return validate
    }

    override fun saveUserData(user: String, password: String,check:Boolean) {
        if (check){
            PelisPediaApp.prefs.savePass = password
            PelisPediaApp.prefs.saveUser = user
            PelisPediaApp.prefs.saveCheck = check
        }else{
            PelisPediaApp.prefs.saveCheck = check
        }
    }

    override fun getSaveUser(): UserSave? {
        if (PelisPediaApp.prefs.saveCheck){
            val user = PelisPediaApp.prefs.savePass?.let {
                PelisPediaApp.prefs.saveUser?.let { it1 ->
                    UserSave(
                        it1,
                        it,PelisPediaApp.prefs.saveCheck)
                }
            }
            return user
        }
        return null
    }

}

interface SignUpContract{
    interface View{
        fun showError()
        fun hideError()
    }

    interface Presenter{
        fun validateInputs(user: String,password:String):Boolean
        fun saveUserData(user: String,password:String,check:Boolean)
        fun getSaveUser():UserSave?
    }
}