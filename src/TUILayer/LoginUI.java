package TUILayer;
import java.util.Scanner;

/**
 * Text User Interface - Login
 *
 * Managing access control for the system.
 *
 * @date (05.26.2012)
 */

public class LoginUI
{
    private MainMenuUI _mainmenuUI;
    
	public void execLogin()
    {
        printLogin();
    }

    private void printLogin()
    {
        GlobalUI.tuiHeader();

        System.out.println("\n\n\n                    -------------------------------------------------------------------");
        System.out.println("                    ¦                          Adgangskontrol                         ¦");
        System.out.println("                    -------------------------------------------------------------------");

        Scanner keyboard = new Scanner(System.in);

        System.out.print("\n                      Indtast Bruger ID: ");
        String username = keyboard.nextLine();
        System.out.print("                      Indtast Adgangskode: ");
        String password = keyboard.nextLine();

        try
        {
                if(!GlobalUI.checkIfEmpty(username) && !GlobalUI.checkIfEmpty(password))
                {
                    username.trim();
                    password.trim();
                }

                else
                {
                    System.out.print("\n                      " + GlobalUI.errorHandling(1));

                    Thread.sleep(2000);
                    printLogin();
                }

                _mainmenuUI = new MainMenuUI();
                _mainmenuUI.execMainMenu();
        }

        catch (Exception e)
        {
            System.out.print("\n                      " + GlobalUI.errorHandling(99));
        }
    }
}