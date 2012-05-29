package ModelLayer;


/**
 * @version: 0.1
 * Filename: SerialNumber.java
 * Description: Class which creates objects of the type serialNumber
 * @changes  
 */
public class SerialNumber
{
    private long _serialNumber;
    private boolean _isAvaible;
    private long _itemNumber;

    /**
     * Constructor for objects of class SerialNumber
     */
    public SerialNumber(long serialNumber, boolean isAvaible, long itemNumber)
    {
        _serialNumber = serialNumber;
        _isAvaible = isAvaible;
        _itemNumber = itemNumber;
    }
    
    public long getSerialNumber()
    {
        return _serialNumber;
    }
    
    public boolean getIsAvaible()
    {
        return _isAvaible;
    }
    
    public long getItemNumber()
    {
        return _itemNumber;
    }
    
    public void setSerialNumber(long serialNumber)
    {
        _serialNumber = serialNumber;
    }
    
    public void updateAvail(boolean isAvaible)
    {
        _isAvaible = isAvaible;
    }
}
