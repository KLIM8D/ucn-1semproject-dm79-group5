package ModelLayer;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * @version: 0.1
 * Filename: LeaseContainer.java
 * Description: Class which contains all the objects of the type Lease
 * @changes  
 */
public class LeaseContainer
{
    private HashMap<Integer, Lease> _leaseCollection;
    private static LeaseContainer _instance;
    private int _lastKey;
    
    public int getLastKey()
    { return _lastKey; }
    
    public LeaseContainer()
    {
        _lastKey = 0;
        _leaseCollection = new HashMap<Integer, Lease>();
    }
    
    public static LeaseContainer getInstance()
    {
        if(_instance == null)
        {
            _instance = new LeaseContainer();
        }
        return _instance;
    }
    
    public boolean addLease(Lease lease)
    {
        int key = lease.getLeaseId();
        if(!_leaseCollection.containsKey(key))
        {
            _leaseCollection.put(key,lease);
            _lastKey++;
            return true;
        }
        return false;
    }
    
    public boolean removeLease(int leaseId)
    {
        Lease value = _leaseCollection.get(leaseId);
        if(value != null)
        {
            _leaseCollection.remove(leaseId);
            return true;
        }
        return false;
    }
    
    public Lease getLease(int leaseId)
    {
        return _leaseCollection.get(leaseId);
    }
    
    public ArrayList<Lease> getCustomerLeases(long customerId)
    {
        ArrayList<Lease> leases = new ArrayList<Lease>();
        for(Lease lease : _leaseCollection.values())
        {
            if(lease.getCustomer().getCustomerId() == customerId)
                leases.add(lease);
        }
        return leases;
    }
    
    public HashMap<Integer, Lease> getAllLeases()
    {
        return _leaseCollection;
    }
    
    public ArrayList<Lease> getLeasesByItemNumber(long itemNumber)
    {
        ArrayList<Lease> leases = new ArrayList<Lease>();
        for(Lease lease : _leaseCollection.values())
        {
            if(lease.getSerialNumber().getItemNumber() == itemNumber)
                leases.add(lease);
        }
        return leases;
    }
}