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

public class ReturnUI extends JFrame {

	private static final long serialVersionUID = 1508718069134916292L;
	protected static final Component frame = null;
	private JPanel contentPane;
	private JTextField txtLeaseId;

	/**
	 * Create the frame.
	 */
	public ReturnUI() {
		setTitle("Retunering af udlejning ");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 450, 126);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		GUILayer.GlobalUI.setWindowStatus(true);
		
		JLabel lblLeaseId = new JLabel("Udlejningsnummer");
		lblLeaseId.setBounds(10, 11, 132, 17);
		contentPane.add(lblLeaseId);
		
		txtLeaseId = new JTextField();
		txtLeaseId.setBounds(154, 8, 280, 20);
		contentPane.add(txtLeaseId);
		txtLeaseId.setColumns(10);
		
		JButton btnCreate = new JButton("Udf\u00F8r");
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
				GUILayer.GlobalUI.setWindowStatus(false);
				setVisible(false);
			}
		});
		btnCancel.setBounds(317, 60, 117, 23);
		contentPane.add(btnCancel);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				GUILayer.GlobalUI.setWindowStatus(false);
			}
		});
	}
	
	private void returnRental() {
		boolean succeeded = false;
		
		try {	
			if(succeeded) {
				JOptionPane.showMessageDialog(frame, GlobalUI.messageHandling(11), "INFORMATION!", JOptionPane.INFORMATION_MESSAGE);
				setVisible(false);
				GUILayer.GlobalUI.setWindowStatus(false);
			}
			else {
				JOptionPane.showMessageDialog(frame, GlobalUI.messageHandling(12), "FEJL!", JOptionPane.WARNING_MESSAGE);
			}
			
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(frame, GlobalUI.messageHandling(99), "FEJL!", JOptionPane.WARNING_MESSAGE);
		}
	}
}