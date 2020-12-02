package id.kharozim.phonebookmvvm.states

import id.kharozim.phonebookmvvm.repository.responses.ContactResponse

sealed class ContactState {
    data class Loading(val message: String = "Loading...") : ContactState()
    data class Error(val exception: Exception) : ContactState()
    data class GetAllContact(val data: List<ContactResponse>) : ContactState()
}