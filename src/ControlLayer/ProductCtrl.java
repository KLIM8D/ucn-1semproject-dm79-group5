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
    private ProductCategoryContainer _prodCatContainer;
    private ProductContainer _prodContainer;
    private ProductGroupContainer _prodGroupContainer;

    public ProductCtrl()
    {
        _prodCatContainer = ProductCategoryContainer.getInstance();
        _prodContainer = ProductContainer.getInstance();
        _prodGroupContainer = ProductGroupContainer.getInstance();
    }

    public boolean createProduct(long itemNumber, String itemName, int minInStock, int maxInStock, String inPrice, int categoryId)
    {
        ProductCategory category = getCategory(categoryId);
        BigDecimal price = new BigDecimal(inPrice);
        Product prod = new Product(itemNumber, itemName, minInStock, maxInStock, price, category);
        return _prodContainer.addProduct(prod);
    }

    public Product getProduct(long itemNumber)
    {
        return _prodContainer.getProduct(itemNumber);
    }

    public Iterable<Product> getAllProducts()
    {
        return _prodContainer.getAllProducts().values();
    }

    public boolean updateProduct(long itemNumber, String itemName, int minInStock, int maxInStock, String inPrice, int categoryId)
    {
        ProductCategory category = getCategory(categoryId);
        BigDecimal price = new BigDecimal(inPrice);
        Product prod = getProduct(itemNumber);
        prod.setItemName(itemName);
        prod.setMinInStock(minInStock);
        prod.setMaxInStock(maxInStock);
        prod.setPrice(price);
        prod.setProductCategory(category);             
        return _prodContainer.updateProduct(prod);
    }

    public boolean removeProduct(long itemNumber)
    {
        return _prodContainer.removeProduct(itemNumber);
    }

    public Product findProduct(String itemName)
    {
        return _prodContainer.findProduct(itemName);
    }

    public boolean createProductCategory(String categoryName)
    {
        ProductCategory prodCat = new ProductCategory(categoryName);
        return _prodCatContainer.addCategory(prodCat);
    }

    public ProductCategory getCategory(int categoryId)
    {
        return _prodCatContainer.getCategory(categoryId);
    }

    public Iterable<ProductCategory> getAllCategories()
    {
        return _prodCatContainer.getAllCategories().values();
    }

    public boolean removeCategory(int categoryId)
    {
        return _prodCatContainer.removeCategory(categoryId);
    }

    public boolean createProductGroup(String productGroupName, String inPrice)
    {
        BigDecimal price = new BigDecimal(inPrice);
        ProductGroup group = new ProductGroup(productGroupName, price);
        return _prodGroupContainer.addProductGroup(group);
    }

    public ProductGroup getProductGroup(int prodGroupId)
    {
        return _prodGroupContainer.getProductGroup(prodGroupId);
    }

    public boolean updateProductGroup(int prodGroupId, String productGroupName, String inPrice)
    {
        BigDecimal price = new BigDecimal(inPrice);
        ProductGroup group = getProductGroup(prodGroupId);
        group.setProductGroupName(productGroupName);
        group.setPrice(price);
        return _prodGroupContainer.updateProductGroup(group);
    }

    public boolean removeProductGroup(int prodGroupId)
    {
        return _prodGroupContainer.removeProductGroup(prodGroupId);
    }

    public Iterable<ProductGroup> getAllProductGroups()
    {
        return _prodGroupContainer.getAllProductGroups().values();
    }

    public void createProductGroupItem(int prodGroupId, long itemNumber, int quantity)
    {
        Product prod = getProduct(itemNumber);
        ProductGroupItem item = new ProductGroupItem(itemNumber, prod, quantity);
        ProductGroup prodGroup = getProductGroup(prodGroupId);
        prodGroup.addItem(item);
    }
}