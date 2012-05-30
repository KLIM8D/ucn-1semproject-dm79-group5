package ModelLayer;

public class OrderStatus
{
    private long _id;
    private String _statusValue;

    public OrderStatus(long id, String statusValue)
    {
        _id = id;
        _statusValue = statusValue;
    }

    public long getId()
    {
        return _id;
    }

    public String getStatusValue()
    {
        return _statusValue;
    }

    public void setStatusValue(String statusValue)
    {
        _statusValue = statusValue;
    }
}
