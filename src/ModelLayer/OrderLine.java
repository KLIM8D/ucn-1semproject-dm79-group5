package ModelLayer;

public class OrderLine
{
    private long _id;
    private ProductPhysicalAvail _product;
    private long _quantity;

    public OrderLine(ProductPhysicalAvail product, long quantity)
    {
        OrderContainer orderContainer = OrderContainer.getInstance();
        _id = orderContainer.getNextOrderLineKey();
        _product = product;
        _quantity = quantity;
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
