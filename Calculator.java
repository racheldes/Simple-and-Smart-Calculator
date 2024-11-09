package calculator;

/**
 * Creates an interface of type calculator.
 * This supports addition, subtraction, and multiplication
 * it can only contain 32-bit integers.
 */
public interface Calculator {


  /**
   * Adds functionality to the calculator by allowing inputs.
   *
   * @param c represents the character given
   * @return Calculator
   */
  public Calculator input(char c);

  /**
   * What is displayed on the calculator at any point.
   *
   * @return the result after calculations
   */
  public String getResult();
}
