package GUILayer.Rental.Item;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import ControlLayer.LeaseCtrl;
import GUILayer.GlobalUI;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CreateUI {

	private static JFrame _frame;
	private static CreateUI _instance;
	private JPanel contentPane;
	private JTextField txtItemNumber;
	private JTextField txtItemName;
	private JTextField txtRentPrice;
	private JTextField txtMaxAvail;
	private LeaseCtrl _leaseCtrl;
	public static JFrame createWindow()
	{
		if(_instance == null)
			_instance = new CreateUI();
		
		return _frame;
	}
	
	private CreateUI() {
		_leaseCtrl = new LeaseCtrl();
		_frame = new JFrame();
		_frame.setTitle("Nyt udlejningsprodukt");
		_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		_frame.setBounds(0, 0, 450, 212);
		_frame.setResizable(false);
		_frame.setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		_frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblItemNumber = new JLabel("Produktnummer");
		lblItemNumber.setBounds(10, 11, 116, 14);
		contentPane.add(lblItemNumber);
		
		txtItemNumber = new JTextField();
		txtItemNumber.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(txtItemNumber.getText().length() > 0)
				{
					GlobalUI.checkIfLong(txtItemNumber);
				}
			}
		});
		txtItemNumber.setBounds(130, 8, 304, 20);
		contentPane.add(txtItemNumber);
		txtItemNumber.setColumns(10);
		
		JLabel lblItemName = new JLabel("Produkt navn");
		lblItemName.setBounds(10, 36, 127, 14);
		contentPane.add(lblItemName);
		
		txtItemName = new JTextField();
		txtItemName.setColumns(10);
		txtItemName.setBounds(130, 33, 304, 20);
		contentPane.add(txtItemName);
		
		JLabel lblPrice = new JLabel("Udlejningspris");
		lblPrice.setBounds(10, 61, 127, 14);
		contentPane.add(lblPrice);
		
		txtRentPrice = new JTextField();
		txtRentPrice.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(txtRentPrice.getText().length() > 0)
				{
					GlobalUI.checkIfLong(txtRentPrice);
				}
			}
		});
		txtRentPrice.setColumns(10);
		txtRentPrice.setBounds(130, 58, 76, 20);
		contentPane.add(txtRentPrice);
		
		JButton btnCreate = new JButton("Opret");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isFormValid())
					createItem();
				else
					JOptionPane.showMessageDialog(null, "Alle felter skal udfyldes", "FEJL!", JOptionPane.WARNING_MESSAGE);
			}
		});
		btnCreate.setBounds(188, 146, 117, 23);
		contentPane.add(btnCreate);
		
		JButton btnCancel = new JButton("Annuller");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_instance = null;
				_frame.dispose();
			}
		});
		btnCancel.setBounds(317, 146, 117, 23);
		contentPane.add(btnCancel);
		
		JLabel lblMaxAvail = new JLabel("Max beholdning");
		lblMaxAvail.setBounds(10, 86, 127, 14);
		contentPane.add(lblMaxAvail);
		
		txtMaxAvail = new JTextField();
		txtMaxAvail.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(txtMaxAvail.getText().length() > 0)
				{
					GlobalUI.checkIfLong(txtMaxAvail);
				}
			}
		});
		txtMaxAvail.setColumns(10);
		txtMaxAvail.setBounds(130, 83, 76, 20);
		contentPane.add(txtMaxAvail);
		
		_frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				_instance = null;
				_frame.dispose();
			}
		});
	}
	
	private void createItem() {
		boolean succeeded = false;
		
		try 
		{	
			long itemNumber = Long.parseLong(txtItemNumber.getText());
			String itemName = txtItemName.getText();
			String rentPrice = txtRentPrice.getText();
			int maxAvaible = Integer.parseInt(txtMaxAvail.getText());
			succeeded = _leaseCtrl.createLeaseItem(itemNumber, itemName, rentPrice, maxAvaible);
			if(succeeded) 
			{
				JOptionPane.showMessageDialog(null, GlobalUI.messageHandling(05), "INFORMATION!", JOptionPane.INFORMATION_MESSAGE);
				_instance = null;
				_frame.dispose();
			}
			else {
				JOptionPane.showMessageDialog(null, GlobalUI.messageHandling(10), "FEJL!", JOptionPane.WARNING_MESSAGE);
			}
			
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, GlobalUI.messageHandling(99), "FEJL!", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private boolean isFormValid()
	{
		if(txtItemNumber.getText().length() > 0 && txtItemName.getText().length() > 0 &&
				txtRentPrice.getText().length() > 0 && txtMaxAvail.getText().length() > 0)
			return true;
		
		return false;
	}
}