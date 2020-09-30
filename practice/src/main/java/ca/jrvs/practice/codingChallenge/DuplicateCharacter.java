package ca.jrvs.practice.codingChallenge;

import java.util.HashSet;
import java.util.Set;

/**
 * ticket: https://www.notion.so/Duplicate-Characters-edd6b4763a634e649534376d3f8456d2
 */
public class DuplicateCharacter {

  /**
   * Big-O: O(n) Justification: Set and for loop.
   */
  public String[] duplicateCharacters(String str) {
    String newStr = str.toLowerCase().replaceAll("\\s", "");
    Set<Character> strSet = new HashSet<Character>(newStr.length());
    Set<String> set = new HashSet<String>(newStr.length());
    for (int i = 0; i < newStr.length(); i++) {
      if (strSet.contains(newStr.charAt(i))) {
        set.add(String.valueOf(newStr.charAt(i)));
      }
      strSet.add(newStr.charAt(i));

    }
    return set.toArray(new String[0]);
  }
}