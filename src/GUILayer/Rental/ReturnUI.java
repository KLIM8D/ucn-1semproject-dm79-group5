package GUILayer.Rental;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ReturnUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtLeaseId;

	/**
	 * Create the frame.
	 */
	public ReturnUI() {
		setTitle("Retunering af udlejning ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 450, 126);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIndtastKundenummer = new JLabel("Udlejningsnummer");
		lblIndtastKundenummer.setBounds(10, 11, 116, 14);
		contentPane.add(lblIndtastKundenummer);
		
		txtLeaseId = new JTextField();
		txtLeaseId.setBounds(120, 8, 314, 20);
		contentPane.add(txtLeaseId);
		txtLeaseId.setColumns(10);
		
		JButton btnCreate = new JButton("Udf\u00F8r");
		btnCreate.setBounds(242, 60, 91, 23);
		contentPane.add(btnCreate);
		
		JButton btnAnnuller = new JButton("Annuller");
		btnAnnuller.setBounds(343, 60, 91, 23);
		contentPane.add(btnAnnuller);
	}
}