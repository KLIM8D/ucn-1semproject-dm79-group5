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
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class UpdatePwdUI extends JFrame {

	private static final long serialVersionUID = -2198226493427431793L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	public UpdatePwdUI() {
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
		
		JLabel label = new JLabel("Ekspedient Id");
		label.setBounds(12, 12, 121, 15);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(137, 10, 299, 19);
		contentPane.add(textField);
		
		JLabel lblNyAdgangskode = new JLabel("Ny adgangskode");
		lblNyAdgangskode.setBounds(12, 39, 132, 15);
		contentPane.add(lblNyAdgangskode);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(137, 37, 299, 19);
		contentPane.add(passwordField);
		
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
		
	}

}
