package GUILayer;

import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import ControlLayer.SalesAssistantCtrl;

public class LoginUI extends JFrame {

	private static final long serialVersionUID = 2890618149912332366L;
	protected static final Component frame = null;
	private JPanel pnlAccessControl;
	private JTextField txtUserID;
	private JTextField txtPassword;
	
	private SalesAssistantCtrl _saController;

	public LoginUI() {
		_saController = new SalesAssistantCtrl();
		
		setTitle("Adgangskontrol");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 450, 140);
		setLocationRelativeTo(null);
		setResizable(false);
		pnlAccessControl = new JPanel();
		pnlAccessControl.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnlAccessControl);
		pnlAccessControl.setLayout(null);
		
		JLabel lblUsername = new JLabel("Bruger ID");
		lblUsername.setBounds(12, 12, 70, 15);
		pnlAccessControl.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Adgangskode");
		lblPassword.setBounds(12, 39, 100, 15);
		pnlAccessControl.add(lblPassword);
		
		txtUserID = new JTextField();
		txtUserID.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(txtUserID.getText().length() > 0)
				{				
					GlobalUI.checkIfInt(txtUserID);				
				}
			}
		});
		txtUserID.setBounds(120, 10, 316, 19);
		pnlAccessControl.add(txtUserID);
		txtUserID.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(120, 37, 316, 19);
		pnlAccessControl.add(txtPassword);
		txtPassword.setColumns(10);
		
		JButton btnCancel = new JButton("Annuller");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(EXIT_ON_CLOSE);
			}
		});
		btnCancel.setBounds(319, 68, 117, 25);
		pnlAccessControl.add(btnCancel);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userLogin();
			}
		});
		btnLogin.setBounds(190, 68, 117, 25);
		pnlAccessControl.add(btnLogin);
	}
	
	private void userLogin() {
		try {			
			if(txtUserID.getText().trim().length() != 0) {
				if(txtPassword.getText().trim().length() != 0) {
					int userID = Integer.parseInt(txtUserID.getText());
					String userPassword = txtPassword.getText();
					
					boolean success = _saController.checkLogin(userID, userPassword);
					if(success || userID == 1) { // userID == 1 is to be removed at final release
						new GUILayer.SystemUI().setVisible(true);
						dispose();
					}
					
					else {
						JOptionPane.showMessageDialog(frame, GlobalUI.messageHandling(03), "FEJL!", JOptionPane.WARNING_MESSAGE);
					}
				}
				
				else {
					JOptionPane.showMessageDialog(frame, GlobalUI.messageHandling(02), "FEJL!", JOptionPane.WARNING_MESSAGE);
				}
			}
			
			else {
				JOptionPane.showMessageDialog(frame, GlobalUI.messageHandling(01), "FEJL!", JOptionPane.WARNING_MESSAGE);
			}
		}
		
		catch (Exception err) {
			JOptionPane.showMessageDialog(frame, GlobalUI.messageHandling(99), "FEJL!", JOptionPane.WARNING_MESSAGE);
		}
	}
}