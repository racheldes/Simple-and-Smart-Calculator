package calculator;


import static java.lang.Integer.parseInt;

/**
 * Simple Calculator implementation of Calculator.
 * This supports addition, subtraction, and multiplication
 * it can only contain 32-bit integers and cannot handle
 * multiple operators without an operand.
 */
public class SimpleCalculator extends AbstractCalculator {

  /**
   * Constructs the initial simple calculator with default fields
   * and calls on super class.
   */
  public SimpleCalculator() {
    super("", 0, 0, 0,
            false);
  }

  /**
   * Constructs a new Simple Calculator with the given fields and
   * calls on superclass.
   *
   * @param currResult    represents the currentResult string
   * @param first         represents the first operand of an equation
   * @param second        represents the second operand of an equation
   * @param counter       counts how many operators are in the result
   * @param lastWasEquals whether the last character added was an =
   */
  private SimpleCalculator(String currResult, int first,
                           int second, int counter,
                           boolean lastWasEquals) {
    super(currResult, first, second, counter, lastWasEquals);
  }

  @Override
  public Calculator input(char c) throws IllegalArgumentException {
    // inputting multiple ==
    if (lastWasEquals && isEquals(c)) {
      return this;
    }
    // should restart if last is equals and character is a num
    else if (lastWasEquals && validNum(c)) {
      return new SimpleCalculator(String.valueOf(c), first, second,
              0, lastWasEquals);
    }
    // compute
    else if (shouldCompute(c)) {
      try {
        String temp = compute(currResult);
        int operand1 = parseInt(temp);
        return new SimpleCalculator(temp, operand1, 0,
                0, true);
      } catch (IllegalArgumentException e) {
        return new SimpleCalculator("0", 0, 0,
                0, true);
      }
    }
    // valid number
    else if (validNum(c) && !mathOverflow(currResult + c)) {
      return new SimpleCalculator(this.currResult + c, first,
              second, counter, false);
    }
    // valid operator added to string
    else if (validOperator(c) && validCondition(c) && this.counter < 1) {
      return new SimpleCalculator(this.currResult + c, first,
              second, counter + 1, false);
    }
    // valid operator but multi-sequence
    else if (validOperator(c) && validCondition(c) && counter >= 1) {
      String temp = compute(currResult);
      if (mathOverflow(temp)) {
        return new SimpleCalculator("0", 0, second,
                0, false);
      } else {
        return new SimpleCalculator(temp + c,
                parseInt(compute(currResult)),
                0, 1, false);
      }
    }
    // clear calculator
    else if (clear(c)) {
      return new SimpleCalculator();
    }
    throw new IllegalArgumentException("Invalid input try again.");
  }

  @Override
  protected boolean validCondition(char c) {
    // character can't add symbols when string is empty
    if (this.currResult.isEmpty()) {
      return false;
    }
    // character can't add multiple symbols
    else return (this.currResult.charAt(this.currResult.length() - 1) != '+' || !validOperator(c))
            && (this.currResult.charAt(this.currResult.length() - 1) != '-' || !validOperator(c))
            && (this.currResult.charAt(this.currResult.length() - 1) != '*' || !validOperator(c))
            && (this.currResult.charAt(this.currResult.length() - 1) != '=' || !restartNeeded(c));
  }

  /**
   * Helper to check whether the operator can have multiple.
   *
   * @param c represents the character passed in.
   * @return a boolean
   */
  private boolean restartNeeded(char c) {
    return lastWasEquals && (c == '+' || c == '-' || c == '*');
  }

}

