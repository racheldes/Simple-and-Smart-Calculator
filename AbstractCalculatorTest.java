import calculator.Calculator;

import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;

/**
 * Test class for the Abstracted tests for the Calculator
 */
abstract public class AbstractCalculatorTest {
  Calculator calc1;

  /**
   * Returns a new instance of the object.
   */
  @Before
  public abstract void setUp();

  @Test
  public void testInitial() {
    assertEquals("", calc1.getResult());
  }

  @Test
  public void testCFirst() {
    assertEquals("", calc1.input('C').getResult());
  }

  // test on a valid number of 0
  @Test
  public void testValidNum0() {
    String expectedOutput = "0";
    assertEquals(expectedOutput, calc1.input('0').getResult());
  }


  // test on valid number 1
  @Test
  public void testValidNum1() {
    String expectedOutput = "1";
    assertEquals(expectedOutput, calc1.input('1').getResult());
  }

  // test on valid number 2
  @Test
  public void testValidNum2() {
    String expectedOutput = "2";
    assertEquals(expectedOutput, calc1.input('2').getResult());
  }

  // test on valid number 3
  @Test
  public void testValidNum3() {
    String expectedOutput = "3";
    assertEquals(expectedOutput, calc1.input('3').getResult());
  }

  // test on valid number 4
  @Test
  public void testValidNum4() {
    String expectedOutput = "4";
    assertEquals(expectedOutput, calc1.input('4').getResult());
  }

  // test on valid number 5
  @Test
  public void testValidNum5() {
    String expectedOutput = "5";
    assertEquals(expectedOutput, calc1.input('5').getResult());
  }


  // test on valid number 6
  @Test
  public void testValidNum6() {
    String expectedOutput = "6";
    assertEquals(expectedOutput, calc1.input('6').getResult());
  }

  // test on valid number 7
  @Test
  public void testValidNum7() {
    String expectedOutput = "7";
    assertEquals(expectedOutput, calc1.input('7').getResult());
  }

  // test on valid number 8
  @Test
  public void testValidNum8() {
    String expectedOutput = "8";
    assertEquals(expectedOutput, calc1.input('8').getResult());
  }

  // test on valid number 9
  @Test
  public void testValidNum9() {
    String expectedOutput = "9";
    assertEquals(expectedOutput, calc1.input('9').getResult());
  }

