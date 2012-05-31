package ModelLayer;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * @version: 0.1
 * Filename: LeasingItem.java
 * Description: Class which creates objects of the type LeasingItem
 * @changes	
 */
public class LeasingItem
{
    private long _itemNumber;
    private String _itemName;
    private BigDecimal _rentPrice;
    private int _maxAvaible;
    private ArrayList<SerialNumber> _serialNumbers;

    /**
     * Constructor for objects of class LeasingItem
     */
    public LeasingItem(long itemNumber, String itemName, BigDecimal rentPrice, int maxAvaible)
    {
        _itemNumber = itemNumber;
        _itemName = itemName;
        _rentPrice = rentPrice;
        _maxAvaible = maxAvaible;
        _serialNumbers = new ArrayList<SerialNumber>();
    }
    
    public long getItemNumber()
    {
        return _itemNumber;
    }
    
    public String getItemName()
    {
        return _itemName;
    }
    
    public BigDecimal getRentPrice()
    {
        return _rentPrice;
    }
    
    public int getMaxAvaible()
    {
        return _maxAvaible;
    }
    
    public ArrayList<SerialNumber> getSerialNumbers()
    {
        return _serialNumbers;
    }
    
    public SerialNumber getAvaible()
    {
        int index = 0;
        boolean found = false;
        while(index < _serialNumbers.size() && !found)
        {
            if(_serialNumbers.get(index).getIsAvaible())
            {
                found = true;
                return _serialNumbers.get(index);
            }
            else
            {
                index++;
            }
        }
        return null;
    }
    
    public void setItemNumber(long itemNumber)
    {
        _itemNumber = itemNumber;
    }
    
    public void setItemName(String itemName)
    {
        _itemName = itemName;
    }
    
    public void setRentPrice(BigDecimal rentPrice)
    {
        _rentPrice = rentPrice;
    }
    
    public void setMaxAvaible(int maxAvaible)
    {
        _maxAvaible = maxAvaible;
    }
    
    public void addItem(SerialNumber serial)
    {
        _serialNumbers.add(serial);
    }
}