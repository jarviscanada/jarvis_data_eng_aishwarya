package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import java.util.Arrays;
import org.junit.Test;

public class DuplicateCharacterTest {

  @Test
  public void duplicateCharacters() {
    String str = "A black cat";
    System.out.println(Arrays.toString(new DuplicateCharacter().duplicateCharacters(str)));
  }
}