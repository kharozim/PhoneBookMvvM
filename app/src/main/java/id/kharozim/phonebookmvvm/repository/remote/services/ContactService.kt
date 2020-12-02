package id.kharozim.phonebookmvvm.repository.remote.services

import id.kharozim.phonebookmvvm.repository.remote.responses.BaseResponse
import id.kharozim.phonebookmvvm.repository.remote.responses.ContactResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ContactService {

    @GET("v1/contacts")
    suspend fun getAllContact(
        @Header("Authorization") token : String
    ): BaseResponse<List<ContactResponse>>

    @DELETE("v1/contacts/{id}")
    suspend fun deleteContact(
        @Header("Authorization")
        token: String,
        @Path("id")
        id: Long
    ): BaseResponse<String>

    @Multipart
    @POST("v1/contacts")
    suspend fun addContact(
        @Header("Authorization")
        token: String,
        @Part("name")
        name: RequestBody,
        @Part("phone")
        phone: RequestBody,
        @Part("email")
        email: RequestBody,
        @Part("job")
        job: RequestBody,
        @Part("company")
        company: RequestBody,
        @Part("image")
        image: RequestBody? = null,
    ): BaseResponse<ContactResponse>

    @Multipart
    @PUT("v1/contacts/{id}")
    suspend fun updateContact(
        @Header("Authorization")
        token: String,
        @Path("id")
        id: Int,
        @Part("name")
        name: RequestBody,
        @Part("phone")
        phone: RequestBody,
        @Part("email")
        email: RequestBody,
        @Part("job")
        job: RequestBody,
        @Part("company")
        company: RequestBody,
        @Part("image")
        image: RequestBody? = null,
    ): BaseResponse<ContactResponse>
}