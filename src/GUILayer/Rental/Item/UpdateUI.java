package GUILayer.Rental.Item;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import GUILayer.GlobalUI;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class UpdateUI extends JFrame {

	private static final long serialVersionUID = -969960698608387632L;
	protected static final Component frame = null;
	private JPanel contentPane;
	private JTextField txtBarcode;
	private JTextField txtproductId;
	private JTextField txtRentPrice;
	private JTextField txtMaxAvail;

	/**
	 * Create the frame.
	 */
	public UpdateUI() {
		setResizable(false);
		setTitle("Opdater udlejningsprodukt");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 450, 212);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		GUILayer.GlobalUI.setWindowStatus(true);
		
		JLabel lblBarCode = new JLabel("Stregkode");
		lblBarCode.setBounds(10, 11, 116, 14);
		contentPane.add(lblBarCode);
		
		txtBarcode = new JTextField();
		txtBarcode.setBounds(130, 8, 304, 20);
		contentPane.add(txtBarcode);
		txtBarcode.setColumns(10);
		
		JLabel lblProduct = new JLabel("Produktnavn");
		lblProduct.setBounds(10, 36, 127, 14);
		contentPane.add(lblProduct);
		
		txtproductId = new JTextField();
		txtproductId.setColumns(10);
		txtproductId.setBounds(130, 33, 304, 20);
		contentPane.add(txtproductId);
		
		JLabel lblPrice = new JLabel("Udlejningspris");
		lblPrice.setBounds(10, 61, 127, 14);
		contentPane.add(lblPrice);
		
		txtRentPrice = new JTextField();
		txtRentPrice.setColumns(10);
		txtRentPrice.setBounds(130, 58, 76, 20);
		contentPane.add(txtRentPrice);
		
		JButton btnCreate = new JButton("Udf\u00F8r");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateItem();
			}
		});
		btnCreate.setBounds(188, 146, 117, 23);
		contentPane.add(btnCreate);
		
		JButton btnCancel = new JButton("Annuller");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUILayer.GlobalUI.setWindowStatus(true);
				setVisible(false);
			}
		});
		btnCancel.setBounds(317, 146, 117, 23);
		contentPane.add(btnCancel);
		
		JLabel lblMax = new JLabel("Max beholdning");
		lblMax.setBounds(10, 86, 127, 14);
		contentPane.add(lblMax);
		
		txtMaxAvail = new JTextField();
		txtMaxAvail.setColumns(10);
		txtMaxAvail.setBounds(130, 83, 76, 20);
		contentPane.add(txtMaxAvail);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				GUILayer.GlobalUI.setWindowStatus(false);
			}
		});
	}
	
	private void updateItem() {
		boolean succeeded = false;
		
		try {	
			if(succeeded) {
				JOptionPane.showMessageDialog(frame, GlobalUI.messageHandling(13), "INFORMATION!", JOptionPane.INFORMATION_MESSAGE);
				setVisible(false);
				GUILayer.GlobalUI.setWindowStatus(false);
			}
			else {
				JOptionPane.showMessageDialog(frame, GlobalUI.messageHandling(14), "FEJL!", JOptionPane.WARNING_MESSAGE);
			}
			
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(frame, GlobalUI.messageHandling(99), "FEJL!", JOptionPane.WARNING_MESSAGE);
		}
	}
}