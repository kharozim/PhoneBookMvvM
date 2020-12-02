package id.kharozim.phonebookmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import id.kharozim.phonebookmvvm.databinding.ActivityMainBinding
import id.kharozim.phonebookmvvm.repository.remote.clients.ContactClient
import id.kharozim.phonebookmvvm.repository.remote.responses.BaseResponse
import id.kharozim.phonebookmvvm.repository.remote.responses.ContactResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val bindding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bindding.root)

        supportActionBar?.hide()

        bindding.run {
           /* ContactClient.contactService.getAllContact("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6ImNvYmFAZ21haWwuY29tIiwicGFzc3dvcmQiOiIyNWQ1NWFkMjgzYWE0MDBhZjQ2NGM3NmQ3MTNjMDdhZCIsImlhdCI6MTYwNjg1OTcxNCwiZXhwIjoxNjA2OTQ2MTE0fQ.C6m-pFEaartvseDJtk7CWo3syTE7LdxW8SlB-Fg2hvE")
                .enqueue(object : Callback<BaseResponse<List<ContactResponse>>> {
                    override fun onResponse(
                        call: Call<BaseResponse<List<ContactResponse>>>,
                        response: Response<BaseResponse<List<ContactResponse>>>
                    ) {
                        Log.e("TAG", "onResponse: ${response.body()?.data}" )
                    }

                    override fun onFailure(
                        call: Call<BaseResponse<List<ContactResponse>>>,
                        t: Throwable
                    ) {
                        TODO("Not yet implemented")
                    }

                })
*/
        }
    }
}