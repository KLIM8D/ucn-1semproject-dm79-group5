package TUILayer;

import ControlLayer.ProductCtrl;
import ModelLayer.Product;

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
		print("                    ¦ 3) - Opdater produkt                                            ¦");
		print("                    ¦ 4) - Slet produkt                                               ¦");
		print("                    ¦ 5) - Find produkt                                               ¦");
		print("                    ¦                                                                 ¦");
		print("                    ¦ 0) - Returnere til hovedmenu                                    ¦");
		print("                    -------------------------------------------------------------------");
		
		int userentry = GlobalUI.inputGetInt("\n                      Menu valg: ");

		try
		{
			boolean succeeded = false;
			switch (userentry)
			{
				case 0:
					_mainmenuUI = new MainMenuUI();
					_mainmenuUI.execMainMenu();
					break;
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
						Thread.sleep(2000);
					}
					execStockMenu();
					break;
					// End of section
				case 2:
					// Start of section
					try 
					{
						long itemNumber = GlobalUI.inputGetLong("Indtast produkt nummer: ");
						print(GlobalUI.getProductInfo(_productController.getProduct(itemNumber)));
						GlobalUI.inputGetLine("Tryk på enter for at forsætte..");
					}
					catch (Exception e)
					{
						GlobalUI.errorHandling(99);
						Thread.sleep(2000);
					}
					execStockMenu();
					break;
					// End of section
				case 3:
					try 
					{
						long itemNumber = GlobalUI.inputGetLong("Indtast produkt nummer: ");
						Product prod = _productController.getProduct(itemNumber);
						if(prod != null)
						{
							String itemName = GlobalUI.inputGetLine("Indtast produkt navn: ");
							int minInStock = GlobalUI.inputGetInt("Indtast minimums lager for produktet: ");
							int maxInStock = GlobalUI.inputGetInt("Indtast maksimums lager for produktet: ");
							String price = GlobalUI.inputGetLine("Indtast produktets pris: ");
							int categoryId = GlobalUI.inputGetInt("Indtast Id'et for produktets kategori: ");
							succeeded = _productController.updateProduct(itemNumber, itemName, minInStock, maxInStock, price, categoryId);

							if(succeeded)
								print("Produktet er nu opdateret!");
							else
								print("Produktet blev ikke opdateret");
						}
						else
							print("Der blev ikke fundet et produkt med det nummer");
					}
					catch (Exception e)
					{
						GlobalUI.errorHandling(99);
						Thread.sleep(2000);
					}
					execStockMenu();
					break;
				case 4:
					// Start of section
					try 
					{
						long itemNumber = GlobalUI.inputGetLong("Indtast produkt nummer: ");
						String answer = GlobalUI.inputGetLine("Er du sikker på du vil slette dette produkt? (Ja/Nej): ");
						if(answer.toLowerCase().equals("ja"))
						{
							succeeded = _productController.removeProduct(itemNumber);
							if(succeeded)
								print("Produktet er nu slettet");
							else
								print(GlobalUI.errorHandling(99));
						}

						GlobalUI.inputGetLine("Tryk på enter for at forsætte..");
					}
					catch (Exception e)
					{
						GlobalUI.errorHandling(99);
						Thread.sleep(2000);
					}
					execStockMenu();
					break;
					// End of section
				case 5:
					try 
					{
						String itemName = GlobalUI.inputGetLine("Indtast produkt navn: ");
						Product prod = _productController.findProduct(itemName);
						if(prod != null)
							GlobalUI.getProductInfo(prod);
						else
							print("Der blev ikke fundet noget produkt");
					}
					catch (Exception e)
					{
						System.out.println(e);
					}
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