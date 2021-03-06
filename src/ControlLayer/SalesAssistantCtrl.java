package ControlLayer;

import ModelLayer.*;
import java.security.MessageDigest;
import java.util.Calendar;

/** 
* @version: 0.1
* Filename: SalesAssistantController.java
* Description: Controller class handling all operations with SalesAssistants
* @changes	
*/

public class SalesAssistantCtrl
{

	private SalesAssistantContainer _saContainer;
    private PersonContainer _perContainer;
	
	public SalesAssistantCtrl()
	{
		_saContainer = SalesAssistantContainer.getInstance();
        _perContainer = PersonContainer.getInstance();
	}

    public int createSalesAssistant(String password, long personId)
    {
        Person person = _perContainer.getPerson(personId);
        if(person != null)
        {
            SalesAssistant sa = new SalesAssistant(person);
            String salt = createSalt(personId);
            sa.setSalt(salt);
            String hashedPassword = hashPassword(password, salt);
            sa.setPassword(hashedPassword);

            boolean success = _saContainer.addSalesAs(sa);
            if(success)
                return sa.getSalesAssistantId();
        }
        
        return 0;
    }

    public boolean removeSalesAssistant(int saId)
    {
        return _saContainer.removeSalesAs(saId);
    }

	public SalesAssistant getSalesAssistant(int saId)
	{
		return _saContainer.getSalesAs(saId);
	}

    public Iterable<SalesAssistant> getAllSalesAssistants()
    {
        return _saContainer.getAllSalesAs().values();
    } 

    public boolean changePassword(int saId, String password)
    {
        SalesAssistant sa = getSalesAssistant(saId);
        if(sa != null)
        {
            String salt = sa.getSalt();
            String hashed = hashPassword(password, salt);

            sa.setPassword(hashed);
            return true;
        }
        return false;
    }

    public boolean checkLogin(int saId, String password)
    {
        SalesAssistant sa = getSalesAssistant(saId);
        if(sa != null)
        {
            String salt = sa.getSalt();
            String pass = sa.getPassword();

            String hashed = hashPassword(password, salt);
            if(hashed.equals(pass))
                return true;
            else
                return false;
        }
        return false;
    }

	private String createSalt(long personId)
	{
		
		Calendar currentDate = Calendar.getInstance();
		Person per = _perContainer.getPerson(personId);
		String name = per.getName();
		long phoneNumber = per.getPhoneNumber();

        String combined = name + phoneNumber + currentDate.getTimeInMillis();
        return createHash(combined);
	}

	public String hashPassword(String password, String salt)
    {
    	return createHash(password + salt);
    }

    private String createHash(String input)
    {
    	try
    	{
        	MessageDigest md = MessageDigest.getInstance("SHA-256");
        	byte[] hash = md.digest(input.getBytes());

        	StringBuffer sb = new StringBuffer();
        	for(byte b : hash) 
        	{
            	sb.append(Integer.toHexString(b & 0xff));
        	}

        	return sb.toString();
    	}
    	catch(Exception e)
    	{}

        return "";		
    }
}
