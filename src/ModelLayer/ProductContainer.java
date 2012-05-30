package ModelLayer;

import java.util.HashMap;

/** 
* @version: 0.1
* Filename: ProductContainer.java
* Description: Class which contains all the Products in the system
* @changes	
*/

public class ProductContainer
{

	private HashMap<Long, Product> _productCollection;
    private static ProductContainer _instance;

    private ProductContainer()
	{
		_productCollection = new HashMap<Long, Product>();
	}

    public static ProductContainer getInstance()
    {
        if(_instance == null)
        {
            _instance = new ProductContainer();    
        }
        return _instance;
    }

    /**
    * Returns a collection of all products
    *
    * @return        HashMap<Long, Product>
    *
    */
    public HashMap<Long, Product> getAllProducts()
    {
    	return _productCollection;
    }

    /**
    * Get a product by an itemNumber
    *
    * @param itemNumber        the itemNumber of that product you want returned
    *
    */
    public Product getProduct(long itemNumber)
    {
    	return _productCollection.get(itemNumber);
    }

    /**
    * Add a product to the collection. Only added if the collection doesn't contain this itemNumber already.
    *
    * @param prod        product to be added
    * @return boolean    returns true if added / false if it's not
    *
    */ 
    public boolean addProduct(Product prod)
    {
    	long key = prod.getItemNumber();
        if(!_productCollection.containsKey(key))
        {
    		_productCollection.put(key, prod);
    		return true;
    	}
    	return false;
    }

    /**
    * Update a product in the collection
    *
    * @param prod        product which should replace the current in the collection
    * @param itemNumber  the itemNumber of the product
    * @return boolean    returns true if updated / false if it's not
    *
    */
    public boolean updateProduct(Product prod)
    {
        long itemNumber = prod.getItemNumber();
    	if(_productCollection.get(itemNumber) != null)
    	{
    		_productCollection.put(itemNumber, prod);
    		return true;
    	}
    	return false;
    }

    /**
    * Remove a product from the collection
    *
    * @param itemNumber        itemNumber of that product you want to remove
    * @return boolean          returns true if removed / false if it's not
    * 
    */
    public boolean removeProduct(long itemNumber)
    {
    	Product value = _productCollection.get(itemNumber);
    	if(value != null)
    	{
    		_productCollection.remove(itemNumber);
    		return true;
    	}
    	return false;
    }

    /**
    * Find a product by it's itemName
    *
    * @param itemName        the itemName of the product you want to find
    * @return Product        returns the product if found; else null
    *
    */
    public Product findProduct(String itemName)
    {
    	for(Product prod : _productCollection.values())
        {
            String prodItemName = prod.getItemName().toLowerCase();
            if(prodItemName.equals(itemName.toLowerCase()))
                return prod;
        }

        return null;
    }
}
