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
    
    private SalesAssistantContainer()
    {
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
    
    public void addSalesAs(SalesAssistant sa)
    {
        int key = sa.getSalesAssistantId();
        _saCollection.put(key, sa);         
    }
    
    public boolean updateSalesAs(SalesAssistant sa)
    {
        int saId = sa.getSalesAssistantId();
        SalesAssistant SalesAs = _saCollection.get(saId);
        if(SalesAs != null)
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
    
    public HashMap getAllSalesAs()
    {
        return _saCollection;
    }
}