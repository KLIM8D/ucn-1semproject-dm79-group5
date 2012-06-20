package GUILayer.Rental.Item;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import GUILayer.GlobalUI;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CreateUI {

	private static JFrame _frame;
	private static CreateUI _instance;
	private JPanel contentPane;
	private JTextField txtBarcode;
	private JTextField txtproductId;
	private JTextField txtRentPrice;
	private JTextField txtMaxAvail;

	public static JFrame createWindow()
	{
		if(_instance == null)
			_instance = new CreateUI();
		
		return _frame;
	}
	
	private CreateUI() {
		_frame = new JFrame();
		_frame.setTitle("Nyt udlejningsprodukt");
		_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		_frame.setBounds(0, 0, 450, 212);
		_frame.setResizable(false);
		_frame.setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		_frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIndtastKundenummer = new JLabel("Stregkode");
		lblIndtastKundenummer.setBounds(10, 11, 116, 14);
		contentPane.add(lblIndtastKundenummer);
		
		txtBarcode = new JTextField();
		txtBarcode.setBounds(130, 8, 304, 20);
		contentPane.add(txtBarcode);
		txtBarcode.setColumns(10);
		
		JLabel lblIndtastProduktnummer = new JLabel("Produktnummer");
		lblIndtastProduktnummer.setBounds(10, 36, 127, 14);
		contentPane.add(lblIndtastProduktnummer);
		
		txtproductId = new JTextField();
		txtproductId.setColumns(10);
		txtproductId.setBounds(130, 33, 304, 20);
		contentPane.add(txtproductId);
		
		JLabel lblLnets = new JLabel("Udlejningspris");
		lblLnets.setBounds(10, 61, 127, 14);
		contentPane.add(lblLnets);
		
		txtRentPrice = new JTextField();
		txtRentPrice.setColumns(10);
		txtRentPrice.setBounds(130, 58, 76, 20);
		contentPane.add(txtRentPrice);
		
		JButton btnCreate = new JButton("Opret");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createItem();
			}
		});
		btnCreate.setBounds(188, 146, 117, 23);
		contentPane.add(btnCreate);
		
		JButton btnCancel = new JButton("Annuller");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_instance = null;
				_frame.dispose();
			}
		});
		btnCancel.setBounds(317, 146, 117, 23);
		contentPane.add(btnCancel);
		
		JLabel lblMaxBeholdning = new JLabel("Max beholdning");
		lblMaxBeholdning.setBounds(10, 86, 127, 14);
		contentPane.add(lblMaxBeholdning);
		
		txtMaxAvail = new JTextField();
		txtMaxAvail.setColumns(10);
		txtMaxAvail.setBounds(130, 83, 76, 20);
		contentPane.add(txtMaxAvail);
		
		_frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				_instance = null;
				_frame.dispose();
			}
		});
	}
	
	private void createItem() {
		boolean succeeded = false;
		
		try {	
			if(succeeded) {
				JOptionPane.showMessageDialog(null, GlobalUI.messageHandling(05), "INFORMATION!", JOptionPane.INFORMATION_MESSAGE);
				_instance = null;
				_frame.dispose();
			}
			else {
				JOptionPane.showMessageDialog(null, GlobalUI.messageHandling(10), "FEJL!", JOptionPane.WARNING_MESSAGE);
			}
			
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, GlobalUI.messageHandling(99), "FEJL!", JOptionPane.WARNING_MESSAGE);
		}
	}
}