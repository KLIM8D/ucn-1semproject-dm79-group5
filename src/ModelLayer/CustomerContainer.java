package ModelLayer;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * Class responsable for containing object of the type customer.
 */
public class CustomerContainer
{
    private HashMap<Integer, Customer> _customerCollection;
    private static CustomerContainer _instance;

    /**
     * Constructor for objects of class CustomerContainer
     */
    public CustomerContainer()
    {
        _customerCollection = new HashMap<Integer, Customer>();
    }
    
    public static CustomerContainer getInstance()
    {
        if(_instance == null)
        {
            _instance = new CustomerContainer();
        }
        return _instance;
    }
    
    public void addCustomer(Customer cus)
    {
        int key = cus.getCustomerId();
        _customerCollection.put(key, cus);
    }    
    
    public boolean updateCustomer(Customer cus)
    {
        int key = cus.getCustomerId();
        Customer customer = _customerCollection.get(key);
        if(customer != null)
        {
            _customerCollection.put(key,cus);
            return true;
        }
        return false;        
    }
    
    public boolean removeCustomer(int customerId)
    {
        Customer cus = _customerCollection.get(customerId);
        if(cus != null)
        {
            _customerCollection.remove(customerId);
            return true;
        }
        return false;        
    }
    
    public Customer getCustomer(int customerId)
    {
        return _customerCollection.get(customerId);
    }
    
    public HashMap getAllCustomers()
    {
        return _customerCollection;
    }
    
    public ArrayList getAllCustomersByBusiness(boolean isBusiness)
    {
        ArrayList<Customer> c = new ArrayList<Customer>();
        for(Customer cus : _customerCollection.values())
        {
            if(cus.getIsBusiness() == isBusiness)
            {
                int id = cus.getCustomerId();
                c.add(_customerCollection.get(id));
            }
        }
        return c;
    }
}