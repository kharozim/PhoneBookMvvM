package id.kharozim.phonebookmvvm.viewmodels

import androidx.lifecycle.*
import id.kharozim.phonebookmvvm.repository.ContactRemoteRepo
import id.kharozim.phonebookmvvm.repository.remote.clients.ContactClient
import id.kharozim.phonebookmvvm.repository.remote.request.AddContactBody
import id.kharozim.phonebookmvvm.repository.remote.responses.ContactResponse
import id.kharozim.phonebookmvvm.views.states.ContactState
import id.kharozim.phonebookmvvm.views.states.CreateContactState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Suppress("UNCHECKED_CAST")
class CreateContactViewModelFactory(
    private val remoteRepo: ContactRemoteRepo
) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CreateContactViewModel(remoteRepo) as T
    }

}

class CreateContactViewModel(private val remoteRepo: ContactRemoteRepo) :ViewModel() {

    private val service by lazy { ContactClient.contactService }
    private val mutableState by lazy { MutableLiveData<CreateContactState>() }
    val state : LiveData<CreateContactState>
        get() {
            return mutableState
        }

    fun addContact(token: String, body: AddContactBody) {
        mutableState.value = CreateContactState.Loading()
        viewModelScope.launch(Dispatchers.IO){

            try {
                val contact = remoteRepo.addContact(token, body)
                mutableState.postValue(CreateContactState.SuccessCreateContact(contact.data))

            } catch (exc : Exception){
                exc.printStackTrace()
                mutableState.postValue(CreateContactState.Error(exc))

            }

        }
    }
}