package com.fileee.payroll.services

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class SmartLogicTests {
    lateinit var logicTasks: SmallLogicTasks
    @Test
    fun testStarringString(){
        logicTasks = SmallLogicTasks()
        var result = logicTasks.starringString("fooBar", "", 0)
        assertThat(result).isEqualTo("fo*oBar")

        result = logicTasks.starringString("aaahhjjj", "", 0)
        assertThat(result).isEqualTo("a*a*ah*hj*j*j")

        result = logicTasks.starringString("bookkeeper", "", 0)
        assertThat(result).isEqualTo("bo*ok*ke*eper")
    }

    @Test
    fun testValidateEmail(){
        logicTasks = SmallLogicTasks()
        var result = logicTasks.isEmailValid("@foo.com")
        assertThat(result).isEqualTo(false)

        result = logicTasks.isEmailValid("foo.bar@com")
        assertThat(result).isEqualTo(false)

        result = logicTasks.isEmailValid("rfashwal@hotmail.com")
        assertThat(result).isEqualTo(true)
    }
}