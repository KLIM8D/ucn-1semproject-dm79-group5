package ModelLayer;

import java.util.ArrayList;

/** 
* @version: 0.1
* Filename: Product.java
* Description: Class that creates objects of the type Customer
* @changes  
*/

public class Customer
{
    private long _customerId;
    private boolean _isBusiness;
    private Person _person;
    private Business _business;
    private ArrayList<Discount> _discounts;

    public Customer(Person person)
    {
        _isBusiness = false;
        _person = person;
        _discounts = new ArrayList<Discount>();
        _customerId = person.getPhoneNumber();
    }

    public Customer(Person person, String contactPerson, long cvrNo)
    {
        _isBusiness = true;
        _business = new Business(contactPerson, cvrNo);
        _person = person;
        _discounts = new ArrayList<Discount>();
        _customerId = person.getPhoneNumber();
    }

    // Business {set; get;}
    public void setBusiness(Business value)
    { _business = value; }
    public Business getBusiness()
    { return _business; }
    
    public long getCustomerId()
    {
        return _customerId;
    }
    
    public boolean getIsBusiness()
    {
        return _isBusiness;
    }
    
    public Person getPerson()
    {
        return _person;
    }
    
    public ArrayList<Discount> getDiscounts()
    {
        return _discounts;
    }
    
    public void setCustomerId(long customerId)
    {
        _customerId = customerId;
    }
    
    public void setIsBusiness(boolean isBusiness)
    {
        _isBusiness = isBusiness;
    }
    
    public void addDiscount(Discount disc)
    {
        _discounts.add(disc);
    }
    
    public void removeDiscount(int discType)
    {
        int index = 0;
        while(index < _discounts.size())
        {
            Discount disc = _discounts.get(index);
            if(disc.getDiscountType() == discType)
                _discounts.remove(index);

            index++;
        }
    }

    public Discount getDiscount(int discType)
    {
        int index = 0;
        boolean found = false;
        while(index < _discounts.size() && !found)
        {
            Discount disc = _discounts.get(index);
            if(disc.getDiscountType() == discType)
            {
                found = true;
                return disc;
            }

            index++;
        }
        return null;
    }
}