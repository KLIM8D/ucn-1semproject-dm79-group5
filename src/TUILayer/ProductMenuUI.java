package TUILayer;

import ControlLayer.ProductCtrl;
import ModelLayer.Product;
import ModelLayer.ProductCategory;
import ModelLayer.ProductGroup;

/**
 * Text User Interface - Product
 *
 * @date (05.28.2012)
 */

public class ProductMenuUI
{
	private MainMenuUI _mainmenuUI;
	private ProductCtrl _productController;

	public ProductMenuUI()
	{
		_productController = new ProductCtrl();
	}

	public void execProductMenu()
	{
		GlobalUI.tuiHeader();

		print("-------------------------------------------------------------------");
		print("¦                           Produkter                             ¦");
		print("-------------------------------------------------------------------");
		print("¦ 1) - Opret produkt                                              ¦");
		print("¦ 2) - Vis produkt                                                ¦");
		print("¦ 3) - Opdater produkt                                            ¦");
		print("¦ 4) - Slet produkt                                               ¦");
		print("¦ 5) - Find produkt                                               ¦");
		print("¦ 6) - Vis alle produkter                                         ¦");
		print("¦                                                                 ¦");
		print("¦ 7) - Produkt kategorier                                         ¦");
		print("¦ 8) - Produkt grupper                                            ¦");
		print("¦                                                                 ¦");
		print("¦ 0) - Returner til hovedmenu                                     ¦");
		print("-------------------------------------------------------------------");
		
		

		try
		{
			System.out.println("\n");
			int userentry = GlobalUI.inputGetInt("Menu valg: ");
			System.out.println("\n");
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
							print("Produkt kategorien blev ikke fundet eller også findes dette produkt allerede");
					}
					catch (Exception e)
					{
						print(GlobalUI.errorHandling(99));
					}

					GlobalUI.inputGetLine("Tryk på enter for at forsætte..");
					execProductMenu();
					break;
					// End of section
				case 2:
					// Start of section
					try 
					{
						long itemNumber = GlobalUI.inputGetLong("Indtast produkt nummer: ");
						Product prod = _productController.getProduct(itemNumber);
						if(prod != null)
							print(GlobalUI.getProductInfo(prod));
						else
							print("Der blev ikke fundet et produkt med det produkt nummer");
					}
					catch (Exception e)
					{
						print(GlobalUI.errorHandling(99));
						Thread.sleep(2000);
					}

					GlobalUI.inputGetLine("Tryk på enter for at forsætte..");
					execProductMenu();
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
							print("Der blev ikke fundet et produkt med det produkt nummer");

					}
					catch (Exception e)
					{
						print(GlobalUI.errorHandling(99));
					}

