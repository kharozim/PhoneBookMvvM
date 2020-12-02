package id.kharozim.phonebookmvvm.repository.remote.utils

class ConstantUtil {

    companion object{

        private const val BASE_URL = "https://phone-book-api.herokuapp.com"
        const val BASE_URL_API = "$BASE_URL/api/"

        const val PREF_TOKEN = "PREF_TOKEN"
        const val PREF_IS_LOGIN = "PREF_IS_LOGIN"
    }
}