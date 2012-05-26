package ModelLayer;

/** 
* @version: 0.1
* Filename: Product.java
* Description: Discount is a class, that creates objects of the type Discount
* @changes  
*/

public class Discount
{
    private int _discountType;
    private double _discountValue;

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