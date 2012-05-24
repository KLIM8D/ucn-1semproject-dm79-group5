package ModelLayer;


/**
 * Discount is a class, that creates objects of the type Discount
 */
public class Discount
{
    private int _discountType;
    private double _discountValue;

    /**
     * Constructor of the class
     */
    public Discount(int discountType, double discountValue)
    {
        _discountType = discountType;
        _discountValue = discountValue;
    }

    public int getDiscountType()
    {
        return _discountType;
    }
    
    public double getDiscountValue()
    {
        return _discountValue;
    }
    
    public void setDiscountType(int discountType)
    {
        _discountType = discountType;
    }
    
    public void setDiscountValue(double discountValue)
    {
        _discountValue = discountValue;
    }
}