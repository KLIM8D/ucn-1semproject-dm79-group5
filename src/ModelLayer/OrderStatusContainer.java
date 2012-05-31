package ModelLayer;

import java.util.HashMap;

public class OrderStatusContainer
{
    private static OrderStatusContainer _instance;

    public static OrderStatusContainer getInstance()
    {
        return _instance != null ? _instance : (_instance = new OrderStatusContainer());
    }

    private HashMap<Long, OrderStatus> _orderStatusCollection;

    private OrderStatusContainer()
    {
        _orderStatusCollection = new HashMap<Long, OrderStatus>();
    }

    public void addOrderStatus(OrderStatus status)
    {
        _orderStatusCollection.put(status.getId(), status);
    }

    public boolean removeOrderStatus(OrderStatus status)
    {
        if (_orderStatusCollection.get(status.getId()) != null)
        {
            _orderStatusCollection.remove(status.getId());
            return true;
        }

        return false;
    }

    public OrderStatus getOrderStatus(long id)
    {
        return _orderStatusCollection.get(id);
    }

    public Iterable<OrderStatus> getAllOrderStatuses()
    {
        return _orderStatusCollection.values();
    }
}
