package ModelLayer;

import java.util.HashMap;
import java.util.ArrayList;

/** 
* @version: 0.1
* Filename: Product.java
* Description: Class responsable for containing object of the type customer.
* @changes  
*/

public class CustomerContainer
{
    private HashMap<Long, Customer> _customerCollection;
    private static CustomerContainer _instance;

    private CustomerContainer()
    {
        _customerCollection = new HashMap<Long, Customer>();
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
        long key = cus.getCustomerId();
        _customerCollection.put(key, cus);
    }    
    
    public boolean updateCustomer(Customer cus)
    {
        long customerId = cus.getCustomerId();
        Customer customer = _customerCollection.get(customerId);
        if(customer != null)
        {
            _customerCollection.put(customerId,cus);
            return true;
        }
        return false;        
    }
    
    public boolean removeCustomer(long customerId)
    {
        Customer cus = _customerCollection.get(customerId);
        if(cus != null)
        {
            _customerCollection.remove(customerId);
            return true;
        }
        return false;        
    }
    
    public Customer getCustomer(long customerId)
    {
        return _customerCollection.get(customerId);
    }
    
    public HashMap<Long, Customer>  getAllCustomers()
    {
        return _customerCollection;
    }
    
    public ArrayList<Customer> getAllCustomersByBusiness(boolean isBusiness)
    {
        ArrayList<Customer> c = new ArrayList<Customer>();
        for(Customer cus : _customerCollection.values())
        {
            if(cus.getIsBusiness() == isBusiness)
                c.add(cus);
        }
        return c;
    }
}