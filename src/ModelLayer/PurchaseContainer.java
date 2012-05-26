package ModelLayer;

import java.util.HashMap;
import java.util.ArrayList;

/** 
* @version: 0.1
* Filename: PurchaseContainer.java
* Description: 
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

    public PurchaseContainer()
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