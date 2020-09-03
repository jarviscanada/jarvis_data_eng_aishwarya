package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/Valid-Palindrome-03a725e5220b41769e293d6349565c3b
 */
public class ValidPalindrome {

  /**
   * Big-O: O(n) Justification: For loop is used.
   */
  public boolean isPalindrome(String s) {
    String alphanumeric = s.toLowerCase().replaceAll("\\W", "");
    int len = alphanumeric.length() - 1;
    for (int i = 0; i <= len; i++) {
      if (alphanumeric.charAt(i) != alphanumeric.charAt(len)) {
        return false;
      }
      len--;
    }
    return true;
  }

  /**
   * Big-O: O(n) Justification: using replaceAll method for focusing on alphanumeric and lowercase
   * of alphabets.
   */
  public boolean isPalindromeRecursion(String s) {
    String t = s.replaceAll("\\W", "");
    return validPalindrome(t.toLowerCase(), 0, t.length());
  }

  private boolean validPalindrome(String s, int low, int high) {
    if (low < high - 1) {
      if (s.charAt(low) == s.charAt(high - 1)) {
        return validPalindrome(s.substring(low + 1, high - 1), low, high - 2);
      } else {
        return false;
      }
    }
    return true;
  }

}