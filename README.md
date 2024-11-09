# Simple-and-Smart-Calculator
This project implements a basic Calculator application that performs integer-only arithmetic operations, simulating the functionality of standard calculators. The calculator operates in two modes: a SimpleCalculator and an Advanced SmartCalculator. Both calculators process inputs one character at a time, replicating the button-press experience on a traditional calculator. They support addition, subtraction, and multiplication, and display the result as soon as each operation is complete.

Features
- SimpleCalculator: Performs basic operations on whole numbers, updating the display in real time. The calculator handles each input sequentially without evaluating full expressions, avoiding complex operator precedence.

- SmartCalculator: An advanced calculator with additional "smart" features, including:

- Repeated "=" Input: Allows chained computations without re-entering operands (e.g., 32 + 24 = = yields 56, 80, etc.).
- Skipping Second Operand: When the "=" is pressed without a second operand, it uses the previous result (e.g., 32 + = yields 64, 96, etc.).
- Handling Multiple Operators: Ignores extraneous operators in sequences (e.g., 32 + - 24 = produces 8).
- Ignoring Leading Operators: Ignores a leading "+" operator (e.g., + 32 - 24 = results in 8).
- Overflow Handling: Returns 0 if an arithmetic operation overflows.
