package ModelLayer;

import java.math.BigDecimal;
import java.util.HashSet;

/** 
* @version: 0.1
* Filename: ProductGroup.java
* Description: Class that creates objects of the type ProductGroup
* @changes	
*/

public class ProductGroup
{

	private int _productGroupId;
	private String _productGroupName;
	private BigDecimal _price;
	private HashSet<ProductGroupItem> _items;

	// ProductGroupId {get;}
	public int getProductGroupId()
	{ return _productGroupId; }

	// ProductGroupName {set; get;}
	public void setProductGroupName(String value)
	{ _productGroupName = value; }
	public String getProductGroupName()
	{ return _productGroupName; }

	// Price {set; get;}
	public void setPrice(BigDecimal value)
	{ _price = value; }
	public BigDecimal getPrice()
	{ return _price; }

	// Items {get;}
	public HashSet<ProductGroupItem> getItems()
	{ return _items; }

	public ProductGroup(String productGroupName, BigDecimal price)
	{
		ProductGroupContainer pgContainer = ProductGroupContainer.getInstance();
		_productGroupId = pgContainer.getLastKey() + 1;
		_productGroupName = productGroupName;
		_price = price;
		_items = new HashSet<ProductGroupItem>();
	}

	/**
	* Add an item to the ProductGroup.
	*
	* @param item		the item to be added. Containing the Product and quantity in this ProductGroup
	*
	*/
	public boolean addItem(ProductGroupItem item)
	{
		return _items.add(item);
	}
}