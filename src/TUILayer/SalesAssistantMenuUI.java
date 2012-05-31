package TUILayer;

import java.util.Scanner;
import ControlLayer.SalesAssistantCtrl;
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

	public SalesAssistantMenuUI() 
	{
		_saController = new SalesAssistantCtrl();
	}

	public void execSalesAsstMenu()
	{
		GlobalUI.tuiHeader();

		print("\n\n\n                    -------------------------------------------------------------------");
		print("                    ¦                          Ekspedientkartotek                     ¦");
		print("                    -------------------------------------------------------------------");
		print("                    ¦ 1) - Opret ekspedient                                           ¦");
		print("                    ¦ 2) - Vis ekspedient information                                 ¦");
		print("                    ¦ 3) - Slet ekspedient                                            ¦");
		print("                    ¦ 4) - Vis alle ekspedienter                                      ¦");
		print("                    ¦ 5) - Skift password                                             ¦");
		print("                    ¦                                                                 ¦");
		print("                    ¦ 9) - Returner til hovedmenu                                     ¦");
		print("                    -------------------------------------------------------------------");

		Scanner keyboard = new Scanner(System.in);

		System.out.print("\n                      Menu valg: ");
		int userentry = keyboard.nextInt();

		try
		{
			boolean succeeded = false;
			SalesAssistant sa = null;
			int salesAsstId = 0;
			switch (userentry)
			{
				case 1:
					// Start of section
					long personId = GlobalUI.inputGetLong("Indtast ID på personen: ");
					String password = GlobalUI.inputGetLine("Indtast det ønsket kodeord: ");
					succeeded = _saController.createSalesAssistant(password, personId);
					if(succeeded)
						print("Ekspedienten er nu oprettet!");
					else
						print(GlobalUI.errorHandling(99));

					GlobalUI.inputGetLine("Tryk på enter for at forsætte..");	
					execSalesAsstMenu();
					break;
					// End of section

				case 2:
					// Start of section
					salesAsstId = GlobalUI.inputGetInt("Indtast ID på ekspedienten: ");
					sa = _saController.getSalesAssistant(salesAsstId);
					if(sa != null)
						print(GlobalUI.getSalesAssistantInfo(sa));
					else
						print("En ekspedient med det ID blev ikke fundet!");
					execSalesAsstMenu();
					break;
					// End of section

				case 3:
					// Start of section
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
								print(GlobalUI.errorHandling(99));
						}
					}
					else
						print("En ekspedient med det ID blev ikke fundet!");
					execSalesAsstMenu();
					break;
					// End of section
				case 4:
					// Start of section
					for(SalesAssistant salesAsst : _saController.getAllSalesAssistants())
						print(GlobalUI.getSalesAssistantInfo(sa));
					execSalesAsstMenu();
					break;
					// End of section
				case 5:
					// Start of section
					salesAsstId = GlobalUI.inputGetInt("Indtast ID på ekspedienten: ");
					String newPassword = GlobalUI.inputGetLine("Indtast det ønskede kodeord: ");
					succeeded = _saController.changePassword(salesAsstId, newPassword);
					if(succeeded)
						print("Kodeordet for ekspedienten blev ændret");
					else
						print(GlobalUI.errorHandling(99));
					execSalesAsstMenu();
					break;
					// End of section
				case 9:
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
		}
	}

	private void print(String inputLine)
	{
		System.out.println(inputLine);
	}
}
