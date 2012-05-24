
/** 
* @version: 0.1
* Filename: ProductLocation.java
* Description: 
* @changes	
*/

public class ProductLocation
{

	private int _locationId;
	private String _locationName;
	private String _address;
	private String _city;
	private int _zipCode;

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
	
	public ProductLocation(int locationId, String locationName, String address, String city, int zipCode)
	{
		_locationId = locationId;
		_locationName = locationName;
		_address = address;
		_city = city;
		_zipCode = zipCode;
	}
}