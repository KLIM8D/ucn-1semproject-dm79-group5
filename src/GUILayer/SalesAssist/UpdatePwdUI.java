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
import javax.swing.JPasswordField;
import javax.swing.JButton;
import ControlLayer.SalesAssistantCtrl;
import ModelLayer.SalesAssistant;
import GUILayer.GlobalUI;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class UpdatePwdUI {

	private SalesAssistantCtrl _saController;
	private static JFrame _frame;
	private static UpdatePwdUI _instance;
	private JPanel contentPane;
	private JTextField txtSalesAssistId;
	private JPasswordField txtPassword;
	
	public static JFrame createWindow()
	{
		if(_instance == null)
			_instance = new UpdatePwdUI();
		
		return _frame;
	}

	private UpdatePwdUI() {
		_saController = new SalesAssistantCtrl();
		
		_frame = new JFrame();
		_frame.setTitle("Skift adgangskode");
		_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		_frame.setBounds(0, 0, 450, 167);
		_frame.setLocationRelativeTo(null);
		_frame.setResizable(false);
		_frame.setVisible(true);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		_frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSalesAssistId = new JLabel("Ekspedient Id");
		lblSalesAssistId.setBounds(12, 12, 121, 15);
		contentPane.add(lblSalesAssistId);
		
		txtSalesAssistId = new JTextField();
		txtSalesAssistId.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(txtSalesAssistId.getText().length() > 0)
				{				
					GlobalUI.checkIfInt(txtSalesAssistId);				
				}
			}
		});
		txtSalesAssistId.setColumns(10);
		txtSalesAssistId.setBounds(137, 10, 299, 19);
		contentPane.add(txtSalesAssistId);
		
		JLabel lblPassword = new JLabel("Ny adgangskode");
		lblPassword.setBounds(12, 39, 132, 15);
		contentPane.add(lblPassword);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(137, 37, 299, 19);
		contentPane.add(txtPassword);
		
		JButton btnChange = new JButton("Skift");
		btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePassword();
			}
		});
		btnChange.setBounds(190, 91, 117, 25);
		contentPane.add(btnChange);
		
		JButton btnCancel = new JButton("Annuller");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_instance = null;
				_frame.dispose();
			}
		});
		btnCancel.setBounds(319, 91, 117, 25);
		contentPane.add(btnCancel);
		
		_frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				_instance = null;
				_frame.dispose();
			}
		});
	}
	
	private void changePassword() {
		String enteredPwd = "";
		for(char c : txtPassword.getPassword())
			enteredPwd += c;
		
		if(txtSalesAssistId.getText().length() == 0) {
			JOptionPane.showMessageDialog(null, GlobalUI.messageHandling(20), "FEJL!", JOptionPane.WARNING_MESSAGE);
		}
		else {
			if(enteredPwd.length() < 6) {
				JOptionPane.showMessageDialog(null, GlobalUI.messageHandling(21), "FEJL!", JOptionPane.WARNING_MESSAGE);
			}
			else {
				try {
					
					int salesAsstId = Integer.parseInt(txtSalesAssistId.getText());
					SalesAssistant sa = _saController.getSalesAssistant(salesAsstId);
					if(sa != null) {
						_saController.changePassword(salesAsstId, enteredPwd);
						JOptionPane.showMessageDialog(null, GlobalUI.messageHandling(23), "INFORMATION!", JOptionPane.INFORMATION_MESSAGE);
						_instance = null;
						_frame.dispose();
					}
					else{
						JOptionPane.showMessageDialog(null, GlobalUI.messageHandling(18), "FEJL!", JOptionPane.WARNING_MESSAGE);
					}
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(null, GlobalUI.messageHandling(99), "FEJL!", JOptionPane.WARNING_MESSAGE);
				}
			}
		}
	}
}
