package id.kharozim.phonebookmvvm.views.states

import id.kharozim.phonebookmvvm.repository.remote.responses.ContactResponse

sealed class ContactState {
    data class Loading(val message: String = "Loading...") : ContactState()
    data class Error(val exception: Exception) : ContactState()
    data class SuccessGetAllContact(val list: List<ContactResponse>) : ContactState()
    data class SuccesstDelete(val data : ContactResponse) : ContactState()
}