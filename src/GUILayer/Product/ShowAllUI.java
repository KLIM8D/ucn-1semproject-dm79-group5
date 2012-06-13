package GUILayer.Product;

import java.awt.BorderLayout;
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
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.InternalFrameEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ControlLayer.ProductCtrl;
import ControlLayer.ProductLocationCtrl;
import GUILayer.GlobalUI;
import ModelLayer.Product;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class ShowAllUI {

	private JPanel contentPane;
	private JTable table;
	private JButton btnEdit;
	private ProductCtrl _prodCtrl;
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
	
	public static JInternalFrame createWindow()
	{
		if(_instance == null)
			_instance = new ShowAllUI();
		
		return _frame;
	}
	
	private ShowAllUI() 
	{
		_prodCtrl = new ProductCtrl();
		
		_frame = new JInternalFrame();		
		_frame.setTitle("Vis alle produkter");
		_frame.setClosable(true);
		_frame.setMaximizable(true);
		_frame.setVisible(true);
		_frame.getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT));
		_frame.setBounds(0, 0, 924, 562);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		_frame.setContentPane(contentPane);
		
		
		//Search
		JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		//searchPanel.setBounds(0, 0, 450, 140);
		
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
		btnSearch.setBounds(656, 5, 61, 25);
		searchPanel.add(btnSearch);
		
		contentPane.add(searchPanel, BorderLayout.NORTH);
		
		//Grid
		gridPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		contentPane.add(gridPanel, BorderLayout.CENTER);
		
		
		
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
		table.setPreferredScrollableViewportSize(new Dimension(880, 500));
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
		System.out.print(scrollPane.getHeight());
		System.out.print(scrollPane.getWidth());
		PropertyChangeListener propertyChangeListener = new PropertyChangeListener()
		{
	        public void propertyChange(PropertyChangeEvent evt) 
	        {
	            if(_frame.isMaximum())
	            {
	            	table.setPreferredScrollableViewportSize(new Dimension(_frame.getWidth()-44, _frame.getHeight()-62));
	            	scrollPane.setPreferredSize(new Dimension(_frame.getWidth()-44, _frame.getHeight()-62));	            	
	            	gridPanel.removeAll();
	            	gridPanel.add(scrollPane);
	            }
	            else
	            {
	            	table.setPreferredScrollableViewportSize(new Dimension(880, 500));
	            	scrollPane.setPreferredSize(new Dimension(880, 500));	            	
	            	gridPanel.removeAll();
	            	gridPanel.add(scrollPane);
	            }
	        };
		};
		_frame.addPropertyChangeListener(propertyChangeListener );

		
		
	}

	private void addShowButton() {
		btnEdit = new JButton("Vis");
		Action delete = new AbstractAction()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		        System.out.println(((DefaultTableModel)table.getModel()));
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
	
	public <E> ArrayList<E> makeCollection(Iterable<E> iter)
	{
		ArrayList<E> list = new ArrayList<E>();
		for (E item : iter) 
			list.add(item);
		return list;
	}
}

