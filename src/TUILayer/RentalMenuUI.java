package TUILayer;
import java.util.Scanner;

/**
 * Text User Interface - Rental
 *
 * @date (05.28.2012)
 */

public class RentalMenuUI
{
	private MainMenuUI _mainmenuUI;

	public void execRentalMenu()
	{
		GlobalUI.tuiHeader();

		System.out.println("\n\n\n                    -------------------------------------------------------------------");
		System.out.println("                    ¦                            Udlejning                            ¦");
		System.out.println("                    -------------------------------------------------------------------");
		System.out.println("                    ¦ 1) - Ny udlejning                                               ¦");
		System.out.println("                    ¦ 2) - Find udlejning                                             ¦");
		System.out.println("                    ¦ 3) - Retunering af udlejning                                    ¦");
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
					execRentalMenu();
					break;
					// End of section

				case 2:
					// Removal of section when function is integrated
					// Start of section
					System.out.print("\n                      " + GlobalUI.errorHandling(03));
					Thread.sleep(2000);
					execRentalMenu();
					break;
					// End of section

				case 3:
					// Removal of section when function is integrated
					// Start of section
					System.out.print("\n                      " + GlobalUI.errorHandling(03));
					Thread.sleep(2000);
					execRentalMenu();
					break;
					// End of section

				case 9:
					_mainmenuUI = new MainMenuUI();
					_mainmenuUI.execMainMenu();
					break;

				default:
					System.out.print("\n                      " + GlobalUI.errorHandling(02));
					Thread.sleep(2000);
					execRentalMenu();
					break;
			}

		}

		catch (Exception e)
		{
			System.out.print("\n                      " + GlobalUI.errorHandling(99));
		}
	}
}