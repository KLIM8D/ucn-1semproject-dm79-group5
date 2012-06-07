package ControlLayer;
import ModelLayer.*;
import java.util.ArrayList;

/** 
* @version: 0.1
* Filename: PersonCtrl.java
* Description: Controller class handling all operations with Persons
* @changes    
*/

public class PersonCtrl
{
    private PersonContainer _personContainer;

    public PersonCtrl()
    {
        _personContainer = PersonContainer.getInstance();
    }
    
    public boolean createPerson(long personId, String name, String address, String city, int zipCode, long phoneNumber)
    {
        Person per = new Person(personId, name, address, city, zipCode, phoneNumber);
        return _personContainer.addPerson(per);
    }
    
    public boolean updatePerson(long personId, String name, String address, String city, int zipCode)
    {
        Person per = _personContainer.getPerson(personId);
        if(per != null)
        {
            per.setName(name);
            per.setAddress(address);
            per.setCity(city);
            per.setZipCode(zipCode);
            return _personContainer.updatePerson(per);
        }
        return false;
    }
    
    public boolean removePerson(long personId)
    {
        return _personContainer.removePerson(personId);
    }
    
    public Person getPerson(long personId)
    {
        return _personContainer.getPerson(personId);
    }
    
    public Person findPerson(String name)
    {
        return _personContainer.findPerson(name);
    }
    
    public Person findPerson(long phoneNumber)
    {
        return _personContainer.findPerson(phoneNumber);
    }
    
    public ArrayList<Person> getAllPersons()
    {
        ArrayList<Person> persons = new ArrayList<Person>();
        for(Person person : _personContainer.getAllPersons().values())
        {
            persons.add(person);
        }
        return persons;
    }
}
