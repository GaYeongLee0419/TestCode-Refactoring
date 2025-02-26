package com.example.test.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class StringCalculatorTest {
    private StringCalculator calculator;

    @BeforeEach
    public void setUp() {
        calculator = new StringCalculator();
    }

    @Test
    public void add_null() throws Exception {
        assertEquals(0, calculator.add(null));
        assertEquals(0, calculator.add(""));
    }

    @Test
    public void add_oneNumber() throws Exception {
        assertEquals(1, calculator.add("1"));
    }

    @Test
    public void add_쉼표구분자() throws Exception {
        assertEquals(3, calculator.add("1,3"));
    }

    @Test
    public void add_쉽표_또는_콜론_구분자() throws Exception {
        assertEquals(6, calculator.add("1,2:3"));
    }

    @Test
    public void add_custom_구분자() throws Exception {
        assertEquals(6, calculator.add("//;\n1;2;3"));
    }

    @Test
    public void add_negative() throws Exception {
        assertThrows(RuntimeException.class, () -> {
            calculator.add("-1,2,3");
        });
    }

}
