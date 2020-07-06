package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import java.util.Arrays;
import org.junit.Test;

public class RemoveElementTest {

  private RemoveElement element = new RemoveElement();

  @Test
  public void removeElement() {
    int[] arr = {1, 2, 4, 5, 2, 2};
    System.out.println("Original array: " + Arrays.toString(arr) + "and length: " + arr.length);
    System.out.println("length after: " + element.removeElement(arr, 2));
  }
}
