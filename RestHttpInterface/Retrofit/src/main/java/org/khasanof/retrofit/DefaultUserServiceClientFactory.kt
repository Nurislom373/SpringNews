package org.khasanof.retrofit

import org.springframework.stereotype.Component
import retrofit2.Retrofit


/**
 * @see org.khasanof.retrofit
 * @author Nurislom
 * @since 1/5/2024 9:38 PM
 */
@Component
class DefaultUserServiceClientFactory : UserServiceClientFactory {

    override fun create(retrofit: Retrofit): UserServiceClient {
        return retrofit.create(UserServiceClient::class.java)
    }

}