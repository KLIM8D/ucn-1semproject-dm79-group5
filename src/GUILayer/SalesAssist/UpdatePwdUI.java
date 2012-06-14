package GUILayer.SalesAssist;

import java.awt.Component;
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

public class UpdatePwdUI extends JFrame {

	private SalesAssistantCtrl _saController;
	private static final long serialVersionUID = -2198226493427431793L;
	protected static final Component frame = null;
	private JPanel contentPane;
	private JTextField txtSalesAssistId;
	private JPasswordField txtPassword;

	public UpdatePwdUI() {
		_saController = new SalesAssistantCtrl();
		
		setTitle("Skift adgangskode");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 450, 167);
		setLocationRelativeTo(null);
		setResizable(false);
		
		GUILayer.GlobalUI.setWindowStatus(true);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
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
				GUILayer.GlobalUI.setWindowStatus(false);
				setVisible(false);
			}
		});
		btnCancel.setBounds(319, 91, 117, 25);
		contentPane.add(btnCancel);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				GUILayer.GlobalUI.setWindowStatus(false);
			}
		});
	}
	
	private void changePassword() {
		if(txtSalesAssistId.getText().length() == 0) {
			JOptionPane.showMessageDialog(frame, GlobalUI.messageHandling(20), "FEJL!", JOptionPane.WARNING_MESSAGE);
		}
		else {
			if(txtPassword.getPassword().toString().length() == 0) {
				JOptionPane.showMessageDialog(frame, GlobalUI.messageHandling(21), "FEJL!", JOptionPane.WARNING_MESSAGE);
			}
			else {
				try {
					int salesAsstId = 0;
					SalesAssistant sa = null;
					
					salesAsstId = Integer.parseInt(txtSalesAssistId.getText());
					sa = _saController.getSalesAssistant(salesAsstId);
					if(sa != null) {
						_saController.changePassword(salesAsstId, txtPassword.getPassword().toString());
						setVisible(false);
						GUILayer.GlobalUI.setWindowStatus(false);
					}
					else{
						JOptionPane.showMessageDialog(frame, GlobalUI.messageHandling(18), "FEJL!", JOptionPane.WARNING_MESSAGE);
					}
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(frame, GlobalUI.messageHandling(99), "FEJL!", JOptionPane.WARNING_MESSAGE);
				}
			}
		}
	}
}