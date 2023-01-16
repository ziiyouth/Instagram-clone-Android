package gachon.third.umc.android.signup

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import gachon.third.umc.android.*

import gachon.third.umc.android.databinding.FragmentSignupMailBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupMailFragment : Fragment() {

    lateinit var binding: FragmentSignupMailBinding

    var name =""
    var pwd = ""
    var nickname = ""



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {

        arguments?.let {
            name = it.getString("name").toString()
            pwd = it.getString("pwd").toString()
            nickname = it.getString("nickname").toString()
        }

//        Log.d("Fragment", name)

        binding = FragmentSignupMailBinding.inflate(inflater, container, false)

        binding.signupEraseIb.setOnClickListener {
            binding.signupEmailEt.text = null
        }

        // 한 자리 이상일 때 활성화
        binding.signupEmailEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(binding.signupEmailEt.length()>0){
                    binding.signEmailNextBt.setBackgroundResource(R.drawable.login_round_real_blue)
                }
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(binding.signupEmailEt.length()>0){
                    binding.signEmailNextBt.setBackgroundResource(R.drawable.login_round_real_blue)

                }
            }

            override fun afterTextChanged(p0: Editable?) {
                if(binding.signupEmailEt.length()>0){
                    binding.signEmailNextBt.setBackgroundResource(R.drawable.login_round_real_blue)
                }
            }
        }
        )


        binding.signEmailNextBt.setOnClickListener {
            if(binding.signupEmailEt.text.toString() != "") {
                Log.d("Email", binding.signupEmailEt.text.toString())
                requestUserJoin()


            }
        }

        return binding.root
    }



    private fun requestUserJoin() {
        val userJoinService = RetrofitClient.getInstance().create(ApiService::class.java)
        val listCall = userJoinService.postSignupUser(UserJoinRequest(name, nickname, binding.signupEmailEt.text.toString(),pwd))
        listCall.enqueue(object : Callback<UserJoinResponse> {
            override fun onResponse(
                call: Call<UserJoinResponse>,
                response: Response<UserJoinResponse>
            ) {
                val responseData = response.body()
                if (response.isSuccessful) {

                    if (responseData != null) {
                        if (responseData.isSuccess) {
//                            Log.d(TAG, "성공 : ${response.raw()}")
                            Log.d("Retrofit", "Response\nSuccess : ${responseData.isSuccess}\n" + "code: ${responseData.code}\nmessage: ${responseData.message}\n" + "result: ${responseData.result}\n")
                            ActivityCompat.finishAffinity(Activity())
                            val toast = Toast.makeText(activity, "회원가입에 성공했습니다", Toast.LENGTH_LONG)
                            toast.show()
                            val intent = Intent(activity, LoginActivity::class.java)
                            startActivity(intent)

                        }
                        else{
                            Log.d("Retrofit", "Response\nSuccess : ${responseData.isSuccess}\n" + "code: ${responseData.code}\nmessage: ${responseData.message}\n" + "result: ${responseData.result}\n")
                            val toast = Toast.makeText(activity, responseData.message, Toast.LENGTH_LONG)
                            toast.show()

                        }
                    }
                } // end if

            }

            override fun onFailure(call: Call<UserJoinResponse>, t: Throwable) {
                // 실패
                Log.d(TAG, "실패 : $t")
            }

        })
    }



}