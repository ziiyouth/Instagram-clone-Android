package gachon.third.umc.android.signup

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import gachon.third.umc.android.databinding.ActivitySignupMailBinding

class SignupMailActivity : AppCompatActivity() {


    lateinit var binding: ActivitySignupMailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySignupMailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        // 값 받아오기
        val extras = intent.extras
        val name = extras!!["name"] as String
        val pwd = extras!!["pwd"] as String
        val nickname = extras!!["nickname"] as String

        val signupAdapter = SignupVPAdapter(this)
        binding.signupVp.adapter = signupAdapter

        connectViewPager()

        // 정보 번들로 이메일 입력 프래그먼트에 넘겨주기
        val bundle = Bundle()
        bundle.putString("name", name)
        bundle.putString("pwd", pwd)
        bundle.putString("nickname", nickname)
        val fragmentA = SignupMailFragment()
        fragmentA.arguments = bundle

        signupAdapter.setBundle(fragmentA)

        setContentView(binding.root)

    }

    // 탭 뷰페이저 연결
    fun connectViewPager() {

        TabLayoutMediator(binding.signupEmailTab, binding.signupVp){ tab, position ->
            when(position){
                0-> tab.text = "전화번호"
                1-> tab.text = "이메일"
            }

        }.attach()
    }
}