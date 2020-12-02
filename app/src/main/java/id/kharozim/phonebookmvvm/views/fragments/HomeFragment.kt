package id.kharozim.phonebookmvvm.views.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import id.kharozim.phonebookmvvm.databinding.FragmentHomeBinding
import id.kharozim.phonebookmvvm.helper.PreferenceHelper
import id.kharozim.phonebookmvvm.repository.remote.utils.ConstantUtil
import id.kharozim.phonebookmvvm.viewmodels.ContactViewModel
import id.kharozim.phonebookmvvm.views.ContactAdapter
import id.kharozim.phonebookmvvm.views.states.ContactState

class HomeFragment : Fragment(), ContactAdapter.ListenerContact {

    private lateinit var binding: FragmentHomeBinding
    private val contactViewModel by viewModels<ContactViewModel>()
    private val sharePref by lazy { PreferenceHelper(requireContext()) }
    private val adapter by lazy { ContactAdapter(requireContext(), this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false).apply {


            val token = sharePref.getString(ConstantUtil.PREF_TOKEN)
            contactViewModel.getAllContact("Bearer $token")

        }

        contactViewModel.state.observe(viewLifecycleOwner) {
            binding.rvContact.adapter = adapter
            when (it) {
                is ContactState.SuccessGetAllContact -> {
                    adapter.listContact = it.list.toMutableList()
                    Log.e("TAG", "onCreateView: ${it.list}")
                }
                is ContactState.Error -> {
                    Toast.makeText(requireContext(), it.exception.message, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

        return binding.root
    }

    override fun onDelete(id: Long) {
        val token = sharePref.getString(ConstantUtil.PREF_TOKEN)

    }
}


