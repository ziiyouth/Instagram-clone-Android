package gachon.third.umc.android

import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @POST("user/join")
    fun postSignupUser(@Body params: UserJoinRequest) : Call<UserJoinResponse>

    @POST("user/login")
    fun postLoginUser(@Body params: UserLoginRequest) : Call<UserLoginResponse>

    @GET("mypage/profile")
    fun getMypageProfile(
        @Header("X-ACCESS-TOKEN") accessToken: String
    ): Call<ProfileMypageResponse>

    @PATCH("user/mod")
    fun patchUserModify(
        @Header("X-ACCESS-TOKEN") accessToken: String,
        @Body params: UserModifyRequest
    ): Call<UserModifyResponse>

}