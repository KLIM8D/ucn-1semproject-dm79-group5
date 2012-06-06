package TUILayer;

import ControlLayer.StatisticCtrl;
import ControlLayer.CustomerCtrl;
import ControlLayer.ProductCtrl;
import ControlLayer.SalesAssistantCtrl;
import ModelLayer.SalesAssistant;
import ModelLayer.Customer;
import ModelLayer.Product;
import java.util.Map;

/**
 * Text User Interface - Statistics
 *
 * @date (05.28.2012)
 */

public class StatisticMenuUI
{
	private MainMenuUI _mainmenuUI;
	private SalesAssistantCtrl _saCtrl;
	private CustomerCtrl _cusCtrl;
	private ProductCtrl _prodCtrl;
	private StatisticCtrl _statisticCtrl;

	public StatisticMenuUI()
	{
		_saCtrl = new SalesAssistantCtrl();
		_cusCtrl = new CustomerCtrl();
		_prodCtrl = new ProductCtrl();
		_statisticCtrl = new StatisticCtrl();
	}

	public void execStatisticMenu()
	{
		GlobalUI.tuiHeader();

		print("-------------------------------------------------------------------");
		print("¦                             Statistiker                         ¦");
		print("-------------------------------------------------------------------");
		print("¦ 1) - Generer statistik ud fra ekspedient                        ¦");
		print("¦ 2) - Generer statistik ud fra kunde                             ¦");
		print("¦ 3) - Generer statistik ud fra vare                              ¦");
		print("¦                                                                 ¦");
		print("¦ 4) - Lav topX over ekspedienter                                 ¦");
		print("¦ 5) - Lav topX over kunder                                       ¦");
		print("¦ 6) - Lav topX over varer                                        ¦");
		print("¦                                                                 ¦");
		print("¦ 0) - Returnere til hovedmenu                                    ¦");
		print("-------------------------------------------------------------------");
		
		try
		{
			System.out.print("\n");
			int userentry = GlobalUI.inputGetInt("Menu valg: ");
			System.out.print("\n");
			switch (userentry)
			{
				case 1:
					// Start of section
					try 
					{
						int salesAsstId = GlobalUI.inputGetInt("Indtast ID for ekspedienten: ");
						SalesAssistant sa = _saCtrl.getSalesAssistant(salesAsstId);
						if(sa != null)
						{
							long[] salesAsstValues = _statisticCtrl.generateStatsFromSalesAsst(salesAsstId);
							print(sa.getPerson().getName() + " har i alt solgt for: ");
							print("Antal ordre: " + salesAsstValues[0]);
							print("Totalt beløb: " + salesAsstValues[1] + " kr.");
						}
						else
							print("En ekspedient med det ID blev ikke fundet");

						GlobalUI.inputGetLine("Tryk på enter for at forsætte..");
					}
					catch (Exception e)
					{
						print(GlobalUI.errorHandling(99));
						Thread.sleep(2000);
					}
					execStatisticMenu();
					break;
					// End of section
				case 2:
					// Start of section
					try 
					{
						long customerId = GlobalUI.inputGetLong("Indtast ID for kunden: ");
						Customer customer = _cusCtrl.getCustomer(customerId);
						if(customer != null)
						{
							long[] customerValues = _statisticCtrl.generateStatsFromCustomer(customerId);
							print(customer.getPerson().getName() + " har i alt købt for: ");
							print("Antal ordre: " + customerValues[0]);
							print("Totalt beløb: " + customerValues[1] + " kr.");
						}
						else
							print("En kunde med det ID blev ikke fundet");

						GlobalUI.inputGetLine("Tryk på enter for at forsætte..");
					}
					catch (Exception e)
					{
						print(GlobalUI.errorHandling(99));
						Thread.sleep(2000);
					}
					execStatisticMenu();
					break;
					// End of section
				case 3:
					// Start of section
					try 
					{
						long itemNumber = GlobalUI.inputGetLong("Indtast produkt nummeret: ");
						Product prod = _prodCtrl.getProduct(itemNumber);
						if(prod != null)
						{
							long[] productValues = _statisticCtrl.generateStatsFromProduct(itemNumber);
							print(prod.getItemName() + " har i alt solgt for: ");
							print("Antal ordre: " + productValues[0]);
							print("Totalt beløb: " + productValues[1] + " kr.");
						}
						else
							print("Kunne ikke finde et produkt med det produkt nummer");

						GlobalUI.inputGetLine("Tryk på enter for at forsætte..");
					}
					catch (Exception e)
					{
						print(GlobalUI.errorHandling(99));
						Thread.sleep(2000);
					}
					execStatisticMenu();
					break;
					// End of section
				case 4:
					// Start of section
					try 
					{
						int take = GlobalUI.inputGetInt("Hvor mange ekspedienter ønsker du at vise en top liste over: ");

						for(Map.Entry<Long,SalesAssistant> entry : _statisticCtrl.getTopXForSalesAsst(take).entrySet())
							print(entry.getValue().getPerson().getName() + " har i alt solgt for: " + entry.getKey() + " kr.");
						
						GlobalUI.inputGetLine("Tryk på enter for at forsætte..");
					}
					catch (Exception e)
					{
						print(GlobalUI.errorHandling(99));
						Thread.sleep(2000);
					}
					execStatisticMenu();
					break;
					// End of section
				case 5:
					// Start of section
					try 
					{
						int take = GlobalUI.inputGetInt("Hvor mange kunder ønsker du at vise en top liste over: ");

						for(Map.Entry<Long,Customer> entry : _statisticCtrl.getTopXForCustomer(take).entrySet())
							print(entry.getValue().getPerson().getName() + " har i alt købt for: " + entry.getKey() + " kr.");
						
						GlobalUI.inputGetLine("Tryk på enter for at forsætte..");
					}
					catch (Exception e)
					{
						print(GlobalUI.errorHandling(99));
						Thread.sleep(2000);
					}
					execStatisticMenu();
					break;
					// End of section
				case 6:
					// Start of section
					try 
					{
						int take = GlobalUI.inputGetInt("Hvor mange produkter ønsker du at vise en top liste over: ");

						for(Map.Entry<Long,Product> entry : _statisticCtrl.getTopXForProduct(take).entrySet())
							print(entry.getValue().getItemName() + " har i alt solgt for: " + entry.getKey() + " kr.");
						
						GlobalUI.inputGetLine("Tryk på enter for at forsætte..");
					}
					catch (Exception e)
					{
						print(GlobalUI.errorHandling(99));
						Thread.sleep(2000);
					}
					execStatisticMenu();
					break;
					// End of section
				case 0:
					_mainmenuUI = new MainMenuUI();
					_mainmenuUI.execMainMenu();
					break;
				default:
					print(GlobalUI.errorHandling(02));
					Thread.sleep(2000);
					execStatisticMenu();
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
            execStatisticMenu();
        }
	}

	private void print(String inputLine)
    {
        System.out.println("                      " + inputLine);
    }
}