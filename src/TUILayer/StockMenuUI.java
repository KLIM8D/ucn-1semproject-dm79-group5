package TUILayer;

import ControlLayer.ProductCtrl;

/**
 * Text User Interface - Stock
 *
 * @date (05.28.2012)
 */

public class StockMenuUI
{
	private MainMenuUI _mainmenuUI;
	private ProductCtrl _productController;

	public StockMenuUI()
	{
		_productController = new ProductCtrl();
	}

	public void execStockMenu()
	{
		GlobalUI.tuiHeader();

		print("\n\n\n                    -------------------------------------------------------------------");
		print("                    ¦                              Lager                              ¦");
		print("                    -------------------------------------------------------------------");
		print("                    ¦ 1) - Opret produkt                                              ¦");
		print("                    ¦ 2) - Vis produkt                                                ¦");
		print("                    ¦ 3) - Slet produkt                                               ¦");
		print("                    ¦ 4) - Find produkt                                               ¦");
		print("                    ¦                                                                 ¦");
		print("                    ¦ 9) - Returnere til hovedmenu                                    ¦");
		print("                    -------------------------------------------------------------------");
		
		int userentry = GlobalUI.inputGetInt("\n                      Menu valg: ");

		try
		{
			boolean succeeded = false;
			switch (userentry)
			{
				case 1:
					// Start of section
					try 
					{
						long itemNumber = GlobalUI.inputGetLong("Indtast produkt nummer: ");
						String itemName = GlobalUI.inputGetLine("Indtast produkt navn: ");
						int minInStock = GlobalUI.inputGetInt("Indtast minimums lager for produktet: ");
						int maxInStock = GlobalUI.inputGetInt("Indtast maksimums lager for produktet: ");
						String price = GlobalUI.inputGetLine("Indtast produktets pris: ");
						int categoryId = GlobalUI.inputGetInt("Indtast Id'et for produktets kategori: ");
						succeeded = _productController.createProduct(itemNumber, itemName, minInStock, maxInStock, price, categoryId);
						if(succeeded)
							print("Produktet er nu oprettet!");
						else
							print("Dette produkt findes allerede");
					}
					catch (Exception e)
					{
						GlobalUI.errorHandling(99);
					}
					execStockMenu();
					break;
					// End of section
				case 2:
					// Start of section
					print(GlobalUI.errorHandling(03));
					Thread.sleep(2000);
					execStockMenu();
					break;
					// End of section
				case 3:
					// Start of section
					print(GlobalUI.errorHandling(03));
					Thread.sleep(2000);
					execStockMenu();
					break;
					// End of section
				case 9:
					_mainmenuUI = new MainMenuUI();
					_mainmenuUI.execMainMenu();
					break;
				default:
					print(GlobalUI.errorHandling(02));
					Thread.sleep(2000);
					execStockMenu();
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
        System.out.println(inputLine);
    }
}