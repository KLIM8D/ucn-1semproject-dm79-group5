package GUILayer.Product;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
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
import java.awt.Font;


public class ShowAllUI {

	private JPanel contentPane;
	private JTable table;
	
	private DefaultTableModel model;
	private static JInternalFrame _frame;
	private static ShowAllUI _instance;
	private JLabel lblItemNumber;
	private JTextField txtItemNumber;
	private JLabel lblItemName;
	private JTextField txtItemName;
	private JButton btnSearch;
	private String[] columnNames;
	private JScrollPane scrollPane;
	private JPanel gridPanel;
	
	//Controllers
	private ProductCtrl _prodCtrl;
	private ProductLocationCtrl _locationCtrl;
	
	//ShowProductPanel
	private JPanel showProductPanel;
	private JLabel lblShowItemNumber;
	private JLabel lblShowItemName;
	private JLabel lblShowItemMinQuantity;
	private JLabel lblShowItemMaxQuantity;
	private JLabel lblShowItemPrice;
	private JLabel lblShowItemCategory;
	private JLabel lblShowLocationsName;
	private JLabel lblShowLocationsAvail;
	private JLabel lblProduktInformation;
	private JLabel lblLagerBeholdning;
	
	public static JInternalFrame createWindow()
	{
		if(_instance == null)
			_instance = new ShowAllUI();
		
		return _frame;
	}
	
