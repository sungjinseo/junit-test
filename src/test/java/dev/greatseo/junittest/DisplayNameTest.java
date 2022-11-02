package dev.greatseo.junittest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class DisplayNameTest {

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