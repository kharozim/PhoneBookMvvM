package id.kharozim.phonebookmvvm.repository.remote.services

import id.kharozim.phonebookmvvm.repository.remote.request.LoginBody
import id.kharozim.phonebookmvvm.repository.remote.request.SignUpBody
import id.kharozim.phonebookmvvm.repository.remote.responses.BaseResponse
import id.kharozim.phonebookmvvm.repository.remote.responses.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("v1/signin")
    suspend fun getLogin(@Body loginRequest: LoginBody) : BaseResponse<LoginResponse>

    @POST("v1/signup")
    suspend fun getSignUp(@Body signUpRequest: SignUpBody) : BaseResponse<String>
}