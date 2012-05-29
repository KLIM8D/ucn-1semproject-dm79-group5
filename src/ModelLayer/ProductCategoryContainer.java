package ModelLayer;

import java.util.HashMap;

/** 
* @version: 0.1
* Filename: ProductCategoryContainer.java
* Description: 
* @changes	
*/

public class ProductCategoryContainer
{

	private HashMap<Integer, ProductCategory> _categoryCollection;
    private static ProductCategoryContainer _instance;
    private int _lastKey;

    // LastKey {get;}
    public int getLastKey()
    { return _lastKey; }

    private ProductCategoryContainer()
	{
        _lastKey = 0;
		_categoryCollection = new HashMap<Integer, ProductCategory>();
	}

    public static ProductCategoryContainer getInstance()
    {
        if(_instance == null)
        {
            _instance = new ProductCategoryContainer();    
        }
        return _instance;
    }

    /**
    * Returns a collection of all product categories
    *
    * @return        HashMap<Integer, ProductCategory>
    *
    */
    public HashMap<Integer, ProductCategory> getAllCategories()
    {
    	return _categoryCollection;
    }

    /**
    * Get a product by an itemNumber
    *
    * @param categoryId        the categoryId of the category you want returned
    *
    */
    public ProductCategory getCategory(int categoryId)
    {
    	return _categoryCollection.get(categoryId);
    }

    /**
     * Add a category to the collection. Only added if the collection doesn't contain this categoryId already.
     *
     * @param prodCat     product category to be added
     * @return boolean    returns true if added / false if it's not
     *
     */
    public boolean addCategory(ProductCategory prodCat)
    {
    	int key = prodCat.getCategoryId();
        if(!_categoryCollection.containsKey(key))
        {
    		_categoryCollection.put(key, prodCat);
            _lastKey++;
    		return true;
    	}
    	return false;
    }

    /**
    * Remove a category from the collection
    *
    * @param categoryId        id of that category you want to remove
    * @return boolean          returns true if removed / false if it's not
    * 
    */
    public boolean removeCategory(int categoryId)
    {
    	ProductCategory value = _categoryCollection.get(categoryId);
    	if(value != null)
    	{
    		_categoryCollection.remove(categoryId);
    		return true;
    	}
    	return false;
    } 
}