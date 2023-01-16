package gachon.third.umc.android.profile


import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayoutMediator
import gachon.third.umc.android.*
import gachon.third.umc.android.story.StoryHLRVAdapter
import gachon.third.umc.android.databinding.FragmentProfileBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProfileFragment: Fragment() {

    lateinit var binding: FragmentProfileBinding
    private var storyHlData : ArrayList<StoryHighlightData> = arrayListOf()
    // registerForActivityResult로 이름, 아이디, 자기소개 받기
    val startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == RESULT_OK) {

            val name = result.data?.getStringExtra("name")!!
            binding.profileName.text = name
            val id = result.data?.getStringExtra("id")!!
            binding.profileId.text = id
            val intro = result.data?.getStringExtra("intro")!!
            binding.profileIntro.text = intro

            // 비어있으면 View.GONE 으로 설정
            if (binding.profileName.text != null && binding.profileName.text != "") {
                binding.profileName.visibility = View.VISIBLE
            }
            else {
                binding.profileName.visibility= View.GONE
            }
            if (binding.profileIntro.text != null && binding.profileIntro.text != "") {
                binding.profileIntro.visibility = View.VISIBLE
            }
            else {
                binding.profileIntro.visibility = View.GONE
            }


        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        if (binding.profileName.text != null && binding.profileName.text != "") {
            binding.profileName.visibility = View.VISIBLE
        }
        else {
            binding.profileName.visibility= View.GONE
        }
        if (binding.profileIntro.text != null && binding.profileIntro.text != "") {
            binding.profileIntro.visibility = View.VISIBLE
        }
        else {
            binding.profileIntro.visibility = View.GONE
        }

        requestProfileMypage()


        // 프로필 수정 버튼
        binding.profileEdit.setOnClickListener{
            val intent = Intent(activity, ProfileEditActivity::class.java)

            // 이름, 아이디, 자기소개 ProfileEditActivity로 보내주기
            var name: String = binding.profileName.text.toString()
            var id: String = binding.profileId.text.toString()
            var intro: String = binding.profileIntro.text.toString()
            intent.putExtra("name", name)
            intent.putExtra("id", id)
            intent.putExtra("intro", intro)

            startForResult.launch(intent)
        }

        connectViewPager()
        applyStoryHlData()

        return binding.root

    }

    override fun onResume(){
        super.onResume()
        requestProfileMypage()
    }



    // 탭 뷰페이저 연결
    private fun connectViewPager() {
        val profileAdapter = ProfileVPAdapter(this)
        binding.profileVp.adapter = profileAdapter

        //val tabIconArray = arrayOf(
        //    R.drawable.ic_postgrid,
        //    R.drawable.ic_myinfo_tag
        //)


        TabLayoutMediator(binding.profileTab, binding.profileVp){ tab, position ->
            //tab.icon = getDrawable(tabIconArray[position])
            when(position){
                0-> tab.setIcon(R.drawable.ic_postgrid)
                1-> tab.setIcon(R.drawable.ic_myinfo_tag)
            }

        }.attach()
    }

    private fun applyStoryHlData() {

        val storyHLAdapter = StoryHLRVAdapter(storyHlData)
        binding.profileStoryHighlightRv.adapter = storyHLAdapter
        binding.profileStoryHighlightRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        storyHlData.apply {
            add(StoryHighlightData(R.drawable.profile_story_add_bt, "", 0))
            add(StoryHighlightData(R.drawable.profile_story_highlight, "", 1))
            add(StoryHighlightData(R.drawable.profile_story_highlight, "", 1))
            add(StoryHighlightData(R.drawable.profile_story_highlight, "", 1))
            add(StoryHighlightData(R.drawable.profile_story_highlight, "", 1))
            add(StoryHighlightData(R.drawable.profile_story_highlight, "", 1))
            add(StoryHighlightData(R.drawable.profile_story_highlight, "", 1))
        }

    }



    private fun requestProfileMypage() {
        val profileMypageService = RetrofitClient.getInstance().create(ApiService::class.java)
        val listCall = profileMypageService.getMypageProfile(MyApplication.prefs.getString("accessToken", "no accessToken"))
        listCall.enqueue(object : Callback<ProfileMypageResponse> {
            override fun onResponse(
                call: Call<ProfileMypageResponse>,
                response: Response<ProfileMypageResponse>
            ) {
                val responseData = response.body()
                if (response.isSuccessful) {

                    if (responseData != null) {
                        if (responseData.isSuccess) {
//                            Log.d(TAG, "성공 : ${response.raw()}")
                            Log.d("Retrofit", "Response\nSuccess : ${responseData.isSuccess}\n" + "code: ${responseData.code}\nmessage: ${responseData.message}\n" + "result: ${responseData.result}\n")
                            binding.profileName.text = responseData.result.userName
                            binding.profileId.text = responseData.result.userID
                            binding.profileIntro.text = responseData.result.userIntro
                            binding.profileFollowingNum.text = responseData.result.followingNum.toString()
                            binding.profileFollowerNum.text = responseData.result.followerNum.toString()
                            binding.profilePostNum.text = responseData.result.postNum.toString()

                        }
                        else{
                            Log.d("Retrofit", "Response\nSuccess : ${responseData.isSuccess}\n" + "code: ${responseData.code}\nmessage: ${responseData.message}\n" + "result: ${responseData.result}\n")
                            val toast = Toast.makeText(activity, responseData.message, Toast.LENGTH_LONG)
                            toast.show()

                        }
                    }
                } // end if

            }

            override fun onFailure(call: Call<ProfileMypageResponse>, t: Throwable) {
                // 실패
                Log.d(ContentValues.TAG, "실패 : $t")
            }

        })
    }







}