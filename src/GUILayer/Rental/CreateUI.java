package GUILayer.Rental;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import ControlLayer.LeaseCtrl;
import GUILayer.GlobalUI;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CreateUI {

	private static JFrame _frame;
	private static CreateUI _instance;
	private JPanel contentPane;
	private JTextField txtCustomerId;
	private JTextField txtBarcode;
	private JTextField txtDays;
	private LeaseCtrl _leaseCtrl;
	public static JFrame createWindow()
	{
		if(_instance == null)
			_instance = new CreateUI();
		
		return _frame;
	}
	
	private CreateUI() 
	{
		_leaseCtrl = new LeaseCtrl();
		_frame = new JFrame();
		_frame.setTitle("Ny udlejning");
		_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		_frame.setBounds(0, 0, 467, 183);
		_frame.setResizable(false);
		_frame.setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		_frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIndtastKundenummer = new JLabel("Kundenummer");
		lblIndtastKundenummer.setBounds(10, 11, 116, 14);
		contentPane.add(lblIndtastKundenummer);
		
		txtCustomerId = new JTextField();
		txtCustomerId.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(txtCustomerId.getText().length() > 0)
				{
					GlobalUI.checkIfLong(txtCustomerId);
				}
			}
		});
		txtCustomerId.setBounds(139, 8, 314, 20);
		contentPane.add(txtCustomerId);
		txtCustomerId.setColumns(10);
		
		JLabel lblIndtastProduktnummer = new JLabel("Produkt nummer");
		lblIndtastProduktnummer.setBounds(10, 36, 127, 17);
		contentPane.add(lblIndtastProduktnummer);
		
		txtBarcode = new JTextField();
		txtBarcode.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(txtBarcode.getText().length() > 0)
				{
					GlobalUI.checkIfLong(txtCustomerId);
				}
			}
		});
		txtBarcode.setColumns(10);
		txtBarcode.setBounds(139, 33, 314, 20);
		contentPane.add(txtBarcode);
		
		JLabel lblDays = new JLabel("Varighed");
		lblDays.setBounds(10, 61, 127, 17);
		contentPane.add(lblDays);
		
		txtDays = new JTextField();
		txtDays.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(txtDays.getText().length() > 0)
				{
					GlobalUI.checkIfInt(txtDays);
				}
			}
		});
		txtDays.setColumns(10);
		txtDays.setBounds(139, 58, 86, 20);
		contentPane.add(txtDays);
		
		JButton btnCreate = new JButton("Opret");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isFormValid())
					createRental();
				else
					JOptionPane.showMessageDialog(null, "Alle felter skal udfyldes", "INFORMATION!", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnCreate.setBounds(207, 117, 117, 23);
		contentPane.add(btnCreate);
		
		JButton btnCancel = new JButton("Annuller");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_instance = null;
				_frame.dispose();
			}
		});
		btnCancel.setBounds(336, 117, 117, 23);
		contentPane.add(btnCancel);
		
		_frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				_instance = null;
				_frame.dispose();
			}
		});
	}
	
	private void createRental() 
	{
		boolean succeeded = false;
		
		try 
		{	long customerId = Long.parseLong(txtCustomerId.getText());
			long itemNumber = Long.parseLong(txtBarcode.getText());
			int days = Integer.parseInt(txtDays.getText());
			succeeded = _leaseCtrl.createLease(customerId, itemNumber, days);
			if(succeeded) 
			{
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
	
	private boolean isFormValid()
	{
		if(txtCustomerId.getText().length() > 0 && txtBarcode.getText().length() > 0 && txtDays.getText().length() > 0)
			return true;
		
		return false;
	}
}