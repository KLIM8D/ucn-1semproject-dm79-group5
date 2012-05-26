package ModelLayer;

import java.util.HashMap;

/** 
* @version: 0.1
* Filename: ProductLocationContainer.java
* Description: 
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

    public ProductLocationContainer()
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

    public boolean updateLocation(ProductLocation loc)
    {
    	int key = loc.getLocationId();
        ProductLocation value = _locationCollection.get(key);
    	if(value != null)
    	{
    		_locationCollection.put(key, value);
    		return true;
    	}
    	return false;
    }

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
