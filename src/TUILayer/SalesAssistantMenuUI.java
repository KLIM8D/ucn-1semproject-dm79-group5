package TUILayer;

import ControlLayer.SalesAssistantCtrl;
import ControlLayer.PersonCtrl;
import ModelLayer.SalesAssistant;

/**
 * Text User Interface - SalesAssistant
 *
 * @date (05.28.2012)
 */

public class SalesAssistantMenuUI
{
	private MainMenuUI _mainmenuUI;
	private SalesAssistantCtrl _saController;
	private PersonCtrl _perController;

	public SalesAssistantMenuUI() 
	{
		_saController = new SalesAssistantCtrl();
		_perController = new PersonCtrl();
	}

	public void execSalesAsstMenu()
	{
		GlobalUI.tuiHeader();

		print("-------------------------------------------------------------------");
		print("¦                          Ekspedientkartotek                     ¦");
		print("-------------------------------------------------------------------");
		print("¦ 1) - Opret ekspedient                                           ¦");
		print("¦ 2) - Vis ekspedient information                                 ¦");
		print("¦ 3) - Opdater ekspedient information                             ¦");
		print("¦ 4) - Slet ekspedient                                            ¦");
		print("¦ 5) - Vis alle ekspedienter                                      ¦");
		print("¦ 6) - Skift password                                             ¦");
		print("¦                                                                 ¦");
		print("¦ 0) - Returner til hovedmenu                                     ¦");
		print("-------------------------------------------------------------------");

		
		try
		{
			System.out.print("\n");
			int userentry = GlobalUI.inputGetInt("Menu valg: ");
			System.out.println("\n");
			boolean succeeded = false;
			SalesAssistant sa = null;
			int salesAsstId = 0;
			switch (userentry)
			{
				case 1:
					// Start of section
					try
					{
						long personId = GlobalUI.inputGetLong("Indtast CPR nummer (uden -): ");
						String personName = GlobalUI.inputGetLine("Indtast navn: ");
						String address = GlobalUI.inputGetLine("Indtast vejnavn + husnummer: ");
						int zipCode = GlobalUI.inputGetInt("Indtast post nummer: ");
						String city = GlobalUI.inputGetLine("Indtast by: ");						
						long phoneNumber = GlobalUI.inputGetLong("Indtast telefon nummer: ");	
						_perController.createPerson(personId, personName, address, city, zipCode, phoneNumber);
						String password = GlobalUI.inputGetLine("Indtast det ønsket kodeord: ");
						int saId = _saController.createSalesAssistant(password, personId);
						if(saId != 0)
							print("Ekspedienten er nu oprettet og kan logge ind med bruger id: " + saId);
						else
							print("Der skete en fejl under oprettelsen af ekspedienten");
					}
					catch(Exception ex)
					{
						print(GlobalUI.errorHandling(99));
						Thread.sleep(2000);
					}			

					GlobalUI.inputGetLine("Tryk på enter for at forsætte..");	
					execSalesAsstMenu();
					break;
					// End of section
				case 2:
					// Start of section
					try
					{
						salesAsstId = GlobalUI.inputGetInt("Indtast ID på ekspedienten: ");
						sa = _saController.getSalesAssistant(salesAsstId);
						if(sa != null)
							print(GlobalUI.getSalesAssistantInfo(sa));
						else
							print("En ekspedient med det ID blev ikke fundet!");
					}	
					catch(Exception ex)
					{
						print(GlobalUI.errorHandling(99));
						Thread.sleep(2000);
					}

					GlobalUI.inputGetLine("Tryk på enter for at forsætte..");
					execSalesAsstMenu();
					break;
					// End of section
				case 3:
					// Start of section
					try
					{
						long personId = GlobalUI.inputGetLong("Indtast CPR nummer (uden -): ");
						String personName = GlobalUI.inputGetLine("Indtast navn: ");
						String address = GlobalUI.inputGetLine("Indtast vejnavn + husnummer: ");
						int zipCode = GlobalUI.inputGetInt("Indtast post nummer: ");
						String city = GlobalUI.inputGetLine("Indtast by: ");						
						//long phoneNumber = GlobalUI.inputGetLong("Indtast telefon nummer: ");	
						succeeded = _perController.updatePerson(personId, personName, address, city, zipCode);
						if(succeeded)
							print("Ekspedientens informationer er nu opdateret!");
						else
							print("Der skete en fejl under opdateringen af ekspedientens informationer");
					}
					catch(Exception ex)
					{
						print(GlobalUI.errorHandling(99));
						Thread.sleep(2000);
					}			

					GlobalUI.inputGetLine("Tryk på enter for at forsætte..");	
					execSalesAsstMenu();
					break;
					// End of section
				case 4:
					// Start of section
					try
					{
						print("Bemærk denne funktion kan ikke fortrydes, så snart den er udført!");
						salesAsstId = GlobalUI.inputGetInt("Indtast ID på ekspedienten: ");
						sa = _saController.getSalesAssistant(salesAsstId);
						if(sa != null)
						{
							print(GlobalUI.getSalesAssistantInfo(sa));
							String answer = GlobalUI.inputGetLine("Er du sikker på du vil slette denne ekspedient? (Ja/Nej): ");
							if(answer.toLowerCase().equals("ja"))
							{
								succeeded = _saController.removeSalesAssistant(salesAsstId);
								if(succeeded)
									print("Ekspedienten er nu slettet");
								else
									print("Ekspedienten kunne ikke slettes");
							}
						}
						else
							print("En ekspedient med det ID blev ikke fundet!");
					}
					catch(Exception ex)
					{
						print(GlobalUI.errorHandling(99));
						Thread.sleep(2000);
					}					

					GlobalUI.inputGetLine("Tryk på enter for at forsætte..");
					execSalesAsstMenu();
					break;
					// End of section
				case 5:
					// Start of section
					for(SalesAssistant salesAsst : _saController.getAllSalesAssistants())
						print(GlobalUI.getSalesAssistantInfo(salesAsst));

					GlobalUI.inputGetLine("Tryk på enter for at forsætte..");
					execSalesAsstMenu();
					break;
					// End of section
				case 6:
					// Start of section
					try
					{
						salesAsstId = GlobalUI.inputGetInt("Indtast ID på ekspedienten: ");
						String newPassword = GlobalUI.inputGetLine("Indtast det ønskede kodeord: ");
						succeeded = _saController.changePassword(salesAsstId, newPassword);
						if(succeeded)
							print("Kodeordet for ekspedienten blev ændret");
						else
							print("Kodeordet til ekspedienten kunne ikke ændres");
					}
					catch(Exception ex)
					{
						print(GlobalUI.errorHandling(99));
						Thread.sleep(2000);
					}					

					GlobalUI.inputGetLine("Tryk på enter for at forsætte..");
					execSalesAsstMenu();
					break;
					// End of section
				case 0:
					_mainmenuUI = new MainMenuUI();
					_mainmenuUI.execMainMenu();
					break;
				default:
					print(GlobalUI.errorHandling(02));
					Thread.sleep(2000);
					execSalesAsstMenu();
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
            execSalesAsstMenu();
        }
	}

	private void print(String inputLine)
	{
		System.out.println("                      " + inputLine);
	}
}
