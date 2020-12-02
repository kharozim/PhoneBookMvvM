package id.kharozim.phonebookmvvm.repository

import id.kharozim.phonebookmvvm.repository.remote.request.LoginBody
import id.kharozim.phonebookmvvm.repository.remote.request.SignUpBody
import id.kharozim.phonebookmvvm.repository.remote.responses.BaseResponse
import id.kharozim.phonebookmvvm.repository.remote.responses.LoginResponse

interface UserRemoteRepo {
    suspend fun userLogin(loginBody : LoginBody) : BaseResponse<LoginResponse>
    suspend fun userRegister(signUpBody: SignUpBody) :BaseResponse<String>
}