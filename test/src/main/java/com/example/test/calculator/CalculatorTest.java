package com.example.test.calculator;

/*
*
*
*/
public class CalculatorTest {

    /*
    테스트를 담당하는 클래스를 별도로 추가하여 테스트 코드를 분리하였지만
    main() 메서드 하나에서 프로덕션 코드의 여러 메서드를 동시에 테스트하고 있다.
    따라서 프로덕션 코드의 복잡도가 증가하면 main() 메서드의 복잡도도 동시에 증가하여 main() 메서드를 유지하기 부담스러워진다.

    public static void main(String[] args) {
        Calculator cal = new Calculator();

        System.out.println(cal.add(1, 2));
        System.out.println(cal.subtract(1, 2));
        System.out.println(cal.multiply(1, 2));
        System.out.println(cal.divide(1, 2));
    }
     */

    /*
    위와 같은 문제를 해결하기 위해 각 메서드 별로 분리하였다.
    그러나 이 또한 테스트 결과를 콘솔에 출력되는 값을 통해 수동으로 확인해야 한다는 불편함이 있다.
    지금처럼 간단한 로직인 경우는 개발자가 쉽게 결과를 예측할 수 있으나 로직의 복잡도가 높은 경우는 다르다.
    구현한 지 한달이 지난 시점에서 프로덕션 코드의 복잡한 로직을 머릿속으로 계산해 결과 값이 정상적으로 출력되는지 일일이 확인해야 한다.
     */

    public static void main(String[] args) {
        Calculator cal = new Calculator();
        add(cal);
        subtract(cal);
        multiply(cal);
        divide(cal);

    }

    private static void add (Calculator cal) {
        System.out.println(cal.add(1, 2));
    }

    private static void subtract (Calculator cal) {
        System.out.println(cal.subtract(1, 2));
    }

    private static void multiply (Calculator cal) {
        System.out.println(cal.multiply(1, 2));
    }

    private static void divide (Calculator cal) {
        System.out.println(cal.divide(1, 2));
    }
}
