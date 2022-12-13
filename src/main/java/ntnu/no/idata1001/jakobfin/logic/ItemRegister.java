package ntnu.no.idata1001.jakobfin.logic;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * A class for holding multiple items for the warehouse. Have some basic operations to manipulate
 * the wares in the warehouse.
 *
 * @author 10042
 */
public class ItemRegister {
  private final HashMap<String, Item> itemMap;

  /**
   * Constructor for the warehouse.
   */
  public ItemRegister() {
    this.itemMap = new HashMap<>();
  }

  /**
   * Add an item to the list.
   *
   * @param item that shall be added to the warehouse
   */
  public void addItem(Item item) {
    if (item != null && !item.getSequenceNumber().isEmpty() && item.getAmount()
            >= 0 && !itemMap.containsKey(item.getSequenceNumber())) {
      itemMap.put(item.getSequenceNumber(), item);
    } else {
      throw new IllegalArgumentException(
          "Item was not valid, please try again");
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
    if (itemMap.containsKey(sequenceNumber.trim().toUpperCase())) {
      foundItem = itemMap.get(sequenceNumber);
    }
    assert foundItem != null;
    return new Item(foundItem);
  }

  /**
   * Search the warehouse for multiple items by description.
   *
   * @param description of the item that shall be searched for
   * @return item if found, if not found returns null
   */
  public List<Item> searchMultipleByDescription(String description) {
    List<Item> foundItem = new ArrayList<>();
    for (Item it : itemMap.values()) {
      if (it.getDescription().equals(description.trim())) {
        foundItem.add(new Item(it));
      }
    }
    return foundItem;
  }

  /**
   * Search the warehouse for multiple items by category.
   *
   * @param category of the item that shall be searched for
   * @return item if found, if not found returns null
   */
  public List<Item> searchMultipleByCategory(Category category) {
    List<Item> foundItem = new ArrayList<>();
    for (Item it : itemMap.values()) {
      if (it.getCategory().equals(category)) {
        foundItem.add(new Item(it));
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
    if (itemMap.containsKey(item.getSequenceNumber())) {
      itemMap.remove(item.getSequenceNumber());
    } else {
      throw new NullPointerException("No such item");
    }
  }

  /**
   * Change the amount of an item in the warehouse. Amount must be over 0.
   *
   * @param amount of the given item in the warehouse
   * @param sequenceNumber of the item that shall be changed
   * @throws IllegalArgumentException if amount is out of bounds
   */
  public void changeWareAmount(String sequenceNumber, int amount)
          throws IllegalArgumentException, NullPointerException {
    itemMap.get(sequenceNumber).setAmount(amount);
  }

  /**
   * Change the price of an item.
   *
   * @param sequenceNumber of the item that shall be altered
   * @param price the new price for the item, must be positive or 0
   * @throws IllegalArgumentException if price is invalid
   * @throws NullPointerException if item is not in register
   */
  public void changeWarePrice(String sequenceNumber, int price)
          throws IllegalArgumentException, NullPointerException {
    itemMap.get(sequenceNumber).setPrice(price);
  }

  /**
   * Change the description of an item.
   *
   * @param sequenceNumber of the item that shall be altered
   * @param description the new description
   * @throws IllegalArgumentException if the description is invalid
   * @throws NullPointerException if item is not in register
   */
  public void changeDescription(String sequenceNumber, String description)
          throws  IllegalArgumentException, NullPointerException {
    itemMap.get(sequenceNumber).setDescription(description);
  }

  /**
   * Change discount of an item.
   *
   * @param sequenceNumber of the item that shall be altered
   * @param discount the new discount, must be between 0 and 100
   * @throws IllegalArgumentException if discount is invalid
   * @throws NullPointerException if item is not in the register
   */
  public void changeDiscount(String sequenceNumber, double discount)
          throws IllegalArgumentException, NullPointerException {
    itemMap.get(sequenceNumber).setDiscount(discount);
  }

  /**
   * Get a basic format for printing the warehouse.
   *
   * @param it iterator for the items that shall be displayed
   * @return string with all the information of the given items
   */
  public String getDescription(Iterator<Item> it) {
    if (it ==  null) {
      throw new IllegalArgumentException("Item cannot be null");
    }
    StringBuilder str = new StringBuilder("\u001B[34m");
    str.append(String.format(
            "| %-15s | %-17s | %-22s | %-13s | %-10s | "
                    + "%-10s | %-10s | %-15s | %-6s | %-18s | %s\n",
            "SEQUENCE NUMBER", "PRICE (DISCOUNT)",
            "BRAND NAME", "WEIGHT", "LENGTH", "HEIGHT", "WIDTH",
            "COLOR", "STOCK", "CATEGORY", "DESCRIPTION"));
    str.append("\u001B[35m");
    while (it.hasNext()) {
      str.append(it.next().toString()).append("\n");
    }

    str.append("\u001B[0m");

    return str.toString();
  }

  /**
   * Get the description of the given item.
   *
   * @param item the item that shall be described
   * @return string with information about the item
   */
  public String getDescription(Item item) {
    if (item ==  null) {
      throw new IllegalArgumentException("Item cannot be null");
    }

    return String.format(
            "| %-15s | %-17s | %-22s | %-13s | %-10s | "
                    + "%-10s | %-10s | %-15s | %-6s | %-18s | %s\n",
            "SEQUENCE NUMBER", "PRICE (DISCOUNT)",
            "BRAND NAME", "WEIGHT", "LENGTH", "HEIGHT", "WIDTH",
            "COLOR", "STOCK", "CATEGORY", "DESCRIPTION") + "\u001B[35m" +
            item + "\n" +
            "\u001B[0m";
  }


  /**
   * Add multiple items to the item map.
   *
   * @param stream of the items that shall be added
   */
  public void addMultipleItems(Stream<Item> stream) {
    stream.forEach(item -> itemMap.put(item.getSequenceNumber(), item));
  }
}
