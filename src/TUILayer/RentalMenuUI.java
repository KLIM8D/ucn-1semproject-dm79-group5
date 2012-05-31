package TUILayer;
import ControlLayer.LeaseCtrl;
import ModelLayer.Lease;
import ModelLayer.LeasingItem;
import java.util.ArrayList;

/**
 * Text User Interface - Rental
 *
 * @date (05.28.2012)
 */

public class RentalMenuUI
{
    private MainMenuUI _mainmenuUI;
    private LeaseCtrl _leaseCtrl;
    
    public RentalMenuUI()
    {
        _leaseCtrl = new LeaseCtrl();
    }

    public void execRentalMenu()
    {
        GlobalUI.tuiHeader();

        System.out.println("\n\n\n                    -------------------------------------------------------------------");
        System.out.println("                    ¦                            Udlejning                            ¦");
        System.out.println("                    -------------------------------------------------------------------");
        System.out.println("                    ¦  1) - Ny udlejning                                               ¦");
        System.out.println("                    ¦  2) - Find udlejning (udlejningsnummer)                          ¦");
        System.out.println("                    ¦  3) - Retunering af udlejning                                    ¦");
        System.out.println("                    ¦  4) - Overskredet afleveringsdato                                ¦");
        System.out.println("                    ¦  5) - Find Alle udlejninger                                      ¦");
        System.out.println("                    ¦  6) - Find udlejning (kundenummer)                               ¦");
        System.out.println("                    ¦  7) - Find Udlejningsprodukt                                     ¦");
        System.out.println("                    ¦  8) - Nyt Udlejningsprodukt                                      ¦");
        System.out.println("                    ¦  9) - Opdater Udlejningsprodukt                                  ¦");
        System.out.println("                    ¦ 10) - Slet Udlejningsprodukt                                     ¦");
        System.out.println("                    ¦  0) - Returnere til hovedmenu                                    ¦");
        System.out.println("                    -------------------------------------------------------------------");

        int userentry = GlobalUI.inputGetInt("\n                      Menu valg: ");

        try
        {
            switch (userentry)
            {
                case 1:
                    long customerId = 0;
                    long itemNumber = 0;
                    int days = 0;
                    try{
                        customerId = GlobalUI.inputGetLong("Indtast Kundenummer: ");
                    }
                    catch(Exception ex)
                    {
                        System.out.println("*FEJL* Kundenummer bestaar kun af tal");
                        GlobalUI.inputGetLine("Tryk enter for at fortsaette..");
                        execRentalMenu();
                    }
                    try{
                        itemNumber = GlobalUI.inputGetLong("Indtast Produktnummer: ");
                    }
                    catch(Exception ex)
                    {
                        System.out.println("*FEJL* UdlejningsID bestaar kun af tal");
                        GlobalUI.inputGetLine("Tryk enter for at fortsaette..");
                        execRentalMenu();
                    }
                    try{
                        days = GlobalUI.inputGetInt("Indtast udlejningens varighed i dage: ");
                    }
                    catch(Exception ex)
                    {
                        System.out.println("*FEJL* UdlejningsID bestaar kun af tal");
                        GlobalUI.inputGetLine("Tryk enter for at fortsaette..");
                        execRentalMenu();
                    }
                    boolean done = _leaseCtrl.createLease(customerId, itemNumber, days);
                    if(!done)
                    {
                        System.out.println(GlobalUI.errorHandling(99));
                        GlobalUI.inputGetLine("Tryk enter for at fortsaette..");
                    }
                    else
                    {
                        System.out.println("Udlaanet er blevet oprettet og gemt!");
                        GlobalUI.inputGetLine("Tryk enter for at fortsaette..");
                    }
                    
                    execRentalMenu();
                    break;

                case 2:
                    int leaseId = 0;
                    try{
                        leaseId = GlobalUI.inputGetInt("Indtast Udlejnings-ID: ");
                        Lease lease = _leaseCtrl.getLease(leaseId);
                        if(lease == null)
                        {
                            System.out.println(GlobalUI.errorHandling(99));
                            GlobalUI.inputGetLine("Tryk enter for at fortsaette..");
                        }
                        else
                        {
                            System.out.println(GlobalUI.getLeaseInfo(lease));
                            GlobalUI.inputGetLine("Tryk enter for at fortsaette..");
                        }
                    }
                    catch(Exception ex)
                    {
                        System.out.println("*FEJL* UdlejningsID bestaar kun af tal");
                        GlobalUI.inputGetLine("Tryk enter for at fortsaette..");
                    }
                    
                    execRentalMenu();
                    break;

                case 3:
                    try{
                        leaseId = GlobalUI.inputGetInt("Indtast Udlejnings-ID: ");
                        done = _leaseCtrl.removeLease(leaseId);
                        if(!done)
                        {
                            System.out.println(GlobalUI.errorHandling(99));
                            GlobalUI.inputGetLine("Tryk enter for at fortsaette..");
                        }
                        else
                        {
                            System.out.println("Udlaanet er blevet afleveret");
                            GlobalUI.inputGetLine("Tryk enter for at fortsaette..");
                        }
                    }
                    catch(Exception ex)
                    {
                        System.out.println("*FEJL* UdlejningsID bestaar kun af tal");
                        GlobalUI.inputGetLine("Tryk enter for at fortsaette..");
                    }                    
                
                    execRentalMenu();
                    break;
                    
                case 4:
                    leaseId = 0;
                    try{
                        for(Lease lease : _leaseCtrl.expiredLeases())
                        {
                            System.out.println(GlobalUI.getLeaseInfo(lease));
                        }
                        if(_leaseCtrl.expiredLeases().size() == 0)
                        {
                            System.out.println("Der er ingen for gamle udlaan!");
                            GlobalUI.inputGetLine("Tryk enter for at fortsaette..");
                        }
                        else
                        {
                            GlobalUI.inputGetLine("Tryk enter for at fortsaette..");
                        }
                    }
                    catch(Exception ex)
                    {
                        System.out.println(GlobalUI.errorHandling(99));
                        GlobalUI.inputGetLine("Tryk enter for at fortsaette..");
                    }                    
                
                    execRentalMenu();
                    break;
                
                case 5:
                    
                    try{
                        for(Lease lease : _leaseCtrl.getLease())
                        {
                            System.out.println(GlobalUI.getLeaseInfo(lease));
                        }
                        if(_leaseCtrl.getLease().size() == 0)
                        {
                            System.out.println("Der er ingen udlaan i oejeblikket");
                            GlobalUI.inputGetLine("Tryk enter for at fortsaette..");
                        }
                        else
                        {
                            GlobalUI.inputGetLine("Tryk enter for at fortsaette..");
                        }
                    }
                    catch(Exception ex)
                    {
                        GlobalUI.inputGetLine("Tryk enter for at fortsaette..");
                    }                        
                    execRentalMenu();
                    break;
                    
                case 6:
                    itemNumber = 0;
                    try{
                        for(Lease lease : _leaseCtrl.getLease(itemNumber))
                        {
                            System.out.println(GlobalUI.getLeaseInfo(lease));
                        }
                        if(_leaseCtrl.expiredLeases().size() == 0)
                        {
                            System.out.println("Der er ingen udlaan med dette produkt");
                            GlobalUI.inputGetLine("Tryk enter for at fortsaette..");
                        }
                        else
                        {
                            GlobalUI.inputGetLine("Tryk enter for at fortsaette..");
                        }
                    }
                    catch(Exception ex)
                    {
                        System.out.println(GlobalUI.errorHandling(99));
                        GlobalUI.inputGetLine("Tryk enter for at fortsaette..");
                    }
                    execRentalMenu();
                    break;
                    
                case 7:
                    itemNumber = 0;
                    try{
                        itemNumber = GlobalUI.inputGetInt("Indtast Udlejnings-ID: ");
                        LeasingItem leaseItem = _leaseCtrl.getLeaseItem(itemNumber);
                        if(leaseItem == null)
                        {
                            System.out.println(GlobalUI.errorHandling(99));
                            GlobalUI.inputGetLine("Tryk enter for at fortsaette..");
                        }
                        else
                        {
                            System.out.println(GlobalUI.getLeasingItemInfo(leaseItem));
                            GlobalUI.inputGetLine("Tryk enter for at fortsaette..");
                        }
                    }
                    catch(Exception ex)
                    {
                        System.out.println("*FEJL* Produktnummer bestaar kun af tal");
                        GlobalUI.inputGetLine("Tryk enter for at fortsaette..");
                    }
                    
                    execRentalMenu();
                    break;                   
                
                case 8:
                    itemNumber = 0;
                    String itemName = "";
                    String rentPrice = "";
                    int maxAvaible = 0;
                    try{
                        itemNumber = GlobalUI.inputGetInt("Indtast Udlejnings-ID: ");
                        itemName = GlobalUI.inputGetLine("Indtast Produkt navn: ");
                        rentPrice = GlobalUI.inputGetLine("Indtast Udlejlingspris: ");
                        maxAvaible = GlobalUI.inputGetInt("Indtast Maximal beholdning: ");
                        done = _leaseCtrl.createLeaseItem(itemNumber, itemName, rentPrice, maxAvaible);
                        if(!done)
                        {
                            System.out.println(GlobalUI.errorHandling(99));
                            GlobalUI.inputGetLine("Tryk enter for at fortsaette..");
                        }
                        else
                        {
                            System.out.println("Produktet er blevet oprettet i udlejningssystemet!");
                            GlobalUI.inputGetLine("Tryk enter for at fortsaette..");
                        }
                    }
                    catch(Exception ex)
                    {
                        System.out.println(GlobalUI.errorHandling(99));
                        GlobalUI.inputGetLine("Tryk enter for at fortsaette..");
                    }                
                    
                    execRentalMenu();
                    break;                    
                  
                case 9:
                    
                    execRentalMenu();
                    break;                   
                    
                case 10:
                    try{
                        itemNumber = GlobalUI.inputGetInt("Indtast Produkt-ID: ");
                        done = _leaseCtrl.deleteLeaseItem(itemNumber);
                        if(!done)
                        {
                            System.out.println(GlobalUI.errorHandling(99));
                            GlobalUI.inputGetLine("Tryk enter for at fortsaette..");
                        }
                        else
                        {
                            System.out.println("Produktet er blevet slettet fra udlejningslisten!");
                            GlobalUI.inputGetLine("Tryk enter for at fortsaette..");
                        }
                    }
                    catch(Exception ex)
                    {
                        System.out.println("*FEJL* UdlejningsID bestaar kun af tal");
                        GlobalUI.inputGetLine("Tryk enter for at fortsaette..");
                    }                    
                    
                    execRentalMenu();
                    break;
                
                case 0:
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