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

public class UpdateUI {

	private static JFrame _frame;
	private static UpdateUI _instance;
	private JPanel contentPane;
	private JTextField txtBarcode;
	private JTextField txtproductId;
	private JTextField txtRentPrice;
	private JTextField txtMaxAvail;
	
	public static JFrame createWindow()
	{
		if(_instance == null)
			_instance = new UpdateUI();
		
		return _frame;
	}

	private UpdateUI() {
		_frame = new JFrame();
		_frame.setResizable(false);
		_frame.setTitle("Opdater udlejningsprodukt");
		_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		_frame.setBounds(0, 0, 450, 212);
		_frame.setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		_frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
				_instance = null;
				_frame.dispose();
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
		
		_frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				_instance = null;
				_frame.dispose();
			}
		});
	}
	
	private void updateItem() {
		boolean succeeded = false;
		
		try {	
			if(succeeded) {
				JOptionPane.showMessageDialog(null, GlobalUI.messageHandling(13), "INFORMATION!", JOptionPane.INFORMATION_MESSAGE);
				_instance = null;
				_frame.dispose();
			}
			else {
				JOptionPane.showMessageDialog(null, GlobalUI.messageHandling(14), "FEJL!", JOptionPane.WARNING_MESSAGE);
			}
			
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, GlobalUI.messageHandling(99), "FEJL!", JOptionPane.WARNING_MESSAGE);
		}
	}
}