package id.kharozim.phonebookmvvm.fragments

import android.os.Bundle
import android.renderscript.ScriptGroup
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import id.kharozim.phonebookmvvm.R
import id.kharozim.phonebookmvvm.databinding.FragmentLoginBinding
import id.kharozim.phonebookmvvm.states.LoginState
import id.kharozim.phonebookmvvm.viewmodels.LoginViewModel
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val loginViewModel by viewModels<LoginViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false).apply {

            tvSignup.setOnClickListener { findNavController().navigate(R.id.action_loginFragment_to_registerFragment) }
            btnLogin.setOnClickListener {
                if (tieEmail.text.isNullOrEmpty() || tiePassword.text.isNullOrEmpty()) {
                    Toast.makeText(
                        requireContext(),
                        "email / password tidak boleh kosong",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    loginViewModel.login(tieEmail.text.toString(), tiePassword.text.toString())

                }
            }

            loginViewModel.state.observe(viewLifecycleOwner) {
                when (it) {
                    is LoginState.Login -> {
//                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                        Toast.makeText(requireContext(), "${it.data.token}", Toast.LENGTH_SHORT)
                            .show()
                    }
                    is LoginState.Error -> Toast.makeText(requireContext(), it.exception.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
        return binding.root
    }
}