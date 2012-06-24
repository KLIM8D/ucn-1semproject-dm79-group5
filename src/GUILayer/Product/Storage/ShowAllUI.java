package GUILayer.Product.Storage;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.InternalFrameEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ControlLayer.ProductLocationCtrl;
import ModelLayer.ProductLocation;

import java.awt.Rectangle;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class ShowAllUI 
{

	private JPanel contentPane;
	private JTable table;
	
	private DefaultTableModel model;
	private static JInternalFrame _frame;
	private static ShowAllUI _instance;
	private String[] columnNames;
	private JScrollPane scrollPane;
	private JPanel gridPanel;
	
	//Controllers
	private ProductLocationCtrl _locationCtrl;
	
	public static JInternalFrame createWindow()
	{
		if(_instance == null)
			_instance = new ShowAllUI();
		
		return _frame;
	}
	
	@SuppressWarnings("serial")
	private ShowAllUI() 
	{
		_locationCtrl = new ProductLocationCtrl();
		
		_frame = new JInternalFrame();		
		_frame.setTitle("Vis alle lagre");
		_frame.setClosable(true);
		_frame.setMaximizable(true);
		_frame.setVisible(true);
		_frame.setBounds(0, 0, 924, 300);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		_frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Grid / table
		gridPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		contentPane.add(gridPanel);		
		
		columnNames = new String[]{"ID", "Lager navn", "Adresse", "Post nr", "By"};
		
		table = new JTable()
		{
			public boolean isCellEditable(int data, int columns)
			{
				return false;
			}
		};
		
		model = new DefaultTableModel();
		
		table.setModel(model);
		table.setFillsViewportHeight(true);
		addData();
		
		scrollPane = new JScrollPane(table);
		gridPanel.add(scrollPane);		
		
		
		//Close window
		_frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		_frame.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
		      public void internalFrameClosing(InternalFrameEvent e) {
		        _frame.dispose();
		        _instance = null;
		      }
		    });
		PropertyChangeListener propertyChangeListener = new PropertyChangeListener()
		{
	        public void propertyChange(PropertyChangeEvent evt) 
	        {
	            autoScaleTable();
	        }			
		};
		_frame.addPropertyChangeListener(propertyChangeListener );
		//Grid / Table end
	}
	
	private void addData()
	{
		ArrayList<ProductLocation> locations = makeCollection(_locationCtrl.getAll());
		Object[][] data = {};
		model.setDataVector(data, columnNames);
		for(ProductLocation location : locations)
		{
			Object[] row = new Object[]{location.getLocationId(), location.getLocationName(), location.getAddress(), location.getZipCode(), location.getCity() };
			model.addRow(row);
		}
	}
	
	private <E> ArrayList<E> makeCollection(Iterable<E> iter)
	{
		ArrayList<E> list = new ArrayList<E>();
		for (E item : iter) 
			list.add(item);
		return list;
	}
	
	private void autoScaleTable() 
	{
		if(_frame.isMaximum())
        {
			gridPanel.setBounds(new Rectangle(0, 10, _frame.getWidth()-8, _frame.getHeight()-92));
        	table.setPreferredScrollableViewportSize(new Dimension(_frame.getWidth()-18, _frame.getHeight()-92));
        	scrollPane.setPreferredSize(new Dimension(_frame.getWidth()-18, _frame.getHeight()-92));
        	gridPanel.removeAll();
        	gridPanel.add(scrollPane);
        }
        else
        {
        	gridPanel.setBounds(new Rectangle(0, 10, 914, 250));
        	table.setPreferredScrollableViewportSize(new Dimension(904, 250));
        	scrollPane.setPreferredSize(new Dimension(904, 250));
        	gridPanel.removeAll();
        	gridPanel.add(scrollPane);
        }
	}
}

