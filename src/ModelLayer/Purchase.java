package ModelLayer;

import java.util.Date;
import java.math.BigDecimal;

/** 
* @version: 0.1
* Filename: Purchase.java
* Description: 
* @changes	
*/

public class Purchase
{
	private int _purchaseId;
	private long _itemNumber;
	private int _quantity;
	private Date _deliveryDate;
	private BigDecimal _price;

	// PurchaseId {get;}
	public int getPurchaseId()
	{ return _purchaseId; }

	// ItemNumber {set; get;}
	public void setItemNumber(long value)
	{ _itemNumber = value; }
	public long getItemNumber()
	{ return _itemNumber; }

	// Quantity {set; get;}
	public void setQuantity(int value)
	{ _quantity = value; }
	public int getQuantity()
	{ return _quantity; }

	// DeliveryDate {set; get;}
	public void setDeliveryDate(Date value)
	{ _deliveryDate = value; }
	public Date getDeliveryDate()
	{ return _deliveryDate; }

	// Price {set; get;}
	public void setPrice(BigDecimal value)
	{ _price = value; }
	public BigDecimal getPrice()
	{ return _price; }
	
	public Purchase(long itemNumber, int quantity, Date deliveryDate, BigDecimal price)
	{
		PurchaseContainer purchConatiner = PurchaseContainer.getInstance();
		_purchaseId = purchConatiner.getLastKey() + 1;
		_itemNumber = itemNumber;
		_quantity = quantity;
		_deliveryDate = deliveryDate;
		_price = price;
	}
}