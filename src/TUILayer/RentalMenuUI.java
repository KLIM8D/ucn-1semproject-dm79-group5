package TUILayer;
import ControlLayer.LeaseCtrl;
import ModelLayer.Lease;
import ModelLayer.LeasingItem;

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

        print("--------------------------------------------------------------------");
        print("¦                            Udlejning                             ¦");
        print("--------------------------------------------------------------------");
        print("¦  1) - Ny udlejning                                               ¦");
        print("¦  2) - Find udlejning (udlejningsnummer)                          ¦");
        print("¦  3) - Retunering af udlejning                                    ¦");
        print("¦  4) - Overskredet afleveringsdato                                ¦");
        print("¦  5) - Find Alle udlejninger                                      ¦");
        print("¦  6) - Find udlejning (kundenummer)                               ¦");
        print("¦  7) - Find Udlejningsprodukt                                     ¦");
        print("¦  8) - Nyt Udlejningsprodukt                                      ¦");
        print("¦  9) - Opdater Udlejningsprodukt                                  ¦");
        print("¦ 10) - Slet Udlejningsprodukt                                     ¦");
        print("¦                                                                  ¦");
        print("¦  0) - Returnere til hovedmenu                                    ¦");
        print("-------------------------------------------------------------------");

        

        try
        {
            System.out.print("\n");
            int userentry = GlobalUI.inputGetInt("Menu valg: ");
            System.out.println("\n");
            switch (userentry)
            {
                case 1:
                    long customerId = 0;
                    long itemNumber = 0;
                    int days = 0;
                    try
                    {
                        customerId = GlobalUI.inputGetLong("Indtast Kundenummer (telefon nummer): ");
                    }
                    catch(Exception ex)
                    {
                        print("*FEJL* Kundenummer består kun af tal");
                    }
                    try
                    {
                        itemNumber = GlobalUI.inputGetLong("Indtast produkt nummer: ");
                    }
                    catch(Exception ex)
                    {
                        print("*FEJL* UdlejningsID består kun af tal");
                    }
                    try
                    {
                        days = GlobalUI.inputGetInt("Indtast udlejningens varighed i dage: ");
                    }
                    catch(Exception ex)
                    {
                        print("*FEJL* UdlejningsID består kun af tal");
                    }
                    boolean done = _leaseCtrl.createLease(customerId, itemNumber, days);
                    if(!done)
                    {
                        print(GlobalUI.errorHandling(99));
                    }
                    else
                    {
                        print("Udlånet er blevet oprettet og gemt!");
                    }
                    
                    GlobalUI.inputGetLine("Tryk enter for at fortsætte..");
                    execRentalMenu();
                    break;

                case 2:
                    int leaseId = 0;
                    try
                    {
                        leaseId = GlobalUI.inputGetInt("Indtast Udlejnings-ID: ");
                        Lease lease = _leaseCtrl.getLease(leaseId);
                        if(lease == null)
                        {
                            print(GlobalUI.errorHandling(99));
                        }
                        else
                        {
                            print(GlobalUI.getLeaseInfo(lease));
                        }
                    }
                    catch(Exception ex)
                    {
                        print("*FEJL* UdlejningsID består kun af tal");
                    }

                    GlobalUI.inputGetLine("Tryk enter for at fortsætte..");
                    execRentalMenu();
                    break;

                case 3:
                    try
                    {
                        leaseId = GlobalUI.inputGetInt("Indtast Udlejnings-ID: ");
                        done = _leaseCtrl.removeLease(leaseId);
                        if(!done)
                        {
                            print(GlobalUI.errorHandling(99));
                        }
                        else
                        {
                            print("Udlånet er blevet afleveret");
                        }
                    }
                    catch(Exception ex)
                    {
                        print("*FEJL* UdlejningsID består kun af tal");
                    }                    
                    GlobalUI.inputGetLine("Tryk enter for at fortsætte..");
                    execRentalMenu();
                    break;
                    
                case 4:
                    leaseId = 0;
                    try
                    {
                        for(Lease lease : _leaseCtrl.expiredLeases())
                        {
                            print(GlobalUI.getLeaseInfo(lease));
                        }
                        if(_leaseCtrl.expiredLeases().size() == 0)
                        {
                            print("Der er ingen for gamle udlån!");
                        }
                    }
                    catch(Exception ex)
                    {
                        print(GlobalUI.errorHandling(99));
                    }                    
                    
                    GlobalUI.inputGetLine("Tryk enter for at fortsætte..");
                    execRentalMenu();
                    break;
                
                case 5:
                    
                    try
                    {
                        for(Lease lease : _leaseCtrl.getLease())
                        {
                            print(GlobalUI.getLeaseInfo(lease));
                        }
                        if(_leaseCtrl.getLease().size() == 0)
                        {
                            print("Der er ingen udlån i øjeblikket");
                        }
                    }
                    catch(Exception ex)
                    {
                        print(GlobalUI.errorHandling(99));
                    }

                    GlobalUI.inputGetLine("Tryk enter for at fortsætte..");                        
                    execRentalMenu();
                    break;
                    
                case 6:
                    try
                    {
                        customerId = GlobalUI.inputGetLong("Indtast kundenummer (telefon nummer): ");
                        for(Lease lease : _leaseCtrl.getCustomerLeases(customerId))
                        {
                            print(GlobalUI.getLeaseInfo(lease));
                        }
                        if(_leaseCtrl.getCustomerLeases(customerId).size() == 0)
                        {
                            print("Denne kunde har ingen udlån");
                        }
                    }
                    catch(Exception ex)
                    {
                        print(GlobalUI.errorHandling(99));
                    }

                    GlobalUI.inputGetLine("Tryk enter for at fortsætte..");
                    execRentalMenu();
                    break;
                    
                case 7:
                    itemNumber = 0;
                    try
                    {
                        itemNumber = GlobalUI.inputGetInt("Indtast produkt nummer: ");
                        LeasingItem leaseItem = _leaseCtrl.getLeaseItem(itemNumber);
                        if(leaseItem == null)
                        {
                            print(GlobalUI.errorHandling(99));
                        }
                        else
                        {
                            print(GlobalUI.getLeasingItemInfo(leaseItem));
                        }
                    }
                    catch(Exception ex)
                    {
                        print("*FEJL* Produktnummer består kun af tal");
                    }

                    GlobalUI.inputGetLine("Tryk enter for at fortsætte..");                    
                    execRentalMenu();
                    break;                   
                
                case 8:
                    itemNumber = 0;
                    String itemName = "";
                    String rentPrice = "";
                    int maxAvaible = 0;
                    try
                    {
                        itemNumber = GlobalUI.inputGetInt("Indtast produkt nummer: ");
                        itemName = GlobalUI.inputGetLine("Indtast produkt navn: ");
                        rentPrice = GlobalUI.inputGetLine("Indtast udlejlingspris: ");
                        maxAvaible = GlobalUI.inputGetInt("Indtast maksimal beholdning: ");
                        done = _leaseCtrl.createLeaseItem(itemNumber, itemName, rentPrice, maxAvaible);
                        if(!done)
                        {
                            print(GlobalUI.errorHandling(99));
                        }
                        else
                        {
                            print("Produktet er blevet oprettet i udlejningssystemet!");
                        }
                    }
                    catch(Exception ex)
                    {
                        print(GlobalUI.errorHandling(99));
                    }                
                    
                    GlobalUI.inputGetLine("Tryk enter for at fortsætte..");
                    execRentalMenu();
                    break;                    
                  
                case 9:
                    try
					{
						itemNumber = GlobalUI.inputGetLong("Indtast produkt nummer: ");
						LeasingItem leaseItem = _leaseCtrl.getLeaseItem(itemNumber);
						if(leaseItem != null)
						{
							itemName = GlobalUI.inputGetLine("Indtast produktets navn: ");
							rentPrice = GlobalUI.inputGetLine("Indtast udlejningsprisen: ");;
							maxAvaible = GlobalUI.inputGetInt("Indtast maksimal beholdning ");
							done = _leaseCtrl.updateLeasingItem(itemNumber, itemName, rentPrice, maxAvaible);
							if(done)
								print("Produktet er nu opdateret");
							else
								print("Produktet kunne ikke opdateres");
						}	
						else
							print("Et produkt med det ID blev ikke fundet");
					}
					catch(Exception ex)
					{
						print(GlobalUI.errorHandling(99));
						Thread.sleep(2000);
					}			

					GlobalUI.inputGetLine("Tryk på enter for at forsætte..");	
					execRentalMenu();
					break;
                    
                case 10:
                    try
                    {
                        itemNumber = GlobalUI.inputGetInt("Indtast produkt nummer: ");
                        done = _leaseCtrl.deleteLeaseItem(itemNumber);
                        if(!done)
                        {
                            print(GlobalUI.errorHandling(99));
                        }
                        else
                        {
                            print("Produktet er blevet slettet fra udlejningslisten!");
                        }
                    }
                    catch(Exception ex)
                    {
                        print("*FEJL* UdlejningsID består kun af tal");
                    }                    
                    
                    GlobalUI.inputGetLine("Tryk enter for at fortsætte..");
                    execRentalMenu();
                    break;
                
                case 0:
                    _mainmenuUI = new MainMenuUI();
                    _mainmenuUI.execMainMenu();
                    break;

                default:
                    print(GlobalUI.errorHandling(02));
                    Thread.sleep(2000);
                    execRentalMenu();
                    break;
            }

        }

        catch (Exception e)
        {
            print(GlobalUI.errorHandling(99));
            try
            {
                Thread.sleep(2000);  
            }
            catch(Exception ee)
            {}  
            execRentalMenu();
        }
    }

    private void print(String inputLine)
    {
        System.out.println("                      " + inputLine);
    }
}
