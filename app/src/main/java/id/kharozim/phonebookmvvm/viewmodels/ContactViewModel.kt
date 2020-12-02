package id.kharozim.phonebookmvvm.viewmodels

import androidx.lifecycle.*
import id.kharozim.phonebookmvvm.repository.ContactRemoteRepo
import id.kharozim.phonebookmvvm.repository.remote.clients.ContactClient
import id.kharozim.phonebookmvvm.views.states.ContactState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Suppress("UNCHECKED_CAST")
class ContactViewModelFactory(
    private val remoteRepo : ContactRemoteRepo
) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ContactViewModel(remoteRepo) as T
    }

}

class ContactViewModel(private val remoteRepo : ContactRemoteRepo) :ViewModel(){

    private val service by lazy { ContactClient.contactService }
    private val mutableState by lazy { MutableLiveData<ContactState>()}
    val state : LiveData<ContactState>
    get() {
        return mutableState
    }

    fun getAllContact(token : String){
        mutableState.value = ContactState.Loading()
        viewModelScope.launch(Dispatchers.IO){
            try {
                val contactResponse = service.getAllContact(token)
                val contactList = contactResponse.data.asSequence().map { it }.toList()
                mutableState.postValue(ContactState.SuccessGetAllContact(contactList))
            } catch (exc : Exception){
                exc.printStackTrace()
                mutableState.postValue(ContactState.Error(exc))
            }
        }
    }

}