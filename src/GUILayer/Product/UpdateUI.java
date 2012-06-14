package GUILayer.Product;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ControlLayer.ProductCtrl;
import GUILayer.GlobalUI;
import ModelLayer.Product;
import ModelLayer.ProductCategory;

import javax.swing.JComboBox;
import javax.swing.JSeparator;
import java.awt.Color;

public class UpdateUI {

	private DefaultTableModel model;
	private static JInternalFrame _frame;
	private static UpdateUI _instance;
	private JPanel contentPane;
	
	//Controls
	//topPanel
	private JLabel lblItemNumber;
	private JTextField txtItemNumber;
	private JButton btnGetProduct;
		
	//MainContent panel
	private JTextField txtItemName;
	private JTextField txtMinInStock;
	private JTextField txtMaxInStock;
	private JTextField txtPrice;
	private JComboBox drpCategories;
	
	//Controllers
	private ProductCtrl _prodCtrl;
	
	
	
	public static JInternalFrame createWindow()
	{
		if(_instance == null)
			_instance = new UpdateUI();
		
		return _frame;
	}
	
	private UpdateUI() {
		_prodCtrl = new ProductCtrl();
		
		_frame = new JInternalFrame();		
		_frame.setTitle("Opdater produkt");
		_frame.setClosable(true);
		_frame.setMaximizable(false);
		_frame.setVisible(true);
		//_frame.getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT));
		_frame.setBounds(0, 0, 600, 350);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		_frame.setContentPane(contentPane);
		
		//Top layout
		JPanel topPanel = new JPanel();
		topPanel.setBounds(5, 0, 590, 41);
		topPanel.setLayout(null);
		txtItemNumber = new JTextField();
		txtItemNumber.setBounds(211, 12, 142, 20);
		txtItemNumber.setColumns(10);
		txtItemNumber.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(txtItemNumber.getText().length() > 0)
				{
					GlobalUI.checkIfInt(txtItemNumber);
				}
			}
		});
		lblItemNumber = new JLabel("Indtast produkt nummer: ");
		lblItemNumber.setBounds(12, 12, 181, 15);
		topPanel.add(lblItemNumber);
		topPanel.add(txtItemNumber);
		btnGetProduct = new JButton("Hent data");
		btnGetProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtItemNumber.getText().length() > 0)
				{
					long itemNumber = Long.parseLong(txtItemNumber.getText());
					getProductData(itemNumber);
				}
			}
		});
		contentPane.setLayout(null);
		btnGetProduct.setBounds(365, 7, 113, 25);
		topPanel.add(btnGetProduct);
		
		contentPane.add(topPanel);
		
		
		
		//Update panel
		JPanel mainContentPanel = new JPanel();
		mainContentPanel.setBounds(5, 44, 590, 250);
		contentPane.add(mainContentPanel);
		mainContentPanel.setLayout(null);
		
		JLabel lblItemName = new JLabel("Produkt navn: ");
		lblItemName.setBounds(12, 31, 120, 20);
		txtItemName = new JTextField();
		txtItemName.setBounds(235, 31, 300, 20);
		txtItemName.setColumns(10);
		mainContentPanel.add(lblItemName);
		mainContentPanel.add(txtItemName);
		
		JLabel lblMinInStock = new JLabel("Minimums beholdning: ");
		lblMinInStock.setBounds(12, 60, 200, 20);
		mainContentPanel.add(lblMinInStock);
		
		txtMinInStock = new JTextField();
		txtMinInStock.setColumns(10);
		txtMinInStock.setBounds(235, 60, 300, 20);
		mainContentPanel.add(txtMinInStock);
		
		JLabel lblMaxInStock = new JLabel("Maksimums beholdning: ");
		lblMaxInStock.setBounds(12, 89, 200, 20);
		mainContentPanel.add(lblMaxInStock);
		
		txtMaxInStock = new JTextField();
		txtMaxInStock.setColumns(10);
		txtMaxInStock.setBounds(235, 89, 300, 20);
		mainContentPanel.add(txtMaxInStock);
		
		JLabel lblPrice = new JLabel("Pris: ");
		lblPrice.setBounds(12, 118, 200, 20);
		mainContentPanel.add(lblPrice);
		
		txtPrice = new JTextField();
		txtPrice.setColumns(10);
		txtPrice.setBounds(235, 118, 300, 20);
		mainContentPanel.add(txtPrice);
		
		JLabel lblCategori = new JLabel("Produkt kategori: ");
		lblCategori.setBounds(12, 147, 200, 20);
		mainContentPanel.add(lblCategori);
		
		ArrayList<ProductCategory> categories = makeCollection(_prodCtrl.getAllCategories());
		String[] categoryNames = new String[categories.size()];
		for(int i = 0; i < categories.size(); i++)
			categoryNames[i] = categories.get(i).getCategoryName();
		
		drpCategories = new JComboBox(categoryNames);
		drpCategories.setBounds(235, 147, 300, 20);
		mainContentPanel.add(drpCategories);
		
		JButton btnOpdater = new JButton("Opdater");
		btnOpdater.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtItemNumber.getText().length() > 0)
				{
					updateProduct();
				}
			}
		});
		btnOpdater.setBounds(418, 186, 117, 25);
		mainContentPanel.add(btnOpdater);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBounds(0, 0, 578, 2);
		mainContentPanel.add(separator);
	}
	
	private void getProductData(long itemNumber)
	{
		Product prod = _prodCtrl.getProduct(itemNumber);
		if(prod != null)
		{
			txtItemName.setText(prod.getItemName());
			txtMinInStock.setText(Integer.toString(prod.getMinInStock()));
			txtMaxInStock.setText(Integer.toString(prod.getMaxInStock()));
			txtPrice.setText(prod.getPrice().toString());
			drpCategories.setSelectedIndex(prod.getProductCategory().getCategoryId()-1);
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Der blev ikke fundet noget produkt, med produkter nummeret: " + itemNumber, "FEJL!", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void updateProduct()
	{
		boolean succeeded = false;
		try
		{
			int categoryId = drpCategories.getSelectedIndex()+1;
			succeeded = _prodCtrl.updateProduct(Long.parseLong(txtItemNumber.getText()), txtItemName.getText(), Integer.parseInt(txtMinInStock.getText()), Integer.parseInt(txtMaxInStock.getText()), 
					txtPrice.getText(), categoryId);
			if(succeeded)
				JOptionPane.showMessageDialog(null, "Produktet er nu opdateret!", "Gennemført!", JOptionPane.WARNING_MESSAGE);
			else
				JOptionPane.showMessageDialog(null, "Der skete en fejl da: " + txtItemNumber.getText() + " skulle opdateres", "FEJL!", JOptionPane.WARNING_MESSAGE);
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, GlobalUI.messageHandling(99), "FEJL!", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private <E> ArrayList<E> makeCollection(Iterable<E> iter)
	{
		ArrayList<E> list = new ArrayList<E>();
		for (E item : iter) 
			list.add(item);
		return list;
	}
}
