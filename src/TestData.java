import ControlLayer.*;
import ModelLayer.*;
import java.util.Calendar;

/** 
* @version: 0.1
* Filename: TestData.java
* Description: 
* @changes	
*/

public class TestData
{

	private CustomerCtrl _customerCtrl;
	private LeaseCtrl _leaseCtrl;
	private OrderCtrl _orderCtrl;
	private ProductCtrl _productCtrl;
	private ProductLocationCtrl _locationCtrl;
	private SalesAssistantCtrl _salesAsstCtrl;
	private PersonCtrl _personCtrl;
	
	public TestData()
	{
        _customerCtrl = new CustomerCtrl();
		_leaseCtrl = new LeaseCtrl();
		_orderCtrl = new OrderCtrl();
		_productCtrl = new ProductCtrl();
		_locationCtrl = new ProductLocationCtrl();
		_salesAsstCtrl = new SalesAssistantCtrl();
		_personCtrl = new PersonCtrl();
	}

	public void addData()
	{
	    //Customer
	    _personCtrl.createPerson(210519802011L, "Mogens Jensen", "Hobrovej 200", "Aalborg", 9000, 85920592L);
	    Person per1 = _personCtrl.getPerson(210519802011L);
        _customerCtrl.createCustomer(per1);
        _customerCtrl.createDiscount(85920592L, 1, "200.00");
        _customerCtrl.createDiscount(85920592L, 2, "500.00");
        _customerCtrl.createDiscount(85920592L, 3, "150.00");
        _personCtrl.createPerson(111219752055L, "Joey Moe", "Landevejen 66", "Vojens", 6500, 22993011L);
	    
        Person per2 = _personCtrl.getPerson(111219752055L);
        _customerCtrl.createCustomer(per2);
        _customerCtrl.createDiscount(22993011L, 1, "250.00");
        _customerCtrl.createDiscount(22993011L, 2, "700.00");
        _customerCtrl.createDiscount(22993011L, 3, "100.00");

        _personCtrl.createPerson(110619201122L, "Birthe Rønn Hornbech", "Jagtvej 69", "København", 2000, 29129422L);
		Person per3 = _personCtrl.getPerson(110619201122L);
        _customerCtrl.createCustomer(per3);
        _customerCtrl.createDiscount(29129422L, 1, "333.00");
        _customerCtrl.createDiscount(29129422L, 2, "666.00");
        _customerCtrl.createDiscount(29129422L, 3, "999.00");
        
        _personCtrl.createPerson(220319881921L, "Lars Jensen", "Valmuevej 22", "København", 2000, 12345678L);
		Person per4 = _personCtrl.getPerson(220319881921L);
        _customerCtrl.createCustomer(per4);
        _customerCtrl.createDiscount(12345678L, 1, "222.00");
        _customerCtrl.createDiscount(12345678L, 2, "444.00");
        _customerCtrl.createDiscount(12345678L, 3, "888.00");
        //Customer END

        //Lease
        _leaseCtrl.createLeaseItem(12345L, "Massiv spade", "200.11", 5);
        _leaseCtrl.createSerialNumber(85832912L, true, 12345L);
        _leaseCtrl.createSerialNumber(85828284L, true, 12345L);
        _leaseCtrl.createSerialNumber(834818238L, true, 12345L);
        _leaseCtrl.createSerialNumber(12491257L, true, 12345L);
        _leaseCtrl.createSerialNumber(21938182L, true, 12345L);
        _leaseCtrl.createLeaseItem(54321L, "Rusten skovl", "525.55", 11);
        _leaseCtrl.createSerialNumber(2848124L, true, 54321L);
        _leaseCtrl.createSerialNumber(918591238L, true, 54321L);
        _leaseCtrl.createSerialNumber(5918258L, true, 54321L);
        _leaseCtrl.createSerialNumber(8148218L, true, 54321L);
        _leaseCtrl.createLeaseItem(98765L, "Feje kost", "125.95", 3);
        _leaseCtrl.createSerialNumber(21949294L, true, 98765L);
        _leaseCtrl.createSerialNumber(238123L, true, 98765L);
        _leaseCtrl.createSerialNumber(3561984L, true, 98765L);
        _leaseCtrl.createLeaseItem(45671L, "Trillebøre", "765.95", 10);
        _leaseCtrl.createSerialNumber(9124992L, true, 45671L);
        
        _leaseCtrl.createLease(85920592L, 12345L, 7);
        _leaseCtrl.createLease(22993011L, 45671L, 14);
        _leaseCtrl.createLease(85920592L, 54321L, 7);
        Lease lease = _leaseCtrl.getLease(3);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -15);
        lease.setRentStartDate(calendar.getTime());
        calendar.add(Calendar.DATE, -10);
        lease.setRentEndDate(calendar.getTime());
        //Lease END


