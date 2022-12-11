package ntnu.no.idata1001.jakobfin;

import ntnu.no.idata1001.jakobfin.logic.Category;
import ntnu.no.idata1001.jakobfin.logic.Item;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class ItemTest {

  @Test
  public void testNegativePrice(){
    assertThrows(IllegalArgumentException.class, () -> { new Item("as","asd",
            -10,"has",19,19,19,"kas",10,4);});
  }

  @Test
  public void testCategory(){
    Item item = new Item("as","asd",
            10,"has",19.5,19,19,"kas",10,3);
    assertEquals(Category.DOOR, item.getCategory());
  }
}
