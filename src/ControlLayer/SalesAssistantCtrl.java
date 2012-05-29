package ControlLayer;

import ModelLayer.*;
import java.security.MessageDigest;
import java.util.Calendar;

/** 
* @version: 0.1
* Filename: SalesAssistantController.java
* Description: 
* @changes	
*/

public class SalesAssistantCtrl
{

	private SalesAssistantContainer _saContainer;
	
	public SalesAssistantCtrl()
	{
		_saContainer = SalesAssistantContainer.getInstance();
	}

	public SalesAssistant getSalesAs(int saId)
	{
		return _saContainer.getSalesAs(saId);
	}

/**
*
* Chris:
*   
* Alt det nederstående skal bruges til login. Men lige pt. kan man kun hashe et password + salt og lave en salt til ens password.
* Du skal have lavet sådan at den tjekker for om man har indtastet det rigtige password. Husk på du skal hashe brugerens input+salt før du 
* sammenligner det med det password som det kræves for at kunne logge ind.
* Spørg hvis du er i tvivl
* //Morten
*
*/

	public String createSalt(int saId)
	{
		
		Calendar currentDate = Calendar.getInstance();
		Person per = getSalesAs(saId).getPerson();
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