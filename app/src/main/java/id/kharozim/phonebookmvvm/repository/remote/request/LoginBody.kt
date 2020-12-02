package id.kharozim.phonebookmvvm.repository.remote.request

import com.google.gson.annotations.SerializedName

data class LoginBody(

	@field:SerializedName("password")
	val password: String,

	@field:SerializedName("email")
	val email: String
)