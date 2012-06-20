package GUILayer.Product;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import ControlLayer.ProductCtrl;
import GUILayer.GlobalUI;
import ModelLayer.ProductCategory;

public class CreateUI {

	private static JFrame _frame;
	private static CreateUI _instance;
	private JPanel contentPane;
	private JTextField txtProdId;
	private JTextField txtProdName;
	private JTextField txtProdMin;
	private JTextField txtProdMax;
	private JTextField txtProdPrice;
	private JComboBox<String> drpCategories;
	private ProductCtrl _productController;
	
	public static JFrame createWindow()
	{
		if(_instance == null)
			_instance = new CreateUI();
		
		return _frame;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private CreateUI() {
		_productController = new ProductCtrl();
		
		_frame = new JFrame();
		
		_frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		_frame.setTitle("Opret produkt");
		_frame.setBounds(0, 0, 509, 269);
		_frame.setResizable(false);
		_frame.setVisible(true);
		

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		_frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblProdId = new JLabel("Produkt nr");
		lblProdId.setBounds(12, 12, 99, 15);
		contentPane.add(lblProdId);
		
		JLabel lblProdName = new JLabel("Produkt navn");
		lblProdName.setBounds(12, 37, 99, 15);
		contentPane.add(lblProdName);
		
		txtProdId = new JTextField();
		txtProdId.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				GlobalUI.checkIfLong(txtProdId);
			}
		});
		txtProdId.setBounds(121, 10, 371, 19);
		contentPane.add(txtProdId);
		txtProdId.setColumns(10);
		
		txtProdName = new JTextField();
		txtProdName.setBounds(121, 35, 371, 19);
		contentPane.add(txtProdName);
		txtProdName.setColumns(10);
		
		JLabel lblCatID = new JLabel("Kategori");
		lblCatID.setBounds(12, 64, 99, 15);
		contentPane.add(lblCatID);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 133, 480, 1);
		contentPane.add(separator);
		
		JLabel lblProdMin = new JLabel("Min. beholding");
		lblProdMin.setBounds(12, 147, 124, 15);
		contentPane.add(lblProdMin);
		
		txtProdMin = new JTextField();
		txtProdMin.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				GlobalUI.checkIfInt(txtProdMin);
			}
		});
		txtProdMin.setBounds(121, 147, 114, 19);
		contentPane.add(txtProdMin);
		txtProdMin.setColumns(10);
		
		JLabel lblProdMax = new JLabel("Max. beholdning");
		lblProdMax.setBounds(253, 147, 124, 15);
		contentPane.add(lblProdMax);
		
		txtProdMax = new JTextField();
		txtProdMax.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				GlobalUI.checkIfInt(txtProdMax);
			}
		});
		txtProdMax.setBounds(378, 145, 114, 19);
		contentPane.add(txtProdMax);
		txtProdMax.setColumns(10);
		
		JLabel lblProdPrice = new JLabel("Pris");
		lblProdPrice.setBounds(12, 105, 70, 15);
		contentPane.add(lblProdPrice);
		
		txtProdPrice = new JTextField();
		txtProdPrice.setBounds(121, 103, 114, 19);
		contentPane.add(txtProdPrice);
		txtProdPrice.setColumns(10);
		
		JLabel lblCurrency = new JLabel("DKK");
		lblCurrency.setBounds(240, 105, 70, 15);
		contentPane.add(lblCurrency);
		
		JButton btnCancel = new JButton("Annuller");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_instance = null;
				_frame.dispose();
			}
		});
		btnCancel.setBounds(375, 199, 117, 25);
		contentPane.add(btnCancel);
		
		JButton btnCreate = new JButton("Opret");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createProduct();
			}
		});
		btnCreate.setBounds(246, 199, 117, 25);
		contentPane.add(btnCreate);
		
		ArrayList<ProductCategory> categories = makeCollection(_productController.getAllCategories());
		String[] categoryNames = new String[categories.size()];
		for(int i = 0; i < categories.size(); i++)
			categoryNames[i] = categories.get(i).getCategoryName();
		
		drpCategories = new JComboBox(categoryNames);
		drpCategories.setBounds(121, 62, 300, 20);
		contentPane.add(drpCategories);
		
		_frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				_instance = null;
				_frame.dispose();
			}
		});
	}
	
	private void createProduct() {
		boolean succeeded = false;
		
		try {
			long itemNumber = Long.parseLong(txtProdId.getText());
			String itemName = txtProdName.getText();
			int minInStock = Integer.parseInt(txtProdMin.getText());
			int maxInStock = Integer.parseInt(txtProdMax.getText());
			String price = txtProdPrice.getText();
			int categoryId = drpCategories.getSelectedIndex() + 1;
			succeeded = _productController.createProduct(itemNumber, itemName, minInStock, maxInStock, price, categoryId);
				
			if(succeeded) {
				JOptionPane.showMessageDialog(null, GlobalUI.messageHandling(05), "INFORMATION!", JOptionPane.INFORMATION_MESSAGE);
				_instance = null;
				_frame.dispose();
			}
			else {
				JOptionPane.showMessageDialog(null, GlobalUI.messageHandling(06), "FEJL!", JOptionPane.WARNING_MESSAGE);
			}
		}
	
		catch (Exception err) {
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