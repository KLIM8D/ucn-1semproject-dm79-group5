package TUILayer;
import java.util.Scanner;

/**
 * Text User Interface - Economy
 *
 * @date (05.28.2012)
 */

public class EconomyMenuUI
{
	private MainMenuUI _mainmenuUI;

	public void execEconomyMenu()
	{
		GlobalUI.tuiHeader();

		System.out.println("\n\n\n                    -------------------------------------------------------------------");
		System.out.println("                    ¦                             Økonomi                             ¦");
		System.out.println("                    -------------------------------------------------------------------");
		System.out.println("                    ¦ 1) - Generere statistik ud fra ekspedient                       ¦");
		System.out.println("                    ¦ 2) - Generere statistik ud fra vare                             ¦");
		System.out.println("                    ¦                                                                 ¦");
		System.out.println("                    ¦ 9) - Returnere til hovedmenu                                    ¦");
		System.out.println("                    -------------------------------------------------------------------");

		Scanner keyboard = new Scanner(System.in);

		System.out.print("\n                      Menu valg: ");
		int userentry = keyboard.nextInt();

		try
		{
			switch (userentry)
			{
				case 1:
					// Removal of section when function is integrated
					// Start of section
					System.out.print("\n                      " + GlobalUI.errorHandling(03));
					Thread.sleep(2000);
					execEconomyMenu();
					break;
					// End of section

				case 2:
					// Removal of section when function is integrated
					// Start of section
					System.out.print("\n                      " + GlobalUI.errorHandling(03));
					Thread.sleep(2000);
					execEconomyMenu();
					break;
					// End of section

				case 9:
					_mainmenuUI = new MainMenuUI();
					_mainmenuUI.execMainMenu();
					break;

				default:
					System.out.print("\n                      " + GlobalUI.errorHandling(02));
					Thread.sleep(2000);
					execEconomyMenu();
					break;
			}

		}

		catch (Exception e)
		{
			System.out.print("\n                      " + GlobalUI.errorHandling(99));
		}
	}
}