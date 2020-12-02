package id.kharozim.phonebookmvvm.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import id.kharozim.phonebookmvvm.databinding.FragmentCreateBinding
import id.kharozim.phonebookmvvm.helper.PreferenceHelper
import id.kharozim.phonebookmvvm.repository.ContactRemoteRepo
import id.kharozim.phonebookmvvm.repository.remote.ContactRemoteRepoImpl
import id.kharozim.phonebookmvvm.repository.remote.clients.ContactClient
import id.kharozim.phonebookmvvm.repository.remote.request.AddContactBody
import id.kharozim.phonebookmvvm.repository.remote.services.ContactService
import id.kharozim.phonebookmvvm.repository.remote.utils.ConstantUtil
import id.kharozim.phonebookmvvm.viewmodels.CreateContactViewModel
import id.kharozim.phonebookmvvm.viewmodels.CreateContactViewModelFactory
import id.kharozim.phonebookmvvm.views.states.CreateContactState
import kotlinx.android.synthetic.main.fragment_create.*

class CreateFragment : Fragment() {

    private lateinit var binding: FragmentCreateBinding
    private val sharePref by lazy { PreferenceHelper(requireContext()) }
    private val service: ContactService by lazy { ContactClient.contactService }
    private val remoteRepo: ContactRemoteRepo by lazy { ContactRemoteRepoImpl(service) }
    private val viewModelFactory by lazy { CreateContactViewModelFactory(remoteRepo) }
    private val viewModel by viewModels<CreateContactViewModel> { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateBinding.inflate(inflater, container, false)

        setView()
        setObserver()
        return binding.root
    }

    private fun setObserver() {
        binding.run {
            viewModel.state.observe(viewLifecycleOwner) {
                when (it) {
                    is CreateContactState.Loading -> showLoading(true)
                    is CreateContactState.Error -> {
                        showLoading(false)
                        Toast.makeText(requireContext(), it.exception.message, Toast.LENGTH_SHORT)
                            .show()
                    }
                    is CreateContactState.SuccessCreateContact -> {
                        showLoading(false)
                        Toast.makeText(
                            requireContext(),
                            "${it.contactResponse.name} berhasil dibuat",
                            Toast.LENGTH_SHORT
                        ).show()
                        requireActivity().onBackPressed()
                    }
                    else -> throw Exception("Unsupported state type")
                }

            }
        }
    }


    private fun showLoading(isLoading: Boolean) {
        if (isLoading) binding.pbAddContact.isVisible = true else binding.pbAddContact.isVisible =
            false
    }

    private fun setView() {
        binding.run {
            btAddContact.setOnClickListener {
                val token = sharePref.getString(ConstantUtil.PREF_TOKEN)
                val body = AddContactBody(
                    tieName.text.toString(),
                    tiePhone.text.toString(),
                    tieJob.text.toString(),
                    tieCompany.text.toString(),
                    tieEmail.text.toString()
                )
                viewModel.addContact("Bearer $token", body)
            }
        }

    }
}
