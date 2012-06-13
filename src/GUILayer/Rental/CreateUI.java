package GUILayer.Rental;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class CreateUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtCustomerId;
	private JTextField txtBarcode;
	private JTextField txtDays;

	/**
	 * Create the frame.
	 */
	public CreateUI() {
		setTitle("Ny Udlejning");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 450, 183);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIndtastKundenummer = new JLabel("Kundenummer");
		lblIndtastKundenummer.setBounds(10, 11, 116, 14);
		contentPane.add(lblIndtastKundenummer);
		
		txtCustomerId = new JTextField();
		txtCustomerId.setBounds(120, 8, 314, 20);
		contentPane.add(txtCustomerId);
		txtCustomerId.setColumns(10);
		
		JLabel lblIndtastProduktnummer = new JLabel("Stregkode");
		lblIndtastProduktnummer.setBounds(10, 36, 127, 14);
		contentPane.add(lblIndtastProduktnummer);
		
		txtBarcode = new JTextField();
		txtBarcode.setColumns(10);
		txtBarcode.setBounds(120, 33, 314, 20);
		contentPane.add(txtBarcode);
		
		JLabel lblLnets = new JLabel("L\u00E5nvarighed");
		lblLnets.setBounds(10, 61, 127, 14);
		contentPane.add(lblLnets);
		
		txtDays = new JTextField();
		txtDays.setColumns(10);
		txtDays.setBounds(120, 58, 86, 20);
		contentPane.add(txtDays);
		
		JButton btnCreate = new JButton("Opret");
		btnCreate.setBounds(242, 117, 91, 23);
		contentPane.add(btnCreate);
		
		JButton btnAnnuller = new JButton("Annuller");
		btnAnnuller.setBounds(343, 117, 91, 23);
		contentPane.add(btnAnnuller);
	}
}