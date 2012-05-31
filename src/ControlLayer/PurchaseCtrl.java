package ControlLayer;

import ModelLayer.*;
import java.math.BigDecimal;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/** 
* @version: 0.1
* Filename: PurchaseCtrl.java
* Description: Controller class handling all operations with Purchases
* @changes	
*/

public class PurchaseCtrl
{

	private PurchaseContainer _purchaseContainer;
	
	public PurchaseCtrl()
	{
		_purchaseContainer = PurchaseContainer.getInstance();
	}

	public boolean createPurchase(long itemNumber, int quantity, String inDeliveryDate, String inPrice)
	{
		BigDecimal price = new BigDecimal(inPrice);
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date deliveryDate = null;
        try 
        {
        	deliveryDate = formatter.parse(inDeliveryDate);
        }
        catch (Exception e) {}

        Purchase purchase = new Purchase(itemNumber, quantity, deliveryDate, price);
        return _purchaseContainer.addPurchase(purchase);
	}

	public boolean removePurchase(int purchaseId)
	{
		return _purchaseContainer.removePurchase(purchaseId);
	}

	public Purchase getPurchase(int purchaseId)
	{
		return _purchaseContainer.getPurchase(purchaseId);
	}

	public ArrayList<Purchase> getPurchasesByItemNumber(long itemNumber)
	{
		return _purchaseContainer.getPurchasesByItemNumber(itemNumber);
	}
}