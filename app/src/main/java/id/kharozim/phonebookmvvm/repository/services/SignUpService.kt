package id.kharozim.phonebookmvvm.repository.services

import id.kharozim.phonebookmvvm.repository.request.SignUpRequest
import id.kharozim.phonebookmvvm.repository.responses.BaseResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpService {

    @POST("v1/signup")
    suspend fun getSignUp(@Body signUpRequest: SignUpRequest) : BaseResponse<String>
}