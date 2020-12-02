package id.kharozim.phonebookmvvm.repository.services

import id.kharozim.phonebookmvvm.repository.responses.BaseResponse
import id.kharozim.phonebookmvvm.repository.responses.ContactResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface ContactService {

    @GET("v1/contacts")
    suspend fun getAllContact(
        @Header("Authorization") token : String
    ): BaseResponse<List<ContactResponse>>

}