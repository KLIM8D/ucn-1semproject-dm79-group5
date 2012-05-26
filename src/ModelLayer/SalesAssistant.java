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
    private Person _person;

    public SalesAssistant(int salesAssistantId, String password, Person person)
    {
        _salesAssistantId = salesAssistantId;
        _password = password;
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