package GUILayer.Product;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.InternalFrameEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ControlLayer.ProductCtrl;
import ControlLayer.ProductLocationCtrl;
import ModelLayer.Product;
import javax.swing.JTextArea;


public class ShowAllUI {

	private JPanel contentPane;
	private JTable table;
	private JButton btnEdit;
	private ProductCtrl _prodCtrl;
	private DefaultTableModel model;
	private static JInternalFrame _frame;
	private static ShowAllUI _instance;
	private JLabel lblItemNumber;
	private JTextArea txtItemNumber;
	private JLabel lblItemName;
	private JTextArea txtItemName;
	private JButton btnSearch;
	
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
		_frame.setIconifiable(true); 
		_frame.setVisible(true);
		_frame.getContentPane().setLayout(new FlowLayout());
		_frame.setBounds(0, 0, 924, 562);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		_frame.setContentPane(contentPane);
		
		
		//Search
		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(new FlowLayout());
		
		lblItemNumber = new JLabel("Produkt nummer: ");
		searchPanel.add(lblItemNumber);
		
		txtItemNumber = new JTextArea();
		txtItemNumber.setBounds(120, 10, 316, 19);
		txtItemNumber.setColumns(10);
		searchPanel.add(txtItemNumber);
		
		lblItemName = new JLabel("Produkt navn: ");
		searchPanel.add(lblItemName);
		
		txtItemName = new JTextArea();
		txtItemName.setBounds(120, 10, 316, 19);
		txtItemName.setColumns(10);
		searchPanel.add(txtItemName);
		
		btnSearch = new JButton("Søg");
		btnSearch.setBounds(190, 68, 117, 25);
		searchPanel.add(btnSearch);
		
		contentPane.add(searchPanel, BorderLayout.NORTH);
		
		//Grid
		JPanel gridPanel = new JPanel();
		contentPane.add(gridPanel, BorderLayout.CENTER);
		
		
		
		String[] columnNames = {"Produkt nummer", "Produkt navn", "Minimums beholdning", "Maksimums beholdning", "Pris", "Kategori", " "};
		
		table = new JTable()
		{
			public boolean isCellEditable(int data, int columns)
			{
				if(columns != 6)
					return false;
				
				return true;
			}
		};
		Object[][] data = {};
		model = new DefaultTableModel(data, columnNames);
		addData();
		table.setModel(model);
		table.setPreferredScrollableViewportSize(new Dimension(880, 500));
		table.setFillsViewportHeight(true);
		
		JScrollPane scrollPane = new JScrollPane(table);
		
		gridPanel.add(scrollPane);
		
		btnEdit = new JButton("Vis");
		Action delete = new AbstractAction()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		        System.out.println(((DefaultTableModel)table.getModel()));
		    }
		};
		btnEdit = new JButton("Vis");
		ButtonColumn buttonColumn = new ButtonColumn(table, delete, 6);
		buttonColumn.setMnemonic(KeyEvent.VK_D);
		
		//Close window
		_frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		_frame.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
		      public void internalFrameClosing(InternalFrameEvent e) {
		        _frame.dispose();
		        _instance = null;
		      }
		    });
	}
	
	private void addData()
	{
		ArrayList<Product> products = makeCollection(_prodCtrl.getAllProducts());
		for(Product prod : products)
		{
			Object[] row = new Object[]{prod.getItemNumber(), prod.getItemName(), prod.getMinInStock(), prod.getMaxInStock(), prod.getPrice(), prod.getProductCategory().getCategoryName(), "Vis" };
			model.addRow(row);
		}
	}
	
	public <E> ArrayList<E> makeCollection(Iterable<E> iter)
	{
		ArrayList<E> list = new ArrayList<E>();
		for (E item : iter) 
			list.add(item);
		return list;
	}
}

