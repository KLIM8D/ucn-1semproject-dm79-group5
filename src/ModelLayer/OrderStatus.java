package ModelLayer;

public class OrderStatus
{
    private long _id;
    private Order _order;
    private int _status;

    public OrderStatus(long id, Order order, int status)
    {
        _id = id;
        _order = order;
        _status = status;
    }

    public long getId()
    {
        return _id;
    }

    public Order getOrder()
    {
        return _order;
    }

    public void setOrder(Order order)
    {
        _order = order;
    }

    public int getStatus()
    {
        return _status;
    }

    public void setStatus(int status)
    {
        _status = status;
    }
}
