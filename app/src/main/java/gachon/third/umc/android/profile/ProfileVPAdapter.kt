package gachon.third.umc.android.profile

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import gachon.third.umc.android.profile.ProfileFragment
import gachon.third.umc.android.profile.ProfilePostFragment
import gachon.third.umc.android.profile.ProfileTagFragment

// ProfileFragment 뷰페이저 어댑터
class ProfileVPAdapter(fragmentActivity: ProfileFragment) : FragmentStateAdapter(fragmentActivity) {
    // item의 개수
    override fun getItemCount(): Int = 2

    // position에 따라 어떤 Fragment 보여줄지
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ProfilePostFragment()
            1 -> ProfileTagFragment()
            else -> ProfilePostFragment()
        }
    }

}