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

public class DeleteUI extends JFrame {

	private static final long serialVersionUID = 7832893035517234399L;
	protected static final Component frame = null;
	private JPanel contentPane;
	private JTextField txtBarcode;

	/**
	 * Create the frame.
	 */
	public DeleteUI() {
		setTitle("Slet Udlejningsprodukt");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 450, 132);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		GUILayer.GlobalUI.setWindowStatus(true);
		
		JLabel lblBarCode = new JLabel("Stregkode");
		lblBarCode.setBounds(10, 11, 116, 17);
		contentPane.add(lblBarCode);
		
		txtBarcode = new JTextField();
		txtBarcode.setBounds(120, 8, 314, 20);
		contentPane.add(txtBarcode);
		txtBarcode.setColumns(10);
		
		JButton btnCreate = new JButton("Udf\u00F8r");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteItem();
			}
		});
		btnCreate.setBounds(193, 66, 117, 23);
		contentPane.add(btnCreate);
		
		JButton btnAnnuller = new JButton("Annuller");
		btnAnnuller.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUILayer.GlobalUI.setWindowStatus(false);
				setVisible(false);
			}
		});
		btnAnnuller.setBounds(322, 66, 117, 23);
		contentPane.add(btnAnnuller);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				GUILayer.GlobalUI.setWindowStatus(false);
			}
		});
	}
	
	private void deleteItem() {
		boolean succeeded = false;
		
		try {	
			if(succeeded) {
				JOptionPane.showMessageDialog(frame, GlobalUI.messageHandling(15), "INFORMATION!", JOptionPane.INFORMATION_MESSAGE);
				setVisible(false);
				GUILayer.GlobalUI.setWindowStatus(false);
			}
			else {
				JOptionPane.showMessageDialog(frame, GlobalUI.messageHandling(16), "FEJL!", JOptionPane.WARNING_MESSAGE);
			}
			
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(frame, GlobalUI.messageHandling(99), "FEJL!", JOptionPane.WARNING_MESSAGE);
		}
	}
}