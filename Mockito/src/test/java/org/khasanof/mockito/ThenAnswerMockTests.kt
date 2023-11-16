package org.khasanof.mockito

import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.mockito.stubbing.Answer


class ThenAnswerMockTests {

    @Test
    fun thenAnswerFirstTest() {

        val mockList = mock(MutableList::class.java)

        `when`(mockList.add(any())).thenAnswer(
            Answer {
                val arguments = it.arguments
                val mock = it.mock
                return@Answer "called with arguments: ${it.arguments.contentToString()}"
            }
        )

        print(mockList.add("foo" as Nothing))
        verify(mockList).add("foo" as Nothing)
    }

    @Test
    fun yourOwnAnswers() {
        mock(MutableList::class.java, Answer { it })
    }

}
