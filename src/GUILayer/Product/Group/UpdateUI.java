package GUILayer.Product.Group;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import ControlLayer.ProductCtrl;
import GUILayer.GlobalUI;
import ModelLayer.ProductGroup;

import javax.swing.JSeparator;
import java.awt.Color;

public class UpdateUI 
{
	private static JFrame _frame;
	private static UpdateUI _instance;
	private JPanel contentPane;
	
	//Controls
	//topPanel
	private JLabel lblProdGroupId;
	private JTextField txtProdGroupId;
	private JButton btnGetProductGroup;
		
	//MainContent panel
	private JTextField txtGroupName;
	private JTextField txtPrice;
	
	//Controllers
	private ProductCtrl _prodCtrl;
	
	
	
	public static JFrame createWindow()
	{
		if(_instance == null)
			_instance = new UpdateUI();
		
		return _frame;
	}
	
	private UpdateUI() {
		_prodCtrl = new ProductCtrl();
		
		_frame = new JFrame();		
		_frame.setTitle("Opdater produkt gruppe");
		_frame.setVisible(true);
		_frame.setResizable(false);
		_frame.setBounds(0, 0, 567, 200);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		_frame.setContentPane(contentPane);
		
		//Top layout
		JPanel topPanel = new JPanel();
		topPanel.setBounds(5, 0, 567, 41);
		topPanel.setLayout(null);
		txtProdGroupId = new JTextField();
		txtProdGroupId.setBounds(235, 10, 142, 20);
		txtProdGroupId.setColumns(10);
		txtProdGroupId.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(txtProdGroupId.getText().length() > 0)
				{
					GlobalUI.checkIfInt(txtProdGroupId);
				}
			}
		});
		lblProdGroupId = new JLabel("Indtast produkt gruppe id: ");
		lblProdGroupId.setBounds(12, 12, 210, 15);
		topPanel.add(lblProdGroupId);
		topPanel.add(txtProdGroupId);
		btnGetProductGroup = new JButton("Hent data");
		btnGetProductGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtProdGroupId.getText().length() > 0)
				{
					int prodGroupId = Integer.parseInt(txtProdGroupId.getText());
					getGroupData(prodGroupId);
				}
			}
		});
		contentPane.setLayout(null);
		btnGetProductGroup.setBounds(420, 7, 113, 25);
		topPanel.add(btnGetProductGroup);
		
		contentPane.add(topPanel);
		
		//Update panel
		JPanel mainContentPanel = new JPanel();
		mainContentPanel.setBounds(5, 44, 567, 136);
		contentPane.add(mainContentPanel);
		mainContentPanel.setLayout(null);
		
		JLabel lblItemName = new JLabel("Titel: ");
		lblItemName.setBounds(12, 31, 120, 20);
		txtGroupName = new JTextField();
		txtGroupName.setBounds(235, 31, 300, 20);
		txtGroupName.setColumns(10);
		mainContentPanel.add(lblItemName);
		mainContentPanel.add(txtGroupName);
		
		JLabel lblPrice = new JLabel("Pris: ");
		lblPrice.setBounds(12, 63, 200, 20);
		mainContentPanel.add(lblPrice);
		
		txtPrice = new JTextField();
		txtPrice.setColumns(10);
		txtPrice.setBounds(235, 63, 300, 20);
		mainContentPanel.add(txtPrice);
		
		JButton btnOpdater = new JButton("Opdater");
		btnOpdater.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtProdGroupId.getText().length() > 0)
				{
					updateProduct();
				}
			}
		});
		btnOpdater.setBounds(290, 95, 117, 25);
		mainContentPanel.add(btnOpdater);
		
		JButton btnCancel = new JButton("Annuller");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_instance = null;
				_frame.dispose();
			}
		});
		btnCancel.setBounds(418, 95, 117, 25);
		mainContentPanel.add(btnCancel);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBounds(0, 0, 578, 2);
		mainContentPanel.add(separator);
		
		//Close window
		_frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		_frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				_frame.dispose();
				_instance = null;
			}
		});
	}
	
	private void getGroupData(int prodGroupId)
	{
		ProductGroup group = _prodCtrl.getProductGroup(prodGroupId);
		if(group != null)
		{
			txtGroupName.setText(group.getProductGroupName());
			txtPrice.setText(group.getPrice().toString());
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Der blev ikke fundet noget produkt, med produkter nummeret: " + prodGroupId, "FEJL!", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void updateProduct()
	{
		boolean succeeded = false;
		try
		{
			succeeded = _prodCtrl.updateProductGroup(Integer.parseInt(txtProdGroupId.getText()), txtGroupName.getText(),	txtPrice.getText());
			if(succeeded)
				JOptionPane.showMessageDialog(null, "Produkt gruppens information er nu opdateret!", "Gennemført!", JOptionPane.WARNING_MESSAGE);
			else
				JOptionPane.showMessageDialog(null, "Der skete en fejl da: " + txtGroupName.getText() + " skulle opdateres", "FEJL!", JOptionPane.WARNING_MESSAGE);
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, GlobalUI.messageHandling(99), "FEJL!", JOptionPane.WARNING_MESSAGE);
		}
	}
}
