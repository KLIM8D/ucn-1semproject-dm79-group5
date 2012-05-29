package TUILayer;

/**
 * Text User Interface - Global
 *
 * @date (05.26.2012)
 */

public class GlobalUI
{
	private LoginUI _loginUI;

	public void mainRun()
	{
		try
		{
            // Section is to be uncommented on final release
			//tuiHeader();
			//tuiSplash();
			//Thread.sleep(5000);

			_loginUI = new LoginUI();
            _loginUI.execLogin();
		}

		catch (Exception e)
		{
			System.out.print("\n " + GlobalUI.errorHandling(99));
		}
	}

	public static String errorHandling(int code)
    {
        switch (code) 
        {
            case 01:
                return "FEJL: Felterne Bruger ID og Adgangskode skal være udfyldt!";
            case 02:
                return "FEJL: Ukendt menu valg!";
            case 03:
                return "FEJL: Funktionen ikke integreret!";
        }

        return "FEJL: En ukendt system fejl er hændt!";
    }

    public static boolean checkIfEmpty(String data)
    {
        return data.length() == 0;
    }

    public static void tuiHeader()
    {
        System.out.println("\f __      __       _   _     _                   ____                                        _");
        System.out.println(" \\ \\    / /      | | | |   (_)                 |  _ \\                                      | |");
        System.out.println("  \\ \\  / /__  ___| |_| |__  _  ___ _ __ __ _   | |_) |_   _  __ _  __ _  ___  ___ ___ _ __ | |_ ___ _ __");
        System.out.println("   \\ \\/ / _ \\/ __| __| '_ \\| |/ _ \\ '__/ _` |  |  _ <| | | |/ _` |/ _` |/ _ \\/ __/ _ \\ '_ \\| __/ _ \\ '__|");
        System.out.println("    \\  /  __/\\__ \\ |_| |_) | |  __/ | | (_| |  | |_) | |_| | (_| | (_| |  __/ (_|  __/ | | | ||  __/ |   ");
        System.out.println("     \\/ \\___||___/\\__|_.__/| |\\___|_|  \\__, |  |____/ \\__, |\\__, |\\__, |\\___|\\___\\___|_| |_|\\__\\___|_|");
        System.out.println("                          _/ |          __/ |          __/ | __/ | __/ |");
        System.out.println("                         |__/          |___/          |___/ |___/ |___/");
    }

    public static void tuiSplash()
    {
        System.out.println("\n\n\n                    -------------------------------------------------------------------");
        System.out.println("                    ¦                                                                 ¦");
        System.out.println("                    ¦    #####                                                        ¦");
        System.out.println("                    ¦    #### _\\_  ________       1. Semesters Projekt                ¦");
        System.out.println("                    ¦    ##=-[.].]| \\      \\      UCN-DM79, Gruppe 5                  ¦");
        System.out.println("                    ¦    #(    _\\ |  |------|                                         ¦");
        System.out.println("                    ¦     #   __| |  ||||||||                                         ¦");
        System.out.println("                    ¦      \\  _/  |  ||||||||     ----------------------------------  ¦");
        System.out.println("                    ¦    --'--'-. |  | ____ |     Alex Rønne Petersen, Chris Tindbæk  ¦");
        System.out.println("                    ¦   / __      `|__|[o__o]|    Mads Nielsen, Morten Klim Sørensen  ¦");
        System.out.println("                    ¦  (____nm_______ /____\\                                          ¦");
        System.out.println("                    ¦                                                                 ¦");
        System.out.println("                    -------------------------------------------------------------------");
    }
}