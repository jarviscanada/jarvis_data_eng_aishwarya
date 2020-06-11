package ca.jrvs.apps.practice;

class HelloWorld {

  // Your program begins with a call to main().
  // Prints "Hello, World" to the terminal window.
  public static void main(String[] args) {
    System.out.println("Hello, World");
    RegexExcImp regexTest = new RegexExcImp();

    System.out.println("Filename test 1: " + regexTest.matchJpeg("pics.JpEg"));
    System.out.println("Filename test 2: " + regexTest.matchJpeg("pics.png"));
    System.out.println("IP test 1: " + regexTest.matchIp("125.194.67.23"));
    System.out.println("IP test 2: " + regexTest.matchIp("1000.567.456.3"));
    System.out.println("Line test 1: " + regexTest.isEmptyLine("   This a test line"));
    System.out.println("Line test 2: " + regexTest.isEmptyLine("No_spaces"));
  }
}