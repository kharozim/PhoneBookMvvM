package id.kharozim.phonebookmvvm.repository.remote

import id.kharozim.phonebookmvvm.repository.UserRemoteRepo
import id.kharozim.phonebookmvvm.repository.remote.request.LoginBody
import id.kharozim.phonebookmvvm.repository.remote.request.SignUpBody
import id.kharozim.phonebookmvvm.repository.remote.responses.BaseResponse
import id.kharozim.phonebookmvvm.repository.remote.responses.LoginResponse
import id.kharozim.phonebookmvvm.repository.remote.services.UserService

class UserRemoteRepoImpl(private val service : UserService) : UserRemoteRepo {
    override suspend fun userLogin(loginBody: LoginBody): BaseResponse<LoginResponse> {
        return service.getLogin(loginBody)
    }

    override suspend fun userRegister(signUpBody: SignUpBody): BaseResponse<String> {
        return service.getSignUp(signUpBody)
    }
}