package ControlLayer;

import ModelLayer.*;
import java.math.BigDecimal;

/** 
* @version: 0.1
* Filename: ProductCtrl.java
* Description: Controller class handling all operations with Products
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
        if(category != null)
        {
            BigDecimal price = new BigDecimal(inPrice);
            Product prod = new Product(itemNumber, itemName, minInStock, maxInStock, price, category);
            return _prodContainer.addProduct(prod);
        }
        return false;
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
        
        Product prod = getProduct(itemNumber);
        ProductCategory category = getCategory(categoryId);
        if(prod != null && category != null)
        {
            BigDecimal price = new BigDecimal(inPrice);
            prod.setItemName(itemName);
            prod.setMinInStock(minInStock);
            prod.setMaxInStock(maxInStock);
            prod.setPrice(price);
            prod.setProductCategory(category);
            return _prodContainer.updateProduct(prod);
        }             
        return false;
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
        ProductGroup group = getProductGroup(prodGroupId);
        if(group != null) 
        {
            BigDecimal price = new BigDecimal(inPrice);
            group.setProductGroupName(productGroupName);
            group.setPrice(price);
            return _prodGroupContainer.updateProductGroup(group);
        }
        return false;
    }

    public boolean removeProductGroup(int prodGroupId)
    {
        return _prodGroupContainer.removeProductGroup(prodGroupId);
    }

    public Iterable<ProductGroup> getAllProductGroups()
    {
        return _prodGroupContainer.getAllProductGroups().values();
    }

    public boolean createProductGroupItem(int prodGroupId, long itemNumber, int quantity)
    {
        Product prod = getProduct(itemNumber);
        ProductGroup prodGroup = getProductGroup(prodGroupId);
        if(prod != null && prodGroup != null)
        {
            ProductGroupItem item = new ProductGroupItem(prod, quantity);
            prodGroup.addItem(item);
            return true;
        }
        return false;
    }
}
