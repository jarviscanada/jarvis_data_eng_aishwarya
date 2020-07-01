package ca.jrvs.practice.codingChallenge;

import java.util.ArrayList;
import java.util.List;

/**
 * ticket: https://www.notion.so/Count-Primes-55059d2ea3b844d69509919efa2faba7
 */
public class CountPrimes {

  /**
   * Big-O: O(n log(log n)) Justification: It iterates over the n numbers and marks every multiple
   * of that number. And non primes > sqrt(n) will be already marked. A number is not divisible by
   * any number > n/2 and in that n/2 -> a number can be given as p*q where p<=q and so p<=sqrt(n).
   *
   * @param num n values
   * @return
   */
  public int primesCount(int num) {
    int count = 0;
    List<Integer> list = new ArrayList<>();
    boolean[] prime = new boolean[num];
    for (int i = 2; i < num; i++) {
      prime[i] = true;
    }

    for (int i = 2; i * i <= num; i++) {
      if (!prime[i]) {
        continue;
      }
      for (int j = i * i; j < num; j += i) {
        prime[j] = false;
      }
    }

    for (int i = 2; i < num; i++) {
      if (prime[i]) {
        list.add(i);
        count++;
      }
    }
    System.out.println(list);
    return count;
  }
}
