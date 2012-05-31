package ModelLayer;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * @version: 0.1
 * Filename: LeasingItemContainer.java
 * Description: Class which contains all of the LeasingItem objects in the system
 * @changes  
 */
public class LeasingItemContainer
{
    private HashMap<Long, LeasingItem> _leasingItemCollection;
    private static LeasingItemContainer _instance;

    public LeasingItemContainer()
    {
        _leasingItemCollection = new HashMap<Long, LeasingItem>();
    }
    
    public static LeasingItemContainer getInstance()
    {
        if(_instance == null)
        {
            _instance = new LeasingItemContainer();
        }
        return _instance;
    }
    
    public boolean addLeasingItem(LeasingItem item)
    {
        long key = item.getItemNumber();
        if(!_leasingItemCollection.containsKey(key))
        {
            _leasingItemCollection.put(key, item);
            return true;
        }
        return false;
    }
    
    public boolean updateLeasingItem(LeasingItem item)
    {
        long key = item.getItemNumber();
        LeasingItem value = _leasingItemCollection.get(key);
        if(value != null)
        {
            _leasingItemCollection.put(key, value);
            return true;
        }
        return false;
    }
    
    public boolean removeLeasingItem(long itemNumber)
    {
        LeasingItem value = _leasingItemCollection.get(itemNumber);
        if(value != null)
        {
            _leasingItemCollection.remove(itemNumber);
            return true;
        }
        return false;
    }
    
    public LeasingItem getLeasingItem(long itemNumber)
    {
        return _leasingItemCollection.get(itemNumber);
    }
    
    public LeasingItem findLeasingItem(String itemName)
    {
        for(LeasingItem item : _leasingItemCollection.values())
        {
            String itemItemName = item.getItemName().toLowerCase();
            if(itemItemName.equals(itemName.toLowerCase()))
                return item;
        }

        return null;
    }
    
    public ArrayList<SerialNumber> getAvaible(long itemNumber)
    {
        LeasingItem item = _leasingItemCollection.get(itemNumber);
        return item.getSerialNumbers();
    }
}