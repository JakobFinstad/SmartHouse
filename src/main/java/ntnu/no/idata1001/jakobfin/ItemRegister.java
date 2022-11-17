package ntnu.no.idata1001.jakobfin;

import java.util.HashMap;

public class ItemRegister {
    private HashMap<Integer, Item> itemMap;

    public ItemRegister(){
      this.itemMap = new HashMap<Integer,Item>();
    }

    public void addItem(Item item){
      if(item != null && item.getSequenceNumber() > 0 && item.getAmount() >= 0){
        itemMap.put(item.getSequenceNumber(), item);
      } else {
        throw new IllegalArgumentException("Item was not valid, must have sequence number and amount higher than zero");
      }
    }

    public Item searchBySequenceNumber(int sequenceNumber){
      Item foundItem = null;
      if(itemMap.containsKey(sequenceNumber)){
        foundItem = itemMap.get(sequenceNumber);
      }
      return foundItem;
    }
}
