package org.khasanof.retrofit

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.assertTrue


/**
 * @see org.khasanof.retrofit
 * @author Nurislom
 * @since 1/5/2024 9:44 PM
 */
@SpringBootTest
class RetrofitClientTest {

    @Autowired
    private lateinit var retrofitFactory: RetrofitFactory

    @Autowired
    private lateinit var userServiceClientFactory: UserServiceClientFactory

    private val objectMapper: ObjectMapper = ObjectMapper()

    @Test
    fun firstTestRetrofitShouldSuccess() {
        objectMapper.registerModules(JavaTimeModule())

        val retrofit = retrofitFactory.create()
        val userServiceClient = userServiceClientFactory.create(retrofit)

        val users = userServiceClient.getUsers()
        val execute = users.execute()

        println("response : ${objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(execute.body())}")

        assertTrue { execute.isSuccessful }
    }

}