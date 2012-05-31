package ModelLayer;

import java.util.ArrayList;
import java.util.HashMap;

public class OrderContainer
{
    private static OrderContainer _instance;

    public static OrderContainer getInstance()
    {
        return _instance != null ? _instance : (_instance = new OrderContainer());
    }

    private HashMap<Long, Order> _orderCollection;
    private long _lastKey;
    private long _lastOrderLineKey;

    // LastKey {get;}
    public long getLastKey()
    { return _lastKey; }

    public long getNextOrderLineKey()
    {
        return _lastOrderLineKey++;
    }

    private OrderContainer()
    {
        _lastKey = 0;
        _orderCollection = new HashMap<Long, Order>();
    }

    public boolean addOrder(Order order)
    {
        if(!_orderCollection.containsKey(order.getId()))
        {
            _orderCollection.put(order.getId(), order);
            _lastKey++;
            return true;
        }
        return false;
    }

    public boolean updateOrder(Order order)
    {
        long orderId = order.getId();
        if(_orderCollection.get(orderId) != null)
        {
            _orderCollection.put(orderId, order);
            return true;
        }
        return false;
    }

    public boolean removeOrder(long id)
    {
        if (_orderCollection.get(id) != null)
        {
            _orderCollection.remove(id);
            return true;
        }

        return false;
    }

    public Order getOrderById(long id)
    {
        return _orderCollection.get(id);
    }

    public Iterable<Order> getCustomerOrders(Customer customer)
    {
        ArrayList<Order> orders = new ArrayList<Order>();

        for (Order order : _orderCollection.values())
            if (order.getCustomer() == customer)
                orders.add(order);

        return orders;
    }

    public Iterable<Order> getSalesAssistantOrders(SalesAssistant salesAsst)
    {
        ArrayList<Order> orders = new ArrayList<Order>();

        for (Order order : _orderCollection.values())
            if (order.getSalesAsst() == salesAsst)
                orders.add(order);

        return orders;
    }

    public Iterable<Order> getAllOrders()
    {
        return _orderCollection.values();
    }
}
