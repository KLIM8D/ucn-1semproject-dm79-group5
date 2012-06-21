package GUILayer.Customer;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import ControlLayer.CustomerCtrl;
import GUILayer.GlobalUI;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AssoDiscGroupUI {

	private CustomerCtrl _customerCtrl;
	private static JFrame _frame;
	private static AssoDiscGroupUI _instance;
	private JPanel contentPane;
	private JComboBox<String> drpDiscGroup;
	private JTextField txtDiscAmount;
	private JTextField txtPhoneNo;
	
	public static JFrame createWindow()
	{
		if(_instance == null)
			_instance = new AssoDiscGroupUI();
		
		return _frame;
	}


	@SuppressWarnings({ "unchecked", "rawtypes" })
	public AssoDiscGroupUI() {
		_customerCtrl = new CustomerCtrl();
		
		_frame = new JFrame();
		_frame.setTitle("Tilknyt kunde til rabatgruppe");
		_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		_frame.setBounds(0, 0, 368, 187);
		_frame.setResizable(false);
		_frame.setVisible(true);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		_frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDiscGroup = new JLabel("Rabat grupper");
		lblDiscGroup.setBounds(12, 12, 116, 15);
		contentPane.add(lblDiscGroup);
		
		JLabel lblPhoneNo = new JLabel("Telefon nummer");
		lblPhoneNo.setBounds(12, 62, 116, 15);
		contentPane.add(lblPhoneNo);
		
		JLabel lblDiscAmount = new JLabel("Rabat beløb");
		lblDiscAmount.setBounds(12, 37, 116, 15);
		contentPane.add(lblDiscAmount);
		
		drpDiscGroup = new JComboBox();
		drpDiscGroup.setBounds(137, 7, 217, 24);
		drpDiscGroup.addItem(GlobalUI.translateDiscountTypes(1));
		drpDiscGroup.addItem(GlobalUI.translateDiscountTypes(2));
		drpDiscGroup.addItem(GlobalUI.translateDiscountTypes(3));
		drpDiscGroup.addItem(GlobalUI.translateDiscountTypes(4));
		drpDiscGroup.addItem(GlobalUI.translateDiscountTypes(5));
		contentPane.add(drpDiscGroup);
		
		txtDiscAmount = new JTextField();
		txtDiscAmount.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(txtDiscAmount.getText().length() > 0)
				{				
					GlobalUI.checkIfInt(txtDiscAmount);				
				}
			}
		});
		txtDiscAmount.setBounds(137, 35, 174, 19);
		contentPane.add(txtDiscAmount);
		txtDiscAmount.setColumns(10);
		
		txtPhoneNo = new JTextField();
		txtPhoneNo.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(txtPhoneNo.getText().length() > 0)
				{				
					GlobalUI.checkIfInt(txtPhoneNo);				
				}
			}
		});
		txtPhoneNo.setBounds(137, 60, 217, 19);
		contentPane.add(txtPhoneNo);
		txtPhoneNo.setColumns(10);
		
		JLabel lblDiscCurrency = new JLabel("DKK");
		lblDiscCurrency.setBounds(312, 37, 42, 15);
		contentPane.add(lblDiscCurrency);
		
		JButton btnCancel = new JButton("Annuller");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_instance = null;
				_frame.dispose();
			}
		});
		btnCancel.setBounds(237, 114, 117, 25);
		contentPane.add(btnCancel);
		
		JButton btnAsso = new JButton("Tilknyt");
		btnAsso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				assoDiscGroup();
			}
		});
		btnAsso.setBounds(108, 114, 117, 25);
		contentPane.add(btnAsso);
		
		_frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				_instance = null;
				_frame.dispose();
			}
		});
	}
	
	private void assoDiscGroup() {
		try {
			long phoneNo = Long.parseLong(txtPhoneNo.getText());
			int groupId = drpDiscGroup.getSelectedIndex() + 1;
			String price = txtDiscAmount.getText();
			
			boolean succeeded = _customerCtrl.createDiscount(phoneNo, groupId, price);
			if (succeeded) {
				JOptionPane.showMessageDialog(null, GlobalUI.messageHandling(26), "INFORMATION!", JOptionPane.INFORMATION_MESSAGE);
				_instance = null;
				_frame.dispose();
				
			}
			else {
				JOptionPane.showMessageDialog(null, GlobalUI.messageHandling(27), "FEJL!", JOptionPane.WARNING_MESSAGE);
			}
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, GlobalUI.messageHandling(99), "FEJL!", JOptionPane.WARNING_MESSAGE);
		}
	}
}
