package ModelLayer;

/** 
* @version: 0.1
* Filename: ProductGroupItem.java
* Description: Class that creates objects of the type ProductGroupItem
* @changes	
*/

public class ProductGroupItem
{

	private Product _product;
	private int _quantity;

	// Product {get;}
	public Product getProduct()
	{ return _product; }

	// Quantity {set; get;}
	public void setQuantity(int value)
	{ _quantity = value; }
	public int getQuantity()
	{ return _quantity; }
	
	public ProductGroupItem(Product product, int quantity)
	{
		_product = product;
		_quantity = quantity;
	}
}