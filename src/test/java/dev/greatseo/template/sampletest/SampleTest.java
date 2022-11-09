package dev.greatseo.template.sampletest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SampleTest {

    /**
     * @RepeatedTest, @ParameterizedTest 처럼 어노테이션명이 Test로 끝나면 별도로 @Test 어노테이션이 없어도 테스트가 가능.
     */


    // 5번 반복
    @RepeatedTest(5)
    void repeatedTestWithRepetitionInfo(RepetitionInfo repetitionInfo) {
        assertEquals(5, repetitionInfo.getTotalRepetitions());
    }

    //{displayName} = @DisplayName (없으면 기본값)
    //{currentRepetition} = 현재 반복 횟수
    //{totalRepetitions} = 총 반복 횟수
    @RepeatedTest(value = 1, name = "{displayName} {currentRepetition}/{totalRepetitions}")
    @DisplayName("Repeat!")
    void customDisplayName(TestInfo testInfo) {
        assertEquals("Repeat! 1/1", testInfo.getDisplayName());
    }

    //RepeatedTest.LONG_DISPLAY_NAME = DISPLAY_NAME_PLACEHOLDER + " :: " + SHORT_DISPLAY_NAME
    @RepeatedTest(value = 1, name = RepeatedTest.LONG_DISPLAY_NAME)
    @DisplayName("Details...")
    void customDisplayNameWithLongPattern(TestInfo testInfo) {
        assertEquals("Details... :: repetition 1 of 1", testInfo.getDisplayName());
    }

    /**
     * @ParameterizedTest는 단독으로 사용되진 않고 어떤 파라미터를 사용하는지에 관한 어노테이션을 추가로 선언해줘야합니다.
     * 추가로 선언하지 않았을 경우 아래와 같은 에러가 발생합니다.
     * org.junit.platform.commons.PreconditionViolationException: Configuration error: You must configure at least one set of arguments for this @ParameterizedTest
     * 파라미터 어노테이션은 총 9개
     */

    /**
     * @ValueSource
     * 다양한 타입의 파라미터를 배열로 받아서 사용할 수 있게 해주는 어노테이션
     * 지원 타입 : short[], byte[], int[], long[], float[], double[], char[], boolean[], String[], Class<?>[]
     * 각 타입명의 소문자에 + "s" 를 붙혀주면 파라미터명입니다. (Strings X, strings O)
     * 파라미터 인자는 1개입니다. 2개 이상 넣을 시 에러가 발생합니다.
     */
    @ParameterizedTest
    @ValueSource(ints = { 1, 2, 3 })
    void testWithValueSource(int intArg) {
        assertTrue(intArg > 0 && intArg < 4);
    }

    // @ValueSource 파라미터로 여러개 값을 넣을 수 없음
    // @ParameterizedTest
    // @ValueSource(ints = { 1, 2, 3 }, strings = {"a", "b", "c"})
    // void testWithValueSource(int intArg, string stringArg) {
    // }

    /**
     * 메소드 인자에 null 값을 넣어줍니다. 메소드 인자가 1개일 때만 사용할 수 있습니다.
     * primitive type (int, long 등)에는 사용할 수 없습니다. 사용하면 아래와 같은 에러 발생합니다.
     * org.junit.jupiter.api.extension.ParameterResolutionException: Error converting parameter at index 0: Cannot convert null to primitive value of type int
     */
    @ParameterizedTest
    @NullSource
    void testWithNullSource(Integer integerArg) {
        assertTrue(integerArg == null);
    }

    // primitive type에는 @NullSource 사용 불가
    // @ParameterizedTest
    // @NullSource
    // void testWithNullSource(int intArg) {
    // }


    /**
     * 메소드 인자에 빈 값 객체 값을 넣어줍니다. 메소드 인자가 1개일 때만 사용할 수 있습니다.
     * 지원 타입 : java.lang.String, java.util.List, java.util.Set, java.util.Map, primitive arrays (e.g., int[], char[][], etc.), object arrays (e.g.,String[], Integer[][], etc.).
     */
    @ParameterizedTest
    @EmptySource
    void testWithEmptySource(Map map) {
        assertTrue(map.isEmpty());
    }

    @Nested
    @DisplayName("A 테스트")
    class testA {

        @Test
        @DisplayName("성공")
        public void success() { /* */ }

        @Test
        @DisplayName("실패")
        public void fail() { /* */ }
    }

    @Nested
    @DisplayName("숫자")
    class testNumber {

        @Nested
        @DisplayName("1 테스트")
        class test1 {

            @Test
            @DisplayName("성공")
            public void success() { /* */ }

            @Test
            @DisplayName("실패")
            public void fail() { /* */ }
        }

        @Nested
        @DisplayName("2 테스트")
        class test2 {

            @Test
            @DisplayName("성공")
            public void success() { /* */ }

            @Test
            @DisplayName("실패")
            public void fail() { /* */ }

        }
    }
}