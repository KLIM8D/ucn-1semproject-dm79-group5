package ModelLayer;

public class OrderLine
{
    private long _id;
    private ProductPhysicalAvail _product;
    private long _quantity;

    public OrderLine(long id, ProductPhysicalAvail product, long quantity)
    {
        _id = id;
        _product = product;
    }

    public long getId()
    {
        return _id;
    }

    public ProductPhysicalAvail getProduct()
    {
        return _product;
    }

    public void setProduct(ProductPhysicalAvail product)
    {
        _product = product;
    }

    public long getQuantity()
    {
        return _quantity;
    }

    public void setQuantity(long quantity)
    {
        _quantity = quantity;
    }
}
