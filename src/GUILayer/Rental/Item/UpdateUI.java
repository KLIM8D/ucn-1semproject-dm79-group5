package GUILayer.Rental.Item;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class UpdateUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtBarcode;
	private JTextField txtproductId;
	private JTextField txtRentPrice;
	private JTextField txtMaxAvail;

	/**
	 * Create the frame.
	 */
	public UpdateUI() {
		setTitle("Opdater UdlejningsProdukt");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 450, 212);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIndtastKundenummer = new JLabel("Stregkode");
		lblIndtastKundenummer.setBounds(10, 11, 116, 14);
		contentPane.add(lblIndtastKundenummer);
		
		txtBarcode = new JTextField();
		txtBarcode.setBounds(120, 8, 314, 20);
		contentPane.add(txtBarcode);
		txtBarcode.setColumns(10);
		
		JLabel lblIndtastProduktnummer = new JLabel("Produktnavn");
		lblIndtastProduktnummer.setBounds(10, 36, 127, 14);
		contentPane.add(lblIndtastProduktnummer);
		
		txtproductId = new JTextField();
		txtproductId.setColumns(10);
		txtproductId.setBounds(120, 33, 314, 20);
		contentPane.add(txtproductId);
		
		JLabel lblLnets = new JLabel("Udlejningspris");
		lblLnets.setBounds(10, 61, 127, 14);
		contentPane.add(lblLnets);
		
		txtRentPrice = new JTextField();
		txtRentPrice.setColumns(10);
		txtRentPrice.setBounds(120, 58, 86, 20);
		contentPane.add(txtRentPrice);
		
		JButton btnCreate = new JButton("Udf\u00F8r");
		btnCreate.setBounds(244, 146, 91, 23);
		contentPane.add(btnCreate);
		
		JButton btnAnnuller = new JButton("Annuller");
		btnAnnuller.setBounds(343, 146, 91, 23);
		contentPane.add(btnAnnuller);
		
		JLabel lblMaxBeholdning = new JLabel("Max beholdning");
		lblMaxBeholdning.setBounds(10, 86, 127, 14);
		contentPane.add(lblMaxBeholdning);
		
		txtMaxAvail = new JTextField();
		txtMaxAvail.setColumns(10);
		txtMaxAvail.setBounds(120, 83, 86, 20);
		contentPane.add(txtMaxAvail);
	}
}