package id.kharozim.phonebookmvvm.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import id.kharozim.phonebookmvvm.R
import id.kharozim.phonebookmvvm.databinding.FragmentRegisterBinding
import id.kharozim.phonebookmvvm.helper.PreferenceHelper
import id.kharozim.phonebookmvvm.repository.UserRemoteRepo
import id.kharozim.phonebookmvvm.repository.remote.UserRemoteRepoImpl
import id.kharozim.phonebookmvvm.repository.remote.clients.UserClient
import id.kharozim.phonebookmvvm.repository.remote.utils.ConstantUtil
import id.kharozim.phonebookmvvm.viewmodels.ContactViewModel
import id.kharozim.phonebookmvvm.viewmodels.SignUpViewModel
import id.kharozim.phonebookmvvm.viewmodels.SignUpViewModelFactory
import id.kharozim.phonebookmvvm.views.states.SignUpState

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private val sharePref by lazy { PreferenceHelper(requireContext()) }
    private val service by lazy { UserClient.userService }
    private val remoteRepo: UserRemoteRepo by lazy { UserRemoteRepoImpl(service) }
    private val viewModelFactory by lazy { SignUpViewModelFactory(remoteRepo) }
    private val viewModel by viewModels<SignUpViewModel> { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)

        binding.tvSignin.setOnClickListener { findNavController().navigate(R.id.action_registerFragment_to_loginFragment) }

        setView()
        setObserver()

        return binding.root
    }

    private fun setObserver() {
        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is SignUpState.Loading -> showLoading(true)
                is SignUpState.Error -> {
                    showLoading(false)
                    showMessage(it.exception.message ?: "Oops something went wrong")
                }
                is SignUpState.SuccessSignUp -> {
                    showLoading(false)
                    sharePref.put(ConstantUtil.PREF_TOKEN, it.data.data)
                    findNavController().navigate(R.id.action_registerFragment_to_homeFragment)
                }
                else -> throw Exception("Unsupported state type")
            }
        }
    }

    private fun showMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.run {
            pbSignup.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    private fun setView() {
        binding.run {
            btnRegister.setOnClickListener {
                viewModel.signup(
                    tieName.text.toString(),
                    tieEmail.text.toString(),
                    tiePassword.text.toString()
                )
            }
        }

    }
}