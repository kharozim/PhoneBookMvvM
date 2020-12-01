package id.kharozim.phonebookmvvm.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import id.kharozim.phonebookmvvm.R
import id.kharozim.phonebookmvvm.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false).apply {

            tvSignin.setOnClickListener {findNavController().navigate(R.id.action_registerFragment_to_loginFragment) }
            btnRegister.setOnClickListener {findNavController().navigate(R.id.action_registerFragment_to_loginFragment) }

        }
        return binding.root
    }
}