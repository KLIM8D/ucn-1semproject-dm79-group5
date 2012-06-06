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

    public CustomerMenuUI()
    {
        _personCtrl = new PersonCtrl();
        _customerCtrl = new CustomerCtrl();
    }

    public void execCustomerMenu()
    {
        GlobalUI.tuiHeader();

        print("-------------------------------------------------------------------");
        print("¦                          Kundekartotek                          ¦");
        print("-------------------------------------------------------------------");
        print("¦ 1) - Opret kunde                                                ¦");
        print("¦ 2) - Find kunde (Kundenummer)                                   ¦");
        print("¦ 3) - Slet kunde                                                 ¦");
        print("¦ 4) - Opdater kunde                                              ¦");
        print("¦ 5) - Vis Alle kunder                                            ¦");
        print("¦ 6) - Vis Business kunder                                        ¦");
        print("¦ 7) - Vis Private kunder                                         ¦");
        print("¦ 8) - Tilknyt kunden en rabatgruppe                              ¦");
        print("¦                                                                 ¦");
        print("¦ 0) - Returner til hovedmenu                                     ¦");
        print("-------------------------------------------------------------------");
        
        

        try
        {
            System.out.print("\n");
            int userentry = GlobalUI.inputGetInt("Menu valg: ");
            System.out.println("\n");
            switch (userentry)
            {
                case 1:
                    try
                    {
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
                                _customerCtrl.createCustomer(per, contactPerson, cvrNo);
                            }
                            else if(isBusiness.toLowerCase().equals("nej"))
                            {
                                Person per = _personCtrl.getPerson(personId);
                                _customerCtrl.createCustomer(per);
                            }
                            else
                            {
                                print("Der skete en fejl under indtastningen af kundens erhvervsoplysninger");
                            }
                        }
                        else
                            print("Der skete en fejl under indtastningen af kundens informationer");
                    }
                    catch(Exception ex)
                    {
                        print("*FEJL* Kundenummer og CVR nummer består kun af tal");                   
                    }
                    GlobalUI.inputGetLine("Tryk på enter for at forsætte..");
                    execCustomerMenu();
                    break;

                case 2:
                    long customerId = 0;
                    try
                    {
                        customerId = GlobalUI.inputGetLong("Indtast Kundenummer: ");
                        Customer customer = _customerCtrl.getCustomer(customerId);
                        if(customer == null)
                        {
                            print("Der blev ikke fundet en kunde med det kundenummer");
                        }
                        else
                        {
                            print(GlobalUI.getCustomerInfo(customer));
                        }
                    }
                    catch(Exception ex)
                    {
                        print("*FEJL* Kundenummer består kun af tal");
                    }
                    GlobalUI.inputGetLine("Tryk på enter for at forsætte..");
                    execCustomerMenu();
                    break;
                    // End of section

                case 3:
                    try
                    {
                        customerId = GlobalUI.inputGetLong("Indtast Kundenummer: ");
                        Person per = _customerCtrl.getCustomer(customerId).getPerson();
                        if(per != null)
                        {
                            long personId = per.getPersonId();
                            boolean done = _personCtrl.removePerson(personId);
                            boolean done2 = _customerCtrl.removeCustomer(customerId);
                            if(!done || !done2)
                            {
                                print(GlobalUI.errorHandling(99));
                            }
                            else
                            {
                                print("Kunden er blevet slettet fra systemet!");
                            }
                        }
                        else
                        {
                            print("Personen eksisterer ikke i person kardoteket!");
                        }
                    }
                    catch(Exception ex)
                    {
                        print("*FEJL* Kundenummer bestaar kun af tal");
                        GlobalUI.inputGetLine("Tryk enter for at fortsaette..");
                    }
                    GlobalUI.inputGetLine("Tryk på enter for at forsætte..");
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
							     print("Kunden er nu opdateret");
							 }
							 else
							 {
							     print("*FEJL* - Kunden blev ikke opdateret ");
							 }
						}	
						else
							print("En kunde med det ID blev ikke fundet");
					}
					catch(Exception ex)
					{
						print(GlobalUI.errorHandling(99));
					}			
	                GlobalUI.inputGetLine("Tryk på enter for at forsætte..");
					execCustomerMenu();
					break;

                case 5:
                    // Start of section
                    try 
                    {
                        for(Customer cust : _customerCtrl.getAllCustomers())
                            print(GlobalUI.getCustomerInfo(cust));
                    }
                    catch (Exception e)
                    {
                        print(e.toString());
                        print("Der skete en fejl da kunde dataen skulle hentes");
                    }
                    GlobalUI.inputGetLine("Tryk på enter for at forsætte..");
                    execCustomerMenu();
                    break;
                    // End of section

                case 6:
                    // Start of section
                    try 
                    {
                        for(Customer cust : _customerCtrl.getAllCustomersByBusiness(true))
                            print(GlobalUI.getCustomerInfo(cust));
                    }
                    catch (Exception e)
                    {
                        print("Der skete en fejl da kunde dataen skulle hentes");
                    }
                    GlobalUI.inputGetLine("Tryk på enter for at forsætte..");
                    execCustomerMenu();
                    break;
                    // End of section
                    
                case 7:
                    // Start of section
                    try 
                    {
                        for(Customer cust : _customerCtrl.getAllCustomersByBusiness(false))
                            print(GlobalUI.getCustomerInfo(cust));
                    }
                    catch (Exception e)
                    {
                        print("Der skete en fejl da kunde dataen skulle hentes");
                    }
                    GlobalUI.inputGetLine("Tryk på enter for at forsætte..");
                    execCustomerMenu();
                    break;
                    // End of section

                case 8:
                    // Start of section
                    try 
                    {
                        print("Mulige rabat grupper: ");
                        print("ID: 1 " + GlobalUI.translateDiscountTypes(1));
                        print("ID: 2 " + GlobalUI.translateDiscountTypes(2));
                        print("ID: 3 " + GlobalUI.translateDiscountTypes(3));
                        print("ID: 4 " + GlobalUI.translateDiscountTypes(4));
                        print("ID: 5 " + GlobalUI.translateDiscountTypes(5));
                        customerId = GlobalUI.inputGetLong("Indtast kundenummer: ");
                        int discId = GlobalUI.inputGetInt("Indtast rabat gruppe ID: ");
                        String price = GlobalUI.inputGetLine("Indtast rabattens beløb: ");
                        boolean succeeded = _customerCtrl.createDiscount(customerId, discId, price);
                        if(succeeded)
                            print("Kunden er nu tildelt denne rabat gruppe");
                        else
                            print("Kunden med det kundenummer blev ikke fundet");
                    }
                    catch (Exception e)
                    {
                        print("Der skete en fejl da kunden skulle tildeles en rabat");
                    }
                    GlobalUI.inputGetLine("Tryk på enter for at forsætte..");
                    execCustomerMenu();
                    break;
                    // End of section
                    
                case 0:
                    _mainmenuUI = new MainMenuUI();
                    _mainmenuUI.execMainMenu();
                    break;

                default:
                    print(GlobalUI.errorHandling(02));
                    Thread.sleep(2000);
                    execCustomerMenu();
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
            execCustomerMenu();
        }
    }

    private void print(String inputLine)
    {
        System.out.println("                      " + inputLine);
    }
}