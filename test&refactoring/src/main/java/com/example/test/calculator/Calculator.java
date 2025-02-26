package com.example.test.calculator;

/*
*
* 프로덕션 코드와 테스트 코드가 같은 클래스에 위치한다.
* 서비스하는 시점에는 테스트 코드가 필요없으므로 테스트 코드(CalculatorTest)를 분리할 필요가 있다.
*
*/
public class Calculator {
    int add (int x, int y) {
        return x + y;
    }

    int subtract (int x, int y) {
        return x - y;
    }

    int multiply (int x, int y) {
        return x * y;
    }

    int divide (int x, int y) {
        return x / y;
    }

//    public static void main(String[] args) {
//        Calculator cal = new Calculator();
//
//        System.out.println(cal.add(1, 2));
//        System.out.println(cal.subtract(1, 2));
//        System.out.println(cal.multiply(1, 2));
//        System.out.println(cal.divide(1, 2));
//    }
}
