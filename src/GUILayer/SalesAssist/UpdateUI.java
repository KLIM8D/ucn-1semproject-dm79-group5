package GUILayer.SalesAssist;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import ControlLayer.PersonCtrl;
import GUILayer.GlobalUI;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class UpdateUI {
	
	private PersonCtrl _perController;
	private static JFrame _frame;
	private static UpdateUI _instance;
	private JPanel contentPane;
	private JTextField txtSocNum;
	private JTextField txtFullName;
	private JLabel lblAddress;
	private JTextField txtAddress;
	private JLabel lblZipCode;
	private JTextField txtZipCode;
	private JLabel lblCity;
	private JTextField txtCity;
	
	public static JFrame createWindow()
	{
		if(_instance == null)
			_instance = new UpdateUI();
		
		return _frame;
	}

	private UpdateUI() {
		_perController = new PersonCtrl();
		
		_frame = new JFrame();
		_frame.setTitle("Opdater ekspedient information");
		_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		_frame.setBounds(0, 0, 368, 217);
		_frame.setResizable(false);
		_frame.setVisible(true);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		_frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSocNum = new JLabel("CPR-Nummer");
		lblSocNum.setBounds(12, 12, 97, 15);
		contentPane.add(lblSocNum);
		
		txtSocNum = new JTextField();
		txtSocNum.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(txtSocNum.getText().length() > 0)
				{				
					GlobalUI.checkIfInt(txtSocNum);				
				}
			}
		});
		txtSocNum.setBounds(127, 10, 227, 19);
		contentPane.add(txtSocNum);
		txtSocNum.setColumns(10);
		
		JLabel lblFullName = new JLabel("Navn");
		lblFullName.setBounds(12, 37, 70, 15);
		contentPane.add(lblFullName);
		
		txtFullName = new JTextField();
		txtFullName.setText("");
		txtFullName.setBounds(127, 35, 227, 19);
		contentPane.add(txtFullName);
		txtFullName.setColumns(10);
		
		lblAddress = new JLabel("Addresse");
		lblAddress.setBounds(12, 64, 70, 15);
		contentPane.add(lblAddress);
		
		txtAddress = new JTextField();
		txtAddress.setBounds(127, 62, 227, 19);
		contentPane.add(txtAddress);
		txtAddress.setColumns(10);
		
		lblZipCode = new JLabel("Post.Nr.");
		lblZipCode.setBounds(12, 91, 70, 15);
		contentPane.add(lblZipCode);
		
		txtZipCode = new JTextField();
		txtZipCode.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(txtZipCode.getText().length() > 0)
				{				
					GlobalUI.checkIfInt(txtZipCode);				
				}
			}
		});
		txtZipCode.setBounds(127, 89, 40, 19);
		contentPane.add(txtZipCode);
		txtZipCode.setColumns(10);
		
		lblCity = new JLabel("By");
		lblCity.setBounds(185, 91, 38, 15);
		contentPane.add(lblCity);
		
		txtCity = new JTextField();
		txtCity.setBounds(210, 89, 145, 19);
		contentPane.add(txtCity);
		txtCity.setColumns(10);
		
		JButton btnUpdate = new JButton("Opdater");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateSalesAssist();
			}
		});
		btnUpdate.setBounds(108, 143, 117, 25);
		contentPane.add(btnUpdate);
		
		JButton btnCancel = new JButton("Annuller");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_instance = null;
				_frame.dispose();
			}
		});
		btnCancel.setBounds(237, 143, 117, 25);
		contentPane.add(btnCancel);
		
		_frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				_instance = null;
				_frame.dispose();
			}
		});
	}
	
	private void updateSalesAssist() {
		try {
			boolean succeeded = false;
			
			long socNum = Long.parseLong(txtSocNum.getText().trim());
			String fullName = txtFullName.getText();
			String address = txtAddress.getText();
			int zipCode = Integer.parseInt(txtZipCode.getText().trim());
			String city = txtCity.getText();
			
			succeeded = _perController.updatePerson(socNum, fullName, address, city, zipCode);
			
			if(succeeded) {
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
