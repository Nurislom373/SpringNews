package org.khasanof.junit5spring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: Nurislom
 * <br/>
 * Date: 05.05.2023
 * <br/>
 * Time: 23:25
 * <br/>
 * Package: org.khasanof.junit5spring
 */
@DisplayName("List Interface Several Case Tests")
public class ListInterfaceTest {

    List<Integer> nums;

    @Test
    void initialize() {
        nums = new ArrayList<>();
    }

    @Nested
    @DisplayName("When Is New")
    class WhenIsNew {

        @BeforeEach
        void beforeEach() {
            nums = new ArrayList<>();
        }

        @Test
        void isNonNullTest() {
            assertFalse(Objects.isNull(nums));
        }

        @Test
        void isEmptyTest() {
            assertTrue(nums.isEmpty());
        }

        @Test
        void throwsRuntimeException() {
            assertThrows(RuntimeException.class, () -> nums.get(0));
        }

        @Test
        void throwsArrayIndexOutOfBound() {
            assertThrows(IndexOutOfBoundsException.class, () -> nums.get(9));
        }

        @Nested
        @DisplayName("After Pushing Element Several Test Cases")
        class AfterPushingElementTest {

            @BeforeEach
            void beforeEach() {
                nums.add(18);
            }

            @Test
            void isNotEmpty() {
                assertFalse(nums.isEmpty());
            }

            @Test
            void listElementEqualsTest() {
                assertEquals(18, nums.get(0));
            }

        }

    }

}
