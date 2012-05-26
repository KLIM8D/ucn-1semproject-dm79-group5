package ModelLayer;

import java.util.HashMap;

/** 
* @author:  Morten Klim Sørensen - mail@kl1m.dk
* Created: 
* @version: 0.1
* Filename: ProductGroupContainer.java
* Description: 
* @changes	
*/

public class ProductGroupContainer
{

	private HashMap<Integer, ProductGroup> _prodGroupCollection;
	private static ProductGroupContainer _instance;
    private int _lastKey;

    // LastKey {get;}
    public int getLastKey()
    { return _lastKey; }

    public ProductGroupContainer()
	{
        _lastKey = 0;
		_prodGroupCollection = new HashMap<Integer, ProductGroup>();
	}

    public static ProductGroupContainer getInstance()
    {
        if(_instance == null)
        {
            _instance = new ProductGroupContainer();    
        }
        return _instance;
    }

    public boolean addProductGroup(ProductGroup prodGroup)
    {
    	int key = prodGroup.getProductGroupId();
    	if(!_prodGroupCollection.containsKey(key))
    	{
    		_prodGroupCollection.put(key, prodGroup);
    		_lastKey++;
    		return true;
    	}
    	return false;
    }

    public boolean updateProductGroup(ProductGroup prodGroup)
    {
    	int key = prodGroup.getProductGroupId();
    	if(_prodGroupCollection.containsKey(key))
    	{
    		_prodGroupCollection.put(key, prodGroup);
    		return true;
    	}
    	return false;
    }

    public boolean removeProductGroup(int prodGroupId)
    {
    	if(_prodGroupCollection.containsKey(prodGroupId))
    	{
    		_prodGroupCollection.remove(prodGroupId);
    		return true;
    	}
    	return false;
    }

    public ProductGroup getProductGroup(int prodGroupId)
    {
    	return _prodGroupCollection.get(prodGroupId);
    }
}