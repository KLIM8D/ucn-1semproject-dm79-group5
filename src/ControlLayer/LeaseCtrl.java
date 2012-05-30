package ControlLayer;
import ModelLayer.*;
import java.util.Date;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

/** 
* @version: 0.1
* Filename: LeaseCtrl.java
* Description: Controller class handling all operations with Leases
* @changes    
*/
public class LeaseCtrl
{
    private LeaseContainer _leaseContainer;
    private LeasingItemContainer _leasingItemContainer;
    private CustomerContainer _customerContainer;

    /**
     * Constructor for objects of class LeaseCtrl
     */
    public LeaseCtrl()
    {
        _leaseContainer = LeaseContainer.getInstance();
        _leasingItemContainer = LeasingItemContainer.getInstance();
        _customerContainer = CustomerContainer.getInstance();
    }
    
    public boolean createLease(Customer cus, SerialNumber sn)
    {
        Date rentStartDate = new Date(); // skal det være bruger input? 
        Date rentEndDate = new Date(); // samme spørgsmål.
        Lease lease = new Lease(cus, sn, rentStartDate, rentEndDate);
        return _leaseContainer.addLease(lease);
    }
    
    public Lease getLease(int leaseId)
    {
        return _leaseContainer.getLease(leaseId);
    }
    
    public HashMap getLease()
    {
        return _leaseContainer.getAllLeases();
    }
    
    public ArrayList<Lease> getLease(long itemNumber)
    {
        return _leaseContainer.getLeasesByItemNumber(itemNumber);
    }
    
    public boolean returnLease(int leaseId)
    {
        return _leaseContainer.removeLease(leaseId);
    }
    
    public boolean createLeaseItem(long itemNumber, String itemName, BigDecimal rentPrice, int maxAvaible)
    {
        LeasingItem leaseItem = new LeasingItem(itemNumber, itemName, rentPrice, maxAvaible);
        return _leasingItemContainer.addLeasingItem(leaseItem);
    }
    
    public LeasingItem getLeaseItem(long itemNumber)
    {
        return _leasingItemContainer.getLeasingItem(itemNumber);
    }
    
    public boolean updateLeasingItem()
    {
        // ikke sikker på hvordan denne metode skal laves
        return true;
    }
    
    public boolean deleteLeaseItem(long itemNumber)
    {
        return _leasingItemContainer.removeLeasingItem(itemNumber);
    }
    
    public ArrayList<Lease> expiredLeases()
    {
        //can be made after issue with Date has been resolved.
        return new ArrayList<Lease>();
    }
}