package id.kharozim.phonebookmvvm.repository.request

import com.google.gson.annotations.SerializedName

data class SignUpRequest(

	@field:SerializedName("password")
	val password: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("email")
	val email: String
)