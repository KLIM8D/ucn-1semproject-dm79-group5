import java.math.BigDecimal;
/** 
* @version: 0.1
* Filename: Product.java
* Description: 
* @changes	
*/

public class Product
{

	private long _itemNumber;
	private String _itemName;
	private int _minInStock;
	private int _maxInStock;
	private BigDecimal _price;
	private ProductCategory _productCategory;
	
	// ItemNumber {get;}
	public long getItemNumber()
	{ return _itemNumber; }

	// ItemName {set; get;}
	public void setItemName(String value)
	{ _itemName = value; }
	public String getItemName()
	{ return _itemName; }

	// MinInStock {set; get;}
	public void setMinInStock(int value)
	{ _minInStock = value; }
	public int getMinInStock()
	{ return _minInStock; }

	// MaxInStock {set; get;}
	public void setMaxInStock(int value)
	{ _maxInStock = value; }
	public int getMaxInStock()
	{ return _maxInStock; }

	// Price {set; get;}
	public void setPrice(BigDecimal value)
	{ _price = value; }
	public BigDecimal getPrice()
	{ return _price; }

	// ProductCategory {set; get;}
	public void setProductCategory(ProductCategory value)
	{ _productCategory = value; }
	public ProductCategory getProductCategory()
	{ return _productCategory; }

	public Product(long itemNumber, String itemName, int minInStock, int maxInStock, BigDecimal price, ProductCategory productCategory)
	{
		_itemNumber = itemNumber;
		_itemName = itemName;
		_minInStock = minInStock;
		_maxInStock = maxInStock;
		_price = price;
		_productCategory = productCategory;
	}
}