import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {

    // Task 2: Functional Interface
    @FunctionalInterface
    interface MathOperation {
        int operate(int a, int b);
    }

    // Task 5: Custom Functional Interface
    @FunctionalInterface
    interface StringOperation {
        String apply(String s1, String s2);
    }

    public static void main(String[] args) {
        // Task 1: Understanding Lambda Expressions
        System.out.println("Task 1: Understanding Lambda Expressions");
        Runnable runnable = () -> System.out.println("Lambda expression implementing Runnable");
        runnable.run();

        // Task 2: Using Lambda Expressions with Functional Interfaces
        System.out.println("\nTask 2: Using Lambda Expressions with Functional Interfaces");
        MathOperation addition = (a, b) -> a + b;
        MathOperation subtraction = (a, b) -> a - b;
        MathOperation multiplication = (a, b) -> a * b;
        MathOperation division = (a, b) -> {
            if (b == 0) {
                throw new ArithmeticException("Division by zero");
            }
            return a / b;
        };

        System.out.println("Addition: " + addition.operate(10, 5));
        System.out.println("Subtraction: " + subtraction.operate(10, 5));
        System.out.println("Multiplication: " + multiplication.operate(10, 5));
        try {
            System.out.println("Division: " + division.operate(10, 5));
        } catch (ArithmeticException e) {
            System.out.println("Error during division: " + e.getMessage());
        }
        try {
            System.out.println("Division by zero: ");
            division.operate(10, 0);
        } catch (ArithmeticException e) {
            System.out.println("Error during division: " + e.getMessage());
        }

        // Task 3: Using Lambda Expressions with Collections
        System.out.println("\nTask 3: Using Lambda Expressions with Collections");
        // Sort a List
        List<String> stringList = new ArrayList<>(Arrays.asList("apple", "banana", "cherry", "date"));
        Collections.sort(stringList, (s1, s2) -> s1.compareTo(s2));
        System.out.println("Sorted String List: " + stringList);

        // Filter a List
        List<Integer> integerList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        List<Integer> oddNumbers = new ArrayList<>();
        integerList.forEach(number -> {
            if (number % 2 != 0) {
                oddNumbers.add(number);
            }
        });
        System.out.println("Odd Numbers: " + oddNumbers);

        // Task 4: Using Built-in Functional Interfaces
        System.out.println("\nTask 4: Using Built-in Functional Interfaces");
        // Use Predicate
        Predicate<String> isEmpty = String::isEmpty;
        System.out.println("Is \"\" empty? " + isEmpty.test(""));
        System.out.println("Is \"hello\" empty? " + isEmpty.test("hello"));

        // Use Function
        Function<String, String> toUpperCase = String::toUpperCase;
        System.out.println("Uppercase of \"hello\": " + toUpperCase.apply("hello"));

        // Task 5: Advanced Usage
        System.out.println("\nTask 5: Advanced Usage");
        // Compose Functions
        Function<Integer, Integer> addOne = x -> x + 1;
        Function<Integer, Integer> multiplyByTen = x -> x * 10;
        Function<Integer, Integer> addOneThenMultiplyByTen = addOne.andThen(multiplyByTen);
        System.out.println("Add 1 then multiply by 10 (5): " + addOneThenMultiplyByTen.apply(5));

        // Custom Functional Interface
        StringOperation concatenate = (s1, s2) -> s1 + s2;
        StringOperation findLonger = (s1, s2) -> s1.length() > s2.length() ? s1 : s2;

        System.out.println("Concatenate \"hello\" and \"world\": " + concatenate.apply("hello", "world"));
        System.out.println("Longer of \"hello\" and \"hi\": " + findLonger.apply("hello", "hi"));
    }
}