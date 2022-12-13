package ntnu.no.idata1001.jakobfin.logic;

/**
 * Class for different items, contains useful information regarding different items.
 *
 * @author 10042
 */
public class Item {
  private String sequenceNumber;
  private String description;
  private int price;
  private double discount;
  private String brand;
  private double weight;
  private double length;
  private double height;
  private double width;
  private Color color;
  private int amount;
  private Category category;

  /**
   * Constructor for the item.
   *
   * @param sequenceNumber the sequence number, cannot be under 0
   * @param description    of the item
   * @param price          of the item, cannot be lower than 0
   * @param discount       of the item, must be between 0 and 100
   * @param brand          of the item, lower case
   * @param weight         of the item, cannot be 0 or lower
   * @param length         of the item, cannot be 0 or lower
   * @param height         of the item, cannot be 0 or lower
   * @param width          of the item, cannot be 0 or lower
   * @param color          of the item, lower case
   * @param amount         of the item, cannot be 0 or lower
   * @param category       of the item, must be between 1-4
   */
  public Item(String sequenceNumber, String description, int price, double discount, String brand,
              double weight, double length, double height, double width,
              Color color, int amount, Category category) {

    setSequenceNumber(sequenceNumber);
    setDescription(description);
    setPrice(price);
    setDiscount(discount);
    setBrand(brand);
    setWeight(weight);
    setLength(length);
    setHeight(height);
    setWidth(width);
    setColor(color);
    setAmount(amount);
    setCategory(category);
  }

  /**
   * Constructor for creating a copy of an item.
   *
   * @param item that shall be copied
   */
  public Item(Item item) {
    setSequenceNumber(item.getSequenceNumber());
    setDescription(item.getDescription());
    setPrice(item.getPrice());
    setDiscount(item.getDiscount());
    setBrand(item.getBrand());
    setWeight(item.getWeight());
    setLength(item.getLength());
    setHeight(item.getHeight());
    setWidth(item.getWidth());
    setColor(item.getColor());
    setAmount(item.getAmount());
    this.category = item.category;
  }

  /**
   * Set the sequence number of the item.
   *
   * @param sequenceNumber used to identify the item
   * @throws IllegalArgumentException if the sequence number is lower or equal to 0
   */
  private void setSequenceNumber(String sequenceNumber) {
    if (sequenceNumber != null && !sequenceNumber.isBlank() && !sequenceNumber.isEmpty()) {
      this.sequenceNumber = sequenceNumber.trim().toUpperCase();
    } else {
      throw new IllegalArgumentException("Sequence number cannot be null");
    }
  }

  /**
   * Set the description of the item. Also clear the blank spaces in-front and after.
   *
   * @param description of the item
   * @throws IllegalArgumentException if the string is null
   */
  protected void setDescription(String description) {
    if (description == null) {
      throw new IllegalArgumentException("Description cannot be null");
    }
    this.description = description.trim();
  }

  /**
   * Set the price of the item.
   *
   * @param price number which indicates how much the item costs
   * @throws IllegalArgumentException if the price is lower than 0
   */
  protected void setPrice(int price) {
    if (price >= 0) {
      this.price = price;
    } else {
      throw new IllegalArgumentException("Price cannot be lower than 0");
    }
  }

  /**
   * Set the discount of this item.
   *
   * @param discount of the item, must be between 0 and 100
   * @throws IllegalArgumentException if discount is lower than 0 or over 100
   */
  protected void setDiscount(Double discount) {
    if (0 <= discount && discount <= 100) {
      this.discount = discount;
    } else {
      throw new IllegalArgumentException("Discount must "
              + "be between 0 and 100");
    }
  }

  /**
   * Set the brand of the given item.
   *
   * @param brand of the item, etc. Hunton, Pergo
   * @throws IllegalArgumentException if input is empty or blank
   */
  private void setBrand(String brand) {
    if (brand == null || brand.isEmpty() || brand.isBlank()) {
      throw new IllegalArgumentException("Brand cannot be empty");
    }
    this.brand = brand.trim().toLowerCase();
  }

