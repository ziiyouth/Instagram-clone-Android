package gachon.third.umc.android

data class ProfileMypageResponse(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: MypageItem
){
    data class MypageItem(
        val userIdx: Int,
        val userID: String,
        val userName: String,
        val userIntro: String,
        val userWebsite: String,
        val userProfileImg: String,
        val postNum: Int,
        val followerNum: Int,
        val followingNum: Int
    )

}