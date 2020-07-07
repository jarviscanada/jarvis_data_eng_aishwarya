package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;

/**
 * ticket: https://www.notion.so/Valid-Anagram-7cfeb052e1de4647b02a88f3b4c924b3
 */
public class ValidAnagram {

  /**
   * Big-O: O(n log n) Justification: Sorting takes (n log n) and comparison o(n).So Sorting
   * dominates.
   */
  public boolean isAnagramSort(String s, String t) {
    char[] str1 = s.toCharArray();
    Arrays.sort(str1);
    char[] str2 = t.toCharArray();
    Arrays.sort(str2);
    if (str1.length == str2.length) {
      return Arrays.equals(str1, str2);
    }
    return false;
  }

  /**
   * Big-O: O(n) Justification: Using for loop.
   */
  public boolean isAnagram(String s, String t) {
    if (s.length() != t.length()) {
      return false;
    }
    int[] count = new int[26];
    for (int i = 0; i < s.length(); i++) {
      count[s.charAt(i) - 'a']++;
      count[t.charAt(i) - 'a']--;
    }
    for (int i = 0; i < 26; i++) {
      if (count[i] != 0) {
        return false;
      }
    }
    return true;
  }
}