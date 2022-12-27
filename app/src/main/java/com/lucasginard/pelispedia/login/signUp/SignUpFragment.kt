package com.lucasginard.pelispedia.login.signUp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lucasginard.pelispedia.databinding.FragmentSignUpBinding
import com.lucasginard.pelispedia.home.HomeActivity
import com.lucasginard.pelispedia.login.signUp.presenter.SignUpContract
import com.lucasginard.pelispedia.login.signUp.presenter.SignUpPresenter

class SignUpFragment : Fragment(),SignUpContract.View {

    lateinit var _binding: FragmentSignUpBinding
    lateinit var presenter: SignUpPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(layoutInflater)
        presenter = SignUpPresenter(this)
        configureOnClickListener()
        return _binding.root
    }

    private fun configureOnClickListener(){
        _binding.btnLogin.setOnClickListener {
            validateLogin()
        }
    }

    private fun validateLogin(){
        try {
            if (presenter.validateInput(_binding.editUser.text.toString()) &&
                presenter.validateInput(_binding.editPassword.text.toString())){
                activity?.startActivity(Intent(activity, HomeActivity::class.java))
            }else{

            }
        }catch (e:Exception){

        }

    }
}