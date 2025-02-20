package com.example.test.calculator;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CalculatorTestJUnit {
    /*
    (1). 한 번에 하나의 메소드에만 집중
            독립적으로 테스트를 할 수 있다.
            -> 현재 내가 구현하고 있는 프로덕션 코드의 메서드만 실행할 수 있다.

    (2). 결과 값을 눈이 아닌 프로그램을 통해 자동화
            assertEquals 메소드를 이용해 테스트가 성공하거나 실패했을 경우는 원인을 알 수 있다.

    (3). 테스트 코드 중복 제거
            @BeforeEach 어노테이션을 사용해 매 테스트마다 생성하도록 하면 중복된 코드를 제거할 수 있다.
            매 테스트마다 인스턴스를 새로 생성해야 하는 이유는 add() 메소드를 실행할 때 Calculator의 상태 값이 변경되어
            다음 테스트 메소드인 subtract() 테스트 메소드를 실행할 때 영향을 미칠 수 있기 때문이다.
            따라서 추후 문제가 발생할 가능성을 없앨 수 있다.

            [어노테이션 실행 시점 static 필요 여부	이유]
            @BeforeAll	모든 테스트 실행 전 (1회)	✅ (필수)	모든 테스트 전에 한 번만 실행되므로, 인스턴스가 필요 없음
            @BeforeEach	각 테스트 실행 전	        ❌ (불필요)	각 테스트마다 새로운 인스턴스가 생성되므로 필요 없음
            @AfterEach	각 테스트 실행 후	        ❌ (불필요)	각 테스트마다 새로운 인스턴스가 생성되므로 필요 없음
            @AfterAll	모든 테스트 실행 후 (1회)	✅ (필수)	모든 테스트 후 한 번만 실행되므로, 인스턴스가 필요 없음

            테스트 결과
            Before executing the test class.
            Setting up before each test.
            subtract
            Tearing down after all tests.
            Setting up before each test.
            divide
            Tearing down after all tests.
            Setting up before each test.
            add
            Tearing down after all tests.
            Setting up before each test.
            multiply
            Tearing down after all tests.
            Tearing down after each test.
     */

    private Calculator cal;     //(3)

    //(3)
    @BeforeAll
    public static void setUpBeforeClass() {
        System.out.println("Before executing the test class.");
    }
    //(3)
    @BeforeEach
    public void setUp() {
        System.out.println("Setting up before each test.");
        cal = new Calculator();
    }

    @Test
    public void add() {
//        Calculator cal = new Calculator();                     (3) 중복 제거
//        System.out.println(cal.add(1, 2));                     (1)
        assertEquals(3, cal.add(1, 2));         //(2)
//        assertEquals(1, cal.add(1, 2));                      //(2)
        System.out.println("add");
        /*
        출력
        3

        Expected :1
        Actual   :3
         */
    }

    @Test
    public void subtract() {
//        Calculator cal = new Calculator();                       (3) 중복 제거
//        System.out.println(cal.subtract(1, 2));                  (1)
        assertEquals(-1, cal.subtract(1, 2));     //(2)
        System.out.println("subtract");
    }

    @Test
    public void multiply() {
//        Calculator cal = new Calculator();                        (3) 중복 제거
//        System.out.println(cal.multiply(1, 2));                   (1)
        assertEquals(2, cal.multiply(1, 2));       //(2)
        System.out.println("multiply");
    }

    @Test
    public void divide() {
//        Calculator cal = new Calculator();                       (3) 중복 제거
//        System.out.println(cal.divide(1, 2));                    (1)
        assertEquals(0, cal.divide(1, 2));        //(2)
        System.out.println("divide");
    }

    //(3)
    @AfterAll
    public static void teardownAfterTestClass() {
        System.out.println("Tearing down after each test.");
    }
    //(3)
    @AfterEach
    public void teardownAfterTest(){
        System.out.println("Tearing down after all tests.");

    }
}
