package com.lucasginard.pelispedia.login.signUp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lucasginard.pelispedia.databinding.FragmentSignUpBinding
import com.lucasginard.pelispedia.home.HomeActivity

class SignUpFragment : Fragment() {

    lateinit var _binding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(layoutInflater)
        configureOnClickListener()
        return _binding.root
    }

    private fun configureOnClickListener(){
        _binding.btnLogin.setOnClickListener {
            activity?.startActivity(Intent(activity, HomeActivity::class.java))
        }
    }

}