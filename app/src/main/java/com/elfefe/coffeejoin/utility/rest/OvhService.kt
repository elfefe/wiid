package com.elfefe.coffeejoin.utility.rest

import com.elfefe.coffeejoin.models.consumervalidation.ConsumerValidation
import com.elfefe.coffeejoin.models.credential.Credentials
import com.elfefe.coffeejoin.utility.*
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


interface OvhService {

    @Headers(
        "Content-Type: application/json",
        "X-Ovh-Application: $APP_KEY"
    )
    @POST("auth/credential")
    fun consumerKey(@Body credentials: Credentials): Call<ConsumerValidation>?

    @GET("cloud/project/$SERVICE_NAME/storage/$CONTAINER_MESSAGERIE")
    fun container(
        @HeaderMap headers: Map<String, String>
    ): Call<String>?

    class Request {
        private fun getService(): OvhService {
            val builder = OkHttpClient
                .Builder()
                .addInterceptor {
                    log(
                        "Headers:\n ${it.request().headers()} \n Body: ${it.request().url().toString()}"
                    )
                    it.proceed(it.request())
                }.build()

            val retrofit = Retrofit
                .Builder()
                .baseUrl(OVH_URL)
                .client(builder)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(OvhService::class.java)
        }

        fun getConsumerValidation(credentials: Credentials): ConsumerValidation? {
            val consumerValidation = getService().consumerKey(credentials)?.execute()
            return consumerValidation?.body()
        }

        fun getContainerContent(headers: Map<String, String>): String? {
            val container = getService().container(headers)?.execute()
            log("Message:\n ${container.toString()}\n body:  ${container?.errorBody()?.string()}")
            return container?.body()
        }
    }
}