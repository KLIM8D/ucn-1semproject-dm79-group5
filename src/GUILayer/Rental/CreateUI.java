package GUILayer.Rental;

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

public class CreateUI extends JFrame {

	private static final long serialVersionUID = -4856264644734812620L;
	protected static final Component frame = null;
	private JPanel contentPane;
	private JTextField txtCustomerId;
	private JTextField txtBarcode;
	private JTextField txtDays;

	/**
	 * Create the frame.
	 */
	public CreateUI() {
		setTitle("Ny Udlejning");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 450, 183);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		GUILayer.GlobalUI.setWindowStatus(true);
		
		JLabel lblIndtastKundenummer = new JLabel("Kundenummer");
		lblIndtastKundenummer.setBounds(10, 11, 116, 14);
		contentPane.add(lblIndtastKundenummer);
		
		txtCustomerId = new JTextField();
		txtCustomerId.setBounds(120, 8, 314, 20);
		contentPane.add(txtCustomerId);
		txtCustomerId.setColumns(10);
		
		JLabel lblIndtastProduktnummer = new JLabel("Stregkode");
		lblIndtastProduktnummer.setBounds(10, 36, 127, 17);
		contentPane.add(lblIndtastProduktnummer);
		
		txtBarcode = new JTextField();
		txtBarcode.setColumns(10);
		txtBarcode.setBounds(120, 33, 314, 20);
		contentPane.add(txtBarcode);
		
		JLabel lblLnets = new JLabel("L\u00E5nvarighed");
		lblLnets.setBounds(10, 61, 127, 17);
		contentPane.add(lblLnets);
		
		txtDays = new JTextField();
		txtDays.setColumns(10);
		txtDays.setBounds(120, 58, 86, 20);
		contentPane.add(txtDays);
		
		JButton btnCreate = new JButton("Opret");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createRental();
			}
		});
		btnCreate.setBounds(188, 117, 117, 23);
		contentPane.add(btnCreate);
		
		JButton btnCancel = new JButton("Annuller");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUILayer.GlobalUI.setWindowStatus(false);
				setVisible(false);
			}
		});
		btnCancel.setBounds(317, 117, 117, 23);
		contentPane.add(btnCancel);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				GUILayer.GlobalUI.setWindowStatus(false);
			}
		});
	}
	
	private void createRental() {
		boolean succeeded = false;
		
		try {	
			if(succeeded) {
				JOptionPane.showMessageDialog(frame, GlobalUI.messageHandling(05), "INFORMATION!", JOptionPane.INFORMATION_MESSAGE);
				setVisible(false);
				GUILayer.GlobalUI.setWindowStatus(false);
			}
			else {
				JOptionPane.showMessageDialog(frame, GlobalUI.messageHandling(10), "FEJL!", JOptionPane.WARNING_MESSAGE);
			}
			
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(frame, GlobalUI.messageHandling(99), "FEJL!", JOptionPane.WARNING_MESSAGE);
		}
	}
}