					GlobalUI.inputGetLine("Tryk på enter for at forsætte..");
					execProductMenu();
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
						else if(answer.toLowerCase().equals("nej"))
						{
							print("Produktet blev ikke slettet");
						}
						else
						{
							print("Ukendt input");
						}
					}
					catch (Exception e)
					{
						print(GlobalUI.errorHandling(99));
					}

					GlobalUI.inputGetLine("Tryk på enter for at forsætte..");
					execProductMenu();
					break;
					// End of section
				case 5:
					try 
					{
						String itemName = GlobalUI.inputGetLine("Indtast produkt navn: ");
						Product prod = _productController.findProduct(itemName);
						if(prod != null)
							print(GlobalUI.getProductInfo(prod));
						else
							print("Der blev ikke fundet noget produkt");
					}
					catch (Exception e)
					{
						print(GlobalUI.errorHandling(99));
					}

					GlobalUI.inputGetLine("Tryk på enter for at forsætte..");
					execProductMenu();
					break;
				case 6:
					for(Product prod : _productController.getAllProducts())
						print(GlobalUI.getProductInfo(prod));

					GlobalUI.inputGetLine("Tryk på enter for at forsætte..");
					execProductMenu();
					break;
				case 7:
					execCategoryMenu();
					break;
				case 8:
					execGroupMenu();
					break;
				default:
					print(GlobalUI.errorHandling(02));
					Thread.sleep(2000);
					execProductMenu();
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
            execProductMenu();
        }
	}

	private void execCategoryMenu()
	{
		GlobalUI.tuiHeader();

		print("-------------------------------------------------------------------");
		print("¦                      Produkt kategorier                         ¦");
		print("-------------------------------------------------------------------");
		print("¦ 1) - Opret produkt kategori                                     ¦");
		print("¦ 2) - Vis produkt kategori                                       ¦");
		print("¦ 3) - Vis alle produkt kategorier                                ¦");
		print("¦ 4) - Slet produkt kategori                                      ¦");
		print("¦                                                                 ¦");
		print("¦ 0) - Returner til produkt menu                                  ¦");
		print("-------------------------------------------------------------------");
		
		try
		{
			System.out.print("\n");
			int userentry = GlobalUI.inputGetInt("Menu valg: ");
			System.out.println("\n");
			boolean succeeded = false;
			switch (userentry)
			{
				case 0:
					execProductMenu();
					break;
				case 1:
					// Start of section
					try 
					{
						String categoryName = GlobalUI.inputGetLine("Indtast det ønskede kategori navn: ");
						succeeded = _productController.createProductCategory(categoryName);
						if(succeeded)
							print("Produkt kategorien er nu oprettet");
						else
							print("Produkt kategorien findes allerede i systemet");
					}
					catch (Exception e)
					{
						print(GlobalUI.errorHandling(99));
					}

					GlobalUI.inputGetLine("Tryk på enter for at forsætte..");
					execCategoryMenu();
					break;
					// End of section
				case 2:
					// Start of section
					try 
					{
						int catId = GlobalUI.inputGetInt("Indtast ID'et på kategorien: ");
						ProductCategory category = _productController.getCategory(catId);
						if(category != null)
							print(GlobalUI.getProductCategoryInfo(category, true));
						else
							print("Der blev ikke fundet en kategori med det ID");
					}
					catch (Exception e)
					{
						print(GlobalUI.errorHandling(99));
					}

					GlobalUI.inputGetLine("Tryk på enter for at forsætte..");
					execCategoryMenu();
					break;
					// End of section
				case 3:
					try 
					{
						for(ProductCategory category : _productController.getAllCategories())
							print(GlobalUI.getProductCategoryInfo(category, true));
					}
					catch (Exception e)
					{
						print(GlobalUI.errorHandling(99));
					}

					GlobalUI.inputGetLine("Tryk på enter for at forsætte..");
					execCategoryMenu();
					break;
				case 4:
					// Start of section
					try 
					{
						int catId = GlobalUI.inputGetInt("Indtast ID'et på kategorien: ");
						
						String answer = GlobalUI.inputGetLine("Er du sikker på du vil slette dette produkt? (Ja/Nej): ");
						if(answer.toLowerCase().equals("ja"))
						{
							succeeded = _productController.removeCategory(catId);
							if(succeeded)
								print("Produkt kategorien er nu slettet");
							else
								print("Produkt kategorien kunne ikke slettes");
						}
						else
							print("Produkt kategorien blev ikke slettet");
					}
					catch (Exception e)
					{
						print(GlobalUI.errorHandling(99));
					}

					GlobalUI.inputGetLine("Tryk på enter for at forsætte..");
					execCategoryMenu();
					break;
					// End of section
				default:
					print(GlobalUI.errorHandling(02));
					Thread.sleep(2000);
					execCategoryMenu();
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
            execCategoryMenu();
        }
	}

	private void execGroupMenu()
	{
		GlobalUI.tuiHeader();

		print("-------------------------------------------------------------------");
		print("¦                        Produkt grupper                          ¦");
		print("-------------------------------------------------------------------");
		print("¦ 1) - Opret produkt gruppe                                       ¦");
		print("¦ 2) - Tilføj et produkt til en produkt gruppe                    ¦");
		print("¦ 3) - Opdater produkt gruppe                                     ¦");
		print("¦ 4) - Vis produkt gruppe                                         ¦");
		print("¦ 5) - Vis alle produkt grupper                                   ¦");
		print("¦ 6) - Slet produkt gruppe                                        ¦");
		print("¦                                                                 ¦");
		print("¦ 0) - Returner til produkt menu                                  ¦");
		print("-------------------------------------------------------------------");
		
		try
		{
			System.out.print("\n");
			int userentry = GlobalUI.inputGetInt("Menu valg: ");
			System.out.println("\n");
			boolean succeeded = false;
			switch (userentry)
			{
				case 0:
					execProductMenu();
					break;
				case 1:
					// Start of section
					try 
					{
						String groupName = GlobalUI.inputGetLine("Indtast det ønskede produkt gruppe navn: ");
						String groupPrice = GlobalUI.inputGetLine("Indtast total prisen for denne gruppe: ");
						succeeded = _productController.createProductGroup(groupName, groupPrice);
						if(succeeded)
							print("Produkt gruppen er nu oprettet");
						else
							print("Produkt gruppen blev ikke oprettet");						
					}
					catch (Exception e)
					{
						print(GlobalUI.errorHandling(99));
					}

					GlobalUI.inputGetLine("Tryk på enter for at forsætte..");
					execGroupMenu();
					break;
					// End of section
				case 2:
					// Start of section
					try 
					{
						int groupId = GlobalUI.inputGetInt("Indtast ID'et på produkt gruppen: ");
						long itemNumber = GlobalUI.inputGetLong("Indtast produktet nummeret på produktet du ønsker at tilføje: ");
						int quantity = GlobalUI.inputGetInt("Indtast det antallet der skal indgå i denne gruppe: ");
						succeeded = _productController.createProductGroupItem(groupId, itemNumber, quantity);
						if(succeeded)
							print("Produktet er nu tilføjet til gruppen");
						else
							print("Der skete en fejl i indtastningen."); 
						    print("Produktet med det produkt nummer og produkt gruppen med det ID skal eksitere");
					}
					catch (Exception e)
					{
						print(GlobalUI.errorHandling(99));
					}

					GlobalUI.inputGetLine("Tryk på enter for at forsætte..");
					execGroupMenu();
					break;
					// End of section
				case 3:
					try 
					{
						int groupId = GlobalUI.inputGetInt("Indtast ID'et på produkt gruppen: ");
						String groupName = GlobalUI.inputGetLine("Indtast det ønskede produkt gruppe navn: ");
						String groupPrice = GlobalUI.inputGetLine("Indtast total prisen for denne gruppe: ");
						succeeded = _productController.updateProductGroup(groupId, groupName, groupPrice);
						if(succeeded)
							print("Produkt gruppen er nu opdateret");
						else
							print("Produkt gruppen blev ikke opdateret. Prøv igen, og hver sikker på at indtastningen er korrekt");
					}
					catch (Exception e)
					{
						print(GlobalUI.errorHandling(99));
					}

					GlobalUI.inputGetLine("Tryk på enter for at forsætte..");
					execGroupMenu();
					break;
				case 4:
					try 
					{
						int groupId = GlobalUI.inputGetInt("Indtast ID'et på produkt gruppen: ");
						ProductGroup group = _productController.getProductGroup(groupId);
						if(group != null)
							print(GlobalUI.getProductGroupInfo(group));
						else
							print("Der blev ikke fundet en produkt gruppe med det ID");
					}
					catch (Exception e)
					{
						print(GlobalUI.errorHandling(99));
					}

					GlobalUI.inputGetLine("Tryk på enter for at forsætte..");
					execGroupMenu();
					break;
				case 5:
					try 
					{
						for(ProductGroup group : _productController.getAllProductGroups())
							print(GlobalUI.getProductGroupInfo(group));
					}
					catch (Exception e)
					{
						print(GlobalUI.errorHandling(99));
					}

					GlobalUI.inputGetLine("Tryk på enter for at forsætte..");
					execGroupMenu();
					break;
				case 6:
					// Start of section
					try 
					{
						int groupId = GlobalUI.inputGetInt("Indtast ID'et på produkt gruppen: ");
						
						String answer = GlobalUI.inputGetLine("Er du sikker på du vil slette dette produkt? (Ja/Nej): ");
						if(answer.toLowerCase().equals("ja"))
						{
							succeeded = _productController.removeProductGroup(groupId);
							if(succeeded)
								print("Produkt gruppen er nu slettet");
							else
								print("Produkt gruppen kunne ikke slettes");
						}
						else
							print("Produkt gruppen blev ikke slettet");
					}
					catch (Exception e)
					{
						print(GlobalUI.errorHandling(99));
					}

					GlobalUI.inputGetLine("Tryk på enter for at forsætte..");
					execGroupMenu();
					break;
					// End of section
				default:
					print(GlobalUI.errorHandling(02));
					Thread.sleep(2000);
					execGroupMenu();
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
            execGroupMenu();
        }
	}

	private void print(String inputLine)
    {
        System.out.println("                      " + inputLine);
    }
}
