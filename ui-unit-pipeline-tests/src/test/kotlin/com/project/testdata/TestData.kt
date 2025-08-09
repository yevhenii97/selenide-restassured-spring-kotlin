package com.project.testdata

import org.junit.jupiter.params.provider.Arguments
import java.util.stream.Stream

object TestData {

    @JvmStatic
    fun testData1(): Stream<Arguments> {
        return Stream.of(
            Arguments.of("value1", "value2"),
            Arguments.of("value3", "value4")
        )
    }

}