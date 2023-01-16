package gachon.third.umc.android

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import gachon.third.umc.android.databinding.FragmentHomeBinding
import gachon.third.umc.android.story.StoryData
import gachon.third.umc.android.story.StoryRVAdapter

class HomeFragment: Fragment() {

    lateinit var binding: FragmentHomeBinding

    private val storyData : ArrayList<StoryData> = arrayListOf()
    private val storyAdapter = StoryRVAdapter(storyData)
    private val postData : ArrayList<PostData> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        applyStoryData()
        applyPostData()

        return binding.root
    }


    private fun applyStoryData() {

        storyData.apply {
            add(StoryData(R.drawable.ic_profile_default,"내 스토리", 0, "1시간 전", R.drawable.ic_story_1))
            add(StoryData(R.drawable.ic_profile_default, "eraofband", 1, "2시간 전", R.drawable.ic_story_2))
            add(StoryData(R.drawable.ic_profile_default, "band_lucy", 1, "3시간 전", R.drawable.ic_story_1))
            add(StoryData(R.drawable.ic_profile_default, "yookihhh", 1, "5시간 전", R.drawable.ic_story_3))
            add(StoryData(R.drawable.ic_profile_default, "d.ddablue", 1, "6시간 전", R.drawable.ic_story_4))
            add(StoryData(R.drawable.ic_profile_default, "phenomenon_h", 1, "10시간 전", R.drawable.ic_story_5))
            add(StoryData(R.drawable.ic_profile_default, "ye___chani", 1, "12시간 전", R.drawable.ic_post_6))
            add(StoryData(R.drawable.ic_profile_default, "leetape", 1, "17시간 전", R.drawable.ic_post_2))
        }

        binding.homeStoryRv.adapter = storyAdapter
        binding.homeStoryRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        storyAdapter.setOnItemClickListener(object : StoryRVAdapter.OnItemClickListener{
            override fun onItemClick(v: View, data: StoryData, pos: Int) {

//                Log.d("Storydata1 : ", data.profileImg.toString())
//                Log.d("Storydata2 : ", data.id)
//                Log.d("Storydata3 : ", data.time)
//                Log.d("Storydata4 : ", data.storyImg.toString())

                val intent = Intent(activity, HomeStoryActivity::class.java)
                intent.putExtra("profileImg", data.profileImg)
                intent.putExtra("id", data.id)
                intent.putExtra("time", data.time)
                intent.putExtra("storyImg", data.storyImg)

                startActivity(intent)

            }

        })

    }

    private fun applyPostData() {

        val postAdapter = PostRVAdapter(postData)
        binding.homePostRv.adapter = postAdapter
        binding.homePostRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        postData.apply {
            add(PostData(R.drawable.ic_profile_default, "zyoxng", R.drawable.ic_post_1, "76", "zyoxng 오늘은 날씨가 좋네요... 흐앙", "36", "4시간 전"))
            add(PostData(R.drawable.ic_profile_default, "ye___chani", R.drawable.ic_post_2, "681", "ye___chani 샤샤샥 샤샤샥 샤샤샥 샤샤샥 샤샤샥 샤샤샥", "142", "9시간 전"))
            add(PostData(R.drawable.ic_profile_default, "d.ddablue", R.drawable.ic_post_3, "983", "d.ddablue 사랑하긴 했었나요 스쳐가는 인연이었나요 짧지않은 우리 함께했던 시간들이 자꾸 내 마음을 가둬두네", "51", "2일 전"))
            add(PostData(R.drawable.ic_profile_default, "_shin__seol_", R.drawable.ic_post_4, "77", "_shin__seol_ 멍멍 멍멍멍!! 멍멍!!!", "6", "5일 전"))
            add(PostData(R.drawable.ic_profile_default, "phenomenon_h", R.drawable.ic_post_5, "25", "phenomenon_h 그래도 사과나무를 심으리", "3", "10월 11일"))
            add(PostData(R.drawable.ic_profile_default, "kim_bora95", R.drawable.ic_post_6, "42", "kim_bora95 지나간 여름 밤", "9", "9월 13일"))

        }
    }


}