  // test inputting a negative symbol without a number
  @Test
  public void testInvalidNum1() {
    try {
      calc1.input('-');
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid input try again.",
              e.getMessage());
    }
  }

  // test on invalid number of a decimal to throw an exception
  @Test
  public void testInvalidNum2() {
    try {
      calc1.input('.');
    } catch (IllegalArgumentException e) {
      String expectedOutput = "Invalid input try again.";
      assertEquals(expectedOutput, e.getMessage());
    }
  }

  // test on invalid number of a decimal to throw an exception
  @Test
  public void testInvalidNum3() {
    try {
      calc1.input('0');
      calc1.input('.');
      calc1.input('1');
    } catch (IllegalArgumentException e) {
      String expectedOutput = "Invalid input try again.";
      assertEquals(expectedOutput, e.getMessage());
    }
  }


  // test inputting a multiplication symbol without a number
  @Test
  public void testInvalidNum5() {
    try {
      calc1.input('*');
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid input try again.",
              e.getMessage());
    }
  }


  // testing valid addition symbol
  @Test
  public void testValidOperator1() {
    String expectedOutput = "1+";
    assertEquals(expectedOutput, calc1.input('1')
            .input('+').getResult());
  }

  // testing valid subtraction symbol
  @Test
  public void testValidOperator2() {
    String expectedOutput = "2-";
    assertEquals(expectedOutput, calc1.input('2')
            .input('-').getResult());
  }

  // testing valid multiplication symbol
  @Test
  public void testValidOperator3() {
    String expectedOutput = "3*";
    assertEquals(expectedOutput, calc1.input('3')
            .input('*').getResult());
  }

  @Test
  // testing that clear works
  public void testClear() {
    String expectedOutput = "";
    assertEquals(expectedOutput, calc1.input('2')
            .input('-').input('3').input('C').getResult());
  }

  @Test
  // testing addition
  public void testAdd1() {
    String expectedOutput = "17";
    assertEquals(expectedOutput, calc1.input('1')
            .input('1').input('+').input('6')
            .input('=').getResult());
  }

  @Test
  // testing addition on multiple operations but
  // doesn't type in the last number
  public void testAdd2() {
    String expectedOutput = "17+";
    assertEquals(expectedOutput, calc1.input('1')
            .input('1').input('+').input('6')
            .input('+').getResult());
  }

  @Test
  // testing addition on multiple operations
  public void testAdd3() {
    String expectedOutput = "17+9";
    assertEquals(expectedOutput, calc1.input('1')
            .input('1').input('+').input('6')
            .input('+').input('9').getResult());
  }

  @Test
  // testing subtraction
  public void testSubtraction1() {
    String expectedOutput = "38";
    assertEquals(expectedOutput, calc1.input('5')
            .input('6').input('-').input('1')
            .input('8').input('=').getResult());
  }

  @Test
  // testing subtraction on multiple operations but
  // doesn't type in the last number
  public void testSubtraction2() {
    String expectedOutput = "191+";
    assertEquals(expectedOutput,
            calc1.input('2').input('0').input('0')
                    .input('-').input('9').input('+')
                    .getResult());
  }

  @Test
  // testing subtraction on multiple operations
  public void testSubtraction3() {
    String expectedOutput = "191+7";
    assertEquals(expectedOutput,
            calc1.input('2').input('0').input('0')
                    .input('-').input('9').input('+')
                    .input('7').getResult());
  }


  @Test
  // testing multiplication on simple operations without equal sign
  public void testMultiplication1() {
    String expectedOutput = "20*7";
    assertEquals(expectedOutput,
            calc1.input('2').input('0').input('*')
                    .input('7').getResult());
  }

  @Test
  // testing multiplication on simple operations with equal sign
  public void testMultiplication2() {
    String expectedOutput = "140";
    assertEquals(expectedOutput,
            calc1.input('2').input('0').input('*')
                    .input('7').input('=').getResult());
  }

  @Test
  // testing multiplication on multiple operations with equal sign
  public void testMultiplication3() {
    String expectedOutput = "789";
    assertEquals(expectedOutput,
            calc1.input('2').input('3').input('*')
                    .input('3').input('4').input('+')
                    .input('7').input('=').getResult());
  }

  @Test
  // testing multiplication on multiple operations without
  // equal sign
  public void testMultiplication4() {
    String expectedOutput = "789-";
    assertEquals(expectedOutput,
            calc1.input('2').input('3').input('*')
                    .input('3').input('4').input('+')
                    .input('7').input('-').getResult());
  }


  @Test
  // testing multiple operations
  public void testMultipleOperations1() {
    String expectedOutput = "689";
    assertEquals(expectedOutput,
            calc1.input('2').input('3').input('*').
                    input('3').input('4').input('=')
                    .input('+').input('7').input('-')
                    .input('1').input('0').input('0')
                    .input('=').getResult());
  }

  @Test
  // testing that addition overflows correctly
  public void testAdditionOverflow() {
    assertEquals("0", calc1.input('1').input('0')
            .input('7').input('3').input('7')
            .input('4').input('1').input('8')
            .input('2').input('4').input('+')
            .input('1').input('0').input('7')
            .input('3').input('7').input('4')
            .input('1').input('8').input('2')
            .input('4').input('=').getResult());
  }


  @Test
  // testing that addition overflows correctly but the
  // second number is what causes overflow
  public void testAdditionOverflow2() {
    try {
      calc1.input('1').input('+').input('1')
              .input('1').input('1').input('1')
              .input('1').input('1').input('1')
              .input('1').input('1').input('1')
              .input('1').getResult();
    } catch (IllegalArgumentException e) {
      assertEquals("0", calc1.getResult());
    }
  }

  @Test
  // testing that subtraction overflows correctly
  public void testSubtractionOverflow() {
    try {
      calc1.input('1').input('1').input('1')
              .input('1').input('1').input('1')
              .input('1').input('1').input('1')
              .input('1').input('+').input('1')
              .input('1').getResult();
    } catch (IllegalArgumentException e) {
      assertEquals("0", calc1.getResult());
    }
  }


  @Test
  // testing that multiplication overflows correctly
  public void testMultiplicationOverflow() {
    assertEquals("0", calc1.input('1')
            .input('1').input('1').input('1')
            .input('1').input('1').input('1')
            .input('1').input('1').input('1')
            .input('*').input('9').input('9')
            .input('9').input('9').input('9')
            .input('9').input('9').input('=')
            .getResult());
  }


  @Test
  // testing invalid operand
  public void testOverflowOperand() {
    try {
      calc1.input('1').input('1').input('1')
              .input('1').input('1').input('1')
              .input('1').input('1').input('1')
              .input('1').input('1').getResult();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid input try again.",
              e.getMessage());
    }
  }

  @Test
  // operand after equals
  public void testMultipleSigns() {
    String expectedOutput = "5";
    assertEquals(expectedOutput, calc1.input('1')
            .input('1').input('+').input('3')
            .input('=').input('5').getResult());
  }

  @Test
  // operand after equals and another operator
  public void testMultipleSigns2() {
    String expectedOutput = "5+";
    assertEquals(expectedOutput, calc1.input('5')
            .input('2').input('+').input('8')
            .input('=').input('5').input('+')
            .getResult());
  }

  @Test
  // hitting clear then math
  public void testClearAfterMath() {
    String expectedOutput = "5+";
    assertEquals(expectedOutput, calc1.input('5')
            .input('2').input('+').input('8')
            .input('C').input('5').input('+')
            .getResult());
  }


  @Test
  // entering first operand
  public void testFunctions() {
    String expectedOutput = "2";
    assertEquals(expectedOutput, calc1.input('2').getResult());
  }

  @Test
  // entering first operator
  public void testFunctions2() {
    String expectedOutput = "2*";
    assertEquals(expectedOutput, calc1.input('2')
            .input('*').getResult());
  }

  @Test
  // entering second operand
  public void testFunctions3() {
    String expectedOutput = "2*4";
    assertEquals(expectedOutput, calc1.input('2').input('*')
            .input('4').getResult());
  }

  @Test
  // entering equals after
  public void testFunctions4() {
    String expectedOutput = "8";
    assertEquals(expectedOutput, calc1.input('2')
            .input('*').input('4').input('=')
            .getResult());
  }

  // 11=
  @Test
  public void testEqualsAfterNum() {
    try {
      calc1.input('1').input('1').input('=');
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid input try again.",
              e.getMessage());
    }
  }

  @Test
  // multiple equals on an overflow
  public void testOverflowEquals() {
    assertEquals("0", calc1.input('1')
            .input('1').input('1').input('1')
            .input('1').input('1').input('1')
            .input('1').input('1').input('1')
            .input('*').input('9').input('9')
            .input('9').input('9').input('9')
            .input('9').input('9').input('=')
            .input('=').getResult());
  }

  // testing to see that negative numbers can be handled
  @Test
  public void testReturnNegative() {
    String expectedOutput = "-1";
    assertEquals(expectedOutput, calc1.input('0').input('-').input('1').input('=').getResult());
  }


}
