package ModelLayer;

/** 
* @version: 0.1
* Filename: Product.java
* Description: Class in charge of creating objects of the type person
* @changes  
*/

public class Person
{
    private long _personId;
    private String _name;
    private String _address;
    private String _city;
    private int _zipCode;
    private long _phoneNumber;

    public Person(long personId, String name, String address, String city, int zipCode, long phoneNumber)
    {
        _personId = personId;
        _name = name;
        _address = address;
        _city = city;
        _zipCode = zipCode;
        _phoneNumber = phoneNumber;
    }

    public long getPersonId()
    {
        return _personId;
    }
    
    public String getName()
    {
        return _name;
    }
    
    public String getAddress()
    {
        return _address;
    }
    
    public String getCity()
    {
        return _city;
    }
    
    public int getZipCode()
    {
        return _zipCode;
    }
    
    public long getPhoneNumber()
    {
        return _phoneNumber;
    }
    
    public void setPersonId(long personId)
    {
        _personId = personId;
    }
    
    public void setName(String name)
    {
        _name = name;
    }
    
    public void setAddress(String address)
    {
        _address = address;
    }
    
    public void setCity(String city)
    {
        _city = city;
    }
    
    public void setZipCode(int zipCode)
    {
        _zipCode = zipCode;
    }
    
    public void setPhoneNumber(long phoneNumber)
    {
        _phoneNumber = phoneNumber;
    }
}