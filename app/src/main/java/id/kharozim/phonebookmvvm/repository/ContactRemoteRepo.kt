package id.kharozim.phonebookmvvm.repository

import id.kharozim.phonebookmvvm.repository.remote.request.AddContactBody
import id.kharozim.phonebookmvvm.repository.remote.responses.BaseResponse
import id.kharozim.phonebookmvvm.repository.remote.responses.ContactResponse

interface ContactRemoteRepo {
    suspend fun getAllContact(token: String) : BaseResponse<List<ContactResponse>>
    suspend fun deleteContact(token: String, id:Long) :BaseResponse<String>
    suspend fun addContact(token: String, addContactBody: AddContactBody): BaseResponse<ContactResponse>
    suspend fun updateContact(token: String, id: Int, addContactBody: AddContactBody): BaseResponse<ContactResponse>
}