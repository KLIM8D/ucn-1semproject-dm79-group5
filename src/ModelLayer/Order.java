package ModelLayer;

import java.util.HashSet;

public class Order
{
    private long _id;
    private SalesAssistant _salesAsst;
    private Customer _customer;
    private String _salesNote;
    private Discount _discount;
    private HashSet<OrderLine> _orderLines;

    public Order(long id, SalesAssistant salesAsst, Customer customer, String salesNote, Discount discount)
    {
        _id = id;
        _salesAssistant = salesAssistant;
        _customer = customer;
        _salesNote = salesNote;
        _discount = discount;
        _orderLines = new HashSet<OrderLine>();
    }

    public long getId()
    {
        return _id;
    }

    public SalesAssistant getSalesAsst()
    {
        return _salesAsst;
    }

    public void setSalesAssistant(SalesAssistant salesAsst)
    {
        _salesAsst = salesAsst;
    }

    public Customer getCustomer()
    {
        return _customer;
    }

    public void setCustomer(Customer customer)
    {
        _customer = customer;
    }

    public String getSalesNote()
    {
        return _salesNote;
    }

    public void setSalesNote(String salesNote)
    {
        _salesNote = salesNote;
    }

    public Discount getDiscount()
    {
        return _discount;
    }

    public void setDiscount(Discount discount)
    {
        _discount = discount;
    }

    public HashSet<OrderLine> getOrderLines()
    {
        return _orderLines;
    }
}
