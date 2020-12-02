package id.kharozim.phonebookmvvm.views.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import id.kharozim.phonebookmvvm.R
import id.kharozim.phonebookmvvm.databinding.FragmentHomeBinding
import id.kharozim.phonebookmvvm.helper.PreferenceHelper
import id.kharozim.phonebookmvvm.repository.ContactRemoteRepo
import id.kharozim.phonebookmvvm.repository.UserRemoteRepo
import id.kharozim.phonebookmvvm.repository.remote.ContactRemoteRepoImpl
import id.kharozim.phonebookmvvm.repository.remote.UserRemoteRepoImpl
import id.kharozim.phonebookmvvm.repository.remote.clients.ContactClient
import id.kharozim.phonebookmvvm.repository.remote.clients.UserClient
import id.kharozim.phonebookmvvm.repository.remote.utils.ConstantUtil
import id.kharozim.phonebookmvvm.viewmodels.ContactViewModel
import id.kharozim.phonebookmvvm.viewmodels.ContactViewModelFactory
import id.kharozim.phonebookmvvm.viewmodels.SignUpViewModel
import id.kharozim.phonebookmvvm.viewmodels.SignUpViewModelFactory
import id.kharozim.phonebookmvvm.views.ContactAdapter
import id.kharozim.phonebookmvvm.views.states.ContactState

class HomeFragment : Fragment(), ContactAdapter.ListenerContact {

    private lateinit var binding: FragmentHomeBinding
    private val sharePref by lazy { PreferenceHelper(requireContext()) }
    private val adapter by lazy { ContactAdapter(requireContext(), this) }
    private val service by lazy { ContactClient.contactService }
    private val remoteRepo: ContactRemoteRepo by lazy { ContactRemoteRepoImpl(service) }
    private val viewModelFactory by lazy { ContactViewModelFactory(remoteRepo) }
    private val viewModel by viewModels<ContactViewModel> { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.ivAdd.setOnClickListener { findNavController().navigate(R.id.action_homeFragment_to_createFragment) }

        setView()
        setObserver()

        return binding.root
    }

    private fun setObserver() {
        binding.run {
            rvContact.adapter = adapter
            viewModel.state.observe(viewLifecycleOwner) {
                when (it) {
                    is ContactState.Loading -> showLoading(true)
                    is ContactState.SuccessGetAllContact -> {
                        showLoading(false)
                        adapter.listContact = it.list.toMutableList()
                        Log.e("TAG", "onCreateView: ${it.list}")
                    }
                    is ContactState.Error -> {
                        showLoading(false)
                        Toast.makeText(requireContext(), it.exception.message, Toast.LENGTH_SHORT)
                            .show()
                    }
                    else -> throw Exception("Unsupported state type")

                }
            }

        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.run {
            if (isLoading) pbHome.isVisible = true else pbHome.isVisible = false
        }

    }

    private fun setView() {
        binding.run {
            val token = sharePref.getString(ConstantUtil.PREF_TOKEN)
            viewModel.getAllContact("Bearer $token")
        }

    }

    override fun onDelete(id: Long) {
//        val token = sharePref.getString(ConstantUtil.PREF_TOKEN)
    }
}


