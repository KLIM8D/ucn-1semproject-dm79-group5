package GUILayer.Rental.Item;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class DeleteUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtBarcode;

	/**
	 * Create the frame.
	 */
	public DeleteUI() {
		setTitle("Slet Udlejningsprodukt");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 450, 132);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIndtastKundenummer = new JLabel("Barcode");
		lblIndtastKundenummer.setBounds(10, 11, 116, 14);
		contentPane.add(lblIndtastKundenummer);
		
		txtBarcode = new JTextField();
		txtBarcode.setBounds(120, 8, 314, 20);
		contentPane.add(txtBarcode);
		txtBarcode.setColumns(10);
		
		JButton btnCreate = new JButton("Udf\u00F8r");
		btnCreate.setBounds(242, 66, 91, 23);
		contentPane.add(btnCreate);
		
		JButton btnAnnuller = new JButton("Annuller");
		btnAnnuller.setBounds(343, 66, 91, 23);
		contentPane.add(btnAnnuller);
	}
}