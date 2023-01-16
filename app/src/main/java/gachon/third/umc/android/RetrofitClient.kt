package gachon.third.umc.android

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

object RetrofitClient {
    /* Open API End Point Url */
    private const val BASE_URL = "https://kimmarie.shop/"
    private var instance: Retrofit? = null

    var gson = GsonBuilder().setLenient().create()


    open fun getInstance() : Retrofit {
        if (instance == null) {
            instance = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        } // end if

        return instance!!
    }

}