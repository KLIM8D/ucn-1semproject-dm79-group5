package ModelLayer;

public class OrderLine
{
    private long _id;
    private Product _product;
    private long _quantity;

    public OrderLine(long id, Product product, long quantity)
    {
        _id = id;
        _product = product;
    }

    public long getId()
    {
        return _id;
    }

    public Product getProduct()
    {
        return _product;
    }

    public void setProduct(Product product)
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
