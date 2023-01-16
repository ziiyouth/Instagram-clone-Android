package gachon.third.umc.android

data class UserLoginResponse(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: LoginItem
){
    data class LoginItem(
        val userIdx: Int,
        val jwt: String
    )

}