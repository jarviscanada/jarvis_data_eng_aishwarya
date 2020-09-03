package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.Collections;

/**
 * ticket: https://www.notion.so/Find-Largest-Smallest-f9dacc526f4740dba72ebe19cd819416
 */
public class FindLargestorSmallest {

  /**
   * Big-O: O(n) Justification: For loop.
   */
  public int findLargest(int[] num) {
    int lar = num[0];
    for(int i = 1; i<num.length;i++){
      if(lar<num[i]){
        lar = num[i];
      }
    }
    return lar;
  }

  /**
   * Big-O: O(n) Justification: iteration to find max.
   */
  public int findLargestStream(int[] num) {
    return Arrays.stream(num).max().getAsInt();
  }

  /**
   * Big-O: O(n) Justification: traverse to find max.
   */
  public int findLargestBuiltIn(Integer[] num) {
    return Collections.max(Arrays.asList(num));
  }

}
