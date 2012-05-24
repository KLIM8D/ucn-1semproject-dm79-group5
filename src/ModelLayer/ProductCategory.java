
/** 
* @version: 0.1
* Filename: ProductCategory.java
* Description: 
* @changes	
*/

public class ProductCategory
{

	private int _categoryId;
	private String _categoryName;

	// CategoryId {set; get;}
	public void setCategoryId(int value)
	{ _categoryId = value; }
	public int getCategoryId()
	{ return _categoryId; }

	// CategoryName {set; get;}
	public void setCategoryName(String value)
	{ _categoryName = value; }
	public String getCategoryName()
	{ return _categoryName; }
	
	public ProductCategory(int categoryId, String categoryName)
	{
		_categoryId = categoryId;
		_categoryName = categoryName;
	}
}