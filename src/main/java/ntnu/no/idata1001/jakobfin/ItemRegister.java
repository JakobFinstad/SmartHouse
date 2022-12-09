package ntnu.no.idata1001.jakobfin;


import java.util.HashMap;
import java.util.Iterator;

/**
 * A class for holding multiple items for the warehouse. Have some basic operations to manipulate
 * the wares in the warehouse.
 *
 * @author 10042
 */
public class ItemRegister {
  private HashMap<String, Item> itemMap;

  /**
   * Constructor for the warehouse.
   */
  public ItemRegister() {
    this.itemMap = new HashMap<String, Item>();
  }

  /**
   * Add an item to the list.
   *
   * @param item that shall be added to the warehouse
   */
  public void addItem(Item item) {
    if (item != null && item.getSequenceNumber().isEmpty() && item.getAmount() >= 0) {
      itemMap.put(item.getSequenceNumber(), item);
    } else {
      throw new IllegalArgumentException(
          "Item was not valid, must have and amount higher than zero");
    }
  }

  /**
   * Search the warehouse for a given item by sequence number.
   *
   * @param sequenceNumber the parameter the method uses to search for the item
   * @return null if the item is not found, if it found returns the item
   */
  public Item searchBySequenceNumber(String sequenceNumber) {
    Item foundItem = null;
    if (itemMap.containsKey(sequenceNumber.trim().toLowerCase())) {
      foundItem = itemMap.get(sequenceNumber);
    }
    return foundItem;
  }

  /**
   * Search the warehouse for a given item by description.
   *
   * @param description of the item that shall be searched for
   * @return item if found, if not found returns null
   */
  public Item searchByDescription(String description) {
    Item foundItem = null;
    for (Item it : itemMap.values()) {
      if (it.getDescription().equals(description.trim())) {
        foundItem = it;
      }
    }
    return foundItem;
  }

  /**
   * Get iterator for the items in this warehouse.
   *
   * @return iterator for items
   */
  public Iterator<Item> getIterator() {
    return this.itemMap.values().iterator();
  }

  /**
   * Delete an item from the list.
   *
   * @param item the item that shall be deleted from the warehouse
   * @throws NullPointerException if the ware does not exist in the warehouse
   */
  public void deleteWare(Item item) throws NullPointerException {
    if (itemMap.containsKey(item)) {
      itemMap.remove(item);
    } else {
      throw new NullPointerException();
    }
  }

  /**
   * Change the amount of an item in the warehouse. Amount must be over 0.
   *
   * @param amount of the given item in the warehouse
   * @param item that shall change amount
   * @throws IllegalArgumentException if amount is out of bounds
   */
  public void changeWareAmount(int amount, Item item) throws IllegalArgumentException {
    itemMap.get(item).setAmount(amount);
  }

  /**
   * Get a basic format for printing the warehouse.
   *
   * @return string with all the information of the given item
   */
  public String getDescription(Item item) {
    String str = "";
    str += "==============================\n"
            + "Sequence Number: " + item.getSequenceNumber() + "\n"
            + "Description: " + item.getDescription() + "\n"
            + "Price: " + item.getPrice() + "\n"
            + "Brand: " + item.getBrand() + "\n"
            + "Weight: " + item.getWeight() + "\n"
            + "Length: " + item.getLength() + "\n"
            + "Height: " + item.getHeight() + "\n"
            + "Color: " + item.getColor() + "\n"
            + "Amount: " + item.getAmount() + "\n"
            + "Category: " + item.getDescription() + "\n"
            + "=================================\n";

    return str;
  }

  /**
   * List all the items in the warehouse.
   *
   * @param it the iterator for the items that shall be written out
   */
  private void listAllItems(Iterator<Item> it) {
    while (it.hasNext()) {
      System.out.println(getDescription(it.next()));
    }
  }
}
