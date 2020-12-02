package id.kharozim.phonebookmvvm.viewmodels

import androidx.lifecycle.*
import id.kharozim.phonebookmvvm.repository.UserRemoteRepo
import id.kharozim.phonebookmvvm.repository.remote.clients.UserClient
import id.kharozim.phonebookmvvm.repository.remote.request.LoginBody
import id.kharozim.phonebookmvvm.views.states.LoginState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModelFactory(
    private val remoteRepo: UserRemoteRepo
) :ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(remoteRepo) as T
    }
}

class LoginViewModel(private val remoteRepo: UserRemoteRepo) : ViewModel() {
    private val service by lazy { UserClient.userService }
    private val mutableState by lazy { MutableLiveData<LoginState>() }
    val state : LiveData<LoginState>
        get() = mutableState

    fun login(email : String, password : String){
        val body = LoginBody(password,email)
        mutableState.value = LoginState.Loading()
        viewModelScope.launch(Dispatchers.IO){
            try {
                val loginResponse = service.getLogin(body).data
                mutableState.postValue(LoginState.SuccessLogin(loginResponse))

            }catch (exc: Exception){
                exc.printStackTrace()
                mutableState.postValue(LoginState.Error(exc))
            }
        }

    }

}