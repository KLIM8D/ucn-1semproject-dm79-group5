package ModelLayer;
import java.util.ArrayList;

/**
 * Class that creates ojbects of the type Customer
 */
public class Customer
{
    private int _customerId;
    private boolean _isBusiness;
    private Person _person;
    private ArrayList<Discount> _discounts;

    /**
     * Constructer of the class
     */
    public Customer(boolean isBusiness, Person person)
    {
        _isBusiness = isBusiness;
        _person = person;
        _discounts = new ArrayList<Discount>();
        _customerId = (int)person.getPhoneNumber();
    }
    
    public int getCustomerId()
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
    
    public void setCustomerId(int customerId)
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
    
    
}