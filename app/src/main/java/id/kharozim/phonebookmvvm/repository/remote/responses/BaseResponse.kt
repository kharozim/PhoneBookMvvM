package id.kharozim.phonebookmvvm.repository.remote.responses

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
	val data: T,
	val message: String,
	val status: Boolean
)