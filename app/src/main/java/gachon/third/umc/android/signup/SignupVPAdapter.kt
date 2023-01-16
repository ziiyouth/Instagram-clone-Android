package gachon.third.umc.android.signup

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import gachon.third.umc.android.signup.SignupMailActivity
import gachon.third.umc.android.signup.SignupMailFragment
import gachon.third.umc.android.signup.SignupPhoneFragment


class SignupVPAdapter (fragmentActivity: SignupMailActivity) : FragmentStateAdapter(fragmentActivity) {
    // item의 개수
    override fun getItemCount(): Int = 2

    lateinit var emailFragment: SignupMailFragment

    fun setBundle(fragment: SignupMailFragment){
        emailFragment = fragment
    }

    // position에 따라 어떤 Fragment 보여줄지
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> SignupPhoneFragment()
            1 -> emailFragment
            else -> SignupPhoneFragment()
        }
    }

}