package ca.jrvs.apps.practice;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExcImp implements RegexExc {

  /**
   * Checks for jpeg or jpg file and is case insensitive.
   * @param filename filename to match
   * @return boolean for pattern matched in filename
   */
  public boolean matchJpeg(String filename) {
    String regexStr = "(?i)^(.*\\.)(jpeg|jpg)$";
    //Pattern pattern = Pattern.compile(regexStr,Pattern.CASE_INSENSITIVE);
    //Matcher matcher = pattern.matcher(filename);
    //return matcher.matches();
    return Pattern.matches(regexStr, filename);
  }

  public boolean matchIp(String ip) {
    String regexIp = "[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}";
    return Pattern.matches(regexIp, ip);
  }

  public boolean isEmptyLine(String line) {
    String regexLine = ".*\\s.*";
    return Pattern.matches(regexLine, line);
  }
}