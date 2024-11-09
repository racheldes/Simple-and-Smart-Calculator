package calculator;

import static java.lang.Integer.parseInt;

/**
 * Smart Calculator implementation of Calculator.
 * This supports addition, subtraction, and multiplication
 * it can only contain 32-bit integers and can handle
 * multiple operators without an operand.
 */
public class SmartCalculator extends AbstractCalculator {
  private boolean lastWasOperand;
  private char last;

  /**
   * Constructs the initial smart calculator with default fields
   * and calls on super class.
   */
  public SmartCalculator() {
   this("", 0, 0 , 0, false,
           false, ' ');
  }

  /**
   * Constructs a new Smart Calculator with the given fields and
   * calls on superclass.
   * @param currResult represents the currentResult string
   * @param first represents the first operand of an equation
   * @param second represents the second operand of an equation
   * @param counter counts how many operators are in the result
   * @param lastWasEquals whether the last character added was an =
   * @param lastWasOperand whether the last character added was operator
   * @param last represents the last operator input
   */
  private SmartCalculator(String currResult, int first,
                           int second, int counter,
                           boolean lastWasEquals, boolean lastWasOperand, char last) {
    super(currResult, first, second, counter, lastWasEquals);
    this.lastWasOperand = lastWasOperand;
    this.last = last;
  }

  @Override
  public Calculator input (char c) throws IllegalArgumentException {
    // adding a plus at the beginning of an empty string
    if (currResult.isEmpty() && c == '+') {
      return new SmartCalculator("", first, 0, counter,
              lastWasEquals, lastWasOperand, ' ');
    }
    // last was equals and the character is equals
    // (returns the same thing over and over)
    else if (lastWasEquals && isEquals(c)) {
       String temp = compute(currResult + last + second);
      return new SmartCalculator(temp, first, second, counter,
              true, false, last);
    }
    // last was an operator and current is equals
    else if (lastWasOperand && isEquals(c)) {
      String temp = compute(currResult + getLastNumber());
      int sec = parseInt(getLastNumber());
      return new SmartCalculator(temp, first, sec,
              counter + 1, true, false, last);
    }
    // last was equals and the character is operator
    else if (lastWasEquals && validOperator(c)) {
     return new SmartCalculator(this.currResult + c, first, second,
             counter + 1, false, true, c);
    }
    // should restart if last is equals and character is a num
    else if (lastWasEquals && validNum(c)) {
      return new SmartCalculator(String.valueOf(c), first, 0,
              0, false, false, ' ');
    }
    // last was an operator and the current character is an operator
    // (need to remove last and replace with this operator)
    else if (lastWasOperand && validOperator(c)) {
      String temp = remove(currResult);
      return new SmartCalculator(temp + c, first, 0, counter,
              false, true, c);
    }
    // c is equals
    else if (shouldCompute(c)) {
      try {
        String temp = compute(currResult);
        int sec = 0;
        boolean change = false;
        if (lastWasOperand) {
          sec = parseInt(getLastNumber());
          change = true;
        }
        else {
          sec = parseInt(getLast(currResult));
        }
        return new SmartCalculator(temp, 0, sec, 0,
                true, change, getLastOperator());
      } catch (IllegalArgumentException e) {
        return new SmartCalculator("0", 0, 0,
                0, false, false, last);
      }
    }
    // validNumber and adding the number doesn't overflow
    else if (validNum(c) && !mathOverflow(currResult + c)) {
      return new SmartCalculator(this.currResult + c, first, second,
              counter, false, false, last);
    }
    // valid operator and valid condition to add an operator
    else if (validOperator(c) && validCondition(c) && counter < 1) {
      return new SmartCalculator(this.currResult + c, first, second,
              counter + 1, false, true, c);
    }
    // valid operator and multi-sequence operation
    else if (validOperator(c) && validCondition(c) && counter >= 1) {
      String temp = compute(currResult);
      // checks if math causes overflow and resets
      if (mathOverflow(temp)) {
        return new SmartCalculator("0", 0, second, 0,
                false, false, ' ');
      } else {
        return new SmartCalculator(temp + c, parseInt(compute(currResult)),
                0, 1, false, true, ' ');
      }
    }
    // reset calculator if C is input
    else if (clear(c)) {
      return new SmartCalculator();
    }
    throw new IllegalArgumentException("Invalid input try again.");
  }

  @Override
    protected boolean validCondition(char c) {
      // character can't add symbols (except +) when string is empty
      if (this.currResult.isEmpty() && (c == '-' || c == '*' || c == '=')) {
        return false;
      }
      else {
        return true;
      }
  }

  /**
   * Helper method to get the last number added,
   * subtracted, or multiplied. If nothing was added,
   * returns the string value of the only operand.
   * @return the String of the last number
   */
  private String getLastNumber() {
    int lastOperatorIndex = -1;
    for (int i = currResult.length() - 1; i >= 0; i--) {
      if (validOperator(currResult.charAt(i))) {
        lastOperatorIndex = i;
      }
    }
    if (lastOperatorIndex == -1) {
      return currResult;
    }
    return currResult.substring(0,lastOperatorIndex);
  }

  /**
   * Helper method to get the last operator added
   * in the case that the there aren't two operands.
   *
   * @param c represents a given string
   * @return the last operator added
   */
  private String getLast (String c) {
    for (int i = c.length() - 1; i >= 0; i--) {
      if (validOperator(c.charAt(i))) {
        return c.substring(i+1);
      }
    }
    return c;
  }

  /**
   * Helper method to get the last operator in the current string.
   * @return the last operator added
   */
  private char getLastOperator() {
    for (int i = currResult.length() - 1; i >= 0; i--) {
      if (validOperator(currResult.charAt(i))) {
        return currResult.charAt(i);
      }
    }
    return ' ';
  }

  /**
   * Helper method to remove the last item in the string.
   * This helps with computing the before value when there
   * aren't multiple operands.
   * @param curr represents the given string
   * @return the new string
   */
  private String remove(String curr) {
    return curr.substring(0, curr.length() - 1);
  }


}
