package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class ValidPalindromeTest {

  private ValidPalindrome palindrome = new ValidPalindrome();

  @Test
  public void isPalindrome() {
    System.out.println(palindrome.isPalindrome("A man, a plan, a canal: Panama"));
    System.out.println(palindrome.isPalindrome("race a car"));
  }

  @Test
  public void isPalindromeRecursion() {
    System.out.println(palindrome.isPalindromeRecursion("A man, a plan, a canal: Panama"));
    System.out.println(palindrome.isPalindromeRecursion("race a car"));
  }
}