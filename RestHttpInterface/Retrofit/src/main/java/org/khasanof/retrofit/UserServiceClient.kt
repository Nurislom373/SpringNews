package org.khasanof.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * @see org.khasanof.retrofit
 * @author Nurislom
 * @since 1/5/2024 9:32 PM
 */
interface UserServiceClient {

    @GET("/users")
    fun getUsers(): Call<List<GitUsersDTO>>

    @GET("/users/{username}")
    fun getUser(@Path("username") username: String): Call<GitUserDTO>

}