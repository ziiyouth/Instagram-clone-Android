package gachon.third.umc.android.story
import java.io.Serializable

data class StoryData(

    var profileImg: Int,
    var id: String,
    var status : Int,
    // 0 = 내 스토리, 1 = 남의 스토리
    var time: String,
    var storyImg : Int

)
