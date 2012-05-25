package ModelLayer;

import java.util.HashMap;
/** 
* @version: 0.1
* Filename: ProductContainer.java
* Description: 
* @changes	
*/

public class ProductContainer
{

	
	private HashMap<Long, Product> _productCollection;
    private static ProductContainer _instance;

    public ProductContainer()
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

    public HashMap<Long, Product> getAllProducts()
    {
    	return _productCollection;
    }

    public Product getProduct(long itemNumber)
    {
    	return _productCollection.get(itemNumber);
    } 

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

    public boolean updateProduct(Product prod)
    {
    	long key = prod.getItemNumber();
    	Product value = _productCollection.get(key);
    	if(value != null)
    	{
    		_productCollection.put(key, prod);
    		return true;
    	}
    	return false;
    }

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
