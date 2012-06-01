package TUILayer;

import ControlLayer.StatisticCtrl;
import ControlLayer.CustomerCtrl;
import ControlLayer.ProductCtrl;
import ControlLayer.SalesAssistantCtrl;
import ModelLayer.SalesAssistant;
import ModelLayer.Customer;
import ModelLayer.Product;

/**
 * Text User Interface - Economy
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
		System.out.print("\n");
		int userentry = GlobalUI.inputGetInt("Menu valg: ");
		System.out.print("\n");
		try
		{
			switch (userentry)
			{
				case 1:
					// Removal of section when function is integrated
					// Start of section
					try 
					{
						int salesAsstId = GlobalUI.inputGetInt("Indtast ID for ekspedienten: ");
						SalesAssistant sa = _saCtrl.getSalesAssistant(salesAsstId);
						if(sa != null)
						{
							long[] salesAsstValues = _statisticCtrl.generateStatsFromSalesAsst(salesAsstId);
							print(sa.getPerson().getName() + " har solgt for: ");
							print("Antal ordre: " + salesAsstValues[0]);
							print("Totalt beløb: " + salesAsstValues[1]);
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
					// Removal of section when function is integrated
					// Start of section
					print(GlobalUI.errorHandling(03));
					Thread.sleep(2000);
					execStatisticMenu();
					break;
					// End of section

				case 9:
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
		}
	}

	private void print(String inputLine)
    {
        System.out.println("                      " + inputLine);
    }
}