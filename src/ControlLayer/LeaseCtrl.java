package ControlLayer;
import ModelLayer.*;
import java.util.Date;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;

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

    public boolean createLease(long customerId, long itemNumber, int days)
    {
        Calendar calendar = Calendar.getInstance();
        Date rentStartDate = calendar.getTime();
        calendar.add(Calendar.DATE, days);
        Date rentEndDate = calendar.getTime();
        
        Customer cus = _customerContainer.getCustomer(customerId);

        LeasingItem item = _leasingItemContainer.getLeasingItem(itemNumber);
        if(item == null)
        {
            return false;
        }
        SerialNumber sn = item.getAvaible();
        if(sn != null && cus != null)
        {
            Lease lease = new Lease(cus, sn, rentStartDate, rentEndDate);
            return _leaseContainer.addLease(lease);
        }
        return false;
    }
    
    public Lease getLease(int leaseId)
    {
        return _leaseContainer.getLease(leaseId);
    }

    public ArrayList<Lease> getLease()
    {
        ArrayList<Lease> leases = new ArrayList<Lease>();
        for(Lease lease : _leaseContainer.getAllLeases().values())
        {
            leases.add(lease);
        }
        return leases;
    }
    
    public ArrayList<Lease> getLease(long itemNumber)
    {
        return _leaseContainer.getLeasesByItemNumber(itemNumber);
    }
    
    public boolean removeLease(int leaseId)
    {
        _leaseContainer.getLease(leaseId).getSerialNumber().updateAvail(true);
        return _leaseContainer.removeLease(leaseId);
    }
    
    public boolean createLeaseItem(long itemNumber, String itemName, String rentPrice, int maxAvaible)
    {
        BigDecimal price = new BigDecimal(rentPrice);
        LeasingItem leaseItem = new LeasingItem(itemNumber, itemName, price, maxAvaible);
        return _leasingItemContainer.addLeasingItem(leaseItem);
    }
    
    public LeasingItem getLeaseItem(long itemNumber)
    {
        return _leasingItemContainer.getLeasingItem(itemNumber);
    }
    
    public boolean updateLeasingItem(long itemNumber, String itemName, String rentPrice, int maxAvaible)
    {
        LeasingItem item = _leasingItemContainer.getLeasingItem(itemNumber);
        if(item != null)
        {
            BigDecimal price = new BigDecimal(rentPrice);
            item.setItemName(itemName);
            item.setRentPrice(price);
            item.setMaxAvaible(maxAvaible);
            return _leasingItemContainer.updateLeasingItem(item);
        }
        return false;
    }
    
    public boolean deleteLeaseItem(long itemNumber)
    {
        return _leasingItemContainer.removeLeasingItem(itemNumber);
    }
    
    public ArrayList<Lease> expiredLeases()
    {
        ArrayList<Lease> leases = getLease();
        ArrayList<Lease> expiredLeases = new ArrayList<Lease>();
        int index = 0;
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        while(index < leases.size())
        {
            if(leases.get(index).getRentEndDate().compareTo(now) > 0)
            {
                expiredLeases.add(leases.get(index));
            }
            index++;
        }
        return expiredLeases;
    }
}