package ntnu.no.idata1001;

/**
 * Class for different items, contains useful information regarding different items.
 *
 * @author 10042
 */
public class Item {
  private int sequenceNumber;
  private String description;
  private int price;
  private String brand;
  private float weight;
  private float length;
  private float height;
  private String color;
  private int amount;
  private Category category;

  /**
   * Constructor for the item.
   *
   * @param sequenceNumber the sequence number, cannot be under 0
   * @param description of the item
   * @param price of the item, cannot be lower than 0
   * @param brand of the item, lower case
   * @param weight of the item, cannot be 0 or lower
   * @param length of the item, cannot be 0 or lower
   * @param height of the item, cannot be 0 or lower
   * @param color of the item, lower case
   * @param amount of the item, cannot be 0 or lower
   * @param category of the item, must be between 1-4
   */
  public Item(int sequenceNumber, String description, int price, String brand,
              float weight, float length, float height,
              String color, int amount, int category) {

    setSequenceNumber(sequenceNumber);
    setDescription(description);
    setPrice(price);
    setBrand(brand);
    setWeight(weight);
    setLength(length);
    setHeight(height);
    setColor(color);
    setAmount(amount);
    setCategory(category);
  }

  /**
   * Set the sequence number of the item.
   *
   * @param sequenceNumber number of the product
   * @throws IllegalArgumentException if the sequence number is lower or equal to 0
   */
  private void setSequenceNumber(int sequenceNumber) {
    if (sequenceNumber > 0) {
      this.sequenceNumber = sequenceNumber;
    } else {
      throw new IllegalArgumentException("Sequence number cannot be under 0");
    }
  }

  /**
   * Set the description of the item. Also clear the blank spaces in-front and after.
   *
   * @param description of the item
   */
  private void setDescription(String description) {
    this.description = description.trim();
  }

  /**
   * Set the price of the item.
   *
   * @param price number which indicates how much the item costs
   * @throws IllegalArgumentException if the price is lower than 0
   */
  public void setPrice(int price) {
    if (price >= 0) {
      this.price = price;
    } else {
      throw new IllegalArgumentException("Price cannot be lower than 0");
    }
  }

  /**
   * Set the brand of the given item.
   *
   * @param brand of the item, etc. Hunton, Pergo
   */
  private void setBrand(String brand) {
    this.brand = brand.trim().toLowerCase();
  }

  /**
   * Set the weight of the given item.
   *
   * @param weight a representation of the item's mass, should not be under 0
   * @throws IllegalArgumentException if weight is not higher than 0
   */
  private void setWeight(float weight) {
    if (weight > 0) {
      this.weight = weight;
    } else {
      throw new IllegalArgumentException("Weight cannot be 0 or lower");
    }
  }

  /**
   * Set the length of a given item.
   *
   * @param length of the item in meters
   * @throws IllegalArgumentException if length is 0 or lower
   */
  private void setLength(float length) {
    if (length > 0) {
      this.length = length;
    } else {
      throw new IllegalArgumentException("Length cannot be lower or equal to 0");
    }
  }

  /**
   * Set the height of the item.
   *
   *@param height of the item, cannot be under 0
   *@throws IllegalArgumentException if height is lower or equal to 0
   */
  private void setHeight(float height) {
    if (height > 0) {
      this.height = height;
    } else {
      throw new IllegalArgumentException("Height cannot be 0 or lower");
    }
  }

  /**
   * Set the color of the given item. Make them lowercase.
   *
   * @param color of the item
   */
  private void setColor(String color) {
    if (!color.isBlank()) {
      this.color = color.trim().toLowerCase();
    }
  }

  /**
   * Set the amount of items.
   *
   * @param amount of the items
   * @throws IllegalArgumentException if the amount is under 0
   */
  public void setAmount(int amount) {
    if (amount >= 0) {
      this.amount = amount;
    } else {
      throw new IllegalArgumentException("Amount cannot be lower than 0");
    }
  }

  /**
   * Set the category of the item.
   *
   * @param category of the item
   * @throws IllegalArgumentException if category is outside the range 1 to 4
   */
  private void setCategory(int category) {
    if (category == 1) {
      this.category = Category.FLOOR_LAMINATION;
    } else if (category == 2) {
      this.category = Category.WINDOW;
    } else if (category == 3) {
      this.category = Category.DOOR;
    } else if (category == 4) {
      this.category = Category.WOOD;
    } else {
      throw new IllegalArgumentException("Category should be between 1 and 4");
    }
  }

  /**
   * Get the sequence number of the given item.
   *
   * @return the sequence number of the item
   */
  public int getSequenceNumber() {
    return sequenceNumber;
  }

  /**
   * Get the description of the given item.
   *
   * @return the description of the item
   */
  public String getDescription() {
    return description;
  }

  /**
   * Get the price of the given item.
   *
   * @return the price of the item
   */
  public int getPrice() {
    return price;
  }

  /**
   * Get the brand of the given item.
   *
   * @return the brand of the item
   */
  public String getBrand() {
    return brand;
  }

  /**
   * Get the weight of the given item.
   *
   * @return the weight of the item
   */
  public float getWeight() {
    return weight;
  }

  /**
   * Get the length of the item.
   *
   * @return the length of the item
   */
  public float getLength() {
    return length;
  }

  /**
   * Get the height of the item.
   *
   * @return the height of the item
   */
  public float getHeight() {
    return height;
  }

  /**
   * Get the color for the item.
   *
   * @return the color of the item
   */
  public String getColor() {
    return color;
  }

  /**
   * Get the amount of given item.
   *
   * @return the amount of items
   */
  public int getAmount() {
    return amount;
  }

  /**
   * Get the category of the item.
   *
   * @return category of the item
   */
  public Category getCategory() {
    return category;
  }


}
