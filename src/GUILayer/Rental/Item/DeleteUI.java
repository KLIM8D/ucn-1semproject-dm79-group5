package GUILayer.Rental.Item;

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

public class DeleteUI 
{

	private static JFrame _frame;
	private static DeleteUI _instance;
	private JPanel contentPane;
	private JTextField txtBarcode;
	private LeaseCtrl _leaseCtrl;
	public static JFrame createWindow()
	{
		if(_instance == null)
			_instance = new DeleteUI();
		
		return _frame;
	}
	
	private DeleteUI() 
	{
		_leaseCtrl = new LeaseCtrl();
		_frame = new JFrame();
		_frame.setResizable(false);
		_frame.setTitle("Slet udlejningsprodukt");
		_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		_frame.setBounds(0, 0, 450, 132);
		_frame.setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		_frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBarCode = new JLabel("Produkt nummer");
		lblBarCode.setBounds(10, 11, 131, 17);
		contentPane.add(lblBarCode);
		
		txtBarcode = new JTextField();
		txtBarcode.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(txtBarcode.getText().length() > 0)
				{
					GlobalUI.checkIfLong(txtBarcode);
				}
			}
		});
		txtBarcode.setBounds(147, 8, 287, 20);
		contentPane.add(txtBarcode);
		txtBarcode.setColumns(10);
		
		JButton btnCreate = new JButton("Slet");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtBarcode.getText().length() > 0)
					deleteItem();
			}
		});
		btnCreate.setBounds(193, 66, 117, 23);
		contentPane.add(btnCreate);
		
		JButton btnAnnuller = new JButton("Annuller");
		btnAnnuller.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_instance = null;
				_frame.dispose();
			}
		});
		btnAnnuller.setBounds(322, 66, 117, 23);
		contentPane.add(btnAnnuller);
		
		_frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				_instance = null;
				_frame.dispose();
			}
		});
	}
	
	private void deleteItem() 
	{
		boolean succeeded = false;
		
		try 
		{	long itemNumber = Long.parseLong(txtBarcode.getText());
			succeeded = _leaseCtrl.deleteLeaseItem(itemNumber);
			if(succeeded) 
			{
				JOptionPane.showMessageDialog(null, GlobalUI.messageHandling(15), "INFORMATION!", JOptionPane.INFORMATION_MESSAGE);
				_instance = null;
				_frame.dispose();
			}
			else 
			{
				JOptionPane.showMessageDialog(null, GlobalUI.messageHandling(16), "FEJL!", JOptionPane.WARNING_MESSAGE);
			}
			
		}
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, GlobalUI.messageHandling(99), "FEJL!", JOptionPane.WARNING_MESSAGE);
		}
	}
}