package id.kharozim.phonebookmvvm.views.states

import id.kharozim.phonebookmvvm.repository.remote.responses.LoginResponse

sealed class LoginState {
    data class Loading(val message: String = "Loading...") : LoginState()
    data class Error(val exception: Exception) : LoginState()
    data class SuccessLogin(val data: LoginResponse) : LoginState()
}
