package ModelLayer;

import java.util.HashMap;

/** 
* @version: 0.2
* Filename: ProductLocation.java
* Description: Class that creates objects of the type ProductLocation
* @changes	
*/

public class ProductLocation
{

	private int _locationId;
	private String _locationName;
	private String _address;
	private String _city;
	private int _zipCode;
	private HashMap<Long, ProductPhysicalAvail> _productCollection;

	// LocationId {get;}
	public int getLocationId()
	{ return _locationId; }

	// LocationName {set; get;}
	public void setLocationName(String value)
	{ _locationName = value; }
	public String getLocationName()
	{ return _locationName; }

	// Address {set; get;}
	public void setAddress(String value)
	{ _address = value; }
	public String getAddress()
	{ return _address; }

	// City {set; get;}
	public void setCity(String value)
	{ _city = value; }
	public String getCity()
	{ return _city; }

	// ZipCode {set; get;}
	public void setZipCode(int value)
	{ _zipCode = value; }
	public int getZipCode()
	{ return _zipCode; }

	// ProductCollection {get;}
	public HashMap<Long, ProductPhysicalAvail> getProductCollection()
	{ return _productCollection; }

	public ProductLocation(String locationName, String address, String city, int zipCode)
	{
		ProductLocationContainer locContainer = ProductLocationContainer.getInstance();
		_locationId = locContainer.getLastKey() + 1;
		_locationName = locationName;
		_address = address;
		_city = city;
		_zipCode = zipCode;
		_productCollection = new HashMap<Long, ProductPhysicalAvail>();
	}

	/**
	* Add a physical product to the location
	*
	* @param prod		the product to be added
	*
	*/
	public boolean addProduct(ProductPhysicalAvail prod)
	{
		long key = prod.getProduct().getItemNumber();
        if(!_productCollection.containsKey(key))
        {
    		_productCollection.put(key, prod);
    		return true;
    	}
    	return false;
	}

	/**
	* Change the physical availble of the specified product on this location
	*
	* @param itemNumber		the itemNumber of the product
	* @param quantity 		the remaining quantity of this product
	*
	*/

	public boolean changeAvail(long itemNumber, int quantity)
	{
		if(_productCollection.containsKey(itemNumber))
		{
			ProductPhysicalAvail pAvail = _productCollection.get(itemNumber);
			pAvail.setQuantity(quantity);
			return true;
		}
		return false;
	}
}
