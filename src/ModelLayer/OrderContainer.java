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

    private OrderContainer()
    {
        _orderCollection = new HashMap<Long, Order>();
    }

    public void addOrder(Order order)
    {
        _orderCollection.put(order.getId(), order);
    }

    public boolean updateOrder(Order order)
    {
        // TODO: Pointless? We do in-memory updates, so this serves no purpose; there's nowhere to persist to.
        return _orderCollection.get(order.getId()) != null;
    }

    public boolean removeOrder(Order order)
    {
        if (_orderCollection.get(order.getId()) != null)
        {
            _orderCollection.remove(order.getId());
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
