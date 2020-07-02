package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/Sample-Check-if-a-number-is-even-or-odd-a251cec88fbd41b5815244d3358c0f91
 */
public class OddEven {

  /**
   * Big-O: O(1) Justification: it's an arithmetic operation.
   */
  public String oddEvenMod(int i) {
    return i % 2 == 0 ? "even" : "odd";
  }

  /**
   * Big-O: O(1) Justification: It's an Bitwise operation.
   */
  public String oddEvenBit(int i) {
    return (i & 1) == 0 ? "even" : "odd";
  }

}