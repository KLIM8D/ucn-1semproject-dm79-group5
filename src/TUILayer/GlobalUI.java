package TUILayer;

import java.util.Scanner;
import ModelLayer.*;

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

    /**
    * Get next int from user input
    *
    * @param text        the text displayed for the user; Example: "Type the id:"
    *
    */
    public static int inputGetInt(String text)
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println(text);
        int no = keyboard.nextInt();
        return no;
    }

    /**
    * Get next double from user input
    *
    * @param text        the text displayed for the user; Example: "Type the price:"
    *
    */
    public static double inputGetDouble(String text)
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println(text);
        double no = keyboard.nextDouble();
        return no;
    }

    /**
    * Get next String from user input
    *
    * @param text        the text displayed for the user; Example: "Type the name:"
    *
    */        
    public static String inputGetLine(String text)
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println(text);
        String title = keyboard.nextLine();
        return title;
    }

    /**
    * Get next long from user input
    *
    * @param text        the text displayed for the user; Example: "Type the id:"
    *
    */        
    public static long inputGetLong(String text)
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println(text);
        long no = keyboard.nextLong();
        return no;
    }

    /**
    * Display information about person
    *
    * @param person        the person object
    *
    */        
    public static String getPersonInfo(Person person)
    {
        StringBuilder sb = new StringBuilder();
        String newLine = System.getProperty("line.separator");
        sb.append("id: " + person.getPersonId() + newLine);
        sb.append("Navn: " + person.getName() + newLine);
        sb.append("Adresse: " + person.getAddress() + newLine);
        sb.append("Post. nr / by: " + person.getZipCode() + " / " + person.getCity() + newLine);
        sb.append("Telefon nummer: " + person.getPhoneNumber() + newLine);        

        return sb.toString();
    }

    /**
    * Display information about customer
    *
    * @param customer        the customer object
    *
    */        
    public static String getCustomerInfo(Customer customer)
    {
        StringBuilder sb = new StringBuilder();
        String newLine = System.getProperty("line.separator");
        sb.append("id: " + customer.getCustomerId() + newLine);
        if(customer.getIsBusiness())
        {
            sb.append("CVR Nr.: " + customer.getBusiness().getCvrNo() + newLine);
            sb.append("Kontakt person: " + customer.getBusiness().getContactPerson() + newLine);
        }
        sb.append(getPersonInfo(customer.getPerson()));
        sb.append("Rabat aftaler: " + newLine);
        for(Discount disc : customer.getDiscounts())
            sb.append(translateDiscountTypes(disc.getDiscountType()) + " Beløb: " + disc.getDiscountValue() + " kr." + newLine);       

        return sb.toString();
    }

    /**
    * Display information about salesassistant
    *
    * @param salesAsst        the salesassistant object
    *
    */        
    public static String getSalesAssistantInfo(SalesAssistant salesAsst)
    {
        StringBuilder sb = new StringBuilder();
        String newLine = System.getProperty("line.separator");
        sb.append("id: " + salesAsst.getSalesAssistantId() + newLine);
        sb.append("Hashed kodeord: " + salesAsst.getPassword() + newLine);
        sb.append("Kodeords salt: " + salesAsst.getSalt() + newLine);
        sb.append(getPersonInfo(salesAsst.getPerson()));

        return sb.toString();
    }


    public static String translateDiscountTypes(int discType)
    {
        switch (discType) 
        {
            case 1:
                return "Rabat 1";
            case 2:
                return "Rabat 2";
            case 3:
                return "Rabat 3";
            case 4:
                return "Rabat 4";
            case 5:
                return "Rabat 5";
        }

        return "Ukendt rabat gruppe";
    }
}