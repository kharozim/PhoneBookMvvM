package id.kharozim.phonebookmvvm.repository.remote.request

import com.google.gson.annotations.SerializedName

data class SignUpBody(

	@field:SerializedName("password")
	val password: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("email")
	val email: String
)