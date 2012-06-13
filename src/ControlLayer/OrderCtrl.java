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
	@SuppressWarnings("unused")
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

    public long createOrder(int salesAsstId, long customerId, String salesNote, long statusId)
    {
        try
        {
            SalesAssistant salesAsst = _saContainer.getSalesAs(salesAsstId);
            Customer customer = _customerContainer.getCustomer(customerId);
            OrderStatus status = _orderStatusContainer.getOrderStatus(statusId);
            if(salesAsst != null && customer != null && status != null)
            {
                Order order = new Order(salesAsst, customer, salesNote, null, status);
                boolean success = _orderContainer.addOrder(order);
                if(success)
                    return order.getId();
                else
                    return 0;
            }
            else
                return 0;
        }
        catch(Exception ex)
        {
            return 0;
        }
    }

	public long createOrder(int salesAsstId, long customerId, String salesNote, int discountType, long statusId)
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
					return 0;

				Order order = new Order(salesAsst, customer, salesNote, discount, status);
				boolean success = _orderContainer.addOrder(order);
                if(success)
                    return order.getId();
                else
                    return 0;
			}
			else
				return 0;
		}
		catch(Exception ex)
		{
			return 0;
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

    public boolean addOrderLine(long orderId, long itemNumber, long quantity)
    {
    	Order order = _orderContainer.getOrderById(orderId);
    	if (order != null) 
    	{
    		ProductPhysicalAvail bestFound = null;
        	for (ProductLocation loc : _locationContainer.getAll())
        	{
            	for (ProductPhysicalAvail ppa : loc.getProductCollection().values())
            	{
                	if (ppa.getProduct().getItemNumber() == itemNumber && (bestFound == null || ppa.getQuantity() > bestFound.getQuantity()))
                    	bestFound = ppa;
            	}
        	}

        	if(bestFound != null)
        	{	
        		order.getOrderLines().add(new OrderLine(bestFound, quantity));
        		return true;
        	}
        }

    	return false;
    }

    public void createOrderStatus(long statusId, String statusValue)
    {
    	OrderStatus status = new OrderStatus(statusId, statusValue);
    	_orderStatusContainer.addOrderStatus(status);
    }

    public Iterable<OrderStatus> getAllOrderStatuses()
    {
        return _orderStatusContainer.getAllOrderStatuses();
    } 
}
