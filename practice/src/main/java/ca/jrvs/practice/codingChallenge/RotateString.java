package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/Rotate-String-041239778e7044d6b7c1d1a4f6a84c60
 */
public class RotateString {

  /**
   * Big-O: O(n) Justification: for loop involved.
   */
  public boolean rotateString(String a, String b) {
    if (a.length() != b.length()) {
      return false;
    }

    for (int i = 0; i < a.length(); i++) {
      if (a.equals(b)) {
        return true;
      }
      String subStr = a.substring(1);
      a = subStr + a.charAt(0);
    }
    return false;
  }
}
