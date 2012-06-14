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
import javax.swing.JButton;

public class DeleteUI extends JFrame {

	private static final long serialVersionUID = 8303757512052314425L;
	private JPanel contentPane;
	private JTextField textField;

	public DeleteUI() {
		setTitle("Slet ekspedient");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 450, 138);
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
		
		textField = new JTextField();
		textField.setBounds(127, 10, 309, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnDelete = new JButton("Slet");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteSalesAssist();
			}
		});
		btnDelete.setBounds(190, 64, 117, 25);
		contentPane.add(btnDelete);
		
		JButton btnCancel = new JButton("Annuller");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUILayer.GlobalUI.setWindowStatus(false);
				setVisible(false);
			}
		});
		btnCancel.setBounds(319, 64, 117, 25);
		contentPane.add(btnCancel);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				GUILayer.GlobalUI.setWindowStatus(false);
			}
		});
	}
	
	private void deleteSalesAssist() {
		
	}

}
