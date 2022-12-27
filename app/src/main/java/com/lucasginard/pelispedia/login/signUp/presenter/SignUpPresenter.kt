package com.lucasginard.pelispedia.login.signUp.presenter

class SignUpPresenter():SignUpContract.Presenter {

    override fun validateInputs(user: String,password:String): Boolean {
        val validate = !user.contains(" ") && !password.contains(" ") && user.isNotEmpty() && password.isNotEmpty()
        return validate
    }

}

interface SignUpContract{
    interface View{
        fun showError()
        fun hideError()
    }

    interface Presenter{
        fun validateInputs(user: String,password:String):Boolean
    }
}