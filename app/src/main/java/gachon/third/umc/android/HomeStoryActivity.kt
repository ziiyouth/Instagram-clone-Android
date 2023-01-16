package gachon.third.umc.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import gachon.third.umc.android.databinding.ActivityHomeStoryBinding
import gachon.third.umc.android.story.StoryData

class HomeStoryActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeStoryBinding
    lateinit var datas : StoryData

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityHomeStoryBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

       val extras = intent.extras
        val id = extras!!["id"] as String
        val time = extras!!["time"] as String
        val storyImg = extras!!["storyImg"] as Int
        val profileImg = extras!!["profileImg"] as Int
        binding.homeStoryUserIDTv.text = id
        binding.storyHourTv.text = time
        binding.homeStoryProImgCv.setBackgroundResource(profileImg)
        binding.homeStoryPhoto.setImageResource(storyImg)


        binding.homeStoryCloseIv.setOnClickListener {
            finish()
        }

        binding.homeStoryHeartIv.setOnClickListener {
            binding.homeStoryHeartIv.setImageResource(R.drawable.ic_heart_fill)
        }

        // 스토리 seekbar
        binding.homeStorySb.isEnabled = false

        val handler= Handler(Looper.getMainLooper())

        Thread() {
            var totalTime = 500
            while(totalTime>= 0) {
                handler.post {
                    binding.homeStorySb.progress = 500 - totalTime
                }
                Thread.sleep(10)
                totalTime--
                if(totalTime == 0){
                    finish()
                }
            }

        }.start()
    }


}