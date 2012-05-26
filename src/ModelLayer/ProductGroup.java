package ModelLayer;

import java.math.BigDecimal;
import java.util.ArrayList;
/** 
* @version: 0.1
* Filename: ProductGroup.java
* Description: 
* @changes	
*/

public class ProductGroup
{

	private int _productGroupId;
	private String _productGroupName;
	private BigDecimal _price;
	private ArrayList<ProductGroupItem> _items;

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
	public ArrayList<ProductGroupItem> getItems()
	{ return _items; }

	public ProductGroup(String productGroupName, BigDecimal price)
	{
		ProductGroupContainer pgContainer = ProductGroupContainer.getInstance();
		_productGroupId = pgContainer.getLastKey() + 1;
		_productGroupName = productGroupName;
		_price = price;
		_items = new ArrayList<ProductGroupItem>();
	}

	public void addItem(ProductGroupItem item)
	{
		_items.add(item);
	}
}