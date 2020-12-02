package id.kharozim.phonebookmvvm.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import id.kharozim.phonebookmvvm.R
import id.kharozim.phonebookmvvm.databinding.FragmentLoginBinding
import id.kharozim.phonebookmvvm.databinding.FragmentRegisterBinding
import id.kharozim.phonebookmvvm.helper.PreferenceHelper
import id.kharozim.phonebookmvvm.repository.UserRemoteRepo
import id.kharozim.phonebookmvvm.repository.remote.UserRemoteRepoImpl
import id.kharozim.phonebookmvvm.repository.remote.clients.UserClient
import id.kharozim.phonebookmvvm.repository.remote.utils.ConstantUtil
import id.kharozim.phonebookmvvm.views.states.LoginState
import id.kharozim.phonebookmvvm.viewmodels.LoginViewModel
import id.kharozim.phonebookmvvm.viewmodels.LoginViewModelFactory
import id.kharozim.phonebookmvvm.viewmodels.SignUpViewModel
import id.kharozim.phonebookmvvm.viewmodels.SignUpViewModelFactory

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val sharePref by lazy { PreferenceHelper(requireContext()) }
    private val service by lazy { UserClient.userService }
    private val remoteRepo: UserRemoteRepo by lazy { UserRemoteRepoImpl(service) }
    private val viewModelFactory by lazy { LoginViewModelFactory(remoteRepo) }
    private val viewModel by viewModels<LoginViewModel> { viewModelFactory }

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

                    setView()
                    setObserver()

                }
            }
        }
        return binding.root
    }

    private fun setObserver() {
        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is LoginState.SuccessLogin -> {
                    showLoading(false)
                    sharePref.put(ConstantUtil.PREF_TOKEN, it.data.token)
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                    Toast.makeText(requireContext(), "${it.data.name} berhasil login", Toast.LENGTH_SHORT)
                        .show()
                }
                is LoginState.Error -> {
                    showLoading(false)
                    Toast.makeText(
                        requireContext(),
                        it.exception.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is LoginState.Loading -> showLoading(true)
                else -> throw Exception("Unsupported state type")
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.run {
            if (isLoading) {
                pbLogin.isVisible = true
                btnLogin.isVisible = false
            } else {
                pbLogin.isVisible = false
                btnLogin.isVisible = true
            }
        }
    }

    private fun setView() {
        binding.run {
            viewModel.login(
                tieEmail.text.toString(),
                tiePassword.text.toString()
            )
        }
    }
}