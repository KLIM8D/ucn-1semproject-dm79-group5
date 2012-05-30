package ModelLayer;

import java.util.HashMap;

/** 
* @version: 0.1
* Filename: SalesAssistantContainer.java
* Description: Class that is responsable for containing objects of the type SalesAssistants
* @changes  
*/

public class SalesAssistantContainer
{
    private HashMap<Integer, SalesAssistant> _saCollection;
    private static SalesAssistantContainer _instance;
    private int _lastKey;

    // LastKey {get;}
    public int getLastKey()
    { return _lastKey; }
    
    private SalesAssistantContainer()
    {
        _lastKey = 0;
        _saCollection = new HashMap<Integer, SalesAssistant>();
    }
    
    public static SalesAssistantContainer getInstance()
    {
        if(_instance == null)
        {
            _instance = new SalesAssistantContainer();
        }
        return _instance;
    }
    
    public boolean addSalesAs(SalesAssistant sa)
    {
        int key = sa.getSalesAssistantId();
        if(!_locationCollection.containsKey(key))
        {
            _saCollection.put(key, sa);
            _lastKey++;
            return true;
        }
        return false;         
    }
    
    public boolean updateSalesAs(SalesAssistant sa)
    {
        int saId = sa.getSalesAssistantId();
        SalesAssistant salesAs = _saCollection.get(saId);
        if(salesAs != null)
        {
            _saCollection.put(saId,sa);
            return true;
        }
        return false;
    }
    
    public boolean removeSalesAs(int saId)
    {
        SalesAssistant sa = _saCollection.get(saId);
        if(sa != null)
        {
            _saCollection.remove(saId);
            return true;
        }
        return false;        
    }
    
    public SalesAssistant getSalesAs(int saId)
    {
        return _saCollection.get(saId);
    }
    
    public HashMap<Integer, SalesAssistant> getAllSalesAs()
    {
        return _saCollection;
    }
}