	private ShowAllUI() 
	{
		_prodCtrl = new ProductCtrl();
		_locationCtrl = new ProductLocationCtrl();
		
		_frame = new JInternalFrame();		
		_frame.setTitle("Vis alle produkter");
		_frame.setClosable(true);
		_frame.setMaximizable(true);
		_frame.setVisible(true);
		_frame.setBounds(0, 0, 924, 562);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		_frame.setContentPane(contentPane);
		
		
		//Search
		JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		searchPanel.setBounds(5, 5, 904, 35);
		
		lblItemNumber = new JLabel("Produkt nummer: ");
		lblItemNumber.setBounds(187, 10, 126, 15);
		searchPanel.add(lblItemNumber);
		
		txtItemNumber = new JTextField();
		txtItemNumber.setBounds(318, 10, 110, 15);
		txtItemNumber.setColumns(10);
		txtItemNumber.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(txtItemNumber.getText().length() > 0)
				{
					GlobalUI.checkIfInt(txtItemNumber);
				}
			}
		});
		searchPanel.add(txtItemNumber);
		
		lblItemName = new JLabel("Produkt navn: ");
		lblItemName.setBounds(433, 10, 103, 15);
		searchPanel.add(lblItemName);
		
		txtItemName = new JTextField();
		txtItemName.setBounds(541, 10, 110, 15);
		txtItemName.setColumns(10);
		searchPanel.add(txtItemName);
		
		btnSearch = new JButton("Søg");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtItemNumber.getText().length() > 0)
				{
					long itemNumber = Long.parseLong(txtItemNumber.getText());
					addData(itemNumber);
				}
				if(txtItemName.getText().length() > 0 && txtItemNumber.getText().length() <= 0)
				{
					addData(txtItemName.getText());
				}
				if(txtItemName.getText().length() <= 0 && txtItemNumber.getText().length() <= 0)
				{
					addData();
				}
			}
		});
		contentPane.setLayout(null);
		btnSearch.setBounds(656, 5, 61, 25);
		searchPanel.add(btnSearch);
		
		contentPane.add(searchPanel);
		//Search end
		
		//Grid / table
		gridPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		contentPane.add(gridPanel);		
		
		columnNames = new String[]{"Produkt nummer", "Produkt navn", "Minimums beholdning", "Maksimums beholdning", "Pris", "Kategori", " "};
		
		table = new JTable()
		{
			public boolean isCellEditable(int data, int columns)
			{
				if(columns != 6)
					return false;
				
				return true;
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
		
		//ShowProductPanel
		showProductPanel = new JPanel();
		showProductPanel.setBounds(5, 315, 904, 200);
		contentPane.add(showProductPanel);
		showProductPanel.setLayout(null);
		lblShowItemNumber = new JLabel();
		lblShowItemNumber.setBounds(12, 15, 350, 20);
		showProductPanel.add(lblShowItemNumber);
		lblShowItemName = new JLabel();
		lblShowItemName.setBounds(12, 47, 350, 20);
		showProductPanel.add(lblShowItemName);
		lblShowItemMinQuantity = new JLabel();
		lblShowItemMinQuantity.setBounds(12, 79, 350, 20);
		showProductPanel.add(lblShowItemMinQuantity);
		lblShowItemMaxQuantity = new JLabel();
		lblShowItemMaxQuantity.setBounds(12, 111, 350, 20);
		showProductPanel.add(lblShowItemMaxQuantity);
		lblShowItemPrice = new JLabel();
		lblShowItemPrice.setBounds(12, 143, 350, 20);
		showProductPanel.add(lblShowItemPrice);
		lblShowItemCategory = new JLabel();
		lblShowItemCategory.setBounds(12, 175, 350, 20);
		showProductPanel.add(lblShowItemCategory);
		lblShowLocationsName = new JLabel();
		lblShowLocationsName.setVerticalAlignment(SwingConstants.TOP);
		lblShowLocationsName.setBounds(444, 15, 150, 200);
		showProductPanel.add(lblShowLocationsName);
		lblShowLocationsAvail = new JLabel();
		lblShowLocationsAvail.setVerticalAlignment(SwingConstants.TOP);
		lblShowLocationsAvail.setBounds(754, 15, 114, 200);
		showProductPanel.add(lblShowLocationsAvail);
		
		lblProduktInformation = new JLabel("Produkt information:");
		lblProduktInformation.setVisible(false);
		lblProduktInformation.setFont(new Font("Dialog", Font.BOLD, 16));
		lblProduktInformation.setBounds(12, 0, 200, 15);
		showProductPanel.add(lblProduktInformation);
		
		lblLagerBeholdning = new JLabel("Lager beholdning:");
		lblLagerBeholdning.setFont(new Font("Dialog", Font.BOLD, 16));
		lblLagerBeholdning.setBounds(444, 0, 200, 15);
		showProductPanel.add(lblLagerBeholdning);
		//ShowProductPanel end
	}

	private void addShowButton() {
		Action delete = new AbstractAction()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		    	JTable table = (JTable) e.getSource();
                int row = Integer.valueOf(e.getActionCommand());
                long itemNumber = Long.parseLong(table.getValueAt(row, 0).toString());
                showProduct(itemNumber);
		    }
		};
		ButtonColumn buttonColumn = new ButtonColumn(table, delete, 6);
		buttonColumn.setMnemonic(KeyEvent.VK_D);
	}
	
	private void addData()
	{
		ArrayList<Product> products = makeCollection(_prodCtrl.getAllProducts());
		Object[][] data = {};
		model.setDataVector(data, columnNames);
		for(Product prod : products)
		{
			Object[] row = new Object[]{prod.getItemNumber(), prod.getItemName(), prod.getMinInStock(), prod.getMaxInStock(), prod.getPrice(), prod.getProductCategory().getCategoryName(), "Vis" };
			model.addRow(row);
		}
		addShowButton();
	}
	
	private void addData(long itemNumber)
	{
		Product prod = _prodCtrl.getProduct(itemNumber);
		
		if(prod != null)
		{
			Object[][] row = new Object[][]
			{
					{ prod.getItemNumber(), prod.getItemName(), prod.getMinInStock(), prod.getMaxInStock(), prod.getPrice(), prod.getProductCategory().getCategoryName(), "Vis" }
			};
			model.setDataVector(row, columnNames);
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Der blev ikke fundet noget produkt, med produkter nummeret: " + itemNumber, "FEJL!", JOptionPane.WARNING_MESSAGE);
		}
		
		addShowButton();
	}
	
	private void addData(String itemName)
	{
		Product prod = _prodCtrl.findProduct(itemName);
		
		if(prod != null)
		{
			Object[][] row = new Object[][]
			{
					{ prod.getItemNumber(), prod.getItemName(), prod.getMinInStock(), prod.getMaxInStock(), prod.getPrice(), prod.getProductCategory().getCategoryName(), "Vis" }
			};
			model.setDataVector(row, columnNames);
		}
		else
		{
			JOptionPane.showMessageDialog(null, itemName + " blev ikke fundet", "FEJL!", JOptionPane.WARNING_MESSAGE);
		}
		
		addShowButton();
	}
	
	private <E> ArrayList<E> makeCollection(Iterable<E> iter)
	{
		ArrayList<E> list = new ArrayList<E>();
		for (E item : iter) 
			list.add(item);
		return list;
	}

	private void showProduct(long itemNumber)
	{
		Product prod = _prodCtrl.getProduct(itemNumber);
		
		if(prod != null)
		{
			lblProduktInformation.setVisible(true);
			lblShowItemNumber.setText("Produkt nummer: " + Long.toString(prod.getItemNumber()));
			lblShowItemName.setText("Produkt navn: " + prod.getItemName());
			lblShowItemMinQuantity.setText("Minimums beholdning: " + Integer.toString(prod.getMinInStock()));
			lblShowItemMaxQuantity.setText("Maksimums beholdning: " + Integer.toString(prod.getMaxInStock()));
			lblShowItemPrice.setText("Pris: " + prod.getPrice().toString() + " kr.");
			lblShowItemCategory.setText("Produkt kategori: " + prod.getProductCategory().getCategoryName());
			String locations = "<html>";
			String avail = "<html>";
			for(ProductLocation loc : _locationCtrl.getAll())
			{
				locations += loc.getLocationName() + "<br/>";
				avail += _locationCtrl.getAvailOnLocation(loc.getLocationId(), itemNumber) + " stk. <br/>";
			}
			locations += "</html>";
			avail += "</html>";
			lblShowLocationsName.setText(locations);
			lblShowLocationsAvail.setText(avail);
		}
	}
	
	private void autoScaleTable() {
		if(_frame.isMaximum())
        {
			gridPanel.setBounds(new Rectangle(0, 40, _frame.getWidth()-8, _frame.getHeight()-312));
        	table.setPreferredScrollableViewportSize(new Dimension(_frame.getWidth()-18, _frame.getHeight()-312));
        	scrollPane.setPreferredSize(new Dimension(_frame.getWidth()-18, _frame.getHeight()-312));
        	showProductPanel.setLocation(showProductPanel.getLocation().x, gridPanel.getLocation().y + gridPanel.getHeight()+10);
        	gridPanel.removeAll();
        	gridPanel.add(scrollPane);
        }
        else
        {
        	gridPanel.setBounds(new Rectangle(0, 40, 914, 250));
        	table.setPreferredScrollableViewportSize(new Dimension(904, 250));
        	scrollPane.setPreferredSize(new Dimension(904, 250));
        	showProductPanel.setLocation(5, 315);
        	gridPanel.removeAll();
        	gridPanel.add(scrollPane);
        }
	}
}

