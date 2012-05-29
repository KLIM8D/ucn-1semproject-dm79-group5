package ModelLayer;


/**
 * Class that creates objects of the type SerialNumber
 */
public class SerialNumber
{
    private long _serialNumber;
    private boolean _isAvaible;

    /**
     * Constructor for objects of class SerialNumber
     */
    public SerialNumber(long serialNumber, boolean isAvaible)
    {
        _serialNumber = serialNumber;
        _isAvaible = isAvaible;
    }
    
    public long getSerialNumber()
    {
        return _serialNumber;
    }
    
    public boolean getIsAvaible()
    {
        return _isAvaible;
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
