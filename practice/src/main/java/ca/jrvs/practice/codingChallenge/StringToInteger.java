package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/String-to-Integer-atoi-cc620b03d1254978a1b2374e8ce44d06
 */
public class StringToInteger {

  /**
   * Big-O: O(n) Justification: replace all
   */
  public int atoi(String str) {
    String numStr = str.replaceAll("[^-+0-9]", "");
    int valueStr;
    try {
      valueStr = Integer.parseInt(numStr);
    } catch (NumberFormatException ex) {
      if (numStr.charAt(0) == '-') {
        return Integer.MIN_VALUE;
      } else {
        return Integer.MAX_VALUE;
      }
    }
    return valueStr;
  }

  /**
   * Big-O: O(n) Justification: for loop.
   */
  public int atoiWithoutParsing(String str) {
    String numStr = str.replaceAll("[^-+0-9]", "");
    int valueStr = 0;
    int sign = 1;
    for (int i = 0; i < numStr.length(); i++) {
      if (numStr.charAt(i) == '-') {
        sign = -1;
      } else {
        int prev = valueStr;
        valueStr = (valueStr * 10) + (numStr.charAt(i) % '0');
        if (valueStr < prev) {
          if (sign == -1) {
            return Integer.MIN_VALUE;
          } else {
            return Integer.MAX_VALUE;
          }
        }
      }
    }
    return sign * valueStr;
  }

}
