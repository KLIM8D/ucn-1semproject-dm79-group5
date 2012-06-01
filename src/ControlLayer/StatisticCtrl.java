package ControlLayer;

import ModelLayer.*;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.NavigableMap;
import java.util.Map;

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

	public TreeMap<Long, Product> getTopXForProduct(int take)
	{
		TreeMap<Long, Product> productList = new TreeMap<Long, Product>();
		for (Product prod : _productContainer.getAllProducts().values()) 
		{
			long key = generateStatsFromProduct(prod.getItemNumber())[1];
			productList.put(key, prod);
		}
		int count = 0;
		TreeMap<Long, Product> returnList = new TreeMap<Long, Product>();
		for (Map.Entry<Long,Product> entry : productList.descendingMap().entrySet()) 
		{
			if(count == take)
				break;

			returnList.put(entry.getKey(), entry.getValue());
			count++;
		}

		return returnList;
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

	public TreeMap<Long, Customer> getTopXForCustomer(int take)
	{
		TreeMap<Long, Customer> customerList = new TreeMap<Long, Customer>();
		for (Customer cus : _customerContainer.getAllCustomers().values()) 
		{
			long key = generateStatsFromCustomer(cus.getCustomerId())[1];
			customerList.put(key, cus);
		}
		int count = 0;
		TreeMap<Long, Customer> returnList = new TreeMap<Long, Customer>();
		for (Map.Entry<Long,Customer> entry : customerList.descendingMap().entrySet()) 
		{
			if(count == take)
				break;

			returnList.put(entry.getKey(), entry.getValue());
			count++;
		}

		return returnList;
	}


	public long[] generateStatsFromCustomer(long customerId)
	{
		long[] returnVal = new long[2];
		Customer cus = _customerContainer.getCustomer(customerId);
		if(cus != null)
		{
			returnVal[0] = countCustomerOrders(cus);
			returnVal[1] = getCustomerOrderValues(cus);
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

	public TreeMap<Long, SalesAssistant> getTopXForSalesAsst(int take)
	{
		TreeMap<Long, SalesAssistant> salesAsstList = new TreeMap<Long, SalesAssistant>();
		for (SalesAssistant sa : _saContainer.getAllSalesAs().values()) 
		{
			long key = generateStatsFromSalesAsst(sa.getSalesAssistantId())[1];
			salesAsstList.put(key, sa);
		}
		int count = 0;
		TreeMap<Long, SalesAssistant> returnList = new TreeMap<Long, SalesAssistant>();
		for (Map.Entry<Long,SalesAssistant> entry : salesAsstList.descendingMap().entrySet()) 
		{
			if(count == take)
				break;

			returnList.put(entry.getKey(), entry.getValue());
			count++;
		}

		return returnList;
	}

	public long[] generateStatsFromSalesAsst(int salesAsstId)
	{
		long[] returnVal = new long[2];
		SalesAssistant sa = _saContainer.getSalesAs(salesAsstId);
		if(sa != null)
		{
			returnVal[0] = countSalesAsstOrders(sa);
			returnVal[1] = getSalesAsstOrderValues(sa);
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
