package ModelLayer;

/** 
* @version: 0.1
* Filename: ProductPhysicalAvail.java
* Description: 
* @changes	
*/

public class ProductPhysicalAvail
{

	private int _quantity;
	private ProductLocation _location;
	private Product _product;

	// Quantity {set; get;}
	public void setQuantity(int value)
	{ _quantity = value; }
	public int getQuantity()
	{ return _quantity; }

	// Location {get;}
	public ProductLocation getLocation()
	{ return _location; }

	// Product {get;}
	public Product getProduct()
	{ return _product; }
	
	public ProductPhysicalAvail(int quantity, ProductLocation location, Product product)
	{
		_quantity = quantity;
		_location = location;
		_product = product;
	}
}
