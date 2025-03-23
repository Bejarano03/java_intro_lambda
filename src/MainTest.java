import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private Main main; // Instance of Main class for testing
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        main = new Main(); // Initialize Main instance
        System.setOut(new PrintStream(outContent));
    }

    @org.junit.jupiter.api.AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    // Helper method to get the MathOperation interface
    private Main.MathOperation getMathOperation(String operation) {
        if (operation.equals("addition")) {
            return (a, b) -> a + b;
        } else if (operation.equals("subtraction")) {
            return (a, b) -> a - b;
        } else if (operation.equals("multiplication")) {
            return (a, b) -> a * b;
        } else if (operation.equals("division")) {
            return (a, b) -> {
                if (b == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return a / b;
            };
        }
        return null;
    }

    // Helper method to get the StringOperation interface
    private Main.StringOperation getStringOperation(String operation) {
        if (operation.equals("concatenate")) {
            return (s1, s2) -> s1 + s2;
        } else if (operation.equals("findLonger")) {
            return (s1, s2) -> s1.length() > s2.length() ? s1 : s2;
        }
        return null;
    }

    // Normal Cases

    @Test
    void testAddition_NormalCase() {
        Main.MathOperation addition = getMathOperation("addition");
        assertEquals(15, addition.operate(10, 5));
    }

    @Test
    void testSubtraction_NormalCase() {
        Main.MathOperation subtraction = getMathOperation("subtraction");
        assertEquals(5, subtraction.operate(10, 5));
    }

    @Test
    void testMultiplication_NormalCase() {
        Main.MathOperation multiplication = getMathOperation("multiplication");
        assertEquals(50, multiplication.operate(10, 5));
    }

    // Edge Cases

    @Test
    void testDivision_EdgeCase_ZeroDividend() {
        Main.MathOperation division = getMathOperation("division");
        assertEquals(0, division.operate(0, 5));
    }

    @Test
    void testDivision_EdgeCase_DivisionByZero() {
        Main.MathOperation division = getMathOperation("division");
        ArithmeticException exception = assertThrows(ArithmeticException.class, () -> division.operate(10, 0));
        assertEquals("Division by zero", exception.getMessage());
    }

    @Test
    void testConcatenate_EdgeCase_EmptyStrings() {
        Main.StringOperation concatenate = getStringOperation("concatenate");
        assertEquals("", concatenate.apply("", ""));
    }

    @Test
    void testFindLonger_EdgeCase_EqualStrings() {
        Main.StringOperation findLonger = getStringOperation("findLonger");
        assertEquals("hello", findLonger.apply("hello", "hello"));
    }

    @Test
    void testFindLonger_NormalCase() {
        Main.StringOperation findLonger = getStringOperation("findLonger");
        assertEquals("hello", findLonger.apply("hello", "hi"));
    }

    @Test
    void testConcatenate_NormalCase() {
        Main.StringOperation concatenate = getStringOperation("concatenate");
        assertEquals("helloworld", concatenate.apply("hello", "world"));
    }

    @Test
    void testSortList_NormalCase() {
        List<String> stringList = new ArrayList<>(Arrays.asList("date", "apple", "cherry", "banana"));
        Collections.sort(stringList, (s1, s2) -> s1.compareTo(s2));
        assertEquals(Arrays.asList("apple", "banana", "cherry", "date"), stringList);
    }

    @Test
    void testFilterList_NormalCase() {
        List<Integer> integerList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        List<Integer> oddNumbers = new ArrayList<>();
        integerList.forEach(number -> {
            if (number % 2 != 0) {
                oddNumbers.add(number);
            }
        });
        assertEquals(Arrays.asList(1, 3, 5, 7, 9), oddNumbers);
    }

    @Test
    void testIsEmpty_NormalCase() {
        Predicate<String> isEmpty = String::isEmpty;
        assertTrue(isEmpty.test(""));
    }

    @Test
    void testIsNotEmpty_NormalCase() {
        Predicate<String> isEmpty = String::isEmpty;
        assertFalse(isEmpty.test("hello"));
    }

    @Test
    void testToUpperCase_NormalCase() {
        Function<String, String> toUpperCase = String::toUpperCase;
        assertEquals("HELLO", toUpperCase.apply("hello"));
    }

    @Test
    void testAddOneThenMultiplyByTen_NormalCase() {
        Function<Integer, Integer> addOne = x -> x + 1;
        Function<Integer, Integer> multiplyByTen = x -> x * 10;
        Function<Integer, Integer> addOneThenMultiplyByTen = addOne.andThen(multiplyByTen);
        assertEquals(60, addOneThenMultiplyByTen.apply(5));
    }

    @Test
    void testSortList_EdgeCase_EmptyList() {
        List<String> stringList = new ArrayList<>();
        Collections.sort(stringList, (s1, s2) -> s1.compareTo(s2));
        assertEquals(new ArrayList<>(), stringList);
    }

    @Test
    void testFilterList_EdgeCase_EmptyList() {
        List<Integer> integerList = new ArrayList<>();
        List<Integer> oddNumbers = new ArrayList<>();
        integerList.forEach(number -> {
            if (number % 2 != 0) {
                oddNumbers.add(number);
            }
        });
        assertEquals(new ArrayList<>(), oddNumbers);
    }

    @Test
    void testFilterList_EdgeCase_AllEven() {
        List<Integer> integerList = new ArrayList<>(Arrays.asList(2, 4, 6, 8, 10));
        List<Integer> oddNumbers = new ArrayList<>();
        integerList.forEach(number -> {
            if (number % 2 != 0) {
                oddNumbers.add(number);
            }
        });
        assertEquals(new ArrayList<>(), oddNumbers);
    }
}