  /**
   * Set the weight of the given item.
   *
   * @param weight a representation of the item's mass, should not be under 0
   * @throws IllegalArgumentException if weight is not higher than -1
   */
  private void setWeight(double weight) {
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
   * @throws IllegalArgumentException if length is under 0
   */
  private void setLength(double length) {
    if (length > 0) {
      this.length = length;
    } else {
      throw new IllegalArgumentException("Length cannot be lower or equal to 0");
    }
  }

  /**
   * Set the height of the item.
   *
   * @param height of the item, cannot be under 0
   * @throws IllegalArgumentException if height is lower or equal to 0
   */
  private void setHeight(double height) {
    if (height > 0) {
      this.height = height;
    } else {
      throw new IllegalArgumentException("Height cannot be 0 or lower");
    }
  }

  /**
   * Set the width of the item.
   *
   * @param width of the item, cannot be under 0
   * @throws IllegalArgumentException if the width is lower or equal to 0
   */
  private void setWidth(double width) {
    if (width > 0) {
      this.width = width;
    } else {
      throw new IllegalArgumentException("Width cannot be 0 or lower");
    }
  }

  /**
   * Set the color of the given item. Make them lowercase.
   *
   * @param color of the item
   * @throws IllegalArgumentException if the color is nothing
   */
  private void setColor(Color color) {
    if (color != null) {
      this.color = color;
    } else {
      throw new IllegalArgumentException();
    }
  }

  /**
   * Set the amount of items.
   *
   * @param amount of the items
   * @throws IllegalArgumentException if the amount is under 0
   */
  protected void setAmount(int amount) {
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
   * @throws IllegalArgumentException if category is null
   */
  private void setCategory(Category category) {
    if (category == null) {
      throw new IllegalArgumentException("Category cannot be null");
    }
    this.category = category;
  }

  /**
   * Get the sequence number of the given item.
   *
   * @return the sequence number of the item
   */
  public String getSequenceNumber() {
    return this.sequenceNumber;
  }

  /**
   * Get the description of the given item.
   *
   * @return the description of the item
   */
  public String getDescription() {
    return this.description.toUpperCase();
  }

  /**
   * Get the price of the given item.
   *
   * @return the price of the item
   */
  public int getPrice() {
    return this.price;
  }

  /**
   * Get the discount of the given item.
   *
   * @return the discount of the item
   */
  public double getDiscount() {
    return this.discount;
  }

  /**
   * Get the brand of the given item.
   *
   * @return the brand of the item
   */
  public String getBrand() {
    return this.brand;
  }

  /**
   * Get the weight of the given item.
   *
   * @return the weight of the item
   */
  public double getWeight() {
    return this.weight;
  }

  /**
   * Get the length of the item.
   *
   * @return the length of the item
   */
  public double getLength() {
    return this.length;
  }

  /**
   * Get the height of the item.
   *
   * @return the height of the item
   */
  public double getHeight() {
    return this.height;
  }

  /**
   * Get width of the item.
   *
   * @return width of the item
   */
  public double getWidth() {
    return this.width;
  }

  /**
   * Get the color for the item.
   *
   * @return the color of the item
   */
  public Color getColor() {
    return this.color;
  }

  /**
   * Get the amount of given item.
   *
   * @return the amount of items
   */
  public int getAmount() {
    return this.amount;
  }

  /**
   * Get the category of the item.
   *
   * @return category of the item
   */
  public Category getCategory() {
    return this.category;
  }

  /**
   * Get standard format for out printing item.
   *
   * @return format for printing item
   */
  @Override
  public String toString() {
    return String.format(
            "| %-15s | %6d (%8.2f%%)| %-22s | "
                    + "%10.2f%-3s | %10.2f | %10.2f | %10.2f | %-15s | "
                    + "%6d | %-18s | %s ",
            this.getSequenceNumber(), this.getPrice(),
            this.getDiscount(), this.getBrand(), this.getWeight(), " KG", this.getLength(),
            this.getHeight(), this.getWidth(), this.getColor(), this.getAmount(),
            this.getCategory(), this.getDescription()
    );
  }
}
