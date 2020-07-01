package ca.jrvs.practice.codingChallenge;

import java.util.List;

/**
 * ticket: https://www.notion.so/Swap-two-numbers-8377365b7b174e14a1a61221230a1822
 */
public class SwapTwoNum {

  /**
   * Big-O: O(1) Justification: it's bit manipulation operation.
   */
  public List<Integer> swapTwoNumBit(List<Integer> num) {
    num.set(0, num.get(0) ^ num.get(1));
    num.set(1, num.get(0) ^ num.get(1));
    num.set(0, num.get(0) ^ num.get(1));
    return num;
  }

  /**
   * Big-O: O(1) Justification: it's an arithmetic operation
   */
  public List<Integer> swapTwoNum(List<Integer> num) {
    num.set(0, Math.abs(num.get(0) - num.get(1)));
    num.set(1, Math.abs(num.get(0) - num.get(1)));
    num.set(0, Math.abs(num.get(0) + num.get(1)));
    return num;
  }

}
