package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class CountPrimesTest {
  private CountPrimes countPrimes = new CountPrimes();

  @Test
  public void primesCount() {
    System.out.println(countPrimes.primesCount(20));
  }
}