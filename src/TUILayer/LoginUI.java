package TUILayer;
import java.util.Scanner;

import ControlLayer.SalesAssistantCtrl;

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
    private SalesAssistantCtrl _saController;

    public LoginUI() 
    {
        _saController = new SalesAssistantCtrl();
    }
    
	public void execLogin()
    {
        printLogin();
    }

    private void printLogin()
    {
        GlobalUI.tuiHeader();

        print("\n\n\n                    -------------------------------------------------------------------");
        print("                    ¦                          Adgangskontrol                         ¦");
        print("                    -------------------------------------------------------------------");

        Scanner keyboard = new Scanner(System.in);
        int userId = GlobalUI.inputGetInt("\n                      Indtast Bruger ID: ");
        String password = GlobalUI.inputGetLine("                      Indtast Adgangskode: ");

        try
        {
                if(!GlobalUI.checkIfEmpty(password))
                {
                    boolean success = _saController.checkLogin(userId, password);
                    if(success || userId == 1) //userId == 1 for testing. Removed at final release
                    {
                        _mainmenuUI = new MainMenuUI();
                        _mainmenuUI.execMainMenu();
                    }
                    else
                    {
                        print(GlobalUI.errorHandling(4));
                        Thread.sleep(2000);
                        printLogin();
                    }
                }

                else
                {
                    print(GlobalUI.errorHandling(1));

                    Thread.sleep(2000);
                    printLogin();
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