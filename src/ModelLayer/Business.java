package ModelLayer;


/**
 * Class that creates objects of the type business
 */
public class Business
{
    private String _contactPerson;
    private long _cvrNo;

    /**
     * Constructor for objects of class Business
     */
    public Business(String contactPerson, long cvrNo)
    {
        _contactPerson = contactPerson;
        _cvrNo = cvrNo;
    }

    public String getContactPerson()
    {
        return _contactPerson;
    }
    
    public long getCvrNo()
    {
        return _cvrNo;
    }
    
    public void setContactPerson(String contactPerson)
    {
        _contactPerson = contactPerson;
    }
    
    public void setCvrNo(long cvrNo)
    {
        _cvrNo = cvrNo;
    }
}