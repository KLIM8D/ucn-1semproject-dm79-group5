package TUILayer;

import ControlLayer.OrderCtrl;
import ControlLayer.CustomerCtrl;
import ModelLayer.Order;
import ModelLayer.Customer;
import ModelLayer.Discount;
import ModelLayer.OrderStatus;

/**
 * Text User Interface - Order
 *
 * @date (05.28.2012)
 */

public class OrderMenuUI
{
	private MainMenuUI _mainmenuUI;
    private OrderCtrl _orderCtrl;
    private CustomerCtrl _customerCtrl;

    public OrderMenuUI()
    {
        _orderCtrl = new OrderCtrl();
        _customerCtrl = new CustomerCtrl();
    }

	public void execOrderMenu()
	{
		GlobalUI.tuiHeader();

		print("-------------------------------------------------------------------");
		print("¦                              Salg                               ¦");
		print("-------------------------------------------------------------------");
		print("¦ 1) - Ny ordre                                                   ¦");
        print("¦ 2) - Tilføj ordrelinjer                                         ¦");
		print("¦ 3) - Vis ordre                                                  ¦");
		print("¦ 4) - Ændre status på ordre                                      ¦");
        print("¦ 5) - Vis en kundes ordre                                        ¦");
        print("¦ 6) - Vis en sælgers ordre                                       ¦");
        print("¦ 7) - Vis alle ordre                                             ¦");
		print("¦                                                                 ¦");
		print("¦ 0) - Returnere til hovedmenu                                    ¦");
		print("-------------------------------------------------------------------");
		

		try
		{
            System.out.print("\n");
            int userentry = GlobalUI.inputGetInt("Menu valg: ");
            System.out.println("\n");
			switch (userentry)
			{
				case 1:
					// Start of section
                    try
                    {
                        int salesAsstId = GlobalUI.inputGetInt("Indtast sælger nummer: ");
                        long customerId = GlobalUI.inputGetLong("Indtast kundens telefon nummer: ");
                        String salesNote = GlobalUI.inputGetLine("Indtast evt. noter til ordren: ");
                        String isDiscountSale = GlobalUI.inputGetLine("Har kunden rabat aftaler? (ja/nej): ");
                        long orderId = 0;
                        if(isDiscountSale.toLowerCase().equals("ja"))
                        {
                            Customer cust = _customerCtrl.getCustomer(customerId);
                            for(Discount disc : cust.getDiscounts())
                                print("ID: " + disc.getDiscountType() + GlobalUI.translateDiscountTypes(disc.getDiscountType()) + "Beløb: " + disc.getDiscountValue() + " kr.");

                            int discountId = GlobalUI.inputGetInt("Indtast rabatens ID: ");
                            orderId = _orderCtrl.createOrder(salesAsstId, customerId, salesNote, discountId, 1L);
                        }
                        else
                            orderId = _orderCtrl.createOrder(salesAsstId, customerId, salesNote, 1L);

                        if(orderId != 0)
                            print("Ordren er nu oprettet og har fået ID: " + orderId);
                        else
                            print("Ordren kunne ikke oprettes");

                    }
                    catch(Exception ex)
                    {
                        print(GlobalUI.errorHandling(99));
                    }
                    
                    GlobalUI.inputGetLine("Tryk på enter for at forsætte..");
					execOrderMenu();
					break;
					// End of section

				case 2:
					// Start of section
                    try
                    {
                        long orderId = GlobalUI.inputGetLong("Indtast ordre nummer: ");
                        Order order = _orderCtrl.getOrder(orderId);
                        if(order != null)
                        {
                            boolean running = true;
                            while(running)
                            {
                                long itemNumber = GlobalUI.inputGetLong("Indtast produkt nummer: ");
                                long quantity = GlobalUI.inputGetLong("Indtast antal: ");
                                boolean succeeded = _orderCtrl.addOrderLine(orderId, itemNumber, quantity);
                                if(succeeded)
                                    print("Ordrelinjen blev tilføjet");
                                else
                                    print("Der blev ikke fundet et produkt med det produkt nummer");

                                String moreLines = GlobalUI.inputGetLine("Vil du tilføje flere ordrelinjer? (ja/nej): ");
                                if(!moreLines.toLowerCase().equals("ja"))
                                    running = false;                                
                            }
                        }
                        else
                            print("Der blev ikke fundet en ordre med det ordre nummer");
                    }
                    catch(Exception ex)
                    {
                        print(GlobalUI.errorHandling(99));
                    }

                    GlobalUI.inputGetLine("Tryk på enter for at forsætte..");
					execOrderMenu();
					break;
					// End of section

				case 3:
					// Start of section
                    try
                    {
                        long orderId = GlobalUI.inputGetLong("Indtast ordre nummer: ");
                        Order order = _orderCtrl.getOrder(orderId);
                        if(order != null)
                        {
                            print(GlobalUI.getOrderInfo(order));
                        }
                        else
                            print("Der blev ikke fundet en ordre med det ordre nummer");
                    }
                    catch(Exception ex)
                    {
                        print(GlobalUI.errorHandling(99));
                    }  

                    GlobalUI.inputGetLine("Tryk på enter for at forsætte.."); 
					execOrderMenu();
					break;
					// End of section

                case 4:
                    // Start of section
                    try
                    {
                        long orderId = GlobalUI.inputGetLong("Indtast ordre nummer: ");
                        Order order = _orderCtrl.getOrder(orderId);
                        if(order != null)
                        {
                            for(OrderStatus status : _orderCtrl.getAllOrderStatuses())
                                print("ID: " + status.getId() + " - " + status.getStatusValue());

                            long statusId = GlobalUI.inputGetLong("Indtast ID på den status ønsker at give ordren: ");
                            boolean succeeded = _orderCtrl.changeOrderStatus(orderId, statusId);
                            if(succeeded)
                                print("Statusen på ordren er nu ændret");
                            else
                                print("Ikke mulig status");
                        }
                        else
                            print("Der blev ikke fundet en ordre med det ordre nummer");
                    }
                    catch(Exception ex)
                    {
                        print(GlobalUI.errorHandling(99));
                    } 

                    GlobalUI.inputGetLine("Tryk på enter for at forsætte..");  
                    execOrderMenu();
                    break;
                    // End of section

                case 5:
                    // Start of section
                    try
                    {
                        long phoneNumber = GlobalUI.inputGetLong("Indtast kundens telefon nummer: ");
                        for(Order order : _orderCtrl.getCustomerOrders(phoneNumber))
                            print(GlobalUI.getOrderInfo(order));
                    }
                    catch(Exception ex)
                    {
                        print(GlobalUI.errorHandling(99));
                    }

                    GlobalUI.inputGetLine("Tryk på enter for at forsætte..");   
                    execOrderMenu();
                    break;
                    // End of section

                case 6:
                    // Start of section
                    try
                    {
                        int salesAsstId = GlobalUI.inputGetInt("Indtast sælgerens ID: ");
                        for(Order order : _orderCtrl.getSalesAssistantOrders(salesAsstId))
                            print(GlobalUI.getOrderInfo(order));
                    }
                    catch(Exception ex)
                    {
                        print(GlobalUI.errorHandling(99));
                    }

                    GlobalUI.inputGetLine("Tryk på enter for at forsætte..");   
                    execOrderMenu();
                    break;
                    // End of section

                case 7:
                    // Start of section
                    try
                    {
                        for(Order order : _orderCtrl.getAllOrders())
                            print(GlobalUI.getOrderInfo(order));
                    }
                    catch(Exception ex)
                    {
                        print(GlobalUI.errorHandling(99));
                    }

                    GlobalUI.inputGetLine("Tryk på enter for at forsætte..");   
                    execOrderMenu();
                    break;
                    // End of section

				case 0:
					_mainmenuUI = new MainMenuUI();
					_mainmenuUI.execMainMenu();
					break;

				default:
					print(GlobalUI.errorHandling(02));
					Thread.sleep(2000);
					execOrderMenu();
					break;
			}

		}

		catch (Exception e)
		{
			print(GlobalUI.errorHandling(99));
            try
            {
                Thread.sleep(2000);  
            }
            catch(Exception ee)
            {}  
            execOrderMenu();
		}
	}

    private void print(String inputLine)
    {
        System.out.println("                      " + inputLine);
    }
}
