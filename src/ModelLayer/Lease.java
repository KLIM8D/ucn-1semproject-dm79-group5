package ModelLayer;
import java.util.Date;

/**
 * @version: 0.1
 * Filename: Lease.java
 * Description: Class which creates objects of the type Lease
 * @changes  
 */
public class Lease
{
    private Customer _customer;
    private SerialNumber _serialNumber;
    private Date _rentStartDate;
    private Date _rentEndDate;
    private int leaseId;
    
    /**
     * Constructor for objects of class Lease
     */
    public Lease(Customer customer, SerialNumber serialNumber, Date rentStartDate, Date rentEndDate)
    {
        _customer = customer;
        _serialNumber = serialNumber;
        _rentStartDate = rentStartDate;
        _rentEndDate = rentEndDate;
    }
    
    public Customer getCustomer()
    {
        return _customer;
    }
    
    public SerialNumber getSerialNumber()
    {
        return _serialNumber;
    }
    
    public Date getRentStartDate()
    {
        return _rentStartDate;
    }
    
    public Date getRentEndDate()
    {
        return _rentEndDate;
    }
    
    public void setSerialNumber(SerialNumber serialNumber)
    {
        _serialNumber = serialNumber;
    }
    
    public void setRentStartDate(Date rentStartDate)
    {
        _rentStartDate = rentStartDate;
    }
    
    public void setRentEndDate(Date rentEndDate)
    {
        _rentEndDate = rentEndDate;
    }
    
    public int getLeaseId()
    {
        return leaseId;
    }
}