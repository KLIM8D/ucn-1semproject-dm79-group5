package ModelLayer;

/** 
* @version: 0.1
* Filename: SalesAssistant.java
* Description: Class that is in charge of making objects of the type SalesAssistant
* @changes  
*/

public class SalesAssistant
{
    private int _salesAssistantId;
    private String _password;
    private String _salt;
    private Person _person;

    public SalesAssistant(Person person)
    {
        SalesAssistantContainer saContainer = SalesAssistantContainer.getInstance();
        _salesAssistantId = saContainer.getLastKey() + 1;
        _person = person;
    }

    public int getSalesAssistantId()
    {
        return _salesAssistantId;
    }
    
    public String getPassword()
    {
        return _password;
    }

    public void setSalt(String salt)
    { 
        _salt = salt; 
    }

    public String getSalt()
    { 
        return _salt; 
    }
    
    public Person getPerson()
    {
        return _person;
    }
    
    public void setSalesAssistantId(int salesAssistantId)
    {
        _salesAssistantId = salesAssistantId;
    }
    
    public void setPassword(String password)
    {
        _password = password;
    }
    
    public void setPerson(Person person)
    {
        _person = person;
    }
}