package ControlLayer;

import ModelLayer.*;

/** 
* @version: 0.1
* Filename: ProductLocationCtrl.java
* Description: 
* @changes	
*/

public class ProductLocationCtrl
{

	
	private ProductLocationContainer _locContainer;

	public ProductLocationCtrl()
	{
		_locContainer = ProductLocationContainer.getInstance();
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
}
