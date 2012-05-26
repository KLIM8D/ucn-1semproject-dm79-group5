package ModelLayer;

/** 
* @version: 0.1
* Filename: Product.java
* Description: Class that creates objects of the type business
* @changes  
*/

public class Business
{
    private String _contactPerson;
    private long _cvrNo;

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