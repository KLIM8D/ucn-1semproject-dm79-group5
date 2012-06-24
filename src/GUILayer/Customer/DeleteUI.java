package GUILayer.Customer;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import ControlLayer.CustomerCtrl;
import ControlLayer.PersonCtrl;
import GUILayer.GlobalUI;
import ModelLayer.Person;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class DeleteUI {

	private PersonCtrl _personCtrl;
    private CustomerCtrl _customerCtrl;
	private static JFrame _frame;
	private static DeleteUI _instance;
	private JPanel contentPane;
	private JTextField txtCustomerId;
	private JButton btnAnnuller;
	private JButton btnSlet;
	
	public static JFrame createWindow()
	{
		if(_instance == null)
			_instance = new DeleteUI();
		
		return _frame;
	}


	public DeleteUI() {
		_personCtrl = new PersonCtrl();
        _customerCtrl = new CustomerCtrl();
        
		_frame = new JFrame();
		_frame.setTitle("Slet kunde");
		_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		_frame.setBounds(0, 0, 368, 135);
		_frame.setResizable(false);
		_frame.setVisible(true);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		_frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCustomerId = new JLabel("Telefon nummer");
		lblCustomerId.setBounds(12, 12, 119, 15);
		contentPane.add(lblCustomerId);
		
		txtCustomerId = new JTextField();
		txtCustomerId.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(txtCustomerId.getText().length() > 0)
				{				
					GlobalUI.checkIfLong(txtCustomerId);				
				}
			}
		});
		txtCustomerId.setBounds(134, 10, 220, 19);
		contentPane.add(txtCustomerId);
		txtCustomerId.setColumns(10);
		
		btnAnnuller = new JButton("Annuller");
		btnAnnuller.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_instance = null;
				_frame.dispose();
			}
		});
		btnAnnuller.setBounds(237, 64, 117, 25);
		contentPane.add(btnAnnuller);
		
		btnSlet = new JButton("Slet");
		btnSlet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteCustomer();
			}
		});
		btnSlet.setBounds(108, 64, 117, 25);
		contentPane.add(btnSlet);
		
		_frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				_instance = null;
				_frame.dispose();
			}
		});
	}
	
	private void deleteCustomer() {
		try {
			long customerId = Long.parseLong(txtCustomerId.getText());
			
			Person per = _customerCtrl.getCustomer(customerId).getPerson();
			if(per != null) {
				long personId = per.getPersonId();
				boolean done = _personCtrl.removePerson(personId);
                boolean done2 = _customerCtrl.removeCustomer(customerId);
                if(!done || !done2) {
                	JOptionPane.showMessageDialog(null, GlobalUI.messageHandling(99), "FEJL!", JOptionPane.WARNING_MESSAGE);
                }
                else {
                	JOptionPane.showMessageDialog(null, GlobalUI.messageHandling(24), "INFORMATION!", JOptionPane.INFORMATION_MESSAGE);
					_instance = null;
					_frame.dispose();
                }
			}
			else {
				JOptionPane.showMessageDialog(null, GlobalUI.messageHandling(25), "FEJL!", JOptionPane.WARNING_MESSAGE);
			}
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, GlobalUI.messageHandling(99), "FEJL!", JOptionPane.WARNING_MESSAGE);
		}
	}

}
