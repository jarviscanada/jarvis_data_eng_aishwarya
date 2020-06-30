package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/Sample-Check-if-a-number-is-even-or-odd-62d0a26c398347e893dc6f96a3bbed1a
 */
public class OddEven {

  /**
   * Big-O: O(1)
   * Justification: it's an arithmetic operation
   */
  public String oddEvenMod(int i){
    return i % 2 == 0 ? "even" : "odd";
  }

  /**
   * Big-O:
   * Justification:
   */
  public String oddEvenBit(int i){
    return (i & 1) == 0 ? "even" : "odd";
  }

}