        //Product
        _productCtrl.createProductCategory("Haven");
        _productCtrl.createProductCategory("El og belysning");  
        _productCtrl.createProductCategory("Bygningsartikler");
        _productCtrl.createProductCategory("VVS og Ventilation");
        _productCtrl.createProductCategory("Bad");

        _productCtrl.createProduct(102938L, "Skruer 500 stk", 10, 100, "200.95", 3);
        _productCtrl.createProduct(203901L, "Monterings sæt", 1, 5, "1000.00", 4);
        _productCtrl.createProduct(858221L, "Super søm 100 stk", 20, 500, "99.95", 3);
        _productCtrl.createProduct(2019549L, "Pælebor", 0, 10, "2000.00", 1);
        _productCtrl.createProduct(956934L, "Stikkontakt med jord", 25, 250, "50.00", 2);

        _productCtrl.createProduct(295992L, "Toilet", 0, 5, "500.00", 5);
        _productCtrl.createProduct(898582L, "Håndvask", 0, 10, "375.00", 5);
        _productCtrl.createProduct(589282L, "Brusekabine", 0, 3, "750.00", 5);
        _productCtrl.createProduct(582885L, "Bruse forhæng", 0, 10, "100.00", 5);
        _productCtrl.createProduct(985952L, "Håndklæde holdere", 0, 100, "25.00", 5);
        _productCtrl.createProduct(858293L, "Toilet børste", 0, 50, "50.00", 5);
        _productCtrl.createProduct(848482L, "Hvide fliser 25x25", 0, 2000, "125.00", 5);
        _productCtrl.createProduct(123456L, "Slagbror maskine", 0, 10, "275.00", 5);

        _productCtrl.createProductGroup("Fint hvidt badeværelse", "20000.00");
        _productCtrl.createProductGroupItem(1, 295992L, 1);
        _productCtrl.createProductGroupItem(1, 898582L, 1);
        _productCtrl.createProductGroupItem(1, 589282L, 1);
        _productCtrl.createProductGroupItem(1, 582885L, 1);
        _productCtrl.createProductGroupItem(1, 985952L, 2);
        _productCtrl.createProductGroupItem(1, 858293L, 1);
        _productCtrl.createProductGroupItem(1, 848482L, 50);
        //Product END

        //ProductLocation
        _locationCtrl.createProductLocation("Butik", "Lassevej 62", "Skive", 6350);
        _locationCtrl.createProductLocation("Fjernlager", "Skivevej 200", "Skive", 6350);

        _locationCtrl.addProduct(2, 102938L, 25);
        _locationCtrl.addProduct(2, 203901L, 3);
        _locationCtrl.addProduct(2, 858221L, 254);
        _locationCtrl.addProduct(2, 2019549L, 7);
        _locationCtrl.addProduct(2, 956934L, 179);
        _locationCtrl.addProduct(2, 123456L, 3);


        _locationCtrl.addProduct(1, 295992L, 3);
        _locationCtrl.addProduct(1, 898582L, 8);
        _locationCtrl.addProduct(1, 589282L, 3);
        _locationCtrl.addProduct(1, 582885L, 4);
        _locationCtrl.addProduct(1, 985952L, 65);
        _locationCtrl.addProduct(1, 858293L, 23);
        _locationCtrl.addProduct(1, 848482L, 1754);
        //ProductLocation END

