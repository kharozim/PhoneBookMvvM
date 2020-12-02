package id.kharozim.phonebookmvvm.repository.remote.request

data class AddContactBody(
    var name: String,
    var phone: String,
    var job: String,
    var company: String,
    var email : String,
    var image: String? = null
)