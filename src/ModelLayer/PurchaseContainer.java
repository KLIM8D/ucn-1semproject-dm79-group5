package ModelLayer;

import java.util.HashMap;
import java.util.ArrayList;

/** 
* @version: 0.1
* Filename: PurchaseContainer.java
* Description: Class which contains all the Purchases in the system.
* @changes	
*/

public class PurchaseContainer
{

	private HashMap<Integer, Purchase> _purchaseCollection;	
	private static PurchaseContainer _instance;
    private int _lastKey;

    // LastKey {get;}
    public int getLastKey()
    { return _lastKey; }

    private PurchaseContainer()
	{
        _lastKey = 0;
		_purchaseCollection = new HashMap<Integer, Purchase>();
	}

    public static PurchaseContainer getInstance()
    {
        if(_instance == null)
        {
            _instance = new PurchaseContainer();    
        }
        return _instance;
    }

    /**
    * Add a purchase to the collection
    *
    * @param purchase        the purchase to be added
    * @return boolean        returns true if added / false if it's not
    *
    */
    public boolean addPurchase(Purchase purchase)
    {
    	int key = purchase.getPurchaseId();
    	if(!_purchaseCollection.containsKey(key))
    	{
    		_purchaseCollection.put(key, purchase);
    		_lastKey++;
    		return true;
    	}
    	return false;
    }

    /**
    * Remove a purchase from the collection
    *
    * @param purchaseId        the id of the purchase which should be removed
    * @return boolean          returns true if removed / false if it's not
    *
    */
    public boolean removePurchase(int purchaseId)
    {
    	if(_purchaseCollection.containsKey(purchaseId))
    	{
    		_purchaseCollection.remove(purchaseId);
    		return true;
    	}
    	return false;
    }


    public Purchase getPurchase(int purchaseId)
    {
    	return _purchaseCollection.get(purchaseId);
    }

    /**
    * Returns a list of all purchases on a specific itemNumber
    *
    * @param itemNumber        the itemNumber of the product you want to find all purchases for
    * @return ArrayList<Purchase>
    *
    */
    public ArrayList<Purchase> getPurchasesByItemNumber(long itemNumber)
    {
    	ArrayList<Purchase> returnList = new ArrayList<Purchase>();
    	for (Purchase purch : _purchaseCollection.values()) 
    	{
    		if(purch.getItemNumber() == itemNumber)
    			returnList.add(purch);
    	}
    	return returnList;
    }
}