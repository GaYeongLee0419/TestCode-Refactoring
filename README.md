# Test-Refactoring

테스트 코드와 리팩토링을 공부하는 과정입니다.

## 요구사항을 작은 단위로 나누기

복잡한 문제를 풀어가기 위해 첫 번째로 진행해야 하는 작업은,  
복잡한 문제를 작은 단위로 나누어 좀 더 쉬운 문제로 만드는 것입니다.

## 모든 단계의 끝은 리팩토링

소스코드의 복잡도가 쉽게 증가하는 이유는  
하나의 요구사항을 완료한 후 리팩토링을 하지 않은 상태에서 다음 단계로 넘어가기 때문입니다.  

각 단계에서 다음 단계로 넘어가기 위한 작업의 끝은  
내가 기대하는 결과를 확인했을 때가 아니라,  
결과를 확인한 후 **리팩토링까지 완료했을 때**입니다.  

즉, **구현 → 테스트를 통한 결과 확인 → 리팩토링** 순으로 진행해야 합니다.

---

## 문자열 계산기 구현

### 요구사항
1. 전달한 문자를 구분자로 분리한 후 각 숫자의 합을 구해 반환한다.
2. 쉼표(,) 또는 콜론(:)을 구분자로 가지는 문자열을 전달하는 경우 구분자를 기준으로 분리한 각 숫자의 합을 반환한다.
3. 기본 구분자 외에 커스턴 구분자를 지정할 수 있다. 커스텀 구분자는 문자열 앞부분의 "//"와 "\n" 사이에 위치하는 문자를 커스텀 구분자로 사용한다.
4. 문자열 계산기에 음수를 전달하는 경우 RuntimeException으로 예외 처리를 해야 한다.

### 메소드는 한 가지 책임만 가진다

다음과 같은 코드는 단일 책임 원칙을 위반한 코드입니다.

```java
public class StringCalculator {
    public int add(String input) throws Exception {
        if (input == null || input.isEmpty()) return 0;

        // 커스텀 구분자가 있는지 확인
        String[] values;
        Matcher m = Pattern.compile("//(.)\n(.*)").matcher(input);
        if (m.find()) {
            values = m.group(2).split(m.group(1));
        } else {
            values = input.split(",|:");
        }

        // 문자열을 숫자로 변환하면서 음수 체크
        int sum = 0;
        for (String value : values) {
            int number = Integer.parseInt(value);
            if (number < 0) throw new RuntimeException(); // 음수 예외 처리
            sum += number;
        }

        return sum;
    }
}
```

### 문제점

- **단일 책임 원칙 위반**  
  → `add` 메소드가 문자열 파싱, 구분자 처리, 숫자 변환, 예외 처리, 합산까지 모든 작업을 수행합니다.
  
- **가독성 저하**  
  → 코드가 길어지면서 한눈에 이해하기 어려워집니다.
  
- **재사용성 부족**  
  → 나중에 문자열 파싱 로직이나 음수 예외 처리 로직을 다른 곳에서 사용하려 해도 분리가 어렵습니다.

---

### 리팩토링된 코드

위 문제를 해결하기 위해 각 기능을 별도의 메소드로 분리했습니다.

```java
public class StringCalculator {
    private String[] split(String input) {
        Matcher m = Pattern.compile("//(.)\n(.*)").matcher(input);

        if (m.find()) {
            String customCompiler = m.group(1);
            return m.group(2).split(customCompiler);
        }

        return input.split(",|:");
    }

    private boolean isBlank(String input) {
        return input == null || input.isEmpty();
    }

    private int[] toInts(String[] values) throws Exception {
        int[] numbers = new int[values.length];

        for (int i = 0; i < values.length; i++) {
            numbers[i] = toPositive(values[i]);
        }

        return numbers;
    }

    private int toPositive(String value) throws RuntimeException {
        int number = Integer.parseInt(value);
        if (number < 0) throw new RuntimeException();

        return number;
    }

    private int sum(int[] numbers) {
        return Arrays.stream(numbers).sum();
    }

    public int add(String input) throws Exception {
        if (isBlank(input)) return 0;

        return sum(toInts(split(input)));
    }
}
```

---

### 리팩토링 결과

- **메소드별로 하나의 책임만 수행하도록 분리**  
- **가독성이 개선되어 코드의 흐름이 명확해짐**  
- **재사용성이 증가하여, 필요할 때 개별 기능을 쉽게 사용할 수 있음**  

리팩토링을 통해 유지보수성과 확장성이 향상되었습니다! 🚀

