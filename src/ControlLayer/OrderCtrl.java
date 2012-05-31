package ControlLayer;

import ModelLayer.*;

/** 
* @version: 0.1
* Filename: OrderCtrl.java
* Description: Controller class handling all operations with Orders
* @changes	
*/

public class OrderCtrl
{

	private OrderContainer _orderContainer;
	private OrderStatusContainer _orderStatusContainer;
	private SalesAssistantContainer _saContainer;
	private ProductContainer _prodContainer;
	private ProductLocationContainer _locationContainer;
	private CustomerContainer _customerContainer;

	public OrderCtrl()
	{
		_orderContainer = OrderContainer.getInstance();
		_orderStatusContainer = OrderStatusContainer.getInstance();
		_saContainer = SalesAssistantContainer.getInstance();
		_prodContainer = ProductContainer.getInstance();
		_locationContainer = ProductLocationContainer.getInstance();
		_customerContainer = CustomerContainer.getInstance();
	}

	public boolean createOrder(int salesAsstId, long customerId, String salesNote, int discountType, long statusId)
	{
		try
		{
			SalesAssistant salesAsst = _saContainer.getSalesAs(salesAsstId);
			Customer customer = _customerContainer.getCustomer(customerId);
			OrderStatus status = _orderStatusContainer.getOrderStatus(statusId);
			if(salesAsst != null && customer != null && status != null)
			{
				Discount discount = customer.getDiscount(discountType);
				if(discount == null)
					return false;

				Order order = new Order(salesAsst, customer, salesNote, discount, status);
				return _orderContainer.addOrder(order);
			}
			else
				return false;
		}
		catch(Exception ex)
		{
			return false;
		}
	}

	public boolean updateOrder(int orderId, int salesAsstId, long customerId, String salesNote, int discountType, long statusId)
	{
		try
		{
			Order order = getOrder(orderId);
			if(order != null)
			{
				SalesAssistant salesAsst = _saContainer.getSalesAs(salesAsstId);
				Customer customer = _customerContainer.getCustomer(customerId);
				OrderStatus status = _orderStatusContainer.getOrderStatus(statusId);
				if(salesAsst != null && customer != null && status != null)
				{
					Discount discount = customer.getDiscount(discountType);
					if(discount == null)
						return false;

					order.setSalesAssistant(salesAsst);
					order.setCustomer(customer);
					order.setSalesNote(salesNote);
					order.setDiscount(discount);
					order.setStatus(status);

					return _orderContainer.updateOrder(order);
				}
				else
					return false;
			}
			else
				return false;
		}
		catch(Exception ex)
		{
			return false;
		}
	}

	public boolean removeOrder(long orderId)
	{
		return _orderContainer.removeOrder(orderId);
	}

	public Order getOrder(long id)
	{
		return _orderContainer.getOrderById(id);
	}

	public Iterable<Order> getCustomerOrders(long customerId)
	{
		Customer customer = _customerContainer.getCustomer(customerId);
		if(customer != null)
		{
			return _orderContainer.getCustomerOrders(customer);
		}
		return null;
	}

	public Iterable<Order> getSalesAssistantOrders(int salesAsstId)
    {
        SalesAssistant salesAsst = _saContainer.getSalesAs(salesAsstId);
        if(salesAsst != null)
		{
			return _orderContainer.getSalesAssistantOrders(salesAsst);
		}
		return null;
    }

    public Iterable<Order> getAllOrders()
    {
    	return _orderContainer.getAllOrders();
    }

	public boolean changeOrderStatus(long orderId, long statusId)
    {
        Order order = _orderContainer.getOrderById(orderId);
        OrderStatus status = _orderStatusContainer.getOrderStatus(statusId);
        if (order != null && status != null)
        {
            order.setStatus(status);
            return true;
        }

        return false;
    }

    public boolean addOrderLine(Order order, long itemNumber, long quantity)
    {
        for (ProductLocation loc : _locationContainer.getAll())
        {
            for (ProductPhysicalAvail ppa : loc.getProductCollection().values())
            {
                if (ppa.getProduct().getItemNumber() == itemNumber && ppa.getQuantity() >= quantity)
                {
                    order.getOrderLines().add(new OrderLine(_orderContainer.getNextOrderLineKey(), ppa, quantity));

                    return true;
                }
            }
        }

    	return false;
    }
}
