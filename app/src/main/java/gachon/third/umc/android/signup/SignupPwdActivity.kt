package gachon.third.umc.android.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import gachon.third.umc.android.R
import gachon.third.umc.android.databinding.ActivitySignupPwdBinding

class SignupPwdActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignupPwdBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySignupPwdBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val extras = intent.extras
        val name = extras!!["name"] as String

//        Log.d("name", name)
        binding.signPwdNextBt.setOnClickListener {
            if(binding.signupPwdEt.text.length > 5) {

                val intent = Intent(this, SignupNameActivity::class.java)
                intent.putExtra("name", name)
                intent.putExtra("pwd", binding.signupPwdEt.text.toString())
                startActivity(intent)
            }
        }

        // 여섯자리 이상일 때 활성화
        binding.signupPwdEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(binding.signupPwdEt.length()>5){
                    binding.signPwdNextBt.setBackgroundResource(R.drawable.login_round_real_blue)
                }
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(binding.signupPwdEt.length()>5){
                    binding.signPwdNextBt.setBackgroundResource(R.drawable.login_round_real_blue)

                }
            }

            override fun afterTextChanged(p0: Editable?) {
                if(binding.signupPwdEt.length()>5){
                    binding.signPwdNextBt.setBackgroundResource(R.drawable.login_round_real_blue)
                }
            }
        }
        )
    }
}