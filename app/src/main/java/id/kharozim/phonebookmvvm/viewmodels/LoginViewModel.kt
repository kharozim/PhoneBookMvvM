package id.kharozim.phonebookmvvm.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.kharozim.phonebookmvvm.repository.clients.LoginClient
import id.kharozim.phonebookmvvm.repository.request.LoginRequest
import id.kharozim.phonebookmvvm.states.LoginState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val service by lazy { LoginClient.loginService }
    private val mutableState by lazy { MutableLiveData<LoginState>() }
    val state : LiveData<LoginState>
        get() = mutableState

    fun login(email : String, password : String){

        val body = LoginRequest(password,email)
        mutableState.value = LoginState.Loading()
        viewModelScope.launch(Dispatchers.IO){
            try {
                val loginResponse = service.getLogin(body).data
                mutableState.postValue(LoginState.Login(loginResponse))

            }catch (exc: Exception){
                exc.printStackTrace()
                mutableState.postValue(LoginState.Error(exc))
            }
        }

    }

}