        //SalesAssistant
        _personCtrl.createPerson(190219752019L, "Birthe Sørensen", "Gasværksvej 22", "Aalborg", 9000, 82312399L);
        _salesAsstCtrl.createSalesAssistant("BirthePassword", 190219752019L);

        _personCtrl.createPerson(250519862011L, "Torben Jensen", "Svanevej 45", "Aalborg", 9000, 22019922L);
        _salesAsstCtrl.createSalesAssistant("TorbenPassword", 250519862011L);

        _personCtrl.createPerson(150119650510L, "Jens Christensen", "Musvågevej 210", "Aalborg", 9000, 88229911L);
        _salesAsstCtrl.createSalesAssistant("JensPassword", 150119650510L);
        
        _personCtrl.createPerson(100519861510L, "Jesper Larsen", "Kongevej 123", "Aalborg", 9000, 33223322);
        _salesAsstCtrl.createSalesAssistant("123456", 100519861510L);
        //SalesAssistant END

        //Order
        _orderCtrl.createOrderStatus(1L, "Åben ordre");
        _orderCtrl.createOrderStatus(2L, "Annulleret ordre");
        _orderCtrl.createOrderStatus(3L, "Afsendt ordre");
        _orderCtrl.createOrderStatus(4L, "Faktureret ordre");

        _orderCtrl.createOrder(1, 85920592L, "Skal ringes inden levering", 1, 1L);
        _orderCtrl.addOrderLine(1L, 102938L, 2);
        _orderCtrl.addOrderLine(1L, 203901L, 4);
        _orderCtrl.addOrderLine(1L, 2019549L, 1);

        _orderCtrl.createOrder(2, 22993011L, "Må sættes på trappen", 2, 1L);
        _orderCtrl.addOrderLine(2L, 102938L, 2);
        _orderCtrl.addOrderLine(2L, 203901L, 4);
        _orderCtrl.addOrderLine(2L, 2019549L, 1);
        _orderCtrl.addOrderLine(2L, 589282L, 2);
        _orderCtrl.addOrderLine(2L, 848482L, 4);
        _orderCtrl.addOrderLine(2L, 295992L, 1);

        _orderCtrl.createOrder(1, 29129422L, "Pas på", 3, 1L);
        _orderCtrl.addOrderLine(3L, 295992L, 2);
        _orderCtrl.addOrderLine(3L, 898582L, 4);
        _orderCtrl.addOrderLine(3L, 589282L, 1);
        _orderCtrl.addOrderLine(3L, 582885L, 2);
        _orderCtrl.addOrderLine(3L, 985952L, 4);
        _orderCtrl.addOrderLine(3L, 858293L, 1);
        _orderCtrl.addOrderLine(3L, 848482L, 1);
        _orderCtrl.addOrderLine(3L, 102938L, 2);
        _orderCtrl.addOrderLine(3L, 203901L, 4);
        _orderCtrl.addOrderLine(3L, 2019549L, 1);
        _orderCtrl.addOrderLine(3L, 589282L, 2);
        _orderCtrl.addOrderLine(3L, 848482L, 4);
        _orderCtrl.addOrderLine(3L, 295992L, 1);
        
        _orderCtrl.createOrder(3, 29129422L, "Pas på hunden", 3, 1L);
        _orderCtrl.addOrderLine(4L, 295992L, 2);
        _orderCtrl.addOrderLine(4L, 898582L, 4);
        _orderCtrl.addOrderLine(4L, 589282L, 1);
        _orderCtrl.addOrderLine(4L, 582885L, 2);
        _orderCtrl.addOrderLine(4L, 985952L, 4);
        _orderCtrl.addOrderLine(4L, 858293L, 1);
        _orderCtrl.addOrderLine(4L, 848482L, 1);
        _orderCtrl.addOrderLine(4L, 102938L, 2);
        _orderCtrl.addOrderLine(4L, 956934L, 1);
        //ORDER END
    }
}
