package gachon.third.umc.android

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.InputType.*
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import gachon.third.umc.android.databinding.ActivityLoginBinding
import gachon.third.umc.android.signup.SignupActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 자동 로그인 설정
//        val auto = getSharedPreferences("autoLogin", MODE_PRIVATE)
//        var userId = auto.getString("userId", null)
//        var password = auto.getString("password", null)
//
//        if(userId != null && password != null){
//            if(userId=="realsoda"&& password == "123123"){
//                Toast.makeText(applicationContext, "$userId 님 자동로그인 되었습니다.", Toast.LENGTH_SHORT).show()
//                val intent = Intent(this, MainActivity::class.java)
//                startActivity(intent)
//                finish()
//            }
//
//        }


        // 비번 암호화 설정
        binding.loginPwOffIb.setOnClickListener {
            binding.loginPwOnIb.isVisible = true
            binding.loginPwEt.inputType = TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            binding.loginPwEt.transformationMethod = HideReturnsTransformationMethod.getInstance()
        }
        binding.loginPwOnIb.setOnClickListener {
            binding.loginPwOnIb.isVisible = false
            binding.loginPwEt.inputType = TYPE_TEXT_VARIATION_PASSWORD
            //binding.loginPwEt.transformationMethod = HideReturnsTransformationMethod()
            binding.loginPwEt.transformationMethod = PasswordTransformationMethod.getInstance();
        }

        // 한 자리 이상일 때 활성화
        binding.loginPwEt.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(binding.loginIdEt.length()>0 && binding.loginPwEt.length()>0){
                    binding.loginBt.setBackgroundResource(R.drawable.login_round_real_blue)
                }
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(binding.loginIdEt.length()>0 && binding.loginPwEt.length()>0){
                    binding.loginBt.setBackgroundResource(R.drawable.login_round_real_blue)

                }
            }

            override fun afterTextChanged(p0: Editable?) {
                if(binding.loginIdEt.length()>0 && binding.loginPwEt.length()>0){
                    //binding.loginBt.setBackgroundColor(Color.parseColor("#4DA2FF"))
                    binding.loginBt.setBackgroundResource(R.drawable.login_round_real_blue)
                }
            }
        }
        )

        binding.loginBt.setOnClickListener {

            requestUserLogin()
//            if(binding.loginIdEt.text.toString() == "soda" && binding.loginPwEt.text.toString() == "UMC_3rd_Android"){
//                val intent = Intent(this, MainActivity::class.java)
//

//                startActivity(intent)
//                finish()
//            }
        }

        binding.loginSignupTv.setOnClickListener{

            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
//            finish()


        }


    }

    private fun requestUserLogin() {
        val userLoginService = RetrofitClient.getInstance().create(ApiService::class.java)
        val listCall = userLoginService.postLoginUser(UserLoginRequest(binding.loginIdEt.text.toString(), binding.loginPwEt.text.toString()))
        listCall.enqueue(object : Callback<UserLoginResponse> {
            override fun onResponse(
                call: Call<UserLoginResponse>,
                response: Response<UserLoginResponse>
            ) {
                val responseData = response.body()
                if (response.isSuccessful) {

                    if (responseData != null) {
                        if (responseData.isSuccess) {

                            val auto = getSharedPreferences("autoLogin", MODE_PRIVATE)
                            val autoLoginEdit = auto.edit()
                            autoLoginEdit.putString("userId", binding.loginIdEt.text.toString())
                            autoLoginEdit.putString("password", binding.loginPwEt.text.toString())
                            MyApplication.prefs.setString("accessToken", responseData.result.jwt)

                            autoLoginEdit.commit()

                            ActivityCompat.finishAffinity(Activity())
                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            startActivity(intent)

                        }
                        else{
                            Log.d("Retrofit", "Response\nSuccess : ${responseData.isSuccess}\n" + "code: ${responseData.code}\nmessage: ${responseData.message}\n" + "result: ${responseData.result}\n")
                            val toast = Toast.makeText(this@LoginActivity, responseData.message, Toast.LENGTH_LONG)
                            toast.show()

                        }
                    }
                } // end if

            }

            override fun onFailure(call: Call<UserLoginResponse>, t: Throwable) {
                // 실패
                Log.d(ContentValues.TAG, "실패 : $t")
            }

        })
    }
}