package TUILayer;
import ControlLayer.PersonCtrl;
//import ControlLayer.CustomerCtrl;
import ModelLayer.Customer;
import ModelLayer.Person;

/**
 * Text User Interface - Customer
 *
 * @date (05.28.2012)
 */

public class CustomerMenuUI
{
    private MainMenuUI _mainmenuUI;
    private PersonCtrl _personCtrl;
    //private CustomerCtrl _customerCtrl;

    public void execCustomerMenu()
    {
        GlobalUI.tuiHeader();

        System.out.println("\n\n\n                    -------------------------------------------------------------------");
        System.out.println("                    ¦                          Kundekartotek                          ¦");
        System.out.println("                    -------------------------------------------------------------------");
        System.out.println("                    ¦ 1) - Opret kunde                                                ¦");
        System.out.println("                    ¦ 2) - Find kunde (Kundenummer)                                   ¦");
        System.out.println("                    ¦ 3) - Slet kunde                                                 ¦");
        System.out.println("                    ¦ 4) - Opdater kunde                                              ¦");
        System.out.println("                    ¦ 5) - Vis Alle kunder                                            ¦");
        System.out.println("                    ¦ 6) - Vis Business kunder                                        ¦");
        System.out.println("                    ¦ 7) - Vis Private kunder                                         ¦");
        System.out.println("                    ¦                                                                 ¦");
        System.out.println("                    ¦ 0) - Returner til hovedmenu                                     ¦");
        System.out.println("                    -------------------------------------------------------------------");
        
        int userentry = GlobalUI.inputGetInt("\n                      Menu valg: ");

        try
        {
            switch (userentry)
            {
                case 1:
                    // Removal of section when function is integrated
                    // Start of section
                    System.out.print("\n                      " + GlobalUI.errorHandling(03));
                    Thread.sleep(2000);
                    execCustomerMenu();
                    break;
                    // End of section

                case 2:
                    // Removal of section when function is integrated
                    // Start of section
                    System.out.print("\n                      " + GlobalUI.errorHandling(03));
                    Thread.sleep(2000);
                    execCustomerMenu();
                    break;
                    // End of section

                case 3:
                //                     try{
                //                         long customerId = GlobalUI.inputGetLong("Indtast Kundenummer: ");
                //                         Person per = _customerCtrl.getCustomer(customerId).getPerson();
                //                         if(per != null)
                //                         {
                //                             long personId = per.getPersonId();
                //                             boolean done = _customerCtrl.removePerson(personId);
                //                             boolean done2 = _customerCtrl.removeCustomer(customerId);
                //                             if(!done || !done2)
                //                             {
                //                                 System.out.println(GlobalUI.errorHandling(99));
                //                                 GlobalUI.inputGetLine("Tryk enter for at fortsaette..");
                //                             }
                //                             else
                //                             {
                //                                 System.out.println("Kunden er blevet slettet fra systemet!");
                //                                 GlobalUI.inputGetLine("Tryk enter for at fortsaette..");
                //                             }
                //                         }
                //                         else
                //                         {
                //                             System.out.println("Personen eksisterer ikke i person kardoteket!");
                //                             GlobalUI.inputGetLine("Tryk enter for at fortsaette..");
                //                         }
                //                     }
                //                     catch(Exception ex)
                //                     {
                //                         System.out.println("*FEJL* Kundenummer bestaar kun af tal");
                //                         GlobalUI.inputGetLine("Tryk enter for at fortsaette..");
                //                     }
                                         
                     execCustomerMenu();
                     break;
                    
                case 4:
                    // Removal of section when function is integrated
                    // Start of section
                    System.out.print("\n                      " + GlobalUI.errorHandling(03));
                    Thread.sleep(2000);
                    execCustomerMenu();
                    break;
                    // End of section

                case 5:
                    // Removal of section when function is integrated
                    // Start of section
                    System.out.print("\n                      " + GlobalUI.errorHandling(03));
                    Thread.sleep(2000);
                    execCustomerMenu();
                    break;
                    // End of section

                case 6:
                    // Removal of section when function is integrated
                    // Start of section
                    System.out.print("\n                      " + GlobalUI.errorHandling(03));
                    Thread.sleep(2000);
                    execCustomerMenu();
                    break;
                    // End of section
                    
                case 7:
                    // Removal of section when function is integrated
                    // Start of section
                    System.out.print("\n                      " + GlobalUI.errorHandling(03));
                    Thread.sleep(2000);
                    execCustomerMenu();
                    break;
                    // End of section
                    
                case 0:
                    _mainmenuUI = new MainMenuUI();
                    _mainmenuUI.execMainMenu();
                    break;

                default:
                    System.out.print("\n                      " + GlobalUI.errorHandling(02));
                    Thread.sleep(2000);
                    execCustomerMenu();
                    break;
            }

        }

        catch (Exception e)
        {
            System.out.print("\n                      " + GlobalUI.errorHandling(99));
        }
    }
}