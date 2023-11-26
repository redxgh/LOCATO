package service

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface AdService {
    @Multipart
    @POST("addAd")
    fun addAd(
        @Part("title") title: RequestBody,
        @Part("description") description: RequestBody,
        @Part("price") price: RequestBody,
        @Part("location") location: RequestBody,
        @Part("surface") surface: RequestBody,
        @Part("rooms") rooms: RequestBody,
        @Part("bathrooms") bathrooms: RequestBody,
        @Part("best") best: RequestBody,
        @Part imagesArr: MultipartBody.Part,
        @Part("type") type: RequestBody,
        @Part("categoryId") categoryId: RequestBody,
        @Part("gender") gender: RequestBody
    ): Call<ResponseBody>
}