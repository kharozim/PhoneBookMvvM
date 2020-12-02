package id.kharozim.phonebookmvvm.states

import id.kharozim.phonebookmvvm.repository.responses.LoginResponse

sealed class LoginState {
    data class Loading(val message: String = "Loading...") : LoginState()
    data class Error(val exception: Exception) : LoginState()
    data class Login(val data: LoginResponse) : LoginState()
}
