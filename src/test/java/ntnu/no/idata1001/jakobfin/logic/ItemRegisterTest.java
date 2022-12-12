package ntnu.no.idata1001.jakobfin.logic;

import ntnu.no.idata1001.jakobfin.logic.Item;
import ntnu.no.idata1001.jakobfin.logic.ItemRegister;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * Class for testing the itemRegister.
 *
 * @author 10042
 */
public class ItemRegisterTest {
  private static ItemRegister itemRegister = new ItemRegister();
  private static Item item = new Item("12","asd",
            10, 10,"has",19
            ,19,19, 19, "kas",10,4);

  /**
   * Testing the iterator function.
   * test will <code>PASS</code> if the iterator is not null
   * test will <code>FAIL</code> if the iterator is null
   */
  @Test
  public void iteratorTest() {
    assertNotNull(itemRegister.getIterator());
  }

  /**
   * Test the add item with valid input. Also tests the iterator.
   * test will <code>PASS</code> if the was added correctly
   * test will <code>FAIl</code> if the item was not added
   */
  @Test
  public void addItemPositiveTest() {
    itemRegister.addItem(item);
    assertEquals(item, itemRegister.getIterator().next());
    itemRegister.deleteWare(item);
  }

  /**
   * Test {@link ItemRegister#addItem(Item)} with invalid arguments.
   * test will <code>PASS</code> if the item does not get added, and {@link IllegalArgumentException} gets thrown
   * test will <code>FAIL</code> if no exception gets thrown
   */
  @Test
  public void addItemNegativeTest() {
    assertThrows(IllegalArgumentException.class, () -> itemRegister.addItem(null));
    itemRegister.addItem(item);
    assertThrows(IllegalArgumentException.class, () -> itemRegister.addItem(item));
    itemRegister.deleteWare(item);
  }

  /**
   * Test {@link ItemRegister#searchBySequenceNumber(String)} for valid and invalid input.
   * test will <code>PASS</code> if {@link NullPointerException} gets thrown, and item is not null after adding
   * test will <code>FAIL</code> if added item is null, and nothing is thrown
   */
  @Test
  public void searchBySequenceNumberTest() {
    assertThrows(NullPointerException.class, () -> itemRegister.searchBySequenceNumber(item.getSequenceNumber()));
    itemRegister.addItem(item);
    assertNotNull(itemRegister.searchBySequenceNumber(item.getSequenceNumber()));
    itemRegister.deleteWare(item);
  }

  /**
   * Test {@link ItemRegister#searchMultipleByDescription(String)} by checking if method can find an object.
   * test will <code>PASS</code> if the added item gets found
   * test will <code>FAIL</code> if the added item does not get found
   */
  @Test
  public void searchByDescription() {
    itemRegister.addItem(item);
    assertEquals(item,itemRegister.searchMultipleByDescription(item.getDescription()).get(0));
    itemRegister.deleteWare(item);
  }

  /**
   * Test {@link ItemRegister#deleteWare(Item)} to ensure that items do get deleted.
   * test will <code>PASS</code> if the item gets deleted and no item gets found
   * test will <code>FAIL</code> if the item don't get deleted
   */
  @Test
  public void deleteWareTest() {
    itemRegister.addItem(item);
    itemRegister.deleteWare(item);
    assertThrows(NullPointerException.class, () -> {
      itemRegister.searchBySequenceNumber(item.getSequenceNumber());
    });
  }

  /**
   * Test {@link ItemRegister#changeDiscount(String, double)}, {@link ItemRegister#changeDescription(String, String)}
   * , {@link ItemRegister#changeWareAmount(String, int)} and {@link ItemRegister#changeWarePrice(String, int)} for
   * valid and invalid input. Also checks if the methods throws when item is not in the register.
   * test will <code>PASS</code> if the methods work properly
   * test will <code>FAIL</code> if the methods don't throw, or can change to invalid arguments
   */
  @Test
  public void alternateWareTest() {
    itemRegister.addItem(item);
    assertThrows(IllegalArgumentException.class, () -> {
      itemRegister.changeDiscount(item.getSequenceNumber(),101);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      itemRegister.changeDiscount(item.getSequenceNumber(),-1);
    });
    itemRegister.changeDiscount(item.getSequenceNumber(),50);
    assertEquals(50.0
            , itemRegister.searchBySequenceNumber(item.getSequenceNumber()).getDiscount(), 0.5);

    assertThrows(IllegalArgumentException.class, () -> {
      itemRegister.changeWarePrice(item.getSequenceNumber(), -10);
    });
    itemRegister.changeWarePrice(item.getSequenceNumber(),50);
    assertEquals(50.0, itemRegister.searchBySequenceNumber(item.getSequenceNumber()).getPrice(), 0.5);

    assertThrows(IllegalArgumentException.class, () -> {
      itemRegister.changeWareAmount(item.getSequenceNumber(), -10);
    });
    itemRegister.changeWareAmount(item.getSequenceNumber(), 10);
    assertEquals(10,itemRegister.searchBySequenceNumber(item.getSequenceNumber()).getAmount());

    itemRegister.deleteWare(item);

    assertThrows(NullPointerException.class, () -> {
      itemRegister.changeDescription(item.getSequenceNumber(),"");
    });
    assertThrows(NullPointerException.class, () -> {
      itemRegister.changeDiscount(item.getSequenceNumber(),19);
    });
    assertThrows(NullPointerException.class, () -> {
      itemRegister.changeWarePrice(item.getSequenceNumber(),10);
    });
    assertThrows(NullPointerException.class, () -> {
      itemRegister.changeWareAmount(item.getSequenceNumber(),10);
    });
  }

  /**
   * Test the {@link ItemRegister#getDescription(Item)} and {@link ItemRegister#getDescription(Iterator)} for
   * invalid arguments.
   * test will <code>PASS</code> if the methods throws
   * test will <code>FAIl</code> if nothing is thrown
   */
  @Test
  public void descriptionTest() {
    assertThrows(IllegalArgumentException.class, () -> {
      itemRegister.getDescription((Iterator<Item>) null);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      itemRegister.getDescription((Item) null);
    });
  }

}
