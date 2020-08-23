package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/Check-if-a-String-contains-only-digits-58313874ca73435aa2a42565a4a7bd0f
 */
public class StringOnlyDigits {

  /**
   * Big-O: O(n) justification: for loop.
   */
  public boolean checkStrings(String str) {
    for (int i = 0; i < str.length(); i++) {
      if (!(str.charAt(i) >= '0' && str.charAt(i) < '9')) {
        return false;
      }
    }
    return true;
  }

  /**
   * Big-O: O(n) justification: Integer.valueof() uses while loop.
   */
  public boolean checkStrings2(String str) {
    int charVal;
    try {
      charVal = Integer.valueOf(str);
    } catch (NumberFormatException ex) {
      return false;
    }
    if (charVal < 0) {
      return false;
    }
    return true;
  }

  /**
   * Big-O: O(n) justification: replace all is using iteration.
   */
  public boolean checkStrings3(String str) {
    String newStr = str.replaceAll("\\D", "");
    if (newStr.length() != str.length()) {
      return false;
    }
    return true;
  }
}