package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class OddEvenTest {
  private OddEven oddEven = new OddEven();

  @Test
  public void oddEvenMod() {
    System.out.println(oddEven.oddEvenMod(4));
    System.out.println(oddEven.oddEvenMod(3));
  }

  @Test
  public void oddEvenBit() {
    System.out.println(oddEven.oddEvenBit(4));
    System.out.println(oddEven.oddEvenBit(3));
  }
}