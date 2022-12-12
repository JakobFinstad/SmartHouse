package ntnu.no.idata1001.jakobfin.ui;

import java.util.Iterator;
import java.util.stream.Stream;
import ntnu.no.idata1001.jakobfin.logic.Item;
import ntnu.no.idata1001.jakobfin.logic.ItemRegister;


/**
 * Class that format and present all the information. Uses the class {@link InputReader} to handle
 * user input.
 *
 * @author 10042
 */
public class Ui {

  //Various fields for the switch case
  private static final int PRINT_WARES = 1;
  private static final int SEARCH_WARE = 2;
  private static final int ADD_WARE = 3;
  private static final int CHANGE_AMOUNT = 4;
  private static final int DELETE_WARE = 5;
  private static final int CHANGE_PRICE = 6;
  private static final int CHANGE_DISCOUNT = 7;
  private static final int CHANGE_DESCRIPTION = 8;
  private static final int EXIT = 9;
  private static InputReader inputReader;
  private static ItemRegister itemRegister;

  /**
   * Class that runs the ui. Display all the different choices etc.
   */
  public void run() {
    // Added for some basic content in the warehouse
    populateTheWareHouse();

    boolean running = true;

    while (running) {
      showMenu();
      int userChoice = inputReader.getInt("Please select one of the choices.");
      switch (userChoice) {
        case PRINT_WARES -> listAllItems(itemRegister.getIterator());
        case SEARCH_WARE -> searchWare();
        case ADD_WARE -> addWare();
        case CHANGE_AMOUNT -> changeAmount();
        case DELETE_WARE -> deleteWare();
        case CHANGE_PRICE -> changePrice();
        case CHANGE_DISCOUNT -> changeDiscount();
        case CHANGE_DESCRIPTION -> changeDescription();
        case EXIT -> {
          running = false;
          showExitMessage();
          System.exit(0);
        }
        default -> System.out.println("Please select one of the menu options");
      }
    }
  }

  /**
   * Constructor for the class.
   */
  public Ui() {
    inputReader = new InputReader();
    itemRegister = new ItemRegister();
  }

  /**
   * Search for a {@link Item} in the {@link ItemRegister}, and prints it out using the
   * {@link ItemRegister#getDescription(Item)}.
   */
  private void searchWare() {
    try {
      int descriptionSequenceNumber = inputReader.getInt("1. Search by sequence number\n"
              + "2. Search by descriptions");
      if (descriptionSequenceNumber == 1) {
        System.out.println(itemRegister.getDescription(
                itemRegister.searchBySequenceNumber(
                        inputReader
                                .getString("Sequence number to search by").trim().toUpperCase())));
      } else if (descriptionSequenceNumber == 2) {
        listAllItems(itemRegister
                .searchMultipleByDescription(
                        inputReader.getString("Description to search by").trim().toUpperCase())
                .iterator());
      }
    } catch (IllegalArgumentException e) {
      System.out.println("Something went wrong: " + e.getMessage());
    } catch (NullPointerException e) {
      System.out.println("No such item in the register...");
    }
  }

  /**
   * Adds an {@link Item} to the register. Fails if an instance of the item
   * is already present in the register, or if the item is invalid.
   */
  private void addWare() {
    try {
      itemRegister.addItem(createItem());
      System.out.println("Item added successfully");
    } catch (IllegalArgumentException e) {
      System.out.println("Could not execute: " + e.getMessage());
    }
  }

  /**
   * Change the amount of an {@link Item} in the register.
   * Fails if the amount is lower than zero, and or if the item is not
   * present in the register.
   */
  private void changeAmount() {
    try {
      itemRegister.changeWareAmount(inputReader.getString("Sequence number for item"),
              inputReader.getInt("The new amount"));
      System.out.println("New amount set...");
    } catch (IllegalArgumentException e) {
      System.out.println("Something went wrong: " + e.getMessage());
    } catch (NullPointerException e) {
      System.out.println("Could not find item: " + e.getMessage());
    }
  }

  /**
   * Remove an {@link Item} from the register. Fails if the item is not present in the register.
   */
  private void deleteWare() {
    try {
      Item item = itemRegister.searchBySequenceNumber(inputReader.getString("Please enter "
              + "the sequence number of the ware that shall"
              + " be deleted.").trim().toUpperCase());
      if (item != null) {
        if (inputReader.getBoolean("Sure you want to delete this item?")) {
          itemRegister.deleteWare(item);
        }
      }

      System.out.println("Ware deleted successfully.....");
    } catch (NullPointerException e) {
      System.out.println("Could not find item: " + e.getMessage());
    }
  }

