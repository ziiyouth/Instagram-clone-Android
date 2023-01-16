package gachon.third.umc.android.profile

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import gachon.third.umc.android.*

import gachon.third.umc.android.databinding.ActivityProfileEditBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileEditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileEditBinding




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ProfileFragment에서 이름, 아이디, 자기소개 받아오기
        val receivedName = intent.getStringExtra("name")
        binding.profileEditNameEt.setText(receivedName)
        val receivedId = intent.getStringExtra("id")
        binding.profileEditIdEt.setText(receivedId)
        val receivedIntro = intent.getStringExtra("intro")
        binding.profileEditIntroEt.setText(receivedIntro)


        // 엑스 아이콘 누르면 닫기
        binding.profileEditClose.setOnClickListener{
            finish()
        }

        // 체크 아이콘 누르면 전달 후 닫기
        with(binding){
            profileEditCheck.setOnClickListener {

//                val intent = Intent(this@ProfileEditActivity, ProfileFragment::class.java).apply {
//                    putExtra("name", profileEditNameEt.text.toString())
//                    putExtra("id", profileEditIdEt.text.toString())
//                    putExtra("intro", profileEditIntroEt.text.toString())
//                }

                // 아이디 비어있으면 버튼 클릭 못하게
                if(profileEditIdEt.text.toString() != ""){
                    requestUserModify()
//                    setResult(RESULT_OK, intent)
                    finish()
                }


            }
        }


    }

    private fun requestUserModify() {
        val userModifyService = RetrofitClient.getInstance().create(ApiService::class.java)
        val listCall = userModifyService.patchUserModify(MyApplication.prefs.getString("accessToken", "no accessToken"), UserModifyRequest(binding.profileEditNameEt.text.toString(), binding.profileEditIdEt.text.toString(), binding.profileEditIntroEt.text.toString(), ""))
        listCall.enqueue(object : Callback<UserModifyResponse> {
            override fun onResponse(
                call: Call<UserModifyResponse>,
                response: Response<UserModifyResponse>
            ) {
                val responseData = response.body()
                if (response.isSuccessful) {

                    if (responseData != null) {
                        if (responseData.isSuccess) {
//                            Log.d(TAG, "성공 : ${response.raw()}")
                            Log.d("Retrofit", "Response\nSuccess : ${responseData.isSuccess}\n" + "code: ${responseData.code}\nmessage: ${responseData.message}\n" + "result: ${responseData.result}\n")
//                            ActivityCompat.finishAffinity(Activity())
                            val toast = Toast.makeText(this@ProfileEditActivity, "사용자 정보를 수정했습니다", Toast.LENGTH_LONG)
                            toast.show()
//                            val intent = Intent(this@ProfileEditActivity, ProfileFragment::class.java)
//                            startActivity(intent)
                        }
                        else{
                            Log.d("Retrofit", "Response\nSuccess : ${responseData.isSuccess}\n" + "code: ${responseData.code}\nmessage: ${responseData.message}\n" + "result: ${responseData.result}\n")
                            val toast = Toast.makeText(this@ProfileEditActivity, responseData.message, Toast.LENGTH_LONG)
                            toast.show()
                        }
                    }
                } // end if

            }

            override fun onFailure(call: Call<UserModifyResponse>, t: Throwable) {
                // 실패
                Log.d(ContentValues.TAG, "실패 : $t")
            }

        })
    }



}