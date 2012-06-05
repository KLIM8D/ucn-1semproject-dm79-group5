package TUILayer;

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
        System.out.println("\n\n");
        print("-------------------------------------------------------------------");
        print("¦                          Adgangskontrol                         ¦");
        print("-------------------------------------------------------------------");

        

        try
        {
            int userId = GlobalUI.inputGetInt("Indtast Bruger ID: ");
            String password = GlobalUI.inputGetLine("Indtast Adgangskode: ");
            if(userId > 0 && !GlobalUI.checkIfEmpty(password))
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
                    Thread.sleep(1000);
                }
            }
            else
            {
                print(GlobalUI.errorHandling(1));
                Thread.sleep(1000);
            }                
        }
        catch (Exception ex)
        {
            print(GlobalUI.errorHandling(99));
            try
            {
                Thread.sleep(2000);  
            }
            catch(Exception ee)
            {}                  
        }
        printLogin();        
    }

    private void print(String inputLine)
    {
        System.out.println("                      " + inputLine);
    }
}
