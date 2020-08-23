package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/Print-letter-with-number-72fe81ae2d2e4308aa3f92e94e2c7fe7
 */
public class LetterWithNumber {

  /**
   * Big-O: O(n) Justification: for loop.
   */
  public String printLetterwithNumbers(String str) {
    String[] newStr = new String[str.length() * 2];
    int j = 0;
    for (int i = 0; i < str.length(); i++) {
      if (str.charAt(i) >= 'a' && str.charAt(i) <= 'z') {
        newStr[j] = String.valueOf(str.charAt(i));
        newStr[j + 1] = String.valueOf(((int) str.charAt(i)) % (int) 'a' + 1);
        j += 2;
      }
      if (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z') {
        newStr[j] = String.valueOf(str.charAt(i));
        newStr[j + 1] = String.valueOf(((int) str.charAt(i) % (int) 'A') + 27);
        j += 2;
      }

    }
    return String.join("", newStr);
  }
}
