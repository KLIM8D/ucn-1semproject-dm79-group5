package GUILayer.Product;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JButton;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import ControlLayer.ProductCtrl;
import GUILayer.GlobalUI;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CreateUI extends JFrame {

	private static final long serialVersionUID = 7199391358909768134L;
	protected static final Component frame = null;
	private JPanel _pnlCreateProduct;
	private JTextField txtProdId;
	private JTextField txtProdName;
	private JTextField txtProdMin;
	private JTextField txtProdMax;
	private JTextField txtProdPrice;
	private JTextField txtCatID;
	private ProductCtrl _productController;

	public CreateUI() {
		_productController = new ProductCtrl();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Opret produkt");
		setBounds(0, 0, 509, 269);
		setLocationRelativeTo(null);
		setResizable(false);
		
		GUILayer.GlobalUI.setWindowStatus(true);

		_pnlCreateProduct = new JPanel();
		_pnlCreateProduct.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(_pnlCreateProduct);
		_pnlCreateProduct.setLayout(null);
		
		JLabel lblProdId = new JLabel("Stregkode");
		lblProdId.setBounds(12, 12, 99, 15);
		_pnlCreateProduct.add(lblProdId);
		
		JLabel lblProdName = new JLabel("Produkt navn");
		lblProdName.setBounds(12, 37, 99, 15);
		_pnlCreateProduct.add(lblProdName);
		
		txtProdId = new JTextField();
		txtProdId.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				GlobalUI.checkIfInt(txtProdId);
			}
		});
		txtProdId.setBounds(121, 10, 371, 19);
		_pnlCreateProduct.add(txtProdId);
		txtProdId.setColumns(10);
		
		txtProdName = new JTextField();
		txtProdName.setBounds(121, 35, 371, 19);
		_pnlCreateProduct.add(txtProdName);
		txtProdName.setColumns(10);
		
		JLabel lblCatID = new JLabel("Kategori ID");
		lblCatID.setBounds(12, 64, 99, 15);
		_pnlCreateProduct.add(lblCatID);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 133, 480, 1);
		_pnlCreateProduct.add(separator);
		
		JLabel lblProdMin = new JLabel("Min. beholding");
		lblProdMin.setBounds(12, 147, 124, 15);
		_pnlCreateProduct.add(lblProdMin);
		
		txtProdMin = new JTextField();
		txtProdMin.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				GlobalUI.checkIfInt(txtProdMin);
			}
		});
		txtProdMin.setBounds(121, 147, 114, 19);
		_pnlCreateProduct.add(txtProdMin);
		txtProdMin.setColumns(10);
		
		JLabel lblProdMax = new JLabel("Max. beholdning");
		lblProdMax.setBounds(253, 147, 124, 15);
		_pnlCreateProduct.add(lblProdMax);
		
		txtProdMax = new JTextField();
		txtProdMax.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				GlobalUI.checkIfInt(txtProdMax);
			}
		});
		txtProdMax.setBounds(378, 145, 114, 19);
		_pnlCreateProduct.add(txtProdMax);
		txtProdMax.setColumns(10);
		
		JLabel lblProdPrice = new JLabel("Pris");
		lblProdPrice.setBounds(12, 107, 70, 15);
		_pnlCreateProduct.add(lblProdPrice);
		
		txtProdPrice = new JTextField();
		txtProdPrice.setBounds(121, 103, 114, 19);
		_pnlCreateProduct.add(txtProdPrice);
		txtProdPrice.setColumns(10);
		
		JLabel lblCurrency = new JLabel("DKK");
		lblCurrency.setBounds(242, 107, 70, 15);
		_pnlCreateProduct.add(lblCurrency);
		
		JButton btnCancel = new JButton("Annuller");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUILayer.GlobalUI.setWindowStatus(false);
				setVisible(false);
			}
		});
		btnCancel.setBounds(375, 199, 117, 25);
		_pnlCreateProduct.add(btnCancel);
		
		JButton btnCreate = new JButton("Opret");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createProduct();
			}
		});
		btnCreate.setBounds(246, 199, 117, 25);
		_pnlCreateProduct.add(btnCreate);
		
		txtCatID = new JTextField();
		txtCatID.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				GlobalUI.checkIfInt(txtCatID);
			}
		});
		txtCatID.setBounds(121, 62, 114, 19);
		_pnlCreateProduct.add(txtCatID);
		txtCatID.setColumns(10);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				GUILayer.GlobalUI.setWindowStatus(false);
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
			int categoryId = Integer.parseInt(txtCatID.getText());
			
			succeeded = _productController.createProduct(itemNumber, itemName, minInStock, maxInStock, price, categoryId);
				
			if(succeeded) {
				JOptionPane.showMessageDialog(frame, GlobalUI.messageHandling(05), "INFORMATION!", JOptionPane.INFORMATION_MESSAGE);
				setVisible(false);
				GUILayer.GlobalUI.setWindowStatus(false);
			}
			else {
				JOptionPane.showMessageDialog(frame, GlobalUI.messageHandling(06), "FEJL!", JOptionPane.WARNING_MESSAGE);
			}
		}
	
		catch (Exception err) {
			JOptionPane.showMessageDialog(frame, GlobalUI.messageHandling(99), "FEJL!", JOptionPane.WARNING_MESSAGE);
		}
	}
}