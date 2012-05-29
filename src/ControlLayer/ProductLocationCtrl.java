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

	
	private ProductLocationContainer locContainer = ProductLocationContainer.getInstace();

	public ProductLocationCtrl()
	{}

	public boolean createProductLocation(String locationName, String address, String city, int zipCode)
	{
		ProductLocation loc = new ProductLocation(locationName, address, city, zipCode);
		return locContainer.addLocation(loc);
	}

	public ProductLocation getLocation(int locId)
    {
    	return locContainer.getLocation(locId);
    }

    public boolean removeLocation(int locId)
    {
    	return locContainer.removeLocation(locId);
    }

    public boolean updateLocation(int locId, String locationName, String address, String city, int zipCode)
    {
        ProductLocation loc = getLocation(locId);
        loc.setLocationName(locationName);
        loc.setAddress(address);
        loc.setCity(city);
        loc.setZipCode(zipCode);
        return locContainer.updateLocation(locId, loc);
    } 
}