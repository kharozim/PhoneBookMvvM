package id.kharozim.phonebookmvvm.repository.services

import id.kharozim.phonebookmvvm.repository.request.LoginRequest
import id.kharozim.phonebookmvvm.repository.request.SignUpRequest
import id.kharozim.phonebookmvvm.repository.responses.BaseResponse
import id.kharozim.phonebookmvvm.repository.responses.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("v1/signin")
    suspend fun getLogin(@Body loginRequest: LoginRequest) : BaseResponse<LoginResponse>
}