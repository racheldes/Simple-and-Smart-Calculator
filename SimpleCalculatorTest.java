import org.junit.Test;
import org.junit.Before;

import calculator.SimpleCalculator;

import static org.junit.Assert.assertEquals;

/**
 * Test class for the SimpleCalculator.
 */
public class SimpleCalculatorTest extends AbstractCalculatorTest {
  @Before
  public void setUp() {
    calc1 = new SimpleCalculator();
  }


  // test inputting an addition symbol without a number
  @Test
  public void testInvalidNum4() {
    try {
      calc1.input('+');
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
  // testing invalid combination of operands
  public void testInvalidCondition2() {
    try {
      calc1.input('1').input('1').input('+')
              .input('-');
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid input try again.",
              e.getMessage());
    }
  }

  @Test
  // testing invalid combination
  public void testInvalidCondition3() {
    try {
      calc1.input('1').input('1').input('+')
              .input('-').input('1');
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid input try again.",
              e.getMessage());
    }
  }

  @Test
  // testing invalid combination of same operands
  public void testInvalidCondition4() {
    try {
      calc1.input('1').input('1').input('+')
              .input('+');
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid input try again.",
              e.getMessage());
    }
  }

  @Test
  // testing invalid combination of same operands
  public void testInvalidCondition5() {
    try {
      calc1.input('1').input('1').input('-')
              .input('-');
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid input try again.",
              e.getMessage());
    }
  }

  @Test
  // testing invalid combination of same operands
  public void testInvalidCondition6() {
    try {
      calc1.input('1').input('1').input('*')
              .input('*');
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid input try again.",
              e.getMessage());
    }
  }

  @Test
  // testing multiple valid ==
  public void testMultipleEquals() {
    String expectedOutput = "14";
    assertEquals(expectedOutput, calc1.input('1')
            .input('1').input('+').input('3')
            .input('=').input('=').getResult());
  }


}
