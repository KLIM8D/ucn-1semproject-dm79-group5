package GUILayer.SalesAssist;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class CreateUI extends JFrame {

	private static final long serialVersionUID = -2548260112937399166L;
	private JPanel contentPane;
	private JTextField txtSocNum;
	private JTextField txtFullName;
	private JLabel lblAddress;
	private JTextField txtAddress;
	private JLabel lblZipCode;
	private JTextField txtZipCode;
	private JLabel lblCity;
	private JTextField txtCity;
	private JLabel lblPhoneNo;
	private JTextField txtPhoneNo;
	private JLabel lblPassword;
	private JPasswordField txtPassword;

	public CreateUI() {
		setTitle("Opret ekspedient");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 368, 286);
		setLocationRelativeTo(null);
		setResizable(false);
		
		GUILayer.GlobalUI.setWindowStatus(true);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSocNum = new JLabel("CPR-Nummer");
		lblSocNum.setBounds(12, 12, 97, 15);
		contentPane.add(lblSocNum);
		
		txtSocNum = new JTextField();
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
		
		lblPhoneNo = new JLabel("Telefon");
		lblPhoneNo.setBounds(12, 118, 70, 15);
		contentPane.add(lblPhoneNo);
		
		txtPhoneNo = new JTextField();
		txtPhoneNo.setBounds(127, 116, 227, 19);
		contentPane.add(txtPhoneNo);
		txtPhoneNo.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 146, 342, 1);
		contentPane.add(separator);
		
		lblPassword = new JLabel("Adgangskode");
		lblPassword.setBounds(12, 159, 97, 15);
		contentPane.add(lblPassword);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(127, 159, 227, 19);
		contentPane.add(txtPassword);
		
		JButton btnCreate = new JButton("Opret");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createSalesAssist();
			}
		});
		btnCreate.setBounds(108, 213, 117, 25);
		contentPane.add(btnCreate);
		
		JButton btnCancel = new JButton("Annuller");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUILayer.GlobalUI.setWindowStatus(false);
				setVisible(false);
			}
		});
		btnCancel.setBounds(237, 213, 117, 25);
		contentPane.add(btnCancel);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				GUILayer.GlobalUI.setWindowStatus(false);
			}
		});
	}
	
	private void createSalesAssist() {
		
	}
}