package org.khasanof.retrofit

import retrofit2.Retrofit


/**
 * @see org.khasanof.retrofit
 * @author Nurislom
 * @since 1/5/2024 9:37 PM
 */
interface UserServiceClientFactory {

    fun create(retrofit: Retrofit): UserServiceClient

}