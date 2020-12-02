package id.kharozim.phonebookmvvm.views.states

import id.kharozim.phonebookmvvm.repository.remote.responses.ContactResponse

sealed class CreateContactState{
    data class Loading(val message: String = "Loading...") : CreateContactState()
    data class Error(val exception: Exception) : CreateContactState()
    data class SuccessCreateContact(val contactResponse: ContactResponse) : CreateContactState()
}
