package com.lucasginard.pelispedia.login.signUp.presenter

class SignUpPresenter(var view:SignUpContract.View):SignUpContract.Presenter {

    override fun validateInputs(user: String,password:String): Boolean {
        val validate = !user.contains(" ") && !password.contains(" ")
        if (!validate) view.showError() else view.hideError()
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