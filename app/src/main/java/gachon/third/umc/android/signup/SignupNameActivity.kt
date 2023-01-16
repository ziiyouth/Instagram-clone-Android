package gachon.third.umc.android.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import gachon.third.umc.android.R
import gachon.third.umc.android.databinding.ActivitySignupNameBinding

class SignupNameActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignupNameBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySignupNameBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val extras = intent.extras

        val name = extras!!["name"] as String
        val pwd = extras!!["pwd"] as String

        Log.d("name 2", name)
        Log.d("pwd", pwd)

        binding.signupNicknameNextBt.setOnClickListener {
            if(binding.signupNicknameEt.text.isNotEmpty()) {
                val intent = Intent(this, SignupMailActivity::class.java)

                intent.putExtra("name", name)
                intent.putExtra("pwd", pwd)
                intent.putExtra("nickname", binding.signupNicknameEt.text.toString())
                startActivity(intent)
            }
        }


        // 한 자리 이상일 때 활성화
        binding.signupNicknameEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(binding.signupNicknameEt.length()>0){
                    binding.signupNicknameNextBt.setBackgroundResource(R.drawable.login_round_real_blue)
                }
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(binding.signupNicknameEt.length()>0){
                    binding.signupNicknameNextBt.setBackgroundResource(R.drawable.login_round_real_blue)

                }
            }

            override fun afterTextChanged(p0: Editable?) {
                if(binding.signupNicknameEt.length()>0){
                    binding.signupNicknameNextBt.setBackgroundResource(R.drawable.login_round_real_blue)
                }
            }
        }
        )

    }
}