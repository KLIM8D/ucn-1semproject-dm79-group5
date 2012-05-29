package ModelLayer;

import java.util.HashMap;

/** 
* @version: 0.2
* Filename: ProductLocationContainer.java
* Description: Class which contains all the ProductLocation in the system
* @changes	
*/

public class ProductLocationContainer
{

	private HashMap<Integer, ProductLocation> _locationCollection;
    private static ProductLocationContainer _instance;
    private int _lastKey;

    // LastKey {get;}
    public int getLastKey()
    { return _lastKey; }

    private ProductLocationContainer()
	{
        _lastKey = 0;
		_locationCollection = new HashMap<Integer, ProductLocation>();
	}

    public static ProductLocationContainer getInstance()
    {
        if(_instance == null)
        {
            _instance = new ProductLocationContainer();    
        }
        return _instance;
    }

    /**
    * Add a location to the collection
    *
    * @param loc         the location to be added
    * @return boolean    returns true if added / false if it's not
    *
    */
    public boolean addLocation(ProductLocation loc)
    {
    	int key = loc.getLocationId();
        if(!_locationCollection.containsKey(key))
        {
    		_locationCollection.put(key, loc);
            _lastKey++;
    		return true;
    	}
    	return false;
    }

    /**
    * Update a location
    *
    * @param loc        object which should replace the current, if contained in the collection.
    * @param locId      the id of the product location
    * @return boolean   returns true if updated / false if it's not
    *
    */
    public boolean updateLocation(ProductLocation loc)
    {
        int locId = loc.getLocationId();
        ProductLocation value = _locationCollection.get(locId);
    	if(value != null)
    	{
    		_locationCollection.put(locId, value);
    		return true;
    	}
    	return false;
    }

    /**
    * Remove a location from the collection
    *
    * @param locId        the id of the collection which should be removed
    * @return boolean     returns true if removed / false if it's not
    *
    */
    public boolean removeLocation(int locId)
    {
    	ProductLocation value = _locationCollection.get(locId);
    	if(value != null)
    	{
    		_locationCollection.remove(locId);
    		return true;
    	}
    	return false;
    }

    public ProductLocation getLocation(int locId)
    {
    	return _locationCollection.get(locId);
    }

    /**
    * Change the physical availble of a Product on a specific ProductLocation
    *
    * @param locId        the id product location
    * @param avail        the remaining quantity of this product on the location
    * @param itemNumber   the itemNumber of the product
    *
    */
    public boolean changeAvail(int locId, int avail, long itemNumber)
    {
        if(_locationCollection.containsKey(locId))
        {
            ProductLocation prodLoc = _locationCollection.get(locId);
            return prodLoc.changeAvail(itemNumber, avail);
        }
        return false;
    }
}
