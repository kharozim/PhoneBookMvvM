package id.kharozim.phonebookmvvm.viewmodels

import androidx.lifecycle.*
import id.kharozim.phonebookmvvm.repository.UserRemoteRepo
import id.kharozim.phonebookmvvm.repository.remote.clients.UserClient
import id.kharozim.phonebookmvvm.repository.remote.request.SignUpBody
import id.kharozim.phonebookmvvm.views.states.SignUpState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpViewModelFactory(
    private val remoteRepo: UserRemoteRepo
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SignUpViewModel(remoteRepo) as T
    }
}

class SignUpViewModel(private val remoteRepo: UserRemoteRepo) : ViewModel() {
    private val service by lazy { UserClient.userService }
    private val mutableState by lazy { MutableLiveData<SignUpState>() }
    val state: LiveData<SignUpState>
        get() = mutableState

    fun signup(name: String, email: String, password: String) {
        val body = SignUpBody(password, name, email)
        mutableState.value = SignUpState.Loading()
        viewModelScope.launch(Dispatchers.IO){
            try {
                val signupResponse = service.getSignUp(body)
                mutableState.postValue(SignUpState.SuccessSignUp(signupResponse))

            }catch (exc:Exception){
                exc.printStackTrace()
                mutableState.postValue(SignUpState.Error(exc))
            }
        }
    }
}