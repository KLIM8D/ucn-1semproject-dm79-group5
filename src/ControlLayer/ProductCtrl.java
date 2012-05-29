package ControlLayer;

import ModelLayer.*;
import java.math.BigDecimal;

/** 
* @version: 0.1
* Filename: ProductCtrl.java
* Description: 
* @changes    
*/

public class ProductCtrl
{
    private ProductCategoryContainer prodCatContainer = ProductCategoryContainer.getInstance();
    private ProductContainer prodContainer = ProductContainer.getInstance();
    private ProductGroupContainer prodGroupContainer = ProductGroupContainer.getInstance();

    public ProductCtrl()
    {}

    public boolean createProduct(long itemNumber, String itemName, int minInStock, int maxInStock, String inPrice, int categoryId)
    {
        ProductCategory category = getCategory(categoryId);
        BigDecimal price = new BigDecimal(inPrice);
        Product prod = new Product(itemNumber, itemName, minInStock, maxInStock, price, category);
        return prodContainer.addProduct(prod);
    }

    public Product getProduct(long itemNumber)
    {
        return prodContainer.getProduct(itemNumber);
    }

    public Iterable<Product> getAllProducts()
    {
        return prodContainer.getAllProducts().values();
    }

    public boolean updateProduct(long itemNumber, String itemName, int minInStock, int maxInStock, String inPrice, int categoryId)
    {
        ProductCategory category = getCategory(categoryId);
        BigDecimal price = new BigDecimal(inPrice);        
        Product prod = new Product(itemNumber, itemName,  minInStock, maxInStock, price, category);
        return prodContainer.updateProduct(itemNumber, prod);
    }

    public boolean removeProduct(long itemNumber)
    {
        return prodContainer.removeProduct(itemNumber);
    }

    public Product findProduct(String itemName)
    {
        return prodContainer.findProduct(itemName);
    }

    public boolean createProductCategory(String categoryName)
    {
        ProductCategory prodCat = new ProductCategory(categoryName);
        return prodCatContainer.addCategory(prodCat);
    }

    public ProductCategory getCategory(int categoryId)
    {
        return prodCatContainer.getCategory(categoryId);
    }

    public Iterable<ProductCategory> getAllCategories()
    {
        return prodCatContainer.getAllCategories().values();
    }

    public boolean removeCategory(int categoryId)
    {
        return prodCatContainer.removeCategory(categoryId);
    }

    public boolean createProductGroup(String productGroupName, String inPrice)
    {
        BigDecimal price = new BigDecimal(inPrice);
        ProductGroup group = new ProductGroup(productGroupName, price);
        return prodGroupContainer.addProductGroup(group);
    }

    public ProductGroup getProductGroup(int prodGroupId)
    {
        return prodGroupContainer.getProductGroup(prodGroupId);
    }

    public boolean updateProductGroup(int prodGroupId, String productGroupName, String inPrice)
    {
        BigDecimal price = new BigDecimal(inPrice);
        ProductGroup group = new ProductGroup(productGroupName, price);
        return prodGroupContainer.updateProductGroup(prodGroupId, group);
    }

    public boolean removeProductGroup(int prodGroupId)
    {
        return prodGroupContainer.removeProductGroup(prodGroupId);
    }

    public Iterable<ProductGroup> getAllProductGroups()
    {
        return prodGroupContainer.getAllProductGroups().values();
    }

    public void createProductGroupItem(int prodGroupId, long itemNumber, int quantity)
    {
        Product prod = getProduct(itemNumber);
        ProductGroupItem item = new ProductGroupItem(itemNumber, prod, quantity);
        ProductGroup prodGroup = getProductGroup(prodGroupId);
        prodGroup.addItem(item);
    }
}