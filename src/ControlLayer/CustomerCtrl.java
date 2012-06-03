package ControlLayer;

import ModelLayer.Business;
import ModelLayer.Customer;
import ModelLayer.CustomerContainer;
import ModelLayer.Person;
import java.util.ArrayList;

public class CustomerCtrl
{
    private CustomerContainer _container;

    public CustomerCtrl()
    {
        _container = CustomerContainer.getInstance();
    }

    public void createCustomer(Person person)
    {
        _container.addCustomer(new Customer(person));
    }

    public void createCustomer(Person person, String contactPerson, long cvrNo)
    {
        _container.addCustomer(new Customer(person, contactPerson, cvrNo));
    }

    public boolean updateCustomer(long id, String contactPerson, long cvrNo)
    {
        Customer customer = _container.getCustomer(id);

        if (customer != null)
        {
            customer.setBusiness(contactPerson != null ? new Business(contactPerson, cvrNo) : null);

            return _container.updateCustomer(customer);
        }

        return false;
    }

    public boolean removeCustomer(long id)
    {
        return _container.removeCustomer(id);
    }

    public Customer getCustomer(long id)
    {
        return _container.getCustomer(id);
    }

    public Iterable<Customer> getAllCustomers()
    {
        return _container.getAllCustomers().values();
    }

    public ArrayList<Customer> getAllCustomersByBusiness(boolean isBusiness)
    {
        return _container.getAllCustomersByBusiness(isBusiness);
    }
}