  /**
   * Change the discount of a given ware.
   */
  private void changeDiscount() {
    try {
      itemRegister.changeDiscount(
              inputReader.getString("Sequence number of the item that shall be changed")
                      .trim().toUpperCase(),
              inputReader.getDouble("Discount"));
      System.out.println("New discount set....");
    } catch (IllegalArgumentException e) {
      System.out.println("New discount is invalid: " + e.getMessage());
    } catch (NullPointerException e) {
      System.out.println("No such item in register: " + e.getMessage());
    }
  }

  /**
   * Change the description for a given ware.
   */
  private void changeDescription() {
    try {
      itemRegister.changeDescription(inputReader.getString(
              "Sequence number of the item that shall be altered"
      ), inputReader.getString("New description for the item"));
      System.out.println("Description changed...");
    } catch (IllegalArgumentException e) {
      System.out.println("Illegal input, try again: " + e.getMessage());
    } catch (NullPointerException e) {
      System.out.println("No such item in register: " + e.getMessage());
    }
  }

  /**
   * Change the price of a given ware.
   */
  private void changePrice() {
    try {
      itemRegister.changeWarePrice(inputReader.getString(
              "Sequence number of the ware that shall be altered."),
              inputReader.getInt("New price for the ware"));
      System.out.println("Price changed....");
    } catch (IllegalArgumentException e) {
      System.out.println("New price cannot be added: " + e.getMessage());
    } catch (NullPointerException e) {
      System.out.println("No such item in the register: " + e.getMessage());
    }
  }

  /**
   * Create an {@link Item}.
   *
   * @return the item that was created
   */
  private Item createItem() {
    Item item = null;
    try {
      item = new Item(inputReader.getString("SequenceNumber"),
              inputReader.getString("Description"),
              inputReader.getInt("Price"),
              inputReader.getDouble("Discount"),
              inputReader.getString("Brand"),
              inputReader.getDouble("Weight"),
              inputReader.getDouble("Length"),
              inputReader.getDouble("Height"),
              inputReader.getDouble("Width"),
              inputReader.getString("Color"),
              inputReader.getInt("Amount"),
              inputReader.getInt("Category, "
                      + "1 for Laminate floor, 2 for window, 3 for door and 4 for wood"));
    } catch (IllegalArgumentException e) {
      System.out.println("Error, something went wrong: " + e.getMessage());
    }
    return item;
  }

  /**
   * Show the menu, to give the user a choice.
   */
  private void showMenu() {
    System.out.println("========================================\n"
            + "1. Print wares in the warehouse.\n"
            + "2. Search on ware.\n"
            + "3. Register a new ware.\n"
            + "4. Change the amount of given ware.\n"
            + "5. Delete a ware from the warehouse.\n"
            + "6. Change the price for given ware.\n"
            + "7. Change the discount for given ware\n"
            + "8. Change the description for given ware\n"
            + "9. Quit\n"
            + "========================================\n");
  }

  /**
   * List all the items in the warehouse.
   *
   * @param it the iterator for the items that shall be written out
   */
  private void listAllItems(Iterator<Item> it) {
    System.out.println(itemRegister.getDescription(it));
  }

  /**
   * Shows exit message, for terminating the program.
   */
  private void showExitMessage() {
    System.out.println("Thank you for using WMS....");
  }

  /**
   * Method for populating the register.
   */
  private void populateTheWareHouse() {
    Item item1 = new Item("1", "Glass pane",
            100, 0, "Ikea", 19, 19, 19, 20, "Invisible", 10, 2);

    Item item2 = new Item("2", "Door knob",
            36, 0, "Ikea", 10.2, 19, 19, 10, "Orange", 20, 3);

    Item item3 = new Item("3", "Door knob",
            10, 0, "Ikea", 19, 19, 19, 18, "kas", 10, 3);

    Item item4 = new Item("4", "Glass pane",
            10, 20, "Jysk", 19, 19, 19, 19, "kas", 10, 2);

    Item item5 = new Item("1HG", "Weights",
            10, 10, "Princess", 19, 19, 19, 19, "kas", 10, 4);

    itemRegister.addMultipleItems(Stream.of(item1, item2, item3, item4, item5));
  }
}
