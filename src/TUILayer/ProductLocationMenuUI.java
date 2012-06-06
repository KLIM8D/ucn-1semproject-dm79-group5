package TUILayer;

import ControlLayer.ProductLocationCtrl;
import ModelLayer.ProductLocation;
/** 
* @version: 0.1
* Filename: ProductLocationMenuUI.java
* Description: 
* @changes	
*/

public class ProductLocationMenuUI
{
	private MainMenuUI _mainmenuUI;
	private ProductLocationCtrl _locController;

	public ProductLocationMenuUI() 
	{
		_locController = new ProductLocationCtrl();
	}

	public void execLocationMenu()
	{
		GlobalUI.tuiHeader();

		print("-------------------------------------------------------------------");
		print("¦                          Lager                                  ¦");
		print("-------------------------------------------------------------------");
		print("¦ 1) - Opret lager loktation                                      ¦");
		print("¦ 2) - Vis lager loktation information                            ¦");
		print("¦ 3) - Slet lager loktation                                       ¦");
		print("¦ 4) - Opdater lager information                                  ¦");
		print("¦ 5) - Vis alle lager loktationer                                 ¦");		
		print("¦                                                                 ¦");
		print("¦ 6) - Tilføj et produkt til et lager                             ¦");
		print("¦ 7) - Vis tilgængelige af et produkt                             ¦");
		print("¦                                                                 ¦");
		print("¦ 0) - Returner til hovedmenu                                     ¦"); 
		print("-------------------------------------------------------------------");

		
		try
		{
			System.out.print("\n");
			int userentry = GlobalUI.inputGetInt("Menu valg: ");
			System.out.println("\n");
			boolean succeeded = false;
			switch (userentry)
			{
				case 1:
					// Start of section
					try
					{	
						String locName = GlobalUI.inputGetLine("Indtast lagrets navn: ");
						String locAddress = GlobalUI.inputGetLine("Indtast lagrets adresse: ");
						String locCity = GlobalUI.inputGetLine("Hvilken by er lagret placeret i: ");
						int locZipCode = GlobalUI.inputGetInt("Post nummer: ");
						succeeded = _locController.createProductLocation(locName, locAddress, locCity, locZipCode);
						if(succeeded)
							print("Lagret er nu oprettet");
						else
							print("Lagret blev ikke oprettet");
					}
					catch(Exception ex)
					{
						print(GlobalUI.errorHandling(99));
						Thread.sleep(2000);
					}			

					GlobalUI.inputGetLine("Tryk på enter for at forsætte..");	
					execLocationMenu();
					break;
					// End of section
				case 2:
					// Start of section
					try
					{	
						int locId = GlobalUI.inputGetInt("Indtast ID'et på lageret: ");
						ProductLocation loc = _locController.getLocation(locId);
						if(loc != null)
							print(GlobalUI.getProductLocationInfo(loc));
						else
							print("Der blev ikke fundet et lager med det ID");
					}
					catch(Exception ex)
					{
						print(GlobalUI.errorHandling(99));
						Thread.sleep(2000);
					}			

					GlobalUI.inputGetLine("Tryk på enter for at forsætte..");	
					execLocationMenu();
					break;
					// End of section
				case 3:
					// Start of section
					try
					{
						print("Bemærk denne funktion kan ikke fortrydes, så snart den er udført!");
						int locId = GlobalUI.inputGetInt("Indtast ID på lageret: ");
						ProductLocation loc = _locController.getLocation(locId);
						if(loc != null)
						{
							print(GlobalUI.getProductLocationInfo(loc));
							String answer = GlobalUI.inputGetLine("Er du sikker på du vil slette dette lager? (Ja/Nej): ");
							if(answer.toLowerCase().equals("ja"))
							{
								succeeded = _locController.removeLocation(locId);
								if(succeeded)
									print("Lageret er nu slettet");
								else
									print("Lageret kunne ikke slettes");
							}	
						}
						else
							print("Et lager med det ID blev ikke fundet");
					}
					catch(Exception ex)
					{
						print(GlobalUI.errorHandling(99));
						Thread.sleep(2000);
					}
					GlobalUI.inputGetLine("Tryk på enter for at forsætte..");
					execLocationMenu();
					break;
					// End of section
				case 4:
					try
					{
						int locId = GlobalUI.inputGetInt("Indtast ID på lageret: ");
						ProductLocation loc = _locController.getLocation(locId);
						if(loc != null)
						{
							String locName = GlobalUI.inputGetLine("Indtast lagrets navn: ");
							String locAddress = GlobalUI.inputGetLine("Indtast lagrets adresse: ");
							String locCity = GlobalUI.inputGetLine("Hvilken by er lagret placeret i: ");
							int locZipCode = GlobalUI.inputGetInt("Post nummer: ");
							succeeded = _locController.updateLocation(locId, locName, locAddress, locCity, locZipCode);
							if(succeeded)
								print("Lagret er nu opdateret");
							else
								print("Lagret kunne ikke opdateres");
						}	
						else
							print("Et lager med det ID blev ikke fundet");
					}
					catch(Exception ex)
					{
						print(GlobalUI.errorHandling(99));
						Thread.sleep(2000);
					}			

					GlobalUI.inputGetLine("Tryk på enter for at forsætte..");	
					execLocationMenu();
					break;
				case 5:
					// Start of section
					for(ProductLocation loc : _locController.getAll())
						print(GlobalUI.getProductLocationInfo(loc));

					GlobalUI.inputGetLine("Tryk på enter for at forsætte..");
					execLocationMenu();
					break;
					// End of section
				case 6:
					// Start of section
					try
					{
						int locId = GlobalUI.inputGetInt("Indtast ID på lageret: ");
						ProductLocation loc = _locController.getLocation(locId);
						if(loc != null)
						{
							long itemNumber = GlobalUI.inputGetLong("Indtast produkt nummer: ");
							int quantity = GlobalUI.inputGetInt("Indtast total antal: ");
							succeeded = _locController.addProduct(locId, itemNumber, quantity);
							if(succeeded)
								print("Produktet blev tilføjet til lageret");
							else
								print("Produktet kunne ikke tilføjes");
						}	
						else
							print("Et lager med det ID blev ikke fundet");
					}
					catch(Exception ex)
					{
						print(GlobalUI.errorHandling(99));
						Thread.sleep(2000);
					}			

					GlobalUI.inputGetLine("Tryk på enter for at forsætte..");	
					execLocationMenu();
					break;
					// End of section
				case 7:
					// Start of section
					try
					{
						int locId = GlobalUI.inputGetInt("Indtast ID på lageret: ");
						ProductLocation loc = _locController.getLocation(locId);
						if(loc != null)
						{
							long itemNumber = GlobalUI.inputGetLong("Indtast produkt nummer: ");
							int quantity = _locController.getAvailOnLocation(locId, itemNumber);
							print("Der blev fundet: " + quantity + " stk af dette produkt lageret");
						}	
						else
							print("Et lager med det ID blev ikke fundet");
					}
					catch(Exception ex)
					{
						print(GlobalUI.errorHandling(99));
						Thread.sleep(2000);
					}			

					GlobalUI.inputGetLine("Tryk på enter for at forsætte..");	
					execLocationMenu();
					break;
					// End of section
				case 0:
					_mainmenuUI = new MainMenuUI();
					_mainmenuUI.execMainMenu();
					break;
				default:
					print(GlobalUI.errorHandling(02));
					Thread.sleep(2000);
					execLocationMenu();
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
            execLocationMenu();
        }
	}

	private void print(String inputLine)
	{
		System.out.println("                      " + inputLine);
	}
}