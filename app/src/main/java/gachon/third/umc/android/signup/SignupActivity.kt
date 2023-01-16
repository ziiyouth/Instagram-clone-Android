package gachon.third.umc.android.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import gachon.third.umc.android.R
import gachon.third.umc.android.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySignupBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.signNameNextBt.setOnClickListener {
            if(binding.signupNameEt.text.toString() != ""){
                val intent = Intent(this, SignupPwdActivity::class.java)
                // 사용자 이름(아이디) 넘겨주기
                intent.putExtra("name", binding.signupNameEt.text.toString())
                startActivity(intent)
            }

        }

        // 한 자리 이상일 때 활성화
        binding.signupNameEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(binding.signupNameEt.length()>0){
                    binding.signNameNextBt.setBackgroundResource(R.drawable.login_round_real_blue)
                }
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(binding.signupNameEt.length()>0){
                    binding.signNameNextBt.setBackgroundResource(R.drawable.login_round_real_blue)

                }
            }

            override fun afterTextChanged(p0: Editable?) {
                if(binding.signupNameEt.length()>0){
                    binding.signNameNextBt.setBackgroundResource(R.drawable.login_round_real_blue)
                }
            }
        }
        )






    }
}