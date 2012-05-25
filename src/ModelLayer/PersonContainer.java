package ModelLayer;
import java.util.HashMap;

/**
 * Class that is responsable for containing objects of the type person
 */
public class PersonContainer
{
    private HashMap<Long, Person> _personCollection;
    private static PersonContainer _instance;
    
    /**
     * Constructor for objects of class PersonContainer.
     */
    public PersonContainer()
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
    
    public void addPerson(Person per)
    {
        long key = per.getPersonId();
        _personCollection.put(key, per);
    }
    
    public boolean updatePerson(Person per)
    {
        long key = per.getPersonId();
        Person person = _personCollection.get(key);
        if(person != null)
        {
            _personCollection.put(key,per);
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
    
    public HashMap getAllPersons()
    {
        return _personCollection;
    }
}