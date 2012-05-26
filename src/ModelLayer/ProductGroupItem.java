package ModelLayer;

/** 
* @version: 0.1
* Filename: ProductGroupItem.java
* Description: Class that creates objects of the type ProductGroupItem
* @changes	
*/

public class ProductGroupItem
{

	private long _itemNumber;
	private Product _product;
	private int _quantity;
	
	public ProductGroupItem(long itemNumber, Product product, int quantity)
	{
		_itemNumber = itemNumber;
		_product = product;
		_quantity = quantity;
	}
}