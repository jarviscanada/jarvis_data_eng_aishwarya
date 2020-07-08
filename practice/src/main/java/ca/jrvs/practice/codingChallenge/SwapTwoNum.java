package ca.jrvs.practice.codingChallenge;

import java.util.List;

/**
 * ticket: https://www.notion.so/Swap-two-numbers-bfaf87d6c69c466fb6244d68ec61d776
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
   * Big-O: O(1) Justification: it's an arithmetic operation.
   */
  public List<Integer> swapTwoNum(List<Integer> num) {
    num.set(0, Math.abs(num.get(0) - num.get(1)));
    num.set(1, Math.abs(num.get(0) - num.get(1)));
    num.set(0, Math.abs(num.get(0) + num.get(1)));
    return num;
  }

}
