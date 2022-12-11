package ntnu.no.idata1001.jakobfin;

import java.util.Scanner;

/**
 * Scanner who interpret input written by user. Methods to decide which
 * input variable type to return.
 *
 * @author 10042
 */
public class InputReader {
  private Scanner scanner;

  /**
   * Create a scanner object.
   */
  public InputReader() {
    scanner = new Scanner(System.in);
  }

  /**
   * Returns a string that user enter.
   *
   * @param displayMessage for better understanding
   * @return string written by user
   */
  public String getString(String displayMessage) {
    System.out.println(displayMessage);
    System.out.print("> ");
    String selectedChoice = scanner.nextLine();
    return selectedChoice;
  }

  /**
   * Return the integer the user enter.
   *
   * @param displayMessage for better understanding
   * @return the written integer by user
   */
  public int getInt(String displayMessage) {
    System.out.println(displayMessage);
    System.out.print("> ");

    while (true) {
      int selectedChoice = 0;
      if (scanner.hasNextInt()) {
        selectedChoice = scanner.nextInt();
        return selectedChoice;
      } else {
        System.out.println("Please enter a number");
      }
    }
  }

  /**
   * Return boolean determined by the user, base value False.
   *
   * @param displayMessage for better understanding
   * @return true for y and false for n
   */
  public boolean getBoolean(String displayMessage) {
    System.out.println(displayMessage);
    System.out.println("y/n?");
    System.out.print("> ");
    String selectedChoice = scanner.nextLine();
    selectedChoice = selectedChoice.toLowerCase();

    boolean booleanToReturn = false;
    if (selectedChoice.equals("y")) {
      booleanToReturn = true;
    } else if (!selectedChoice.equals("y") && !selectedChoice.equals("n")) {
      System.out.println("Please enter y or n!");
    }
    return booleanToReturn;
  }

  /**
   * Get float from the user. Default value is 0.
   *
   * @param displayMessage message that outputs on the screen
   * @return the double the user entered
   */
  public double getDouble(String displayMessage) {
    System.out.println(displayMessage);
    double toReturn = 0;
    boolean needDouble = true;
    while (needDouble) {
      if (scanner.hasNextDouble()) {
        toReturn = scanner.nextDouble();
        needDouble = false;
      } else {
        System.out.println("Please enter a float");
      }
    }
    return toReturn;
  }
}