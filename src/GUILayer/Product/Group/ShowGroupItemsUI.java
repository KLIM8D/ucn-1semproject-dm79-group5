package GUILayer.Product.Group;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.table.DefaultTableModel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import ControlLayer.ProductCtrl;
import GUILayer.GlobalUI;
import ModelLayer.ProductGroupItem;

import javax.swing.JTable;

public class ShowGroupItemsUI 
{
	private static JFrame _frame;
	private static ShowGroupItemsUI _instance;
	private JPanel contentPane;
	private JTextField txtItemNumber;
	private JTextField txtQuantity;
	private ProductCtrl _productController;
	private DefaultTableModel model;
	private String[] columnNames;
	private JScrollPane scrollPane;
	private JTable table;
	private JPanel gridPanel;
	
	public static JFrame createWindow(int prodGroupId)
	{
		if(_instance == null)
			_instance = new ShowGroupItemsUI(prodGroupId);
		
		return _frame;
	}

	@SuppressWarnings("serial")
	private ShowGroupItemsUI(final int prodGroupId) 
	{
		_productController = new ProductCtrl();
		
		_frame = new JFrame();
		_frame.setResizable(false);
		_frame.setTitle("Vis produkt gruppe");
		_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		_frame.setBounds(0, 0, 524, 500);
		_frame.setLocationRelativeTo(null);
		_frame.setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		_frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Grid / table
		gridPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		gridPanel.setBounds(new Rectangle(0, 40, 514, 200));
		contentPane.add(gridPanel);		
				
		columnNames = new String[]{"Produkt nummer", "Produkt navn", "Antal"};
				
		table = new JTable()
		{
			public boolean isCellEditable(int data, int columns)
			{
				return false;
			}
		};
		table.setPreferredScrollableViewportSize(new Dimension(504, 200));		
		model = new DefaultTableModel();
				
		table.setModel(model);
		table.setFillsViewportHeight(true);
		addData(prodGroupId);
				
		scrollPane = new JScrollPane(table);
    	scrollPane.setPreferredSize(new Dimension(504, 200));
		gridPanel.add(scrollPane);	
		// GRID END
		
		JLabel lblGroup = new JLabel("Produkt nummer");
		lblGroup.setBounds(12, 363, 163, 15);
		contentPane.add(lblGroup);
		
		txtItemNumber = new JTextField();
		txtItemNumber.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(txtItemNumber.getText().length() > 0)
				{
					GlobalUI.checkIfLong(txtItemNumber);
				}
			}
		});
		txtItemNumber.setBounds(138, 363, 317, 19);
		txtItemNumber.setColumns(10);
		contentPane.add(txtItemNumber);
		
		
		JLabel lblPrice = new JLabel("Antal");
		lblPrice.setBounds(12, 392, 70, 15);
		contentPane.add(lblPrice);
		
		txtQuantity = new JTextField();
		txtQuantity.setBounds(138, 392, 114, 19);
		txtQuantity.setColumns(10);
		contentPane.add(txtQuantity);		
		
		JButton btnCancel = new JButton("Annuller");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_instance = null;
				_frame.dispose();
			}
		});
		btnCancel.setBounds(338, 446, 117, 25);
		contentPane.add(btnCancel);
		
		JButton btnCreate = new JButton("Tilføj");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(txtItemNumber.getText().length() > 0 && txtQuantity.getText().length() > 0)
				{
					long itemNumber = Long.parseLong(txtItemNumber.getText());
					int quantity = Integer.parseInt(txtQuantity.getText());
					addGroupItem(prodGroupId, itemNumber, quantity);
				}
			}
		});
		btnCreate.setBounds(212, 446, 117, 25);
		contentPane.add(btnCreate);
		
		
		_frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				_instance = null;
				_frame.dispose();
			}
		});
	}
	
	private void addData(int prodGroupId)
	{
		ArrayList<ProductGroupItem> prodGroupItems = makeCollection(_productController.getProductGroup(prodGroupId).getItems());
		Object[][] data = {};
		model.setDataVector(data, columnNames);
		for(ProductGroupItem item : prodGroupItems)
		{
			Object[] row = new Object[]{item.getProduct().getItemNumber(), item.getProduct().getItemName(), item.getQuantity() };
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
	
	private void addGroupItem(int prodGroupId, long itemNumber, int quantity) {
		boolean succeeded = false;
		
		try 
		{
			succeeded = _productController.createProductGroupItem(prodGroupId, itemNumber, quantity);
			if(succeeded) 
			{
				JOptionPane.showMessageDialog(null, "Produktet er nu tilføjet til denne produkt gruppe", "INFORMATION!", JOptionPane.INFORMATION_MESSAGE);
				txtItemNumber.setText("");
				txtQuantity.setText("");
				addData(prodGroupId);
			}
			else 
				JOptionPane.showMessageDialog(null, "Der skete en fejl da produktet skulle tilføjes til gruppen. Tjek at et produkt eksistere med det produktnummer", "FEJL!", JOptionPane.WARNING_MESSAGE);
		}
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, GlobalUI.messageHandling(99), "FEJL!", JOptionPane.WARNING_MESSAGE);
		}
	}
}
