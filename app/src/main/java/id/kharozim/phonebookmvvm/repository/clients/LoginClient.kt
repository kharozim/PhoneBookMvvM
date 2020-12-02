package id.kharozim.phonebookmvvm.repository.clients

import com.google.gson.GsonBuilder
import id.kharozim.phonebookmvvm.repository.services.ContactService
import id.kharozim.phonebookmvvm.repository.services.LoginService
import id.kharozim.phonebookmvvm.repository.utils.ConstantUtil
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object LoginClient {
    private val interceptor by lazy { HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)}

    private val client by lazy { OkHttpClient.Builder().addInterceptor(interceptor).build()}

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(ConstantUtil.BASE_URL_API)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .client(client)
            .build()
    }
    val loginService by lazy {
        retrofit.create(LoginService::class.java)
    }
}