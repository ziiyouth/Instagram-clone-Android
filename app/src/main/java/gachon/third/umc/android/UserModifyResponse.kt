package gachon.third.umc.android

data class UserModifyResponse(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: String
)