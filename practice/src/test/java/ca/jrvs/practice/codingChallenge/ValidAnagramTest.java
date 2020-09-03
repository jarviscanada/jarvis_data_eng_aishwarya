package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class ValidAnagramTest {

  private ValidAnagram anagram = new ValidAnagram();

  @Test
  public void isAnagramSort() {
    System.out.println(anagram.isAnagramSort("anagram", "nagaram"));
    System.out.println(anagram.isAnagramSort("onion", "nion"));

  }

  @Test
  public void isAnagram() {
    System.out.println(anagram.isAnagram("iceman", "cinema"));
    System.out.println(anagram.isAnagram("people", "peeple"));
  }
}