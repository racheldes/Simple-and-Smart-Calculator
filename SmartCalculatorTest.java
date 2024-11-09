import org.junit.Before;
import org.junit.Test;

import calculator.SmartCalculator;

import static org.junit.Assert.assertEquals;

/**
 * Test class for the Smart Calculator
 */
public class SmartCalculatorTest extends AbstractCalculatorTest {

  @Before
  public void setUp() {
    calc1 = new SmartCalculator();
  }

  // test inputting a + sign in front
  @Test
  public void testValid1() {
    String expectedOutput = "4";
    assertEquals(expectedOutput, calc1.input('+').input('4')
            .getResult());
  }

  // test inputting multiple operators after each other
  // checking that first operator is ignored
  @Test
  public void testValid2() {
    String expectedOutput = "64-";
    assertEquals(expectedOutput, calc1.input('6').input('4')
            .input('+').input('-').getResult());
  }

  // test inputting multiple operators after each other with multiplication
  // checking that first operator is ignored
  @Test
  public void testValid3() {
    String expectedOutput = "34+";
    assertEquals(expectedOutput, calc1.input('3').input('4')
            .input('*').input('+').getResult());
  }

  // test inputting multiple operators of the same kind multiplication
  @Test
  public void testValid4() {
    String expectedOutput = "34*";
    assertEquals(expectedOutput, calc1.input('3').input('4')
            .input('*').input('*').getResult());
  }

  // test inputting multiple operators of the same kind addition
  @Test
  public void testValid5() {
    String expectedOutput = "34+";
    assertEquals(expectedOutput, calc1.input('3').input('4')
            .input('+').input('+').getResult());
  }

  // test inputting multiple operators of the same kind subtraction
  @Test
  public void testValid6() {
    String expectedOutput = "34-";
    assertEquals(expectedOutput, calc1.input('3').input('4')
            .input('-').input('-').getResult());
  }

  @Test
  // test inputting += after a number
  public void testEqualsAfterAdd() {
    String expectedOutput = "64";
    assertEquals(expectedOutput, calc1.input('3').input('2')
            .input('+').input('=').getResult());
  }

  @Test
  // test inputting *= after a number
  public void testEqualsAfterMul() {
    String expectedOutput = "1024";
    assertEquals(expectedOutput, calc1.input('3').input('2')
            .input('*').input('=').getResult());
  }

  @Test
  // test inputting -= after a number
  public void testEqualsAfterSub() {
    String expectedOutput = "0";
    assertEquals(expectedOutput, calc1.input('3').input('2')
            .input('-').input('=').getResult());
  }

  @Test
  // test inputting multiple == after a +
  public void testMultipleEqualsAfterAdd() {
    String expectedOutput = "96";
    assertEquals(expectedOutput, calc1.input('3').input('2')
            .input('+').input('=').input('=').getResult());
  }

  @Test
  // testing multiple valid ==
  public void testMultipleEquals() {
    String expectedOutput = "17";
    assertEquals(expectedOutput, calc1.input('1')
            .input('1').input('+').input('3')
            .input('=').input('=').getResult());
  }

  // 32+5=-==
  @Test
  // test a series of valid operators after an expression
  public void testMultipleEquals1() {
    String expectedOutput = "-37";
    assertEquals(expectedOutput, calc1.input('3').input('2')
            .input('+').input('5').input('=').input('-')
            .input('=').input('=').getResult());
  }


  @Test
  // testing multiple = calls after an equals button
  // has been used to compute an expression
  public void testMultipleEqualsAfterExpression1() {
    String expectedOutput = "74";
    assertEquals(expectedOutput, calc1.input('1').input('2')
            .input('*').input('5').input('=')
            .input('+').input('7').input('=')
            .input('=').getResult());
  }


  @Test
  // testing multiple = calls after an equals button
  // has been used to compute an expression then calling
  // a 7 to see if it'll clear and not allow an equals
  public void testMultipleEqualsAfterExpression2() {
    try {
      calc1.input('1').input('9').input('+').input('3')
              .input('=').input('7').input('=').input('=');
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid input try again.",
              e.getMessage());
    }
  }

  @Test
  // testing invalid combination of operands
  public void testInvalidCondition1() {
    try {
      calc1.input('3').input('2').input('+')
              .input('=');
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid input try again.",
              e.getMessage());
    }
  }

  @Test
  // testing starting with plus and doing arithmetic
  public void testPlusFirst() {
    String expectedOutput = "9";
    assertEquals(expectedOutput, calc1.input('+').input('3').input('+')
            .input('6').input('=').getResult());
  }

  @Test
  // testing starting with plus and adding a minus sign
  public void testInvalidWithPlus1() {
    try {
      calc1.input('+').input('-');
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid input try again.",
              e.getMessage());
    }
  }

  @Test
  // testing starting with plus and adding a plus sign
  public void testInvalidWithPlus2() {
    try {
      calc1.input('+').input('+');
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid input try again.",
              e.getMessage());
    }
  }

  @Test
  // testing starting with plus and adding a multiplication sign
  public void testInvalidWithPlus3() {
    try {
      calc1.input('+').input('*');
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid input try again.",
              e.getMessage());
    }
  }

  @Test
  // testing a multisequence expression
  public void testMulti() {
    String expectedOutput = "63";
    assertEquals(expectedOutput, calc1.input('1').input('5')
            .input('+').input('6').input('-').input('+')
            .input('=').input('=').getResult());
  }

  @Test
  // arithmetic causing overflow with ==
  public void testOverFlowWithEquals() {
    assertEquals("0", calc1.input('1')
            .input('1').input('1').input('1')
            .input('1').input('1').input('1')
            .input('1').input('1').input('1')
            .input('*').input('9').input('9')
            .input('9').input('9').input('9')
            .input('9').input('=')
            .input('=').getResult());
  }

  @Test
  // testing invalid sequence after plus
  public void testExceptionWithPlus() {
    try {
      calc1.input('+').input('4').input('=').input('=');
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid input try again.",
              e.getMessage());
    }
  }

  @Test
  // testing multiple operators and no second operand
  public void testMulti2() {
    String expectedOutput = "64";
    assertEquals(expectedOutput, calc1.input('3').input('2')
            .input('-').input('*').input('+').input('=').getResult());
  }
}
