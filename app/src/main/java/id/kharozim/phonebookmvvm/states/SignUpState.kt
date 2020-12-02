package id.kharozim.phonebookmvvm.states

import id.kharozim.phonebookmvvm.repository.responses.ContactResponse
import id.kharozim.phonebookmvvm.repository.responses.SignUpResponse

sealed class SignUpState{
    data class Loading(val message: String = "Loading...") : SignUpState()
    data class Error(val exception: Exception) : SignUpState()
    data class SignUp(val data: SignUpResponse) : SignUpState()
}
