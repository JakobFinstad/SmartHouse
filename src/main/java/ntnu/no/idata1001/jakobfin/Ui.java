package ntnu.no.idata1001.jakobfin;

import java.util.stream.Stream;

public class Ui {

  private static final int PRINT_WARES = 1;
  private static final int SEARCH_WARE = 2;
  private static final int ADD_WARE = 3;
  private static final int CHANGE_AMOUNT = 4;
  private static final int DELETE_WARE = 5;
  private static final int CHANGE_PRICE_DESCRIPTION = 6;
  private static final int EXIT = 9;
  private static InputReader inputReader;
  private static ItemRegister itemRegister;

  public Ui() {
    inputReader = new InputReader();
    itemRegister = new ItemRegister();
    // Added for some basic content in the warehouse
    populateTheWareHouse();

    boolean running = true;

    while(running) {
      showMenu();
      int userChoice = inputReader.getInt("Please select one of the choices.");
      switch (userChoice){
        case PRINT_WARES:
          itemRegister.listAllItems(itemRegister.getIterator());
          break;


        case SEARCH_WARE:
          try {
            int descriptionSequenceNumber = inputReader.getInt("1. Search by sequence number\n"
                    + "2. Search by descriptions");
            if (descriptionSequenceNumber == 1) {
              System.out.println(itemRegister.getDescription(itemRegister
                      .searchBySequenceNumber(inputReader.getString("Sequence number to search by"))));
            } else if (descriptionSequenceNumber == 2) {
              itemRegister.listAllItems(itemRegister
                      .searchMultipleByDescription(inputReader.getString("Description to search by"))
                      .iterator());
            }
          } catch (IllegalArgumentException IAE) {
            System.out.println("Something went wrong: " + IAE.getMessage());
          }
          break;


        case ADD_WARE:
          try {
            itemRegister.addItem(createItem());
          } catch (IllegalArgumentException IAE) {
            System.out.println("Could not execute: " + IAE.getMessage());
          }
          break;


        case CHANGE_AMOUNT:
          try {
            itemRegister.changeWareAmount(itemRegister
                            .searchBySequenceNumber(inputReader.getString("Sequence number for item"))
                    , inputReader.getInt("The new amount"));
          } catch (IllegalArgumentException IAE) {
            System.out.println("Something went wrong: " + IAE.getMessage());
          }
          break;


        case DELETE_WARE:
          try {
            itemRegister.deleteWare(itemRegister.searchBySequenceNumber(
                    inputReader.getString("Please enter the sequence number of the ware that shall"
                            + " be deleted.")));
          } catch (NullPointerException NE) {
            System.out.println("Could not find item: " + NE.getMessage());
          }
          break;


        case CHANGE_PRICE_DESCRIPTION:
          Item changedItem = itemRegister.searchBySequenceNumber("Sequence number on the ware that shall be changed");
          int changePriceOrDescription = inputReader.getInt("Enter 1 for changing the price, "
                  + "and 2 for changing description...");
          if(changePriceOrDescription == 1) {
            int newPrice = inputReader.getInt("The new price.");

            if(inputReader.getBoolean("Want to add any discount?")) {
              newPrice -= inputReader.getInt("Write the discount");
              changedItem.setPrice(newPrice);

            } else {
              changedItem.setPrice(newPrice);
            }

          } else if (changePriceOrDescription == 2) {
            changedItem.setDescription(inputReader.getString("Write the new description on this item"));
          } else {
            System.out.println("Try again, and please select 1 or 2");
          }
          break;


        case EXIT:
          running = false;
          showExitMessage();
          break;

      }
    }
  }

  private Item createItem(){
    Item item = null;
    try {
      item = new Item(inputReader.getString("SequenceNumber"),
              inputReader.getString("Description"),
              inputReader.getInt("Price"),
              inputReader.getString("Brand"),
              inputReader.getDouble("Weight"),
              inputReader.getDouble("Length"),
              inputReader.getDouble("Height"),
              inputReader.getString("Color"),
              inputReader.getInt("Amount"),
              inputReader.getInt("Category, 1 for Laminate floor, 2 for window, 3 for door and 4 for wood"));
    } catch(IllegalArgumentException IAE) {
      System.out.println("Error, something went wrong: " + IAE.getMessage());
    }
    return item;
  }

  private void showMenu(){
    System.out.println("========================================\n"
            + "1. Print wares in the warehouse.\n"
            + "2. Search on ware.\n"
            + "3. Register a new ware.\n"
            + "4. Change the amount of given ware.\n"
            + "5. Delete a ware from the warehouse.\n"
            + "6. Change the description or price for given ware.\n"
            + "9. Quit\n"
            + "========================================\n");
  }

  private void showExitMessage(){
    System.out.println("Thank you for using WMS....");
  }

  private void populateTheWareHouse(){
    Item item1 = new Item("1","Glass pane",
            100,"Ikea",19,19,19,"Invisible",10,2);

    Item item2 = new Item("2","Door knob",
            36,"Ikea",10.2,19,19,"Orange",20,3);

    Item item3 = new Item("3","Door knob",
            10,"Ikea",19,19,19,"kas",10,3);

    Item item4 = new Item("4","Glass pane",
            10,"Jysk",19,19,19,"kas",10,2);

    Item item5 = new Item("1HG","Weights",
            10,"Princess",19,19,19,"kas",10,4);

    itemRegister.addMultipleItems(Stream.of(item1,item2,item3,item4,item5));
  }
}
