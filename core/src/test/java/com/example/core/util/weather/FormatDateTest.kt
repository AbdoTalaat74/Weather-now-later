package com.example.core.util.weather

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class FormatDateTest {


    @Test
    fun testFormatDateSimple_validDate() {
        // Arrange
        val validDate = "2025-04-08 06:00:00"
        val expectedOutput = "8 April"

        // Act
        val result = formatDateSimple(validDate)

        // Assert
        assertEquals(expectedOutput, result)
    }

    // Test with an invalid date format (fallback to the original string)
    @Test
    fun testFormatDateSimple_invalidDate() {
        // Arrange
        val invalidDate = "2025-04-08"

        // Act
        val result = formatDateSimple(invalidDate)

        // Assert
        assertEquals(invalidDate, result) // Should return the original string
    }

    // Test edge case: Empty string input
    @Test
    fun testFormatDateSimple_emptyString() {
        // Arrange
        val emptyString = ""

        // Act
        val result = formatDateSimple(emptyString)

        // Assert
        assertEquals(emptyString, result)
    }

    // Test edge case: Null input (it will be tested like an empty string because we don't handle null)
    @Test
    fun testFormatDateSimple_nullString() {
        // Arrange
        val nullString = "null"

        // Act
        val result = formatDateSimple(nullString)

        // Assert
        assertEquals(nullString, result)
    }


}