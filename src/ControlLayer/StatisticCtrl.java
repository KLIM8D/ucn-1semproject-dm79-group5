package ControlLayer;

import ModelLayer.*;
import java.util.ArrayList;

/** 
* @version: 0.1
* Filename: StatisticCtrl.java
* Description: 
* @changes	
*/

public class StatisticCtrl
{

	private CustomerContainer _customerContainer;
	private OrderContainer _orderContainer;
	private ProductContainer _productContainer;
	private SalesAssistantContainer _saContainer;
	
	public StatisticCtrl()
	{
		_customerContainer = CustomerContainer.getInstance();
		_orderContainer = OrderContainer.getInstance();
		_productContainer = ProductContainer.getInstance();
		_saContainer = SalesAssistantContainer.getInstance();
	}

	public long[] generateStatsFromProduct(long itemNumber)
	{
		long[] returnVal = new long[2];
		Product prod = _productContainer.getProduct(itemNumber);
		if(prod != null)
		{
			returnVal[0] = countProductOrders(prod);
			returnVal[1] = getProductOrderValues(prod);
		}
		return returnVal;
	}

	private long getProductOrderValues(Product prod)
	{
		long value = 0;
		for(Order order : _orderContainer.getAllOrders())
		{
			for (OrderLine line : order.getOrderLines()) 
			{
				if(line.getProduct().getProduct() == prod)
					value += (line.getProduct().getProduct().getPrice().longValue() * line.getQuantity());
			}
		}
		return value;
	}

	private long countProductOrders(Product prod)
	{
		long count = 0;
		for(Order order : _orderContainer.getAllOrders())
		{
			for (OrderLine line : order.getOrderLines()) 
			{
				if(line.getProduct().getProduct() == prod)
					count++;
			}
		}
		return count;
	}

	public long[] generateStatsFromCustomer(long customerId)
	{
		long[] returnVal = new long[2];
		Customer cus = _customerContainer.getCustomer(customerId);
		if(cus != null)
		{
			long ordersCount = countCustomerOrders(cus);
			long orderValues = getCustomerOrderValues(cus);
			returnVal[0] = ordersCount;
			returnVal[1] = orderValues;
		}
		return returnVal;
	}

	private long getCustomerOrderValues(Customer cus)
	{
		long value = 0;
		ArrayList<Order> orders = new ArrayList<Order>();
		for(Order order : _orderContainer.getAllOrders())
		{
			if(order.getCustomer() == cus)
				orders.add(order);
		}

		for (Order order : orders) 
		{
			for (OrderLine line : order.getOrderLines()) 
			{
				value += (line.getProduct().getProduct().getPrice().longValue() * line.getQuantity());
			}
		}
		return value;
	}

	private long countCustomerOrders(Customer cus)
	{
		long count = 0;
		for(Order order : _orderContainer.getAllOrders())
		{
			if(order.getCustomer() == cus)
				count++;
		}
		return count;	
	}

	public long[] generateStatsFromSalesAsst(int salesAsstId)
	{
		long[] returnVal = new long[2];
		SalesAssistant sa = _saContainer.getSalesAs(salesAsstId);
		if(sa != null)
		{
			long ordersCount = countSalesAsstOrders(sa);
			long orderValues = getSalesAsstOrderValues(sa);
			returnVal[0] = ordersCount;
			returnVal[1] = orderValues;
			
		}
		return returnVal;
	}

	private long countSalesAsstOrders(SalesAssistant salesAsst)
	{
		long count = 0;
		for(Order order : _orderContainer.getAllOrders())
		{
			if(order.getSalesAsst() == salesAsst)
				count++;
		}
		return count;	
	}

	private long getSalesAsstOrderValues(SalesAssistant salesAsst)
	{
		long value = 0;
		ArrayList<Order> orders = new ArrayList<Order>();
		for(Order order : _orderContainer.getAllOrders())
		{
			if(order.getSalesAsst() == salesAsst)
				orders.add(order);
		}

		for (Order order : orders) 
		{
			for (OrderLine line : order.getOrderLines()) 
			{
				value += (line.getProduct().getProduct().getPrice().longValue() * line.getQuantity());
			}
		}
		return value;
	}
}
