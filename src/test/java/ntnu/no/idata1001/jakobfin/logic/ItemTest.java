package ntnu.no.idata1001.jakobfin.logic;

import ntnu.no.idata1001.jakobfin.logic.Category;
import ntnu.no.idata1001.jakobfin.logic.Item;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * Test the Item class.
 *
 * @author 10042
 */
public class ItemTest {
  private static Item  item1 = new Item(
          "12","asd", 10
          , 10,"has",19,19
          ,19, 19, Color.BLACK,10,Category.DOOR);

  /**
   * Test item constructor for invalid input.
   * test will <code>PASS</code> if constructor throws when invalid argument is entered
   * test will <code>FAIL</code> if object is created with invalid arguments
   */
  @Test
  void testNegativeValues(){
    assertThrows(IllegalArgumentException.class, () -> { new Item("as","asd",
            -10, 10,"has",19
            ,19,19, 19, Color.BLACK,10,Category.DOOR);});

    assertThrows(IllegalArgumentException.class, () -> {
      new Item("","asd",
              10, 10,"has",19
              ,19,19, 19, Color.BLACK,10,Category.DOOR);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new Item("1","12",
              10, -110,"has",19
              ,19,19, 19, Color.BLACK,10,Category.DOOR);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new Item("12","asd",
              10, 110,"has",19
              ,19,19, 19, Color.BLACK,10,Category.DOOR);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new Item("12","asd",
              10, 10,null,19
              ,19,19, 19, Color.BLACK,10,Category.DOOR);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new Item("12","asd",
              10, 10,"has",-10
              ,19,19, 19, Color.BLACK,10,Category.DOOR);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new Item("12","asd",
              10, 10,"has",19
              ,-19,19, 19, Color.BLACK,10,Category.DOOR);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new Item("12","asd",
              10, 10,"has",19
              ,19,-19, 19, Color.BLACK, 10,Category.DOOR);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new Item("12","asd",
              10, 10,"has",19
              ,19,19, -19, Color.BLACK,10,Category.DOOR);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new Item("12","asd",
              10, 10,"has",19
              ,19,19, 19, null,10,Category.DOOR);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new Item("12","asd",
              10, 10,"has",19
              ,19,19, 19, Color.BLACK, -1,Category.DOOR);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new Item("12","asd",
              10, 10,"has",19
              ,19,19, 19, Color.BLACK,10,null);
    });
  }

  /**
   * Test constructor with positive arguments.
   * test will <code>PASS</code> if item is created
   * test will <code>FAIL</code> if something went wrong under creation of item
   */
  @Test
  void testPositiveConstructors() {
    assertEquals(item1.getClass(),new Item("12","asd",
              10, 10,"has",19
              ,19,19, 19, Color.BLACK,10,Category.DOOR).getClass());
    assertEquals(item1.getClass(), new Item(item1).getClass());
  }

  /**
   * Test the enum class category.
   * test will <code>PASS</code> if category was the same
   * test will <code>FAIL</code> if the categories was not alike
   */
  @Test
  void testCategory(){
    Item item = new Item("as","asd",
            10, 0,"has",19.5,19,19, 19, Color.BLACK,10,Category.DOOR);
    assertEquals(Category.DOOR, item.getCategory());
  }
}
