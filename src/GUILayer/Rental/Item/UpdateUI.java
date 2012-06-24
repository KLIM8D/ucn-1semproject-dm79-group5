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
import ModelLayer.LeasingItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JSeparator;

public class UpdateUI 
{

	private static JFrame _frame;
	private static UpdateUI _instance;
	private JPanel contentPane;
	private JTextField txtItemNumber;
	private JTextField txtItemName;
	private JTextField txtRentPrice;
	private JTextField txtMaxAvail;
	private LeaseCtrl _leaseCtrl;
	private long itemNumber;
	public static JFrame createWindow()
	{
		if(_instance == null)
			_instance = new UpdateUI();
		
		return _frame;
	}

	private UpdateUI() 
	{
		_leaseCtrl = new LeaseCtrl();
		_frame = new JFrame();
		_frame.setResizable(false);
		_frame.setTitle("Opdater udlejningsprodukt");
		_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		_frame.setBounds(0, 0, 600, 212);
		_frame.setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		_frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBarCode = new JLabel("Produkt nummer");
		lblBarCode.setBounds(12, 27, 137, 14);
		contentPane.add(lblBarCode);
		
		txtItemNumber = new JTextField();
		txtItemNumber.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(txtItemNumber.getText().length() > 0)
				{
					GlobalUI.checkIfLong(txtItemNumber);
				}
			}
		});
		txtItemNumber.setBounds(155, 24, 281, 20);
		contentPane.add(txtItemNumber);
		txtItemNumber.setColumns(10);
		
		JLabel lblProduct = new JLabel("Produktnavn");
		lblProduct.setBounds(12, 67, 127, 14);
		contentPane.add(lblProduct);
		
		txtItemName = new JTextField();
		txtItemName.setColumns(10);
		txtItemName.setBounds(155, 64, 281, 20);
		contentPane.add(txtItemName);
		
		JLabel lblPrice = new JLabel("Udlejningspris");
		lblPrice.setBounds(12, 92, 127, 14);
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
		txtRentPrice.setBounds(155, 90, 76, 20);
		contentPane.add(txtRentPrice);
		
		JButton btnCreate = new JButton("Udfør");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isFormValid())
					updateItem();
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
		
		JLabel lblMax = new JLabel("Max beholdning");
		lblMax.setBounds(12, 117, 127, 14);
		contentPane.add(lblMax);
		
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
		txtMaxAvail.setBounds(155, 114, 76, 20);
		contentPane.add(txtMaxAvail);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(22, 53, 400, 2);
		contentPane.add(separator);
		
		JButton btnGetdata = new JButton("Hent data");
		btnGetdata.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtItemNumber.getText().length() > 0)
				{
					itemNumber = Long.parseLong(txtItemNumber.getText());
					addData();
				}
			}
		});
		btnGetdata.setBounds(448, 22, 117, 25);
		contentPane.add(btnGetdata);
		
		_frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				_instance = null;
				_frame.dispose();
			}
		});
	}
	
	private void addData()
	{
		try
		{
			LeasingItem item = _leaseCtrl.getLeaseItem(itemNumber);
			if(item != null)
			{
				txtItemName.setText(item.getItemName());
				txtRentPrice.setText(item.getRentPrice().toString());
				txtMaxAvail.setText(item.getMaxAvaible() + "");
			}
			else 
			{
				JOptionPane.showMessageDialog(null, "Der blev ikke fundet et udlejnings produkt med det produkt nummer", "FEJL!", JOptionPane.WARNING_MESSAGE);
			}
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, GlobalUI.messageHandling(99), "FEJL!", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void updateItem() 
	{
		boolean succeeded = false;
		
		try 
		{
			String itemName = txtItemName.getText();
			String rentPrice = txtRentPrice.getText();
			int maxAvaible = Integer.parseInt(txtMaxAvail.getText());
			succeeded =  _leaseCtrl.updateLeasingItem(itemNumber, itemName, rentPrice, maxAvaible);
			if(succeeded) 
			{
				JOptionPane.showMessageDialog(null, GlobalUI.messageHandling(13), "INFORMATION!", JOptionPane.INFORMATION_MESSAGE);
				_instance = null;
				_frame.dispose();
			}
			else 
			{
				JOptionPane.showMessageDialog(null, GlobalUI.messageHandling(14), "FEJL!", JOptionPane.WARNING_MESSAGE);
			}
			
		}
		catch (Exception e) 
		{
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
