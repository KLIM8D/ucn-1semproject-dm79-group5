package GUILayer.Product.Storage;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.InternalFrameEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ControlLayer.ProductCtrl;
import ControlLayer.ProductLocationCtrl;
import GUILayer.GlobalUI;
import ModelLayer.Product;
import ModelLayer.ProductLocation;

import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JComboBox;


public class ShowAllAvailbleUI {

	private JPanel contentPane;
	private JTable table;
	
	private DefaultTableModel model;
	private static JInternalFrame _frame;
	private static ShowAllAvailbleUI _instance;
	private JLabel lblStorageId;
	private JButton btnSearch;
	private String[] columnNames;
	private JScrollPane scrollPane;
	private JPanel gridPanel;
	
	//Controllers
	private ProductCtrl _prodCtrl;
	private ProductLocationCtrl _locationCtrl;
	private JComboBox<String> drpLocations;
	
	public static JInternalFrame createWindow()
	{
		if(_instance == null)
			_instance = new ShowAllAvailbleUI();
		
		return _frame;
	}
	
	@SuppressWarnings("serial")
	private ShowAllAvailbleUI() 
	{
		_prodCtrl = new ProductCtrl();
		_locationCtrl = new ProductLocationCtrl();
		
		_frame = new JInternalFrame();		
		_frame.setTitle("Vis tilgænglige produkter");
		_frame.setClosable(true);
		_frame.setMaximizable(true);
		_frame.setVisible(true);
		_frame.setBounds(0, 0, 924, 330);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		_frame.setContentPane(contentPane);
		
		
		//Search
		JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		searchPanel.setBounds(5, 5, 904, 35);
		
		lblStorageId = new JLabel("Lager ");
		lblStorageId.setBounds(187, 10, 126, 15);
		searchPanel.add(lblStorageId);
		
		btnSearch = new JButton("Hent data");
		btnSearch.setBounds(656, 5, 61, 25);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int locId = drpLocations.getSelectedIndex() + 1;
				addData(locId);
			}
		});
		
		
		drpLocations = new JComboBox<String>();
		drpLocations.setSize(250, 25);
		searchPanel.add(drpLocations);
		searchPanel.add(btnSearch);
		
		addData();
		
		contentPane.add(searchPanel);
		//Search end
		
		//Grid / table
		gridPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		contentPane.add(gridPanel);		
		
		columnNames = new String[]{"Produkt nummer", "Antal tilgængelige"};
		
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
	
	private void addData(int locId)
	{
		ProductLocation location = _locationCtrl.getLocation(locId);
		Object[][] data = {};
		model.setDataVector(data, columnNames);
		if(location != null)
		{
			for(Product prod : _prodCtrl.getAllProducts())
			{
				int avail = _locationCtrl.getAvailOnLocation(locId, prod.getItemNumber());
				if(avail > 0)
				{
					Object[] row = new Object[]{prod.getItemNumber(), avail };
					model.addRow(row);
				}
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Der blev ikke fundet et lager med ID'et: " + locId, "FEJL!", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void addData()
	{
		try 
		{
			for(ProductLocation loc : _locationCtrl.getAll())
				drpLocations.addItem(loc.getLocationName());
		}
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, GlobalUI.messageHandling(99), "FEJL!", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void autoScaleTable() 
	{
		if(_frame.isMaximum())
        {
			gridPanel.setBounds(new Rectangle(0, 40, _frame.getWidth()-8, _frame.getHeight()-92));
        	table.setPreferredScrollableViewportSize(new Dimension(_frame.getWidth()-18, _frame.getHeight()-92));
        	scrollPane.setPreferredSize(new Dimension(_frame.getWidth()-18, _frame.getHeight()-92));
        	gridPanel.removeAll();
        	gridPanel.add(scrollPane);
        }
        else
        {
        	gridPanel.setBounds(new Rectangle(0, 40, 914, 250));
        	table.setPreferredScrollableViewportSize(new Dimension(904, 250));
        	scrollPane.setPreferredSize(new Dimension(904, 250));
        	gridPanel.removeAll();
        	gridPanel.add(scrollPane);
        }
	}
}

