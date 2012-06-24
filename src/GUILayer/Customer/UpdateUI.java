package GUILayer.Customer;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import ControlLayer.CustomerCtrl;
import ControlLayer.PersonCtrl;
import GUILayer.GlobalUI;
import ModelLayer.Customer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class UpdateUI {

	private PersonCtrl _personCtrl;
    private CustomerCtrl _customerCtrl;
	private static JFrame _frame;
	private static UpdateUI _instance;
	private JPanel pnlUpdate;
	private JTextField txtZipCode_Private;
	private JTextField txtCity_Private;
	private JTextField txtAddress_Private;
	private JTextField txtFullName_Private;
	private JTextField txtPhoneNo_Private;
	private JTextField txtVatNo_Business;
	private JTextField txtZipCode_Business;
	private JTextField txtCity_Business;
	private JTextField txtCompanyName_Business;
	private JTextField txtContactPerson_Business;
	private JTextField txtPhoneNo_Business;
	private JTextField txtAddress_Business;

	public static JFrame createWindow()
	{
		if(_instance == null)
			_instance = new UpdateUI();
		
		return _frame;
	}
	
	private UpdateUI() {
		_personCtrl = new PersonCtrl();
        _customerCtrl = new CustomerCtrl();
        
		_frame = new JFrame();
		_frame.setTitle("Opdatere kunde");
		_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		_frame.setBounds(0, 0, 382, 298);
		_frame.setResizable(false);
		_frame.setVisible(true);
		pnlUpdate = new JPanel();
		pnlUpdate.setBorder(new EmptyBorder(5, 5, 5, 5));
		pnlUpdate.setLayout(new BorderLayout(0, 0));
		_frame.setContentPane(pnlUpdate);
		
		JTabbedPane tabUpdate = new JTabbedPane(JTabbedPane.TOP);
		pnlUpdate.add(tabUpdate, BorderLayout.CENTER);
		
		JPanel pnlPrivate = new JPanel();
		tabUpdate.addTab("Privat", null, pnlPrivate, null);
		pnlPrivate.setLayout(null);
		
		JLabel lblZipCode_Private = new JLabel("Post.Nr.");
		lblZipCode_Private.setBounds(12, 89, 70, 15);
		pnlPrivate.add(lblZipCode_Private);
		
		txtZipCode_Private = new JTextField();
		txtZipCode_Private.setEnabled(false);
		txtZipCode_Private.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(txtZipCode_Private.getText().length() > 0)
				{				
					GlobalUI.checkIfInt(txtZipCode_Private);				
				}
			}
		});
		txtZipCode_Private.setColumns(10);
		txtZipCode_Private.setBounds(127, 87, 40, 19);
		pnlPrivate.add(txtZipCode_Private);
		
		JLabel lblCity_Private = new JLabel("By");
		lblCity_Private.setBounds(185, 89, 38, 15);
		pnlPrivate.add(lblCity_Private);
		
		txtCity_Private = new JTextField();
		txtCity_Private.setEnabled(false);
		txtCity_Private.setColumns(10);
		txtCity_Private.setBounds(210, 87, 145, 19);
		pnlPrivate.add(txtCity_Private);
		
		txtAddress_Private = new JTextField();
		txtAddress_Private.setEnabled(false);
		txtAddress_Private.setColumns(10);
		txtAddress_Private.setBounds(127, 62, 227, 19);
		pnlPrivate.add(txtAddress_Private);
		
		JLabel lblAddress_Private = new JLabel("Adresse");
		lblAddress_Private.setBounds(12, 64, 70, 15);
		pnlPrivate.add(lblAddress_Private);
		
		JLabel lblFullName_Private = new JLabel("Navn");
		lblFullName_Private.setBounds(12, 39, 70, 15);
		pnlPrivate.add(lblFullName_Private);
		
		txtFullName_Private = new JTextField();
		txtFullName_Private.setEnabled(false);
		txtFullName_Private.setText("");
		txtFullName_Private.setColumns(10);
		txtFullName_Private.setBounds(127, 37, 227, 19);
		pnlPrivate.add(txtFullName_Private);
		
		txtPhoneNo_Private = new JTextField();
		txtPhoneNo_Private.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(txtPhoneNo_Private.getText().length() > 0)
				{				
					GlobalUI.checkIfLong(txtPhoneNo_Private);				
				}
			}
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					long data = Long.parseLong(txtPhoneNo_Private.getText());
					getPrivateInfo(data);
				}
			}
		});
		txtPhoneNo_Private.setColumns(10);
		txtPhoneNo_Private.setBounds(127, 12, 227, 19);
		pnlPrivate.add(txtPhoneNo_Private);
		
		JLabel lblPhoneNo_Private = new JLabel("Telefon");
		lblPhoneNo_Private.setBounds(12, 14, 97, 15);
		pnlPrivate.add(lblPhoneNo_Private);
		
		JButton btnUpdate_Private = new JButton("Opdater");
		btnUpdate_Private.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				long data = Long.parseLong(txtPhoneNo_Private.getText());
				updatePrivateCustomer(data);
			}
		});
		btnUpdate_Private.setBounds(105, 191, 117, 25);
		pnlPrivate.add(btnUpdate_Private);
		
		JButton btnCancel_Private = new JButton("Annuller");
		btnCancel_Private.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_instance = null;
				_frame.dispose();
			}
		});
		btnCancel_Private.setBounds(238, 191, 117, 25);
		pnlPrivate.add(btnCancel_Private);
		
		JPanel pnlBusiness = new JPanel();
		tabUpdate.addTab("Erhverv", null, pnlBusiness, null);
		pnlBusiness.setLayout(null);
		
		txtVatNo_Business = new JTextField();
		txtVatNo_Business.setEnabled(false);
		txtVatNo_Business.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(txtVatNo_Business.getText().length() > 0)
				{				
					GlobalUI.checkIfLong(txtVatNo_Business);				
				}
			}
		});
		txtVatNo_Business.setColumns(10);
		txtVatNo_Business.setBounds(128, 137, 227, 19);
		pnlBusiness.add(txtVatNo_Business);
		
		JLabel lblVatNo_Business = new JLabel("CVR-Nummer");
		lblVatNo_Business.setBounds(12, 139, 98, 15);
		pnlBusiness.add(lblVatNo_Business);
		
		JLabel lblZipCode_Business = new JLabel("Post.Nr.");
		lblZipCode_Business.setBounds(12, 114, 70, 15);
		pnlBusiness.add(lblZipCode_Business);
		
		txtZipCode_Business = new JTextField();
		txtZipCode_Business.setEnabled(false);
		txtZipCode_Business.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(txtZipCode_Business.getText().length() > 0)
				{				
					GlobalUI.checkIfInt(txtZipCode_Business);				
				}
			}
		});
		txtZipCode_Business.setColumns(10);
		txtZipCode_Business.setBounds(128, 112, 40, 19);
		pnlBusiness.add(txtZipCode_Business);
		
		JLabel lblCity_Business = new JLabel("By");
		lblCity_Business.setBounds(184, 114, 38, 15);
		pnlBusiness.add(lblCity_Business);
		
		txtCity_Business = new JTextField();
		txtCity_Business.setEnabled(false);
		txtCity_Business.setColumns(10);
		txtCity_Business.setBounds(210, 112, 145, 19);
		pnlBusiness.add(txtCity_Business);
		
		txtCompanyName_Business = new JTextField();
		txtCompanyName_Business.setEnabled(false);
		txtCompanyName_Business.setColumns(10);
		txtCompanyName_Business.setBounds(127, 62, 227, 19);
		pnlBusiness.add(txtCompanyName_Business);
		
		JLabel lblCompanyName_Business = new JLabel("Virksomhed");
		lblCompanyName_Business.setBounds(12, 64, 112, 15);
		pnlBusiness.add(lblCompanyName_Business);
		
		JLabel lblContactPerson_Business = new JLabel("Kontaktperson");
		lblContactPerson_Business.setBounds(12, 39, 112, 15);
		pnlBusiness.add(lblContactPerson_Business);
		
		txtContactPerson_Business = new JTextField();
		txtContactPerson_Business.setEnabled(false);
		txtContactPerson_Business.setText("");
		txtContactPerson_Business.setColumns(10);
		txtContactPerson_Business.setBounds(127, 37, 227, 19);
		pnlBusiness.add(txtContactPerson_Business);
		
		txtPhoneNo_Business = new JTextField();
		txtPhoneNo_Business.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(txtPhoneNo_Business.getText().length() > 0)
				{				
					GlobalUI.checkIfInt(txtPhoneNo_Business);				
				}
			}
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					long data = Long.parseLong(txtPhoneNo_Business.getText());
					getBusinessInfo(data);
				}
			}
		});
		txtPhoneNo_Business.setColumns(10);
		txtPhoneNo_Business.setBounds(127, 12, 227, 19);
		pnlBusiness.add(txtPhoneNo_Business);
		
		JLabel lblPhoneNo_Business = new JLabel("Telefon");
		lblPhoneNo_Business.setBounds(12, 14, 97, 15);
		pnlBusiness.add(lblPhoneNo_Business);
		
		txtAddress_Business = new JTextField();
		txtAddress_Business.setEnabled(false);
		txtAddress_Business.setColumns(10);
		txtAddress_Business.setBounds(127, 86, 227, 19);
		pnlBusiness.add(txtAddress_Business);
		
		JLabel lblAddress_Business = new JLabel("Adresse");
		lblAddress_Business.setBounds(12, 89, 112, 15);
		pnlBusiness.add(lblAddress_Business);
		
		JButton btnAnnuller_Business = new JButton("Annuller");
		btnAnnuller_Business.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_instance = null;
				_frame.dispose();
			}
		});
		btnAnnuller_Business.setBounds(238, 191, 117, 25);
		pnlBusiness.add(btnAnnuller_Business);
		
		JButton btnUpdate_Business = new JButton("Opdater");
		btnUpdate_Business.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				long data = Long.parseLong(txtPhoneNo_Business.getText());
				updateBusinessCustomer(data);
			}
		});
		btnUpdate_Business.setBounds(105, 191, 117, 25);
		pnlBusiness.add(btnUpdate_Business);
		
		_frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				_instance = null;
				_frame.dispose();
			}
		});
	}
	
	private void getPrivateInfo(long data) {
		try {
			data = Long.parseLong(txtPhoneNo_Private.getText());
			Customer customer = _customerCtrl.getCustomer(data);
			if(customer == null) {
				JOptionPane.showMessageDialog(null, GlobalUI.messageHandling(30), "FEJL!", JOptionPane.WARNING_MESSAGE);
			}
			else {
				if(customer.getIsBusiness()) {
					JOptionPane.showMessageDialog(null, GlobalUI.messageHandling(28), "FEJL!", JOptionPane.WARNING_MESSAGE);
				}
				else {
					txtPhoneNo_Private.setEnabled(false);
					txtFullName_Private.setEnabled(true);
					txtAddress_Private.setEnabled(true);
					txtZipCode_Private.setEnabled(true);
					txtCity_Private.setEnabled(true);
					
					txtFullName_Private.setText(customer.getPerson().getName());
					txtAddress_Private.setText(customer.getPerson().getAddress());
					txtZipCode_Private.setText(Integer.toString(customer.getPerson().getZipCode()));
					txtCity_Private.setText(customer.getPerson().getCity());
				}
	
			}
			
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, GlobalUI.messageHandling(99), "FEJL!", JOptionPane.WARNING_MESSAGE);
		}
		
	}
	
	private void getBusinessInfo(long data) {
		try {
			data = Long.parseLong(txtPhoneNo_Private.getText());
			Customer customer = _customerCtrl.getCustomer(data);
			if(customer == null) {
				JOptionPane.showMessageDialog(null, GlobalUI.messageHandling(30), "FEJL!", JOptionPane.WARNING_MESSAGE);
			}
			else {
				if(customer.getIsBusiness()) {
					txtPhoneNo_Business.setEnabled(false);
					txtContactPerson_Business.setEnabled(true);
					txtCompanyName_Business.setEnabled(true);
					txtAddress_Business.setEnabled(true);
					txtZipCode_Business.setEnabled(true);
					txtCity_Business.setEnabled(true);
					txtVatNo_Business.setEnabled(true);
					
					txtContactPerson_Business.setText(customer.getBusiness().getContactPerson());
					txtCompanyName_Business.setText(customer.getPerson().getName());
					txtAddress_Business.setText(customer.getPerson().getAddress());
					txtZipCode_Business.setText(Integer.toString(customer.getPerson().getZipCode()));
					txtCity_Business.setText(customer.getPerson().getCity());
					txtVatNo_Business.setText(Long.toString(customer.getBusiness().getCvrNo()));	
				}
				else {
					JOptionPane.showMessageDialog(null, GlobalUI.messageHandling(29), "FEJL!", JOptionPane.WARNING_MESSAGE);
				}
				
			}
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, GlobalUI.messageHandling(99), "FEJL!", JOptionPane.WARNING_MESSAGE);
		}
		
	}
	
	private void updatePrivateCustomer(long data) {
		try {
			Customer customer = _customerCtrl.getCustomer(data);
			if(customer != null) {
				int zipCode = Integer.parseInt(txtZipCode_Private.getText());
				long phoneNo = Long.parseLong(txtPhoneNo_Private.getText());
				
				_personCtrl.updatePerson(phoneNo, txtFullName_Private.getText(), txtAddress_Private.getText(), txtCity_Private.getText(), zipCode);
				
				JOptionPane.showMessageDialog(null, GlobalUI.messageHandling(13), "INFORMATION!", JOptionPane.INFORMATION_MESSAGE);
                _instance = null;
				_frame.dispose();
			}
			else {
				JOptionPane.showMessageDialog(null, GlobalUI.messageHandling(14), "FEJL!", JOptionPane.WARNING_MESSAGE);
			}
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, GlobalUI.messageHandling(99), "FEJL!", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void updateBusinessCustomer(long data) {
		try {
			Customer customer = _customerCtrl.getCustomer(data);
			if(customer != null) {
				int zipCode = Integer.parseInt(txtZipCode_Business.getText());
				long phoneNo = Long.parseLong(txtPhoneNo_Business.getText());
				long vatNo = Long.parseLong(txtVatNo_Business.getText());
				
				_personCtrl.updatePerson(phoneNo, txtCompanyName_Business.getText(), txtAddress_Business.getText(), txtCity_Business.getText(), zipCode);
				_customerCtrl.updateCustomer(phoneNo, txtContactPerson_Business.getText(), vatNo);
				
				JOptionPane.showMessageDialog(null, GlobalUI.messageHandling(13), "INFORMATION!", JOptionPane.INFORMATION_MESSAGE);
                _instance = null;
				_frame.dispose();
			}
			else {
				JOptionPane.showMessageDialog(null, GlobalUI.messageHandling(14), "FEJL!", JOptionPane.WARNING_MESSAGE);
			}
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, GlobalUI.messageHandling(99), "FEJL!", JOptionPane.WARNING_MESSAGE);
		}
	}
}
