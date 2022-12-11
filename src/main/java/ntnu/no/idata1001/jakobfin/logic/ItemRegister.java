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
    Item returnItem = new Item(foundItem);
    return returnItem;
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
        foundItem.add(it);
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

  public void changeWarePrice(String sequenceNumber, int price)
          throws IllegalArgumentException, NullPointerException {
    itemMap.get(sequenceNumber).setPrice(price);
  }

  public void changeDescription(String sequenceNumber, String description)
          throws  IllegalArgumentException, NullPointerException {
    itemMap.get(sequenceNumber).setDescription(description);
  }

  /**
   * Get a basic format for printing the warehouse.
   *
   * @return string with all the information of the given item
   */
  public String getDescription(Item item) {
    if (item ==  null) {
      throw new IllegalArgumentException("Item cannot be null");
    }
    String str = "";
    str += "==============================\n"
            + "Sequence Number: " + item.getSequenceNumber() + "\n"
            + "Description: " + item.getDescription() + "\n"
            + "Price: " + item.getPrice() + "\n"
            + "Brand: " + item.getBrand() + "\n"
            + "Weight: " + item.getWeight() + "\n"
            + "Length: " + item.getLength() + "\n"
            + "Height: " + item.getHeight() + "\n"
            + "Width: " + item.getWidth() + "\n"
            + "Color: " + item.getColor() + "\n"
            + "Amount: " + item.getAmount() + "\n"
            + "Category: " + item.getCategory() + "\n"
            + "=================================\n";

    return str;
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
