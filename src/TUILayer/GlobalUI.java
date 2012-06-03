package TUILayer;

import java.util.Scanner;
import java.text.SimpleDateFormat;
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
            case 04:
                return "FEJL: Forkert brugernavn eller password. Prøv igen.";
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
        print(text);
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
        print(text);
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
        print(text);
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
        print(text);
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
        sb.append(centerText() + "id: " + person.getPersonId() + newLine);
        sb.append(centerText() + "Navn: " + person.getName() + newLine);
        sb.append(centerText() + "Adresse: " + person.getAddress() + newLine);
        sb.append(centerText() + "Post. nr / by: " + person.getZipCode() + " / " + person.getCity() + newLine);
        sb.append(centerText() + "Telefon nummer: " + person.getPhoneNumber() + newLine);        

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
            sb.append(centerText() + "CVR Nr.: " + customer.getBusiness().getCvrNo() + newLine);
            sb.append(centerText() + "Kontakt person: " + customer.getBusiness().getContactPerson() + newLine);
        }
        sb.append(getPersonInfo(customer.getPerson()));
        sb.append(centerText() + "Rabat aftaler: " + newLine);
        for(Discount disc : customer.getDiscounts())
            sb.append(centerText() + translateDiscountTypes(disc.getDiscountType()) + " Beløb: " + disc.getDiscountValue() + " kr." + newLine);       

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
        sb.append(centerText() + "Hashed kodeord: " + salesAsst.getPassword() + newLine);
        sb.append(centerText() + "Kodeords salt: " + salesAsst.getSalt() + newLine);
        sb.append(getPersonInfo(salesAsst.getPerson()));

        return sb.toString();
    }

    /**
    * Display information about the product
    *
    * @param product        the product object
    *
    */        
    public static String getProductInfo(Product product)
    {
        StringBuilder sb = new StringBuilder();
        String newLine = System.getProperty("line.separator");
        sb.append("Produkt nummer: " + product.getItemNumber() + newLine);
        sb.append(centerText() + "Produktnavn: " + product.getItemName() + newLine);
        sb.append(centerText() + "Minimums lager: " + product.getMinInStock() + newLine);
        sb.append(centerText() + "Maksimums lager: " + product.getMaxInStock() + newLine);
        sb.append(centerText() + "Pris: " + product.getPrice() + " kr." + newLine);
        sb.append(centerText() + getProductCategoryInfo(product.getProductCategory(), false));

        return sb.toString();
    }

    /**
    * Display information about the productcategory
    *
    * @param category        the productcategory object
    *
    */        
    public static String getProductCategoryInfo(ProductCategory category, boolean wantId)
    {
        StringBuilder sb = new StringBuilder();
        String newLine = System.getProperty("line.separator");
        if(wantId)
        {
            sb.append("Kategori Id: " + category.getCategoryId() + newLine);
            sb.append(centerText() + "Kategori navn: " + category.getCategoryName() + newLine);
        }
        else
            sb.append("Produkt kategori: " + category.getCategoryName() + newLine);

        return sb.toString();
    }

    /**
    * Display information about the lease
    *
    * @param lease        the lease object
    *
    */        
    public static String getLeaseInfo(Lease lease)
    {
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String newLine = System.getProperty("line.separator");
        sb.append("ID: " + lease.getLeaseId() + newLine);
        sb.append(centerText() + "Tilknyttet kunde: " + newLine);
        sb.append(getCustomerInfo(lease.getCustomer()));
        sb.append(centerText() + "Serienummer: " + getSerialNumberInfo(lease.getSerialNumber()) + newLine);
        sb.append(centerText() + "Start dato: " + formatter.format(lease.getRentStartDate()) + newLine);
        sb.append(centerText() + "Slut dato: " + formatter.format(lease.getRentEndDate()) + newLine);

        return sb.toString();
    }

    public static String getLeasingItemInfo(LeasingItem leasingItem)
    {
        StringBuilder sb = new StringBuilder();
        String newLine = System.getProperty("line.separator");
        sb.append("ID: " + leasingItem.getItemNumber() + newLine);
        sb.append(centerText() + "Produktnavn: " + leasingItem.getItemName() + newLine);
        sb.append(centerText() + "Leje Pris: " + leasingItem.getRentPrice() + newLine);
        sb.append(centerText() + "Maksimum tilgængelig: " + leasingItem.getMaxAvaible() + newLine);

        return sb.toString();
    }
    
    /**
    * Display information about the serialnumber
    *
    * @param serialnumber        the serialnumber object
    *
    */        
    public static String getSerialNumberInfo(SerialNumber serialnumber)
    {
        StringBuilder sb = new StringBuilder();
        String newLine = System.getProperty("line.separator");
        sb.append("Serienummer: " + serialnumber.getSerialNumber() + newLine);
        sb.append(centerText() + "Tilgængelig: " + passBoolean(serialnumber.getIsAvaible()) + newLine);
        sb.append(centerText() + "Produkt nummer: " + serialnumber.getItemNumber() + newLine);

        return sb.toString();
    }

    /**
    * Display information about the product group
    *
    * @param group        the productgroup object
    *
    */        
    public static String getProductGroupInfo(ProductGroup group)
    {
        StringBuilder sb = new StringBuilder();
        String newLine = System.getProperty("line.separator");
        sb.append("ID: " + group.getProductGroupId() + newLine);
        sb.append(centerText() + "Titel: " + group.getProductGroupName() + newLine);
        sb.append(centerText() + "Total pris: " + group.getPrice() + newLine);
        for(ProductGroupItem item : group.getItems())
            sb.append(getProductGroupItemInfo(item));
        
        return sb.toString();
    }

    /**
    * Display information about the product group item
    *
    * @param item        the productgroupitem object
    *
    */        
    public static String getProductGroupItemInfo(ProductGroupItem item)
    {
        StringBuilder sb = new StringBuilder();
        String newLine = System.getProperty("line.separator");
        sb.append(centerText() + "Produkt nummer: " + item.getProduct().getItemNumber() + newLine);
        sb.append(centerText() + "Antal: " + item.getQuantity() + newLine);

        return sb.toString();
    }

    /**
    * Display information about the product location
    *
    * @param location        the productlocation object
    *
    */        
    public static String getProductLocationInfo(ProductLocation location)
    {
        StringBuilder sb = new StringBuilder();
        String newLine = System.getProperty("line.separator");
        sb.append("ID: " + location.getLocationId() + newLine);
        sb.append(centerText() + "Navn: " + location.getLocationName() + newLine);
        sb.append(centerText() + "Adresse: " + location.getAddress() + newLine);
        sb.append(centerText() + "By: " + location.getCity() + newLine);
        sb.append(centerText() + "Post nummer: " + location.getZipCode() + newLine);

        return sb.toString();
    }

    public static String passBoolean(boolean bool)
    {
        return bool ? "Ja" : "Nej";
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

    private static String centerText()
    {
        return "                      ";
    }

    private static void print(String inputLine)
    {
        System.out.print(centerText() + inputLine);
    }
}
