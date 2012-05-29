package ModelLayer;

/** 
* @version: 0.1
* Filename: ProductCategory.java
* Description: Class that creates objects of the type ProductCategory
* @changes	
*/

public class ProductCategory
{

	private int _categoryId;
	private String _categoryName;

	// CategoryId {get;}
	public int getCategoryId()
	{ return _categoryId; }

	// CategoryName {set; get;}
	public void setCategoryName(String value)
	{ _categoryName = value; }
	public String getCategoryName()
	{ return _categoryName; }
	
	public ProductCategory(String categoryName)
	{
		ProductCategoryContainer prodCatContainer = ProductCategoryContainer.getInstance();
		_categoryId = prodCatContainer.getLastKey() + 1;
		_categoryName = categoryName;
	}
}
