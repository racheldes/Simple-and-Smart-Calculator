package calculator;

import java.math.BigInteger;

/**
 * Abstract Calculator class that supports addition, subtraction,
 * and multiplication. This calculator can only support 32 bits.
 */
public abstract class AbstractCalculator implements Calculator {
  protected String currResult;
  protected int first;
  protected int second;
  protected int counter;
  protected boolean lastWasEquals;


  /**
   * Constructs the initial abstract calculator with default fields.
   */
  public AbstractCalculator() {
    this("", 0, 0, 0,
            false);
  }

  /**
   * Constructs a new Abstract Calculator with the given fields.
   *
   * @param currResult    represents the currentResult string
   * @param first         represents the first operand of an equation
   * @param second        represents the second operand of an equation
   * @param counter       counts how many operators are in the result
   * @param lastWasEquals whether the last character added was an =
   */
  protected AbstractCalculator(String currResult, int first,
                               int second, int counter,
                               boolean lastWasEquals) {
    this.currResult = currResult;
    this.first = first;
    this.second = second;
    this.counter = counter;
    this.lastWasEquals = lastWasEquals;
  }

  @Override
  public abstract Calculator input(char c);

  @Override
  public String getResult() {
    return currResult;
  }

  /**
   * Helper to determine whether the character passed is a valid number.
   *
   * @param c represents the character passed in
   * @return whether the character is a valid number
   */
  protected boolean validNum(char c) {
    if (Character.isDigit(c)) {
      return c >= '0' && c <= '9';
    }
    return false;
  }


  /**
   * Helper to determine whether the character is a valid operator.
   *
   * @param c represents the character passed in
   * @return a boolean
   */
  protected boolean validOperator(char c) {
    return c == '+' || c == '-' || c == '*' || c == '=';
  }


  /**
   * Helper to determine whether the math will cause an overflow.
   *
   * @param curr represents the String passed
   * @return a boolean
   */
  protected boolean mathOverflow(String curr) {
    // check if math causes overflow
    try {
      BigInteger result = new BigInteger(curr);
      return result.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) > 0
              || result.compareTo(BigInteger.valueOf(Integer.MIN_VALUE)) < 0;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  /**
   * Helper to check if the character can be added.
   *
   * @param c represents the character
   * @return a boolean
   */
  protected abstract boolean validCondition(char c);

  /**
   * Helper to check whether the calculator should be cleared.
   *
   * @param c represents the character passed in
   * @return whether the Character matches 'C'
   */
  protected boolean clear(char c) {
    return c == 'C';
  }

  /**
   * Helper to compute based on operator and catch overflow.
   *
   * @param curr represents the string passed in
   * @return the String value of the sum/product/difference
   * @throws IllegalArgumentException if overflow
   */
  protected String compute(String curr) throws IllegalArgumentException {
    BigInteger total = BigInteger.valueOf(0);
    for (int i = 1; i < curr.length(); i++) {
      String tempFirst = curr.substring(0, i);
      String tempSecond = curr.substring(i + 1);
      if (curr.charAt(i) == '+') {
        BigInteger tF = new BigInteger(tempFirst);
        BigInteger tS = new BigInteger(tempSecond);
        total = tF.add(tS);
      } else if (curr.charAt(i) == '-') {
        BigInteger tF = new BigInteger(tempFirst);
        BigInteger tS = new BigInteger(tempSecond);
        total = tF.subtract(tS);
      } else if (curr.charAt(i) == '*') {
        BigInteger tF = new BigInteger(tempFirst);
        BigInteger tS = new BigInteger(tempSecond);
        total = tF.multiply(tS);
      }
    }
    if (mathOverflow(total.toString())) {
      throw new IllegalArgumentException("This will cause an overflow try again.");
    }
    return total.toString();
  }

  /**
   * Helper method to look at the correct conditions and evaluates
   * whether they've been met.
   *
   * @param c represents the given character
   * @return whether the conditions are valid enough to compute
   */
  protected boolean shouldCompute(char c) {
    return (c == '=' && validCondition(c)) || counter == 2;
  }

  /**
   * Helper method to determine whether the helper is an equals.
   * @param c1 represents the given character
   * @return whether the character is =
   */
  private boolean isEquals(char c1) {
    return c1 == '=';
  }



}
