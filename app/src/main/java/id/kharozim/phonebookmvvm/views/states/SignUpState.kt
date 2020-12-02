package id.kharozim.phonebookmvvm.views.states

import id.kharozim.phonebookmvvm.repository.remote.responses.BaseResponse

sealed class SignUpState{
    data class Loading(val message: String = "Loading...") : SignUpState()
    data class Error(val exception: Exception) : SignUpState()
    data class SuccessSignUp(val data: BaseResponse<String>) : SignUpState()
}
