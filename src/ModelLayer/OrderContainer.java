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

    private HashMap<Long, Order> _orders;

    private OrderContainer()
    {
        _orders = new HashMap<Long, Order>();
    }

    public void addOrder(Order order)
    {
        _orders.put(order.getId(), order);
    }

    public boolean updateOrder(long orderId, Order order)
    {
        // TODO: Pointless? We do in-memory updates, so this serves no purpose; there's nowhere to persist to.
        return _orders.get(order.getId()) != null;
    }

    public boolean removeOrder(Order order)
    {
        if (_orders.get(order.getId()) != null)
        {
            _orders.remove(order.getId());
            return true;
        }

        return false;
    }

    public Order getOrder(long id)
    {
        return _orders.get(id);
    }

    public Iterable<Order> findOrders(Customer customer)
    {
        ArrayList<Order> orders = new ArrayList<Order>();

        for (Order order : _orders.values())
            if (order.getCustomer() == customer)
                orders.add(order);

        return orders;
    }

    public Iterable<Order> getAllOrders()
    {
        return _orders.values();
    }
}
