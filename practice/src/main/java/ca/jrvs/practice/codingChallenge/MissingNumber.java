package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * ticket: https://www.notion.so/Missing-Number-7b2cfa427f0547559b830ddd61c2e65a
 */
public class MissingNumber {

  /**
   * Big-O: O(n)  Justification: It is iterating over an array.
   * Considering Not sorted.
   */
  public int sumNum(int[] arrNum){
    int sum = 0;
    int arrSum = 0;
    for(int i = 1; i<=arrNum.length;i++){
      arrSum+=arrNum[i-1];
      sum+=i;
    }
    return sum-arrSum;
  }

  /**
   * Big-O: O(n)  Justification: HashSet is used. Creating set is O(n) and contains operation is O(1) due to HashTable/HashMap.
   */
  public int numSet(int[] arrNum){
    Set<Integer> set = new HashSet<>();
    for(Integer num:arrNum){
      set.add(num);
    }
    for(int i =0 ;i<=arrNum.length;i++){
      if(!set.contains(i)){
        return i;
      }
    }
    return -1;
  }

  /**
   * Big-O: O(n)  Justification: It is iterating over an array.
   * Considering Not sorted.
   */
  public int numGauss(int[] arrNum){
    int sum = (arrNum.length*(arrNum.length+1))/2;
    int arrSum = 0;
    for(int num: arrNum){
      arrSum+=num;
    }
    return sum-arrSum;
  }
}
