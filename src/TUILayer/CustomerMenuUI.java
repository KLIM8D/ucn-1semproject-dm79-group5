package TUILayer;
import ControlLayer.PersonCtrl;
import ControlLayer.CustomerCtrl;
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
    private CustomerCtrl _customerCtrl;

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
                    try{
						long personId = GlobalUI.inputGetLong("Indtast CPR nummer (uden -): ");
						String personName = GlobalUI.inputGetLine("Indtast navn: ");
						String address = GlobalUI.inputGetLine("Indtast vejnavn + husnummer: ");
						int zipCode = GlobalUI.inputGetInt("Indtast post nummer: ");
						String city = GlobalUI.inputGetLine("Indtast by: ");						
						long phoneNumber = GlobalUI.inputGetLong("Indtast telefon nummer: ");
						boolean done = _personCtrl.createPerson(personId, personName, address, city, zipCode, phoneNumber);
                        if(done)
                        {
                            String isBusiness = GlobalUI.inputGetLine("Er der tale om en erhvervskunde? (ja/nej): ");
                            if(isBusiness.toLowerCase().equals("ja"))
                            {
                                String contactPerson =  GlobalUI.inputGetLine("Indtast kontaktperson navn: ");
                                long cvrNo =  GlobalUI.inputGetLong("Indtast virksomhedens CVR-nummer: ");
                                Person per = _personCtrl.getPerson(personId);
                                _customerCtrl.createCustomer(per);
                            }
                            else if(isBusiness.toLowerCase().equals("nej"))
                            {
                                Person per = _personCtrl.getPerson(personId);
                                _customerCtrl.createCustomer(per);
                            }
                            else
                            {
                                System.out.println("Der skete en fejl under indtastningen af kundens erhvervsoplysninger");
                            }
                        }
                        else
                            System.out.println("Der skete en fejl under indtastningen af kundens informationer");
                    }
                    catch(Exception ex)
                    {
                        System.out.println("*FEJL* Kundenummer bestaar kun af tal");
                        GlobalUI.inputGetLine("Tryk enter for at fortsaette..");                       
                    }
                
                    execCustomerMenu();
                    break;

                case 2:
                    long customerId = 0;
                    try{
                        customerId = GlobalUI.inputGetLong("Indtast Kundenummer: ");
                        Customer customer = _customerCtrl.getCustomer(customerId);
                        if(customer == null)
                        {
                            System.out.println(GlobalUI.errorHandling(99));
                            GlobalUI.inputGetLine("Tryk enter for at fortsaette..");
                        }
                        else
                        {
                            System.out.println(GlobalUI.getCustomerInfo(customer));
                            GlobalUI.inputGetLine("Tryk enter for at fortsaette..");
                        }
                    }
                    catch(Exception ex)
                    {
                        System.out.println("*FEJL* UdlejningsID bestaar kun af tal");
                        GlobalUI.inputGetLine("Tryk enter for at fortsaette..");
                    }
                    execCustomerMenu();
                    break;
                    // End of section

                case 3:
                    try{
                        customerId = GlobalUI.inputGetLong("Indtast Kundenummer: ");
                        Person per = _customerCtrl.getCustomer(customerId).getPerson();
                        if(per != null)
                        {
                            long personId = per.getPersonId();
                            boolean done = _personCtrl.removePerson(personId);
                            boolean done2 = _customerCtrl.removeCustomer(customerId);
                            if(!done || !done2)
                            {
                                System.out.println(GlobalUI.errorHandling(99));
                                GlobalUI.inputGetLine("Tryk enter for at fortsaette..");
                            }
                            else
                            {
                                System.out.println("Kunden er blevet slettet fra systemet!");
                                GlobalUI.inputGetLine("Tryk enter for at fortsaette..");
                            }
                        }
                        else
                        {
                            System.out.println("Personen eksisterer ikke i person kardoteket!");
                            GlobalUI.inputGetLine("Tryk enter for at fortsaette..");
                        }
                    }
                    catch(Exception ex)
                    {
                        System.out.println("*FEJL* Kundenummer bestaar kun af tal");
                        GlobalUI.inputGetLine("Tryk enter for at fortsaette..");
                    }
                                         
                     execCustomerMenu();
                     break;
                    
                case 4:
                    try
					{
						String contact = "";
						long cvrNo = 0;
						boolean done2 = true;
					    customerId = GlobalUI.inputGetLong("Indtast ID på lageret: ");
						Customer customer = _customerCtrl.getCustomer(customerId);
						if(customer != null)
						{
							 String cusName = GlobalUI.inputGetLine("Indtast Kundens navn: ");
							 String cusAddress = GlobalUI.inputGetLine("Indtast Kundens addresse: ");;
							 String cusCity = GlobalUI.inputGetLine("Indtast By: ");
							 int cusZipCode = GlobalUI.inputGetInt("Indtast Postnummer: ");
							 long cusPhoneNumber = GlobalUI.inputGetLong("Indtast Telefonnummer: ");
							 if(customer.getIsBusiness())
							 {
							     contact = GlobalUI.inputGetLine("Indtast Kontaktperson: ");
							     cvrNo = GlobalUI.inputGetLong("Indtast CVR-nummer: ");
							     done2 = _customerCtrl.updateCustomer(customerId, contact, cvrNo);
							 }
							 boolean done = _personCtrl.updatePerson(customerId, cusName, cusAddress, cusCity, cusZipCode, cusPhoneNumber);
							 if(done && done2)
							 {
							     System.out.println("Kunden er nu opdateret");
							 }
							 else
							 {
							     System.out.println("*FEJL* - Kunden blev ikke opdateret ");
							 }
						}	
						else
							System.out.println("En kunde med det ID blev ikke fundet");
					}
					catch(Exception ex)
					{
						System.out.println(GlobalUI.errorHandling(99));
						GlobalUI.inputGetLine("Tryk på enter for at forsætte..");
					}			
	
					execCustomerMenu();
					break;

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