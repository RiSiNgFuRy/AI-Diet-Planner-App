package com.example.aidietplanner_v1.Interfaces

import com.example.aidietplanner_v1.Java.Models.*
import com.example.aidietplanner_v1.Kotlin.Models.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface RetrofitApiInterface {

    @POST("/registration")
    fun registerUser(@Body registerCredentials: UserRegistrationModel?): Call<UserRegistrationResponseModel?>

    @POST("/login")
    fun verifyLoginCredentials(@Body loginCredential: UserLoginModel): Call<UserLoginResponseModel>

    @POST("/token-verification")
    fun verifyToken(@Body tokenModel: LoginTokenVerificationModel): Call<TokenStatus>

    @GET("/genderType")
    suspend fun getGenderTypes(): Response<GenderOptionsResponseModel>

    @GET("/foodPreferences")
    suspend fun getFoodPreferences(): Response<FoodPreferenceOptionsResponseModel>

    @GET("/goalType")
    suspend fun getAllGoalTypes(): Response<ArrayList<GoalOptionsModel>>

    @POST("/user/{userId}/bmiDetails")
    suspend fun setUserBmiDetails(
        @Path("userId") userId: String,
        @Body bmiDetailsRequestModel: BmiDetailsRequestModel
    ): Response<BMIDetailsModel>

    @POST("/user/{userId}/genderType")
    suspend fun setUserGenderType(
        @Path("userId") userId: String,
        @Query("genderId") genderId: String
    ): Response<SuccessStatusCommonModel>

    @POST("/user/{userId}/age")
    suspend fun setUserAge(
        @Path("userId") userId: String,
        @Query("age") age: Int
    ): Response<UserAgeResponseModel>

    @POST("/user/{userId}/goalType")
    suspend fun setUserGoalType(
        @Path("userId") userId: String,
        @Query("goalId") goalId: String
    ): Response<SuccessStatusCommonModel>

    @POST("/user/{userId}/foodPreferenceType")
    suspend fun setUserFoodPreferenceType(
        @Path("userId") userId: String,
        @Query("foodPreferenceId") foodPreferenceId: String
    ): Response<SuccessStatusCommonModel>

    @GET("/user/{userId}/dietChart")
    suspend fun getUserDietChart(
        @Path("userId") userId: String,
        @Query("type") type: String
    ): Response<DietChartResponseModel>

}