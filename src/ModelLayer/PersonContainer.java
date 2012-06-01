package ModelLayer;

import java.util.HashMap;

/** 
* @version: 0.1
* Filename: Product.java
* Description: Class that is responsable for containing objects of the type person
* @changes  
*/

public class PersonContainer
{
    private HashMap<Long, Person> _personCollection;
    private static PersonContainer _instance;
    
    private PersonContainer()
    {
        _personCollection = new HashMap<Long, Person>();
    }
    
    public static PersonContainer getInstance()
    {
        if(_instance == null)
        {
            _instance = new PersonContainer();
        }
        return _instance;
    }
    
    public boolean addPerson(Person per)
    {
        if(per != null)
        {
            long key = per.getPersonId();
            _personCollection.put(key, per);
            return true;
        }
        return false;
    }
    
    public boolean updatePerson(Person per)
    {
        long personId = per.getPersonId();
        Person person = _personCollection.get(personId);
        if(person != null)
        {
            _personCollection.put(personId,per);
            return true;
        }
        return false;
    }
    
    public boolean removePerson(long personId)
    {
        Person person = _personCollection.get(personId);
        if(person != null)
        {
            _personCollection.remove(personId);
            return true;
        }
        return false;
    }
    
    public Person getPerson(long personId)
    {
        return _personCollection.get(personId);
    }
    
    public Person findPerson(String name)
    {
        for(Person per : _personCollection.values())
        {
            if(per.getName().toLowerCase().equals(name.toLowerCase()))
            {
                return per;
            }
        }
        return null;
    }
    
    public Person findPerson(long phoneNumber)
    {
        for(Person per : _personCollection.values())
        {
            if(per.getPhoneNumber() == phoneNumber)
            {
                return per;
            }
        }
        return null;
    }
    
    public HashMap<Long, Person> getAllPersons()
    {
        return _personCollection;
    }
}