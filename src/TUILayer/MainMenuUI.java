package TUILayer;

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
	private ProductMenuUI _productmenuUI;
	private StatisticMenuUI _statisticmenuUI;
	private SalesAssistantMenuUI _salesAssistantMenuUI;
	private ProductLocationMenuUI _locMenuUI;

	public void execMainMenu()
	{
		GlobalUI.tuiHeader();

		System.out.println("\n\n\n                    -------------------------------------------------------------------");
		System.out.println("                    ¦                            Hovedmenu                            ¦");
		System.out.println("                    -------------------------------------------------------------------");
		System.out.println("                    ¦ 1) - Salg                                                       ¦");
		System.out.println("                    ¦ 2) - Udlejning                                                  ¦");
		System.out.println("                    ¦ 3) - Kundekartotek                                              ¦");
		System.out.println("                    ¦ 4) - Ekspedientkartotek                                         ¦");
		System.out.println("                    ¦ 5) - Produkter                                                  ¦");
		System.out.println("                    ¦ 6) - Statistiker                                                ¦");
		System.out.println("                    ¦ 7) - Lager                                                      ¦");
		System.out.println("                    ¦                                                                 ¦");
		System.out.println("                    ¦ 0) - Afslut                                                     ¦");
		System.out.println("                    -------------------------------------------------------------------");
		
		int userentry = GlobalUI.inputGetInt("Menu valg: ");

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
					_salesAssistantMenuUI = new SalesAssistantMenuUI();
					_salesAssistantMenuUI.execSalesAsstMenu();
					break;	

				case 5:
					_productmenuUI = new ProductMenuUI();
					_productmenuUI.execProductMenu();
					break;

				case 6:
					_statisticmenuUI = new StatisticMenuUI();
					_statisticmenuUI.execStatisticMenu();
					break;

				case 7:
					_locMenuUI = new ProductLocationMenuUI();
					_locMenuUI.execLocationMenu();
					break;

				case 0:
					System.out.println("\n                      Systemet er afsluttet...");
					System.exit(0);

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