package id.kharozim.phonebookmvvm.repository.remote

import id.kharozim.phonebookmvvm.repository.ContactRemoteRepo
import id.kharozim.phonebookmvvm.repository.remote.request.AddContactBody
import id.kharozim.phonebookmvvm.repository.remote.responses.BaseResponse
import id.kharozim.phonebookmvvm.repository.remote.responses.ContactResponse
import id.kharozim.phonebookmvvm.repository.remote.services.ContactService
import okhttp3.MediaType
import okhttp3.RequestBody


class ContactRemoteRepoImpl(private val service: ContactService) : ContactRemoteRepo {

    override suspend fun getAllContact(token: String): BaseResponse<List<ContactResponse>> {
        return service.getAllContact(token)
    }

    override suspend fun deleteContact(token: String, id: Long): BaseResponse<String> {
        return service.deleteContact(token, id)
    }

    override suspend fun addContact(
        token: String,
        addContactBody: AddContactBody
    ): BaseResponse<ContactResponse> {
        return service.addContact(
            token,
            RequestBody.create(MediaType.parse("text/plain"), addContactBody.name),
            RequestBody.create(MediaType.parse("text/plain"), addContactBody.phone),
            RequestBody.create(MediaType.parse("text/plain"), addContactBody.email),
            RequestBody.create(MediaType.parse("text/plain"), addContactBody.job),
            RequestBody.create(MediaType.parse("text/plain"), addContactBody.company)
        )
    }

    override suspend fun updateContact(
        token: String,
        id: Int,
        addContactBody: AddContactBody
    ): BaseResponse<ContactResponse> {
        return service.updateContact(
            token,
            id,
            RequestBody.create(MediaType.parse("text/plain"), addContactBody.name),
            RequestBody.create(MediaType.parse("text/plain"), addContactBody.phone),
            RequestBody.create(MediaType.parse("text/plain"), addContactBody.email),
            RequestBody.create(MediaType.parse("text/plain"), addContactBody.job),
            RequestBody.create(MediaType.parse("text/plain"), addContactBody.company)
        )
    }

}