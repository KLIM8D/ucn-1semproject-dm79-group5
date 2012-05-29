package TUILayer;
import java.util.Scanner;

/**
 * Text User Interface - MainMenu
 *
 * @date (05.26.2012)
 */

public class MainMenuUI
{
	private SalesMenuUI _salesmenuUI;
	private RentalMenuUI _rentalmenuUI;
	private CustomerMenuUI _customermenuUI;
	private StockMenuUI _stockmenuUI;
	private EconomyMenuUI _economymenuUI;

	public void execMainMenu()
	{
		GlobalUI.tuiHeader();

		System.out.println("\n\n\n                    -------------------------------------------------------------------");
		System.out.println("                    ¦                            Hovedmenu                            ¦");
		System.out.println("                    -------------------------------------------------------------------");
		System.out.println("                    ¦ 1) - Salg                                                       ¦");
		System.out.println("                    ¦ 2) - Udlejning                                                  ¦");
		System.out.println("                    ¦ 3) - Kundekartotek                                              ¦");
		System.out.println("                    ¦ 4) - Lager                                                      ¦");
		System.out.println("                    ¦ 5) - Økonomi                                                    ¦");
		System.out.println("                    ¦                                                                 ¦");
		System.out.println("                    ¦ 9) - Afslut                                                     ¦");
		System.out.println("                    -------------------------------------------------------------------");

		Scanner keyboard = new Scanner(System.in);

		System.out.print("\n                      Menu valg: ");
		int userentry = keyboard.nextInt();

		try
		{
			switch (userentry)
			{
				case 1:
					_salesmenuUI = new SalesMenuUI();
					_salesmenuUI.execSalesMenu();
					break;

				case 2:
					_rentalmenuUI = new RentalMenuUI();
					_rentalmenuUI.execRentalMenu();
					break;

				case 3:
					_customermenuUI = new CustomerMenuUI();
					_customermenuUI.execCustomerMenu();
					break;

				case 4:
					_stockmenuUI = new StockMenuUI();
					_stockmenuUI.execStockMenu();
					break;

				case 5:
					_economymenuUI = new EconomyMenuUI();
					_economymenuUI.execEconomyMenu();
					break;

				case 9:
					System.out.print("\n                      Systemet er afsluttet...");
					return;

				default:
					System.out.print("\n                      " + GlobalUI.errorHandling(2));
					Thread.sleep(2000);
					execMainMenu();
					break;
			}

		}

		catch (Exception e)
		{
			System.out.print("\n                      " + GlobalUI.errorHandling(99));
		}
	}
}