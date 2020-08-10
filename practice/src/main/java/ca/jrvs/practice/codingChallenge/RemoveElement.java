package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/Remove-Element-891d2cb7d8de4971af44c911d906e987
 */
public class RemoveElement {

  /**
   * Big-O: O(n)  Justification: For loop is used.
   */
  public int removeElement(int[] num, int val) {
    int length = num.length;
    for (int i = 0; i < length; ) {
      if (num[i] == val) {
        num[i] = num[length - 1];
        length--;
      } else {
        i++;
      }
    }
    for (int i = 0; i < length; i++) {
      System.out.println(num[i]);
    }
    return length;
  }
}