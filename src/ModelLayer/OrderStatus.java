package ModelLayer;

public class OrderStatus
{
    private long _id;
    //private Order _order;
    private String _statusValue;

    public OrderStatus(long id, String statusValue)
    {
        _id = id;
        //_order = order;
        _statusValue = statusValue;
    }

    public long getId()
    {
        return _id;
    }

/*    public Order getOrder()
    {
        return _order;
    }

    public void setOrder(Order order)
    {
        _order = order;
    }*/

    public String getStatusValue()
    {
        return _statusValue;
    }

    public void setStatusValue(String statusValue)
    {
        _statusValue = statusValue;
    }
}
