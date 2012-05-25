package ModelLayer;
import java.util.HashMap;

/**
 * Class that is responsable for containing objects of the type SalesAssistants
 */
public class SalesAssistantContainer
{
    private HashMap<Integer, SalesAssistant> _saCollection;
    private static SalesAssistantContainer _instance;
    
    /**
     * Constructor for objects of class SalesAssistantContainer.
     */
    public SalesAssistantContainer()
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
        int key = sa.getSalesAssistantId();
        SalesAssistant SalesAs = _saCollection.get(key);
        if(SalesAs != null)
        {
            _saCollection.put(key,sa);
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