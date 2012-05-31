package TUILayer;
import ControlLayer.LeaseCtrl;
import ModelLayer.Lease;
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
        System.out.println("                    Â¦                            Udlejning                            Â¦");
        System.out.println("                    -------------------------------------------------------------------");
        System.out.println("                    Â¦ 1) - Ny udlejning                                               Â¦");
        System.out.println("                    Â¦ 2) - Find udlejning                                             Â¦");
        System.out.println("                    Â¦ 3) - Retunering af udlejning                                    Â¦");
        System.out.println("                    Â¦ 4) - Overskredet afleveringsdato                                Â¦");
        System.out.println("                    Â¦                                                                 Â¦");
        System.out.println("                    Â¦ 9) - Returnere til hovedmenu                                    Â¦");
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
                        System.out.println("*FEJL* Kundenummer består kun af tal");
                        GlobalUI.inputGetLine("Tryk enter for at fortsætte..");
                        execRentalMenu();
                    }
                    try{
                        itemNumber = GlobalUI.inputGetLong("Indtast Produktnummer: ");
                    }
                    catch(Exception ex)
                    {
                        System.out.println("*FEJL* UdlejningsID består kun af tal");
                        GlobalUI.inputGetLine("Tryk enter for at fortsætte..");
                        execRentalMenu();
                    }
                    try{
                        days = GlobalUI.inputGetInt("Indtast udlejningens varighed i dage: ");
                    }
                    catch(Exception ex)
                    {
                        System.out.println("*FEJL* UdlejningsID består kun af tal");
                        GlobalUI.inputGetLine("Tryk enter for at fortsætte..");
                        execRentalMenu();
                    }
                    boolean done = _leaseCtrl.createLease(customerId, itemNumber, days);
                    if(!done)
                    {
                        System.out.println("GlobalUI.errorHandling(99)");
                        GlobalUI.inputGetLine("Tryk enter for at fortsætte..");
                    }
                    else
                    {
                        System.out.println("Udlånet er blevet oprettet og gemt!");
                        GlobalUI.inputGetLine("Tryk enter for at fortsætte..");
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
                            GlobalUI.inputGetLine("Tryk enter for at fortsætte..");
                        }
                        else
                        {
                            System.out.println(GlobalUI.getLeaseInfo(lease));
                            GlobalUI.inputGetLine("Tryk enter for at fortsætte..");
                        }
                    }
                    catch(Exception ex)
                    {
                        System.out.println("*FEJL* UdlejningsID består kun af tal");
                        GlobalUI.inputGetLine("Tryk enter for at fortsætte..");
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
                            GlobalUI.inputGetLine("Tryk enter for at fortsætte..");
                        }
                        else
                        {
                            System.out.println("Udlånet er blevet afleveret");
                            GlobalUI.inputGetLine("Tryk enter for at fortsætte..");
                        }
                    }
                    catch(Exception ex)
                    {
                        System.out.println("*FEJL* UdlejningsID består kun af tal");
                        GlobalUI.inputGetLine("Tryk enter for at fortsætte..");
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
                            System.out.println("Der er ingen for gamle udlån!");
                            GlobalUI.inputGetLine("Tryk enter for at fortsætte..");
                        }
                        else
                        {
                            GlobalUI.inputGetLine("Tryk enter for at fortsætte..");
                        }
                    }
                    catch(Exception ex)
                    {
                        GlobalUI.inputGetLine("Tryk enter for at fortsætte..");
                    }                    
                
                    execRentalMenu();
                    break;

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