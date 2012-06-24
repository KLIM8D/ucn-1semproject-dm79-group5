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

public class ReturnUI {

	private static JFrame _frame;
	private static ReturnUI _instance;
	private JPanel contentPane;
	private JTextField txtLeaseId;
	private LeaseCtrl _leaseCtrl;
	public static JFrame createWindow()
	{
		if(_instance == null)
			_instance = new ReturnUI();
		
		return _frame;
	}
	
	private ReturnUI() {
		_leaseCtrl = new LeaseCtrl();
		_frame = new JFrame();
		_frame.setTitle("Retunering af udlejning ");
		_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		_frame.setBounds(0, 0, 450, 126);
		_frame.setResizable(false);
		_frame.setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		_frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLeaseId = new JLabel("Udlejningsnummer");
		lblLeaseId.setBounds(10, 11, 132, 17);
		contentPane.add(lblLeaseId);
		
		txtLeaseId = new JTextField();
		txtLeaseId.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(txtLeaseId.getText().length() > 0)
				{
					GlobalUI.checkIfInt(txtLeaseId);
				}
			}
		});
		txtLeaseId.setBounds(154, 8, 280, 20);
		contentPane.add(txtLeaseId);
		txtLeaseId.setColumns(10);
		
		JButton btnCreate = new JButton("Bekræft");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				returnRental();
			}
		});
		btnCreate.setBounds(188, 60, 117, 23);
		contentPane.add(btnCreate);
		
		JButton btnCancel = new JButton("Annuller");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_instance = null;
				_frame.dispose();
			}
		});
		btnCancel.setBounds(317, 60, 117, 23);
		contentPane.add(btnCancel);
		
		_frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				_instance = null;
				_frame.dispose();
			}
		});
	}
	
	private void returnRental() {
		boolean succeeded = false;
		
		try 
		{	
			int leaseId = Integer.parseInt(txtLeaseId.getText());
			succeeded = _leaseCtrl.removeLease(leaseId);
			if(succeeded) 
			{
				JOptionPane.showMessageDialog(null, GlobalUI.messageHandling(11), "INFORMATION!", JOptionPane.INFORMATION_MESSAGE);
				_instance = null;
				_frame.dispose();
			}
			else {
				JOptionPane.showMessageDialog(null, GlobalUI.messageHandling(12), "FEJL!", JOptionPane.WARNING_MESSAGE);
			}
			
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, GlobalUI.messageHandling(99), "FEJL!", JOptionPane.WARNING_MESSAGE);
		}
	}
}