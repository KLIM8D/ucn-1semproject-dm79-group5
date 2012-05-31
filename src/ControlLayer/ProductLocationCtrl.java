package ControlLayer;

import ModelLayer.*;

/** 
* @version: 0.1
* Filename: ProductLocationCtrl.java
* Description: Controller class handling all operations with ProductLocations
* @changes	
*/

public class ProductLocationCtrl
{

	
	private ProductLocationContainer _locContainer;
    private ProductContainer _prodContainer;

	public ProductLocationCtrl()
	{
		_locContainer = ProductLocationContainer.getInstance();
        _prodContainer = ProductContainer.getInstance();
	}

	public boolean createProductLocation(String locationName, String address, String city, int zipCode)
	{
		ProductLocation loc = new ProductLocation(locationName, address, city, zipCode);
		return _locContainer.addLocation(loc);
	}

	public ProductLocation getLocation(int locId)
    {
    	return _locContainer.getLocation(locId);
    }

    public Iterable<ProductLocation> getAll()
    {
        return _locContainer.getAll();
    }

    public boolean removeLocation(int locId)
    {
    	return _locContainer.removeLocation(locId);
    }

    public boolean updateLocation(int locId, String locationName, String address, String city, int zipCode)
    {
        ProductLocation loc = getLocation(locId);
        loc.setLocationName(locationName);
        loc.setAddress(address);
        loc.setCity(city);
        loc.setZipCode(zipCode);
        return _locContainer.updateLocation(loc);
    }

    public boolean changeAvail(int locId, int avail, long itemNumber)
    {
        return _locContainer.changeAvail(locId, avail, itemNumber);
    }

    public boolean addProduct(int locId, long itemNumber, int quantity)
    {
        ProductLocation loc = getLocation(locId);
        Product prod = _prodContainer.getProduct(itemNumber);
        if(loc == null || prod == null)
            return false;
        ProductPhysicalAvail prodPhysicalAvail = new ProductPhysicalAvail(quantity, loc, prod);
        return loc.addProduct(prodPhysicalAvail);
    }

    public int getAvailOnLocation(int locId, long itemNumber)
    {
        ProductLocation loc = getLocation(locId);
        if(loc != null)
        {
            for(ProductPhysicalAvail prod : loc.getProductCollection().values())
            {
                if(prod.getProduct().getItemNumber() == itemNumber)
                    return prod.getQuantity();
            }
        }
        return 0;
    }
}
