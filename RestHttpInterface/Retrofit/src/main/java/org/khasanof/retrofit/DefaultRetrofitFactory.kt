package org.khasanof.retrofit

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.springframework.stereotype.Component
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * @see org.khasanof.retrofit
 * @author Nurislom
 * @since 1/5/2024 9:41 PM
 */
@Component
class DefaultRetrofitFactory : RetrofitFactory {

    private val httpLoggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BASIC)

    override fun create(): Retrofit {
        val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        val okHttpClient: OkHttpClient = httpClient.build()

        addLoggingInterceptor(okHttpClient = okHttpClient)
        addAuthorizationInterceptor(okHttpClient = okHttpClient)

        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build();
    }

    private fun addLoggingInterceptor(okHttpClient: OkHttpClient) {
        if (!okHttpClient.interceptors.contains(httpLoggingInterceptor))
            okHttpClient.interceptors.plus(httpLoggingInterceptor)
    }

    private fun addAuthorizationInterceptor(okHttpClient: OkHttpClient) {
        okHttpClient.interceptors.plus(Interceptor { chain: Interceptor.Chain ->
            val request = chain.request()
            val buildRequest = request.newBuilder()
                .header("Authorization", "YOUR_TOKEN")
                .build()

            return@Interceptor chain.proceed(buildRequest)
        })
    }

}