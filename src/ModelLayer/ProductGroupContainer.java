package ModelLayer;

import java.util.HashMap;

/** 
* @version: 0.1
* Filename: ProductGroupContainer.java
* Description: Class containing all the ProductGroups in the system.
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

    private ProductGroupContainer()
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

    /**
    * Add a product group to the collection.
    *
    * @param prodGroup        the product group to be added
    * @return boolean         returns true if added / false if it's not
    *
    */
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

    /**
    * Update a product group.
    *
    * @param prodGroup        object which replaces the current
    * @return boolean         returns true if updated / false if it's not
    *
    */
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

    /**
    * Remove a product group from the collection
    *
    * @param prodGroupId        the id of the productGroup which should be removed
    * @return boolean           returns true if removed / false if it's not
    *
    */
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