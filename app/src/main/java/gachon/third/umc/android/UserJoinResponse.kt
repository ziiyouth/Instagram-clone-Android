package gachon.third.umc.android

data class UserJoinResponse(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: SignupItem
){
    data class SignupItem(
            val userIdx: Int,
            val jwt: String
        )

}


