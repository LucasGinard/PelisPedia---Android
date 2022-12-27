package com.lucasginard.pelispedia.login.signUp.presenter

class SignUpPresenter(view:SignUpContract.View):SignUpContract.Presenter {

    override fun validateInput(input: String): Boolean {
        return !input.contains(" ")
    }

}

interface SignUpContract{
    interface View{

    }

    interface Presenter{
        fun validateInput(input:String):Boolean